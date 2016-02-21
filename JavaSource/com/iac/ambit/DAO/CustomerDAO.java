package com.iac.ambit.DAO;

import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;

import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Group;


public interface CustomerDAO {

	public String addLoginInfo ( String userId, boolean isSuccess ) throws Exception;

	public String getLastLoginDate ( String userId, boolean isSuccess,StringHolder lastLoginDate ) throws Exception;
	
	public String addUser(Customer newUser,Group[] groups) throws Exception;
	
	public String updateUser(Customer newUser, Group[] groups) throws Exception;
	
	public String customerAuthenticate(String userId,String userPassword,ObjectHolder userInfo)
	throws Exception;
	
	public String changePassword(String userId,String userPreviousPassword,String userNewPassword)
	throws Exception;
	
	public String  getAllCustomers(String statusCustomers,ObjectHolder allCustomers) throws Exception;
	
	public String getUserDetail(ObjectHolder user, ObjectHolder allGroups) throws Exception;

}
