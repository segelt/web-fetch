package hibernateFetch;

import java.util.List;

import dao.FonDao;
import models.Fon;

public class DBFonService {

	public static boolean checkIfFundExists(String Fund){
		FonDao fd = new FonDao();
		return fd.checkIfFundExists(Fund);
	}
	
	public static boolean insertFund(Fon Fund){
		FonDao fd = new FonDao();
		return fd.insertFund(Fund);
	}
	
	public static List<Fon> currentFunds(){
		return new FonDao().fetchAllFunds();
	}
	
	public static boolean isThisFundInformationIncomplete(String fonKodu){
		FonDao fd = new FonDao();
		if(!fd.checkIfFundExists(fonKodu)){
			return false;
		}
		
		Fon fundInDb = fd.fetchFundByCompanyName(fonKodu);
		if(fundInDb != null){
			String FonTuru = fundInDb.getFonTuru();
			int CompanyType = fundInDb.getCompanyType();
			
			if(FonTuru == null || FonTuru.trim().isEmpty() || CompanyType == -1){
				//There are incomplete parts in this fund
				return true;
			}
			else {
				return false;
			}
		}
		return false;
	}
	
	public static boolean RepairFundInformation(String FonKodu, String FonTuru){
		FonDao fd = new FonDao();
		return fd.TryUpdateFund(FonKodu, FonTuru);
	}
	
}
