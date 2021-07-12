package dao;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import hibernateFetch.HibernateUtils;
import models.DateValueDTO;
import models.HistoricData;


public class HistoricDataDao {

	private SessionFactory sessionFactory;
	
	public HistoricDataDao(){
		sessionFactory = HibernateUtils.getSessionFactory();
	}
	
	public boolean insertHistoricData(HistoricData toAdd){
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		try {
			session.save(toAdd);
		}catch(Exception e){
			return false;
		}finally {
			try{
				session.flush();
				session.close();	
			}catch(Exception ex2){
				session.close();
				return false;
			}
		}
		return true;
	}
	
	public List<HistoricData> fetchAllData(String FonKodu){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<HistoricData> historicData = null;
		try {
			String jpql = "select h from HistoricData h where h.FonKodu = :fonKodu";
			historicData = 
		            session.createQuery(jpql, HistoricData.class)
		              .setParameter("fonKodu", FonKodu)
		              .getResultList();
		}catch(Exception e){
			return null;
		}finally {
			session.flush();
			session.close();
		}
        
		return historicData;
	}

	public List<HistoricData> fetchRecordsSinceDate(String FonKodu, LocalDate beginningDate){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<HistoricData> historicData = null;
		try {
			String jpql = "select h from HistoricData h where h.FonKodu = :fonKodu and h.Tarih >= :tarih ";
			historicData = 
		            session.createQuery(jpql, HistoricData.class)
		              .setParameter("fonKodu", FonKodu)
		              .setParameter("tarih", beginningDate)
		              .getResultList();
		}catch(Exception e){
			System.err.println(e);
			return null;
		}finally {
			session.flush();
			session.close();
		}
        
		return historicData;
	}
	
	public LocalDate latestTime(String FonKodu){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		HistoricData historicData = null;
		
		try {
			String jpql = "select h from HistoricData h where h.FonKodu = :fonKodu ORDER BY tarih DESC";
			historicData = 
		            session.createQuery(jpql, HistoricData.class)
		              .setParameter("fonKodu", FonKodu)
		              .setMaxResults(1)
		              .getSingleResult();
		}catch(Exception e){
			System.err.println(e);
			return null;
		}finally {
			session.flush();
			session.close();
		}
        
		if(historicData == null) return null;
		return historicData.getTarih();
	}

	public boolean insertBatchData(List<HistoricData> dataToAdd){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		try{
			for (HistoricData currentItem : dataToAdd) {	
	        	session.save(currentItem);
	        }	
		}catch(Exception e){
			System.err.println(e);
			if(tx != null) tx.rollback();
			return false;
		}
		finally{
			try{
				session.flush();
				session.close();
			}catch(Exception ex){
				System.err.println(ex);
				if(tx != null) tx.rollback();
				return false;
			}	
		}
		return true;
	}

	//Compare true fund values with obtained fund values from api. Update the db
	public boolean updateFundValuesFromTakasbank(String FonKodu, 
			LocalDate beginningDate, 
			List<DateValueDTO> trueData){
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<HistoricData> historicDataCollection = null;
		try {
			String jpql = "select h from HistoricData h where h.FonKodu = :fonKodu and h.Tarih >= :tarih ";
			historicDataCollection = 
		            session.createQuery(jpql, HistoricData.class)
		              .setParameter("fonKodu", FonKodu)
		              .setParameter("tarih", beginningDate)
		              .getResultList();
			
			for(int i = 0; i < trueData.size(); i++){
				DateValueDTO currentObject = trueData.get(i);
				HistoricData itemStoredInDB = historicDataCollection
						.stream()
						.filter(item -> item.getTarih().equals(currentObject.getDate()))
						.findFirst()
						.orElse(null);
				
				if(itemStoredInDB != null && (currentObject.getValue() != itemStoredInDB.getBirimPayDegeri())){
					itemStoredInDB.setBirimPayDegeri(currentObject.getValue());
				}
			}
			return true;
		}catch(Exception e){
			System.err.println(e);
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}

	//Compare true fund values with obtained fund values from api. Update the db
	public boolean RepairTheFundValues(String FonKodu, 
			LocalDate beginningDate, 
			List<DateValueDTO> trueData){
		
		Session session = sessionFactory.getCurrentSession();
		Transaction tx = session.getTransaction();
		tx.begin();
		
		List<HistoricData> historicDataCollection = null;
		List<HistoricData> missingData = new ArrayList<HistoricData>();
		
		try {
			String jpql = "select h from HistoricData h where h.FonKodu = :fonKodu and h.Tarih >= :tarih ";
			historicDataCollection = 
		            session.createQuery(jpql, HistoricData.class)
		              .setParameter("fonKodu", FonKodu)
		              .setParameter("tarih", beginningDate)
		              .getResultList();
			
			for(int i = 0; i < trueData.size(); i++){
				DateValueDTO currentObject = trueData.get(i);
				HistoricData itemStoredInDB = historicDataCollection
						.stream()
						.filter(item -> item.getTarih().equals(currentObject.getDate()))
						.findFirst()
						.orElse(null);
				if(itemStoredInDB != null){
					if((Math.abs(currentObject.getValue() - itemStoredInDB.getBirimPayDegeri()) > 0.01) && currentObject.getValue() != 0){
						itemStoredInDB.setBirimPayDegeri(currentObject.getValue());
					}	
				}	
				else{
					//create new historicdataDao object and add it to collection
					
					HistoricData missingItem = new HistoricData();
					
					missingItem.setTarih(currentObject.getDate());
					missingItem.setFonKodu(FonKodu);
					missingItem.setToplamDeger(BigDecimal.valueOf(-1));
					missingItem.setBirimPayDegeri(currentObject.getValue());
					missingItem.setDolasimdakiPaySayisi(BigDecimal.valueOf(-1));
					missingData.add(missingItem);
				}
			}
			
			//finally, add all new items
			for (HistoricData currentItem : missingData) {	
	        	session.save(currentItem);
	        }	
			return true;
		}catch(Exception e){
			System.err.println(e);
			if(tx != null) tx.rollback();
			return false;
		}finally {
			session.flush();
			session.close();
		}
	}
}
