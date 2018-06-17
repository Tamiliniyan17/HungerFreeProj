package com.iniyan.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.HibernateException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.iniyan.dto.DonorDTO;
import com.iniyan.dto.HungerPlacesDTO;
import com.iniyan.dto.VolunteerDTO;
import com.iniyan.service.AdminService;
import com.iniyan.service.DonorService;
import com.iniyan.service.HungerPlacesService;
import com.iniyan.service.VolunteerService;

//@WebServlet("/controller")
public class MainControllerServlet extends HttpServlet {
	
	private ApplicationContext ctx=null;
	
	 @Override
	public void init() throws ServletException {
		 //create IOC container
		 ctx=new ClassPathXmlApplicationContext("com/iniyan/cfgs/applicationContext.xml");
	 }
	
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String rType=null;
		DonorDTO donorDto=null;
		DonorService donorService=null;
		String result=null;
		RequestDispatcher rd=null;
		PrintWriter pw=null;
		HungerPlacesService hungerService=null;
		HungerPlacesDTO dto=null;
		String result1=null;
		List<String> list=null;
		String loc=null;
		List<HungerPlacesDTO> listDto=null;
		VolunteerService vService=null;
		String uname=null,pwd=null,auname=null,apwd=null;
		VolunteerDTO vDto=null;
		List<DonorDTO> listDonorDTO=null;
		HttpSession httpSes=null;
		AdminService adService=null;
		List<VolunteerDTO> volDTO=null;
		
		try {
		//general settings
		pw=res.getWriter();
		res.setContentType("text/html");
		//read req type
		rType=req.getParameter("rType");
		httpSes=req.getSession();
		//get Bean
		hungerService=ctx.getBean("hungerService",HungerPlacesService.class);
		vService=ctx.getBean("vService",VolunteerService.class);
		adService=ctx.getBean("adminService",AdminService.class);
		
		if(rType.equalsIgnoreCase("donorSubmit")) {
			try {
				donorDto=new DonorDTO();
				donorDto.setName(req.getParameter("name"));
				donorDto.setAddress(req.getParameter("addrs"));
				donorDto.setFoodItems(req.getParameter("fItems"));
				donorDto.setQty(Integer.parseInt(req.getParameter("qty")));
				donorDto.setMobile(Long.parseLong(req.getParameter("mobile")));
				donorDto.setPreparedTime(Integer.parseInt(req.getParameter("time")));
			
				//get Bean
				donorService=ctx.getBean("donorService",DonorService.class);
				//invoke method of DonorService
		
				result=donorService.saveDonor(donorDto);
				}
		
				catch(Exception e) {
					result="Internal Problem...Try Again";
					return;
				}
				
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>"+result+"</div>");
				
		}//if
		
		
		else if(rType.equalsIgnoreCase("addHunger")) {
			
			if(req.getParameter("flag").equalsIgnoreCase("false")) {
			
				String email=req.getParameter("email");
				int  lastAtPos = email.lastIndexOf("@");
				 int lastDotPos = email.lastIndexOf(".");
				  if(!(lastAtPos < lastDotPos && lastAtPos > 0 && email.indexOf("@@") == -1 && lastDotPos > 2 && (email.length() - lastDotPos) > 2 && (lastDotPos-lastAtPos)>=4) || 	(req.getParameter("type").equals("-1"))) {
						pw.println("<body align='center' style='color:yellow'>");
						pw.println("<div align='center'>You have given Wrong Data...<br>Try Again</div>");
					  return;
				  }//if client side validation not completed
			}
			try {
				dto=new HungerPlacesDTO();
				dto.setType(req.getParameter("type"));
				dto.setLocation(req.getParameter("loc"));
				dto.setPeopleCount(Integer.parseInt(req.getParameter("count")));
				dto.setMobile(Long.parseLong(req.getParameter("mobile")));
				dto.setEmail(req.getParameter("email"));
			
				//invoke method of HungerPlacesService
				result1=hungerService.regHungerPlace(dto);
				
			}//try
			catch(HibernateException he) {
				Throwable t=he.getCause();
				if(t.getMessage().contains("unique")) {
					pw.println("<body align='center' style='color:yellow'>");
					pw.println("<div align='center'>This Email has already registered..</div>");
					return;
				}
				else {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
				}
			}
				catch(Exception e) {
					result1="Internal Problem...Try Again";
					return;
				}
				
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>"+result1+"</div>");
			
		}//add hunger
		
		else if(rType.equalsIgnoreCase("viewhunger")) {
			try {
			//invoke method of HungerPlacesService
			list=hungerService.fetchAvailLocations();
			req.setAttribute("loc",list);
			System.out.println(list);
			rd=req.getRequestDispatcher("/view_hunger.jsp");
			rd.forward(req, res);	
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}//if view hunger link clicked
		
		
		else if(rType.equals("ViewHungers")) {
			try {
				loc=req.getParameter("loc");
				listDto=hungerService.fetchHungerPlaceByLoc(loc);
				req.setAttribute("hplaces",listDto);
				rd=req.getRequestDispatcher("/display_hungers.jsp");
				rd.forward(req, res);
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}
		
		else if(rType.equalsIgnoreCase("viewhungervol")) {
			try {
			//invoke method of HungerPlacesService
			list=hungerService.fetchAvailLocations();
			req.setAttribute("loc",list);
			System.out.println(list);
			rd=req.getRequestDispatcher("/view_hunger_log.jsp");
			rd.forward(req, res);	
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}//if view hunger link clicked
		
		
		else if(rType.equals("viewHungers")) {
			try {
				loc=req.getParameter("loc");
				listDto=hungerService.fetchHungerPlaceByLoc(loc);
				req.setAttribute("hplaces",listDto);
				rd=req.getRequestDispatcher("/display_hungers_log.jsp");
				rd.forward(req, res);
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}
		
		else if(rType.equalsIgnoreCase("Login")) {
			uname=req.getParameter("uname");
			pwd=req.getParameter("pwd");
			try {
			int valid=vService.validateVolunteer(uname, pwd);
			if(valid==1) {			//if uname,pwd are valid
				httpSes.setAttribute("uname",uname);
				httpSes.setAttribute("pwd", pwd);
				rd=req.getRequestDispatcher("/volunteer_home.html");
				rd.forward(req, res);
			}
			else {						//if uname,pwd are invalid
				rd=req.getRequestDispatcher("/volunteer_login_failed.html");
				rd.forward(req, res);
			}		
			}//try
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}
		
		else if(rType.equalsIgnoreCase("logout")) {
			httpSes.setAttribute("uname", null);
			httpSes.setAttribute("pwd", null);
			httpSes.invalidate();
			rd=req.getRequestDispatcher("/volunteer_login.html");
			rd.forward(req, res);
		}
		else if(rType.equalsIgnoreCase("volunteer")) {
			System.out.println("volunteer");
		if(httpSes.getAttribute("uname")==null) {		//if not logged in
				System.out.println(httpSes.getAttribute("uname")+" not logged in");
				rd=req.getRequestDispatcher("/volunteer_login.html");
				rd.forward(req, res);
			}
			else {											//if already logged in
				System.out.println(httpSes.getAttribute("uname")+"  logged in");
				rd=req.getRequestDispatcher("/volunteer_home.html");
				rd.forward(req, res);
			}
		}
		
		
		
		else if(rType.equalsIgnoreCase("join")){
			try {
			if(req.getParameter("flag").equalsIgnoreCase("false")) {				
				String email=req.getParameter("email");
				int  lastAtPos = email.lastIndexOf("@");
				 int lastDotPos = email.lastIndexOf(".");
				  if(!(lastAtPos < lastDotPos && lastAtPos > 0 && email.indexOf("@@") == -1 && lastDotPos > 2 && (email.length() - lastDotPos) > 2 && (lastDotPos-lastAtPos)>=4) || 	!(req.getParameter("pwd").equals(req.getParameter("repwd")))) {
						pw.println("<body align='center' style='color:yellow'>");
						pw.println("<div align='center'>You have given Wrong Data...<br>Try Again</div>");
					  return;
				  }
				 }//if client side validation not completed
				 vDto=new VolunteerDTO();
				 vDto.setName(req.getParameter("name"));
				vDto.setLocation(req.getParameter("loc"));
				vDto.setMobile(Long.parseLong(req.getParameter("mobile")));
				vDto.setEmail(req.getParameter("email"));  
				vDto.setPassword(req.getParameter("pwd"));
				
				result=vService.regVolunteer(vDto);
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>"+result+"</div>");
			
			}//try
			catch(HibernateException he) {
				Throwable t=he.getCause();
				if(t.getMessage().contains("unique")) {
					pw.println("<body align='center' style='color:yellow'>");
					pw.println("<div align='center'>This Email has already registered..</div>");
					return;
				}
				else {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
				}
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
			
		}
		
		else if(rType.equalsIgnoreCase("viewdonors")) {
			try {
				listDonorDTO=vService.getRecentDonors();
				req.setAttribute("listDonors", listDonorDTO);
				rd=req.getRequestDispatcher("/display_donors.jsp");
				rd.forward(req,res);
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}	
		}
		
		else if(rType.equalsIgnoreCase("adminLogin")) {
			int admin=0;
			uname=req.getParameter("uname");
			pwd=req.getParameter("pwd");
			try {
			admin=adService.validateAdmin(uname, pwd);
			}//try
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
			if(admin==0) {
				rd=req.getRequestDispatcher("/admin_login_failed.html");
				rd.forward(req, res);
			}
			else {
				httpSes.setAttribute("auname",uname);
				httpSes.setAttribute("apwd", pwd);
				rd=req.getRequestDispatcher("/admin_home.html");
				rd.forward(req, res);
			}
		}
		
		else if(rType.equalsIgnoreCase("admin")) {
		if(httpSes.getAttribute("auname")==null) {		//if not logged in
				rd=req.getRequestDispatcher("/admin_login.html");
				rd.forward(req, res);
			}
			else {											//if already logged in
				rd=req.getRequestDispatcher("/admin_home.html");
				rd.forward(req, res);
			}
		}//else if
		
		else if(rType.equalsIgnoreCase("viewVolunteers")) {
			try {
			volDTO=adService.fetchAllVolunteerDetails();
			rd=req.getRequestDispatcher("/volunteer_list.jsp");
			req.setAttribute("volDTO", volDTO);
			rd.forward(req, res);
			}
			catch(Exception e) {
				pw.println("<body align='center' style='color:yellow'>");
				pw.println("<div align='center'>Internal Problem..Try Again</div>");
				return;
			}
		}//else if
	}//try
		catch(Exception e) {
			pw.println("<body align='center' style='color:yellow'>");
			pw.println("<div align='center'>Internal Problem..Try Again</div>");
			return;
		}
		
				
	}//doGet(-,-)

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("dopost");
		doGet(request, response);
	}
	
	@Override
	public void destroy() {
		((AbstractApplicationContext) ctx).close();
	}

}
