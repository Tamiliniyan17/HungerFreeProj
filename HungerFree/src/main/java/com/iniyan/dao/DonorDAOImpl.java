package com.iniyan.dao;

import java.util.Date;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.iniyan.domain.Donor;
import com.iniyan.utility.HibernateUtil;

@Repository("donorDao")
public class DonorDAOImpl implements DonorDAO {

	@Override
	public int registerDonor(Donor donor) {
		Session ses=null;
		Transaction tx=null;
		int idVal=0;
		boolean flag=false;
		
		//get Session
		ses=HibernateUtil.getSession();
		try {
			tx=ses.beginTransaction();
			idVal=(int) ses.save(donor);
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
		}//finally
	}//metod

}//class
