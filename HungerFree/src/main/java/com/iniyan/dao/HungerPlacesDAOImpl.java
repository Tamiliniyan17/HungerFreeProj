package com.iniyan.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.iniyan.domain.HungerPlaces;
import com.iniyan.utility.HibernateUtil;

@Repository
public class HungerPlacesDAOImpl implements HungerPlacesDAO {

	@Override
	public int registerHungerPlace(HungerPlaces hp) {
		Session ses=null;
		Transaction tx=null;
		int idVal=0;
		boolean flag=false;
		
		//get Session
		ses=HibernateUtil.getSession();
		try {
			tx=ses.beginTransaction();
			idVal=(int) ses.save(hp);
			flag=true;
		}
		catch(HibernateException he) {
			flag=false;
			throw he;
		}
		finally {
			if(flag) {
				tx.commit();
				return idVal;
			}
			else
				tx.rollback();
				return 0;
		}
	}//registerHungerPlaces()
	
	@Override
	public List<String> getAvailLocations() {
		Session ses=null;
		Query query=null;
		List<String> list=null;
		
		//get Session
		ses=HibernateUtil.getSession();
		query=ses.createQuery("select distinct(location) from HungerPlaces");
		list=query.list();
	
		return list;
	}//getAvailLocation()

	@Override
	public List<HungerPlaces> getHungerPlaceByLoc(String loc) {
		Session ses=null;
		Criteria criteria=null;
		Criterion cond=null;
		List<HungerPlaces> list=null;
		
		//get session
		ses=HibernateUtil.getSession();
		criteria=ses.createCriteria(HungerPlaces.class);
		cond=Restrictions.eq("location", loc);
		criteria.add(cond);
		
		list=criteria.list();
		
		return list;	
	}//getHungerPlaceByLoc   
	
}
