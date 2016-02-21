package com.iac.ambit.webservice;

import java.util.List;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;
import com.iac.ambit.model.ActivityLog;
import com.iac.ambit.model.BlackedReason;
import com.iac.ambit.model.CodeActiveFlag;
import com.iac.ambit.model.TransactionLog;
import com.iac.ambit.model.CardInfo;
import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Group;
import com.iac.ambit.model.Permissions;

import com.iac.ambit.model.BlackList;

public interface WebServiceMethods {
	// Permission Service
	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception;

	public String getAllActivity(ObjectHolder allActivity) throws Exception;

	public String getCustomerSysPermissionsIds(String UserId,
			ObjectHolder permissionIdList) throws Exception;

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURI)
			throws Exception;

	public String isPermissionAvailableToCustomer(String userId,
			String permissionName, BooleanHolder isPermissionAvailable)
			throws Exception;

	public String customerAuthenticate(String userId, String userPassword,
			ObjectHolder userInfo) throws Exception;

	public String activationPermission(List permissionId) throws Exception;

	// Customer Service
	public String addLoginInfo(String userId, boolean isSuccess)
			throws Exception;

	public String getLastLoginDate(String userId, boolean isSuccess,
			StringHolder lastLoginDate) throws Exception;

	// AuditLog Service
	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception;

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception;

	public String logUserActivity(String userId, String activityDesc)
			throws Exception;

	public String logUserAggregateActivity(String userId, String activityDesc)
			throws Exception;

	public String logLoginActivity(String userId, String activityDesc)
			throws Exception;

	public String logLogoutActivity(String userId, String activityDesc)
			throws Exception;

	public String logPermissionActivationActivity(String userId,
			String activityDesc) throws Exception;

	public String logBlackListTransReportActivity(String userId,
			String activityDesc) throws Exception;

	public String logSearchPanInformationActivity(String userId,
			String activityDesc) throws Exception;

	public String logGetTransForMonitoringActivity(String userId,
			String activityDesc) throws Exception;

	public String logChangeRefreshIntervalInSecActivity(String userId,
			String activityDesc) throws Exception;

	public String logChangeAlertInfoActivity(String userId, String activityDesc)
			throws Exception;

	public String logAddGroupActivity(String userId, String activityDesc)
			throws Exception;

	public String logUpdateGroupActivity(String userId, String activityDesc)
			throws Exception;

	public String logViewGroupActivity(String userId, String activityDesc)
			throws Exception;

	public String logAddUserActivity(String userId, String activityDesc)
			throws Exception;

	public String logUpdateUserActivity(String userId, String activityDesc)
			throws Exception;

	public String logViewUserActivity(String userId, String activityDesc)
			throws Exception;

	public String logChangePasswordActivity(String userId, String activityDesc)
			throws Exception;

	public String logUpdateBlackListForLimitationCardActivity(String userId,
			String activityDesc) throws Exception;

	public String logSearchBlackListActivity(String userId, String activityDesc)
			throws Exception;

	public String logAddInBlackListActivity(String userId, String activityDesc)
			throws Exception;

	public String logEditBlackListActivity(String userId, String activityDesc)
			throws Exception;

	public String logDeleteFromBlackListActivity(String userId,
			String activityDesc) throws Exception;

	// Group service
	public String addGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception;

	public String updateGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception;

	public String getAllGroups(String statusGroups, ObjectHolder allGroup)
			throws Exception;

	public String getGroupDetail(String groupId, ObjectHolder group,
			ObjectHolder allPermission) throws Exception;

	// Customer
	public String addUser(Customer newUser, Group[] groups) throws Exception;

	public String updateUser(Customer newUser, Group[] groups) throws Exception;

	public String changePassword(String userId, String userPreviousPassword,
			String userNewPassword) throws Exception;

	public String getAllCustomers(String statusCustomers, ObjectHolder allUsers)
			throws Exception;

	public String getUserDetail(String userId, ObjectHolder user,
			ObjectHolder allGroups) throws Exception;

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception;

	public String searchPanInformation(String pan, CardInfo cardInfo)
			throws Exception;

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception;

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception;

	public String getMaxLogId(StringHolder maxLogId)
	throws Exception;
	
	// blackList
	public String searchBlackList(String fromDate, String toDate, String pan,
			String activeFlag ,ObjectHolder list) throws Exception;

	public String searchBlackReason(BlackedReason list) throws Exception;

	public String updateBlackList(BlackList blackList) throws Exception;

	public String updateBlackListForLimitationCard(BlackList blackList)
			throws Exception;

	public String searchPanInBlackList(String pan, ObjectHolder blackList)
			throws Exception;

	public String addBlackList(BlackList blackList) throws Exception;

	public String inactivatePanInBlackList(String pan) throws Exception;
	
	// codeActiveFlag
	
	public String getAllFlags(CodeActiveFlag allFlags) throws Exception;
	
	
}
