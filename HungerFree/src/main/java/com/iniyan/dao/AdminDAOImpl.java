package com.iniyan.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.StandardBasicTypes;
import org.springframework.stereotype.Repository;

import com.iniyan.domain.Volunteer;
import com.iniyan.utility.HibernateUtil;

@Repository
public class AdminDAOImpl implements AdminDAO {

	private static final String VALIDATE_ADMIN="SELECT COUNT(*) FROM HUNGER_ADMIN WHERE UNAME=:email AND PWD=:pass";
	
	@Override
	public List<Integer> adminValidation(String email, String pwd) {
		Session ses=null;
		SQLQuery query=null;
		List<Integer> list=null;		
		//get session
		ses=HibernateUtil.getSession();
		//prepare query objetc
		query=ses.createSQLQuery(VALIDATE_ADMIN);
		//set param value
		query.setString("email",email);
		query.setString("pass", pwd);
		
		query.addScalar("count(*)",StandardBasicTypes.INTEGER);
		//execute query
		list=query.list();
		return list;
	}//adminValidation
	
	@Override
	public List<Volunteer> getAllVolunteerDetails(){
		Session ses=null;
		Criteria criteria=null;
		List<Volunteer> listVolunteers=null;
		//get SEssion
		ses=HibernateUtil.getSession();
		//create criteria
		criteria=ses.createCriteria(Volunteer.class);
		//execute criteria
		listVolunteers=criteria.list();
		
		return listVolunteers;
	}//getAllVolunteerDetails

}
