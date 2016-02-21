package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;


import com.iac.ambit.DAO.CustomerDAO;
import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Group;

public class CustomerServiceImpl implements CustomerService {

	private CustomerDAO customerDAO;

	
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 	
	public String addLoginInfo(String userId, boolean isSuccess)throws Exception {
		return this.customerDAO.addLoginInfo(userId, isSuccess);
	}

	public String getLastLoginDate(String userId, boolean isSuccess,StringHolder lastLoginDate) throws Exception{
		return this.customerDAO.getLastLoginDate(userId, isSuccess,lastLoginDate);
	}

	public String addUser(Customer newUser, Group[] groups) throws Exception {
		return this.customerDAO.addUser(newUser,  groups);
	}

	public String updateUser(Customer newUser, 	Group[] groups) throws Exception {
		return this.customerDAO.updateUser(newUser,  groups);
	}
	
	public void setCustomerDAO(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;
	}
	public String customerAuthenticate(String userId,String userPassword,ObjectHolder userInfo)
	throws Exception{
		return this.customerDAO.customerAuthenticate( userId,userPassword,userInfo);
	}
	
	public String changePassword(String userId,String userPreviousPassword,String userNewPassword)
	throws Exception{
		return this.customerDAO.changePassword(userId, userPreviousPassword, userNewPassword);
	}
	public String getAllCustomers(String statusCustomers,ObjectHolder allCustomers) throws Exception{
		return this.customerDAO.getAllCustomers(statusCustomers,allCustomers);
	}
	
	 public String getUserDetail(ObjectHolder user, ObjectHolder allGroups) throws Exception{
		return this.customerDAO.getUserDetail(user,  allGroups);
	}
}
