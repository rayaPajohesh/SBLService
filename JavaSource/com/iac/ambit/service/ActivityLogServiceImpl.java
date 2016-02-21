package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.xml.rpc.holders.ObjectHolder;
import com.iac.ambit.DAO.AcitivityLogDAO;
import com.iac.ambit.model.ActivityLog;
import com.iac.ambit.utils.Config;

public class ActivityLogServiceImpl implements ActivityLogService {

	// Activity Type
	public static final String LogoutActivity = Config.getProperty("Logout");

	public static final String LoginActivity = Config.getProperty("Login");

	public static final String PermissionActivationActivity = Config
			.getProperty("PermissionActivation");

	public static final String AddGroup = Config.getProperty("AddGroup");

	public static final String UpdateGroup = Config.getProperty("UpdateGroup");

	public static final String ViewGroups = Config.getProperty("ViewGroups");

	public static final String AddUser = Config.getProperty("AddUser");

	public static final String UpdateUser = Config.getProperty("UpdateUser");

	public static final String ViewUsers = Config.getProperty("ViewUsers");

	public static final String UserActivity = Config
			.getProperty("UserActivity");

	public static final String UserAggregateActivity = Config
			.getProperty("UserAggregateActivity");

	public static final String ChangePassword = Config
			.getProperty("ChangePassword");

	public static final String ChangeLimit = Config.getProperty("ChangeLimit");

	public static final String SearchBlackList = Config
			.getProperty("SearchBlackList");

	public static final String AddInBlackList = Config
			.getProperty("AddInBlackList");
	
	public static final String EditBlackList = Config
	.getProperty("EditBlackList");
	
	public static final String DeleteFromBlackList = Config
	.getProperty("DeleteFromBlackList");

	public static String BlackListTransReportActivity = Config
			.getProperty("BlackListTransReportActivity");

	public static String SearchPanInformationActivity = Config
			.getProperty("SearchPanInformationActivity");

	public static String GetTransForMonitoringActivity = Config
			.getProperty("GetTransForMonitoringActivity");
	
	public static String ChangeRefreshIntervalInSecActivity = Config
	.getProperty("ChangeRefreshIntervalInSecActivity");
	
	
	public static String ChangeAlertInfoActivity = Config
	.getProperty("ChangeAlertInfoActivity");  
	

	// Activity Description
	public static final String LoginDescription = Config
			.getProperty("LoginDescription");

	public static final String LogoutDescription = Config
			.getProperty("LogoutDescription");

	public static final String PermissionActivationDescription = Config
			.getProperty("PermissionActivationDescription");

	public static final String AddGroupDescription = Config
			.getProperty("AddGroupDescription");

	public static final String UpdateGroupDescription = Config
			.getProperty("UpdateGroupDescription");

	public static final String ViewGroupDescription = Config
			.getProperty("ViewGroupDescription");

	public static final String AddUserDescription = Config
			.getProperty("AddUserDescription");

	public static final String UpdateUserDescription = Config
			.getProperty("UpdateUserDescription");

	public static final String ViewUserDescription = Config
			.getProperty("ViewUserDescription");

	public static final String UserActivityDescription = Config
			.getProperty("UserActivityDescription");

	public static final String UserAggregateActivityDescription = Config
			.getProperty("UserAggregateActivityDescription");

	public static final String ChangePasswordDescription = Config
			.getProperty("ChangePasswordDescription");

	

	private AcitivityLogDAO activityLogDAO;

	// jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	// jazimagh : 1386/07/16

	public String logLoginActivity(String userId, String activityDesc)
			throws Exception {

		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(LoginActivity);
		// activityLog.setActivityDescription(LoginDescription);
		activityLog.setActivityDescription(activityDesc);

		return this.activityLogDAO.logActivity(activityLog);

	}

	public String logLogoutActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(LogoutActivity);
		// activityLog.setActivityDescription(LogoutDescription);
		activityLog.setActivityDescription(activityDesc);

		return this.activityLogDAO.logActivity(activityLog);

	}

	public String logUserActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(UserActivity);
		// activityLog.setActivityDescription(UserActivityDescription);
		activityLog.setActivityDescription(activityDesc);

		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logUserAggregateActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(UserAggregateActivity);
		// activityLog.setActivityDescription(UserAggregateActivityDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logPermissionActivationActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(PermissionActivationActivity);
		// activityLog.setActivityDescription(PermissionActivationDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}


	public String logBlackListTransReportActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(BlackListTransReportActivity);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}


	public String logGetTransForMonitoringActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(GetTransForMonitoringActivity);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}
	
	public String logChangeRefreshIntervalInSecActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ChangeRefreshIntervalInSecActivity);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}
	
	public String logChangeAlertInfoActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ChangeAlertInfoActivity);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}


	public String logSearchPanInformationActivity(String userId,
			String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(SearchPanInformationActivity);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logAddGroupActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(AddGroup);
		// activityLog.setActivityDescription(AddGroupDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logUpdateGroupActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(UpdateGroup);
		// activityLog.setActivityDescription(UpdateGroupDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logViewGroupActivity(String userId, String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ViewGroups);
	//	activityLog.setActivityDescription(ViewGroupDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logAddUserActivity(String userId, String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(AddUser);
		//activityLog.setActivityDescription(AddUserDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logUpdateUserActivity(String userId, String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(UpdateUser);
		//activityLog.setActivityDescription(UpdateUserDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logViewUserActivity(String userId, String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ViewUsers);
		//activityLog.setActivityDescription(ViewUserDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logChangePasswordActivity(String userId, String activityDesc) throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ChangePassword);
	//	activityLog.setActivityDescription(ChangePasswordDescription);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logUpdateBlackListForLimitationCardActivity(String userId, String activityDesc)
			throws Exception {
		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(ChangeLimit);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logSearchBlackListActivity(String userId, String activityDesc) throws Exception {

		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(SearchBlackList);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public String logAddInBlackListActivity(String userId, String activityDesc) throws Exception {

		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(AddInBlackList);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}
	
	public String logEditBlackListActivity(String userId, String activityDesc) throws Exception {

		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(EditBlackList);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}
	
	public String logDeleteFromBlackListActivity(String userId, String activityDesc) throws Exception {

		ActivityLog activityLog = new ActivityLog();
		activityLog.setUserId(userId);
		activityLog.setActivityTypeName(DeleteFromBlackList);
		activityLog.setActivityDescription(activityDesc);
		return this.activityLogDAO.logActivity(activityLog);
	}

	public void setActivityLogDAO(AcitivityLogDAO activityLogDAO) {
		this.activityLogDAO = activityLogDAO;

	}

	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception {

		return this.activityLogDAO.searchLogActivity(activityLog, listActivity);
	}

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception {
		return this.activityLogDAO.searchAggregateAllActivities(activityLog,
				aggregateActivityList);
	}

}
