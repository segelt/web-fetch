package hibernateFetch;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import dao.HistoricDataDao;
import models.HistoricData;

public class DBHistoricDataService {

	//This method is not used since repairing funds are no longer supported
	public static List<LocalDate> findAllMissingFundsFromDB(String Fund){
		HistoricDataDao fd = new HistoricDataDao();
		List<HistoricData> fetchedData = fd.fetchAllData(Fund);
		
		//Sort the collection by their dates
		fetchedData.sort(new Comparator<HistoricData>() {
			public int compare(HistoricData arg0, HistoricData arg1) {
				if( arg0.getTarih().isAfter(arg1.getTarih())){
					return -1;
				}else return 1;
			}
			
		});
		
		List<LocalDate> existingDates = fetchedData.stream()
				.map(item -> item.getTarih())
				.collect(Collectors.toList());
		
		List<LocalDate> missingDates = new ArrayList<LocalDate>();
		
		LocalDate lastDate = fetchedData.get(0).getTarih();
		LocalDate firstDate = fetchedData.get(fetchedData.size() - 1).getTarih();
		
		for(LocalDate iter = firstDate; !iter.isAfter(lastDate); iter = iter.plusDays(1)){
			DayOfWeek thisDay = iter.getDayOfWeek();
			if(thisDay != DayOfWeek.SATURDAY && thisDay != DayOfWeek.SUNDAY && !existingDates.contains(iter)){
				missingDates.add(iter);
			}
		}
		
		return missingDates;
	}
}
