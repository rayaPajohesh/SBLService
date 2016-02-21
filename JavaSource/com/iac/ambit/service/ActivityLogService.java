package com.iac.ambit.service;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.DAO.AcitivityLogDAO;
import com.iac.ambit.model.ActivityLog;

public interface ActivityLogService {

	public void setActivityLogDAO(AcitivityLogDAO activityLogDAO);

	public String logLoginActivity(String userId, String activityDesc) throws Exception;

	public String logLogoutActivity(String userId, String activityDesc) throws Exception;

	public String logPermissionActivationActivity(String userId, String activityDesc)
			throws Exception;
		
	public String logBlackListTransReportActivity(String userId, String activityDesc)
	throws Exception;
	
	public String logSearchPanInformationActivity(String userId, String activityDesc)
	throws Exception ;
	                                                                                                                                                  
	public String logGetTransForMonitoringActivity(String userId, String activityDesc)
	throws Exception ;

	public String logChangeRefreshIntervalInSecActivity(String userId, String activityDesc)
	throws Exception ;
	
	public String logChangeAlertInfoActivity(String userId, String activityDesc)
	throws Exception ;
	
	public String logAddGroupActivity(String userId, String activityDesc) throws Exception;

	public String logUpdateGroupActivity(String userId, String activityDesc) throws Exception;
	
	public String logViewGroupActivity(String userId, String activityDesc) throws Exception;

	public String logAddUserActivity(String userId, String activityDesc) throws Exception;

	public String logUpdateUserActivity(String userId, String activityDesc) throws Exception;
	
	public String logViewUserActivity(String userId, String activityDesc) throws Exception;

	public String logChangePasswordActivity(String userId, String activityDesc) throws Exception;
	
	public String logUpdateBlackListForLimitationCardActivity(String userId, String activityDesc) throws Exception;
	
	public String logSearchBlackListActivity(String userId, String activityDesc) throws Exception;
	
	public String logAddInBlackListActivity(String userId, String activityDesc) throws Exception;
	
	public String logEditBlackListActivity(String userId, String activityDesc) throws Exception;
	
	public String logDeleteFromBlackListActivity(String userId, String activityDesc) throws Exception;

	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception;

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception;
	public String logUserActivity(String userId, String activityDesc) throws Exception;
	public String logUserAggregateActivity(String userId, String activityDesc) throws Exception;
}