package webapifetch;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;

import dao.HistoricDataDao;
import hibernateFetch.Constants;
import hibernateFetch.DBFonService;
import models.Fon;
import models.HistoricData;
import utils.apiUtils;

public class fetchFromSPK {

	//todo - find a way to fetch fonturu
	//Gets a list of funds from SPK
	public static List<Fon> fetchFundsFromSpk(){
		
		try {
        	String api = "https://ws.spk.gov.tr/PortfolioValues/api/Funds/1";
        	String output = apiUtils.ReadFromUrlAndFetchStringData(api);
        	
			if(output == null || output.trim().isEmpty()){
				return null;
			}
			
			//iterate over funds, add each one by one
			JsonArray data = JsonParser.parseString(output).getAsJsonArray();
	        List<Fon> dataList = new ArrayList<Fon>();
	        
	        for (JsonElement element : data) {
	        	JsonObject obj = element.getAsJsonObject();
	        	
	        	String fonTipi = obj.get("Tipi").toString();
	        	if(fonTipi.contains("F")){
	        		Fon newFund = new Fon();
		        	newFund.setFonAdi(obj.get("Adi").toString().replace("\"", ""));
		        	newFund.setFonKodu(obj.get("Kodu").toString().replace("\"", ""));
		        	newFund.setFonTipi(obj.get("Tipi").toString().replace("\"", ""));
		        	newFund.setCompanyType(1);
		        	//log newFon
		        	dataList.add(newFund);	
	        	}
	        }
	        return dataList;
        } catch (Exception e) {
            System.out.println("Exception in NetClientGet:- " + e);
            return null;
        }
	}

	
	//Generate a url, used to fetch data from ws.spk
	public static String FetchLatestDataFromSPK(String FonKodu){
		HistoricDataDao dataAccessor = new HistoricDataDao();
		LocalDate lastExistingTime = dataAccessor.latestTime(FonKodu);

		LocalDate lastExistingDate = null;
		
		if(lastExistingTime == null) lastExistingDate = Constants.initialDate;
		else {
			lastExistingDate = lastExistingTime.plusDays(1);	
		}
		
		LocalDate today = LocalDate.now();
		
		String UrlGenerator = "https://ws.spk.gov.tr/PortfolioValues/api/PortfoyDegerleri/%s/1/%s/%s";
		DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern("MM-dd-yyyy");		

		return String.format(UrlGenerator, FonKodu, formatter1.format(lastExistingDate), formatter1.format(today));
	}

	public static void AddDataToDB(String jsonData, String FonKodu){
		try {            
        	JsonArray data = JsonParser.parseString(jsonData).getAsJsonArray();
            List<HistoricData> dataList = new ArrayList<HistoricData>();
            HistoricDataDao dataAccessor = new HistoricDataDao();

            Gson gson = new GsonBuilder().registerTypeAdapter(LocalDate.class, new JsonDeserializer<LocalDate>() {
                @Override
                public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                        throws JsonParseException {
                	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
                    return LocalDateTime.parse(json.getAsString(), formatter).toLocalDate();
                }
            }).create();
            
            boolean isThisFundIncomplete = DBFonService.isThisFundInformationIncomplete(FonKodu);
            
            for (JsonElement element : data) {
            	HistoricData parsedObject = gson.fromJson(element, HistoricData.class);
            	
            	//log parsedObject

            	if(isThisFundIncomplete){
            		String FonTuru = ((JsonObject)element).get("FonTuru").getAsString();
            		
            		//If the fund is repaired, then it is no longer incomplete
            		isThisFundIncomplete = !DBFonService.RepairFundInformation(FonKodu, FonTuru);
            	}
            	

            	dataList.add(parsedObject);
            }
            dataAccessor.insertBatchData(dataList);
            
        } catch (Exception e) {
            System.err.println(e);
        }
	}

	public static boolean FillThisFund(String FonKodu){
		//for each fund, first fill the nonexisting parts from spk.
		
		try{
			//fill the nonexisting parts from spk
			String url = fetchFromSPK.FetchLatestDataFromSPK(FonKodu);
			if(url == null || url.length() == 0){
				return false;
			}else {
				String data = apiUtils.ReadFromUrlAndFetchStringData(url);
				if(data != null && data.length() > 0){
					AddDataToDB(data, FonKodu);		
				}
			}
			return true;
		}catch(Exception ex){
			System.err.println("Error for the following fund: " + FonKodu);
			System.err.println(ex);
			return false;
		}
	}
	
	public static void IterateOverExistingFundsAndUpdateThem(){
		List<Fon> funds = DBFonService.currentFunds();
		
		int iter = 0;
		for(; iter < funds.size(); iter++){
			Fon item = funds.get(iter);
			try {
				FillThisFund(item.getFonKodu());
				Thread.sleep(3500);
			} catch (InterruptedException e) {
				System.err.println(iter + "Error for " + item.getFonKodu() + " iterator: " + iter);
				e.printStackTrace();
				break;
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
