package webapifetch;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import dao.FonDao;
import models.Fon;

/*
 * Check for missing funds (not data) in DB. Insert the funds if there is any missing
 * If a new fund has been introduced (such as GZH), detect it and add it in DB.
 */
public class FundCollectionUtils {

	public static List<Fon> CheckForMissingFunds(){
		
		List<Fon> UpdatedList = fetchFromSPK.fetchFundsFromSpk()
				.stream()
				.filter(fon -> fon.getFonTipi().contains("F"))
				.collect(Collectors.toList());
		
		List<Fon> CurrentList = new FonDao().fetchAllFunds()
				.stream()
				.filter(fon -> fon.getFonTipi().contains("F"))
				.collect(Collectors.toList());
				
		List<Fon> excluded = UpdatedList.stream()
				.filter(p -> !CurrentList.stream().anyMatch(x -> x.getFonKodu().contains(p.getFonKodu())))
				.collect(Collectors.toList());
		
		return excluded;
	}
	
	public static boolean AddSelectedFunds(String... args){
		List<String> FonKodlari = Arrays.asList(args);
		
		//Fetch fund info from API
		//Check if fund exists in the DB, if not, add it to DB
		List<Fon> funds = fetchFromSPK.fetchFundsFromSpk();
		
		List<Fon> desiredFunds = funds.stream().filter(p -> FonKodlari.contains(p.getFonKodu())).collect(Collectors.toList());

		if(desiredFunds.size() > 0){
			FonDao fd = new FonDao();
			for(int i = 0; i < desiredFunds.size(); i++){
				Fon newFund = desiredFunds.get(i);
				if(!fd.checkIfFundExists(newFund.getFonKodu())){
					boolean insertResult = fd.insertFund(newFund);
					
					if(!insertResult) return insertResult;
				}
			}
		}
		
		return true;
	}
}
