package com.iac.ambit.webservice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;
import org.springframework.remoting.jaxrpc.ServletEndpointSupport;
import com.iac.ambit.model.ActivityLog;
import com.iac.ambit.model.BlackedReason;
import com.iac.ambit.model.CodeActiveFlag;
import com.iac.ambit.model.TransactionLog;
import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;
import com.iac.ambit.model.BlackList;
import com.iac.ambit.webservice.WebServiceMethods;
import com.iac.ambit.model.CardInfo;

public class SpringWSEndPoint extends ServletEndpointSupport implements
		WebServiceMethods {

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

	private WebServiceMethods springWS;

	// URL WebService : http://localhost/SBLService/axis/SpringWS?wsdl
	protected void onInit() {
		this.springWS = (WebServiceMethods) getWebApplicationContext().getBean(
				"webServiceMethods");
	}

	public SpringWSEndPoint() {

	}

	// Permission Service
	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception {
		return this.springWS
				.getAllPermissions(statusPermissions, allPermission);
	}

	public String getAllActivity(ObjectHolder allActivity) throws Exception {
		return this.springWS.getAllActivity(allActivity);
	}

	public String getCustomerSysPermissionsIds(String userId,
			ObjectHolder permissionIdList) throws Exception {
		return this.springWS.getCustomerSysPermissionsIds(userId,
				permissionIdList);
	}

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURI)
			throws Exception {
		return this.springWS.loadSysPermissionURIs(sysPermissionURI);
	}

	public String isPermissionAvailableToCustomer(String userId,
			String permissionName, BooleanHolder isPermissionAvailable)
			throws Exception {
		return this.springWS.isPermissionAvailableToCustomer(userId,
				permissionName, isPermissionAvailable);
	}

	public String activationPermission(List permissionId) throws Exception {
		return this.springWS.activationPermission(permissionId);
	}

	// Customer Service
	public String addLoginInfo(String userId, boolean isSuccess)
			throws Exception {
		return this.springWS.addLoginInfo(userId, isSuccess);
	}

	public String getLastLoginDate(String userId, boolean isSuccess,
			StringHolder lastLoginDate) throws Exception {
		return this.springWS.getLastLoginDate(userId, isSuccess, lastLoginDate);
	}

	public String customerAuthenticate(String userId, String userPassword,
			ObjectHolder userInfo) throws Exception {
		return this.springWS.customerAuthenticate(userId, userPassword,
				userInfo);
	}

	// AuditLog Service
	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception {
		return this.springWS.searchLogActivity(activityLog, listActivity);
	}

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception {
		return this.springWS.searchAggregateAllActivities(activityLog,
				aggregateActivityList);
	}

	public String logLoginActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logLoginActivity(userId, activityDesc);
	}

	public String logLogoutActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logLogoutActivity(userId, activityDesc);
	}

	public String logPermissionActivationActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logPermissionActivationActivity(userId,
				activityDesc);
	}

	public String logBlackListTransReportActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logBlackListTransReportActivity(userId,
				activityDesc);
	}

	public String logSearchPanInformationActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logSearchPanInformationActivity(userId,
				activityDesc);
	}

	public String logGetTransForMonitoringActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logGetTransForMonitoringActivity(userId,
				activityDesc);
	}

	public String logChangeRefreshIntervalInSecActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logChangeRefreshIntervalInSecActivity(userId,
				activityDesc);
	}

	public String logChangeAlertInfoActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logChangeAlertInfoActivity(userId, activityDesc);
	}

	public String logAddGroupActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logAddGroupActivity(userId, activityDesc);
	}

	public String logUpdateGroupActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logUpdateGroupActivity(userId, activityDesc);
	}

	public String logViewGroupActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logViewGroupActivity(userId, activityDesc);
	}

	public String logAddUserActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logAddUserActivity(userId, activityDesc);
	}

	public String logUpdateUserActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logUpdateUserActivity(userId, activityDesc);
	}

	public String logViewUserActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logViewUserActivity(userId, activityDesc);
	}

	public String logChangePasswordActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logChangePasswordActivity(userId, activityDesc);
	}

	public String logSearchBlackListActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logSearchBlackListActivity(userId, activityDesc);
	}

	public String logAddInBlackListActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logAddInBlackListActivity(userId, activityDesc);
	}

	public String logEditBlackListActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logEditBlackListActivity(userId, activityDesc);
	}

	public String logDeleteFromBlackListActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logDeleteFromBlackListActivity(userId,
				activityDesc);
	}

	public String logUpdateBlackListForLimitationCardActivity(String userId,
			String activityDesc) throws Exception {
		return this.springWS.logUpdateBlackListForLimitationCardActivity(
				userId, activityDesc);
	}

	public String logUserActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logUserActivity(userId, activityDesc);
	}

	public String logUserAggregateActivity(String userId, String activityDesc)
			throws Exception {
		return this.springWS.logUserAggregateActivity(userId, activityDesc);
	}

	public String addGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		return this.springWS.addGroup(newGroup, groupPermissions);
	}

	public String updateGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		return this.springWS.updateGroup(newGroup, groupPermissions);
	}

	public String getAllGroups(String statusGroups, ObjectHolder allGroups)
			throws Exception {
		return this.springWS.getAllGroups(statusGroups, allGroups);
	}

	public String getGroupDetail(String groupId, ObjectHolder group,
			ObjectHolder allPermission) throws Exception {
		return this.springWS.getGroupDetail(groupId, group, allPermission);
	}

	public String addUser(Customer newUser, Group[] groups) throws Exception {
		return this.springWS.addUser(newUser, groups);
	}

	public String updateUser(Customer newUser, Group[] groups) throws Exception {
		return this.springWS.updateUser(newUser, groups);
	}

	public String changePassword(String userId, String userPreviousPassword,
			String userNewPassword) throws Exception {
		return this.springWS.changePassword(userId, userPreviousPassword,
				userNewPassword);
	}

	public String getAllCustomers(String statusCustomers, ObjectHolder allUsers)
			throws Exception {
		return this.springWS.getAllCustomers(statusCustomers, allUsers);
	}

	public String getUserDetail(String userId, ObjectHolder user,
			ObjectHolder allGroups) throws Exception {
		return this.springWS.getUserDetail(userId, user, allGroups);
	}

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception {
		return this.springWS.searchBlackListTrans(fromDate, toDate, fromTime,
				toTime, pan, stan, rrn, transactionLogList);

	}

	public String searchPanInformation(String pan, CardInfo cardInfo)
			throws Exception {
		return this.springWS.searchPanInformation(pan, cardInfo);

	}

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception {
		return this.springWS.getTransForMonitoring(lastLogId,
				transactionLogList);

	}

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception {
		return this.springWS.getDBCurrentDateTime(currentDateTime);

	}

	public String getMaxLogId(StringHolder maxLogId) throws Exception {
		return this.springWS.getMaxLogId(maxLogId);

	}

	public String searchBlackList(String fromDate, String toDate, String pan,
			String activeFlag , ObjectHolder list) throws Exception {
		return this.springWS.searchBlackList(fromDate, toDate, pan, activeFlag ,list);

	}

	public String searchBlackReason(BlackedReason list) throws Exception {
		return this.springWS.searchBlackReason(list);

	}

	public String updateBlackList(BlackList blackList) throws Exception {
		return this.springWS.updateBlackList(blackList);
	}

	public String updateBlackListForLimitationCard(BlackList blackList)
			throws Exception {
		return this.springWS.updateBlackListForLimitationCard(blackList);
	}

	public String searchPanInBlackList(String pan, ObjectHolder blackList)
			throws Exception {
		return this.springWS.searchPanInBlackList(pan, blackList);
	}

	public String addBlackList(BlackList blackList) throws Exception {
		return this.springWS.addBlackList(blackList);
	}

	public String inactivatePanInBlackList(String pan) throws Exception {
		return this.springWS.inactivatePanInBlackList(pan);
	}
	
	public String getAllFlags(CodeActiveFlag allFlags) throws Exception {
		return this.springWS.getAllFlags(allFlags);
	}
	
}