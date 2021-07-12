package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import hibernateFetch.HibernateUtils;
import models.Fon;


public class FonDao {
private SessionFactory sessionFactory;
	
	public FonDao(){
		sessionFactory = HibernateUtils.getSessionFactory();
	}
	
	public boolean insertFund(Fon toAdd){
		
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
	
	public List<Fon> fetchAllFunds(){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<Fon> funds = null;
		try {
			funds = session.createQuery("FROM Fon").list();
		}catch(Exception e){
			return null;
		}finally {
			session.flush();
			session.close();
		}
        
		return funds;
	}
	
	public List<Fon> fetchAllFundsWhereCompanyType(int companyType){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		List<Fon> funds = null;
		try {
			String jpql = "select f from Fon f where f.companyType = :companyType";
	        funds = 
	            session.createQuery(jpql, Fon.class)
	              .setParameter("companyType", companyType)
	              .getResultList();
		}catch(Exception e){
			return null;
		}finally {
			session.flush();
			session.close();
		}
		return funds;
	}
	
	public boolean checkIfFundExists(String fund){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		boolean fundExists = false;
		
		try {
			Fon exists = (Fon)session.get(Fon.class, fund);
			fundExists = (exists != null);
		}catch(Exception e){
			return false;
		}finally {
			session.flush();
			session.close();
		}
		return fundExists;
	}

	/*
	 * Primary key can be AFA, AOY, TKF etc..
	 */
	public Fon fetchFundByCompanyName(String FonKodu){
		
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		Fon fund = null;
		
		try {
			fund = (Fon)session.get(Fon.class, FonKodu);
		}catch(Exception e){
			return null;
		}finally {
			try{
				session.flush();
				session.close();	
				}
			catch(Exception ex){
				return null;
			}
		}
		return fund;
	}

	public boolean TryUpdateFund(String FonKodu, String FonTuru){
		Session session = sessionFactory.getCurrentSession();
		session.getTransaction().begin();
		
		try {
			Fon fund = (Fon)session.get(Fon.class, FonKodu);
			
			if(fund == null) return false;
			fund.setFonTuru(FonTuru);
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
}
