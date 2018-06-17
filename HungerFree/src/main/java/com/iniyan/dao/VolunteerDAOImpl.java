package com.iniyan.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.iniyan.domain.Donor;
import com.iniyan.domain.Volunteer;
import com.iniyan.utility.HibernateUtil;

@Repository
public class VolunteerDAOImpl implements VolunteerDAO {

	private static final String VALIDATE_VOLUNTEER="SELECT COUNT(*) FROM HUNGER_VOLUNTEERS WHERE EMAIL=:mail AND PASSWORD=:pwd";
	private static final String GET_DONORS="SELECT * FROM HUNGER_DONORS WHERE TRUNC(PREPAREDDATE)=TRUNC(SYSDATE)";
	
	@Override
	public List<Integer> verifyVolunteer(String email, String pwd) {
		Session ses=null;
		SQLQuery query=null;
		List<Integer> res=null;
		
		//get session
		ses=HibernateUtil.getSession();
		//prepare query
		query=ses.createSQLQuery(VALIDATE_VOLUNTEER);
		//set param values
		query.setString("mail", email);
		query.setString("pwd", pwd);
		
	query.addScalar("count(*)",StandardBasicTypes.INTEGER);
		//execute query
		res=query.list();
		return res;
	}
	
	@Override
	public int addVolunteer(Volunteer v) {
		Session ses=null;
		Transaction tx=null;
		int idVal=0;
		boolean flag=false;
		
		//get Session
		ses=HibernateUtil.getSession();
		try {
			tx=ses.beginTransaction();
			idVal=(int) ses.save(v);
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
			else {
				tx.rollback();
				return 0;
			}
		}
	
	}
	
	@Override
	public List<Donor> getRecentDonors() {
		Session ses=null;
		SQLQuery query=null;
		List<Donor> list=null;
		//get Session 
		ses=HibernateUtil.getSession();
		query=ses.createSQLQuery(GET_DONORS);
		query.addEntity(Donor.class);
		//execute query
		list=query.list();
		return list;
	}
	
	
	
	
	
	
}
