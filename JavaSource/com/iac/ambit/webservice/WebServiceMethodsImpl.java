package com.iac.ambit.webservice;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Hashtable;
import java.util.List;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.commons.lang.StringUtils;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Tracer;
import com.iac.ambit.utils.Constants;

import com.iac.ambit.model.ActivityLog;
import com.iac.ambit.model.TransactionLog;

import com.iac.ambit.model.BlackList;
import com.iac.ambit.model.BlackedReason;
import com.iac.ambit.model.CodeActiveFlag;
import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;
import com.iac.ambit.model.CardInfo;
import com.iac.ambit.service.BlackedReasonService;
import com.iac.ambit.service.CodeActiveFlagService;
import com.iac.ambit.service.TransactionLogService;
import com.iac.ambit.service.CardInfoService;
import com.iac.ambit.service.BlackListService;
import com.iac.ambit.service.SysPermissionService;
import com.iac.ambit.service.CustomerService;
import com.iac.ambit.service.ActivityLogService;
import com.iac.ambit.service.GroupService;

public class WebServiceMethodsImpl implements WebServiceMethods {
	private SysPermissionService sysPermissionService;

	private CustomerService customerService;

	private ActivityLogService activityLogService;

	private GroupService groupService;

	private BlackListService blackListService;

	private TransactionLogService transactionLogService;

	private CardInfoService cardInfoService;

	private BlackedReasonService blackReasonService;

	private CodeActiveFlagService codeActiveFlagService;

	private Hashtable params = new Hashtable();

	public CodeActiveFlagService getCodeActiveFlagService() {
		return codeActiveFlagService;
	}

	public void setCodeActiveFlagService(
			CodeActiveFlagService codeActiveFlagService) {
		this.codeActiveFlagService = codeActiveFlagService;
	}

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private SysPermissionService getSysPermissionService() {
		return this.sysPermissionService;
	}

	public void setSysPermissionService(
			SysPermissionService sysPermissionService) {
		this.sysPermissionService = sysPermissionService;
	}

	private CustomerService getCustomerService() {
		return this.customerService;
	}

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	private ActivityLogService getActivityLogService() {
		return this.activityLogService;
	}

	public void setActivityLogService(ActivityLogService activityLogService) {
		this.activityLogService = activityLogService;
	}

	private GroupService getGroupService() {
		return this.groupService;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}

	public BlackListService getBlackListService() {
		return blackListService;
	}

	public void setBlackListService(BlackListService blackListService) {
		this.blackListService = blackListService;
	}

	public TransactionLogService getTransactionLogService() {
		return transactionLogService;
	}

	public void setTransactionLogService(
			TransactionLogService transactionLogService) {
		this.transactionLogService = transactionLogService;
	}

	public CardInfoService getCardInfoService() {
		return cardInfoService;
	}

	public void setCardInfoService(CardInfoService cardInfoService) {
		this.cardInfoService = cardInfoService;
	}

	public BlackedReasonService getBlackReasonService() {
		return blackReasonService;
	}

	public void setBlackReasonService(BlackedReasonService blackReasonService) {
		this.blackReasonService = blackReasonService;
	}

	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphanumeric(statusPermissions, 1, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getSysPermissionService().getAllPermissions(
					statusPermissions, allPermission);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "AllPermissions", e);
		}
		return responseCode;
	}

	public String getAllActivity(ObjectHolder allActivity) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			responseCode = this.getSysPermissionService().getAllActivity(
					allActivity);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getAllActivity", e);
		}
		return responseCode;
	}

	public String getCustomerSysPermissionsIds(String userId,
			ObjectHolder permissionIdList) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getSysPermissionService()
					.getCustomerSysPermissionsIds(userId, permissionIdList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"getCustomerSysPermissionsIds", e);
		}
		return responseCode;
	}

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURI)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			responseCode = this.getSysPermissionService()
					.loadSysPermissionURIs(sysPermissionURI);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "loadSysPermissionURIs", e);
		}
		return responseCode;
	}

	public String logUserActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphanumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			
			responseCode = this.getActivityLogService().logUserActivity(userId,
					activityDesc);
		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logUserActivity", e);
		}
		return responseCode;
	}

	public String logUserAggregateActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logUserAggregateActivity(userId, activityDesc);
		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logUserAggregateActivity", e);
		}
		return responseCode;
	}

	public String isPermissionAvailableToCustomer(String userId,
			String permissionName, BooleanHolder isPermissionAvailable)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(permissionName, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}

			responseCode = this.getSysPermissionService()
					.isPermissionAvailableToCustomer(userId, permissionName,
							isPermissionAvailable);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"isPermissionAvailableToCustomer", e);
		}
		return responseCode;
	}

	public String activationPermission(List permissionId) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			for (int k = 0; k < permissionId.size(); k++) {
				if (!AmbitUtility.isNumeric((String) permissionId.get(k), 10)) {
					responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
					return responseCode;
				}
			}
			responseCode = this.getSysPermissionService().activationPermission(
					permissionId);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "activationPermission", e);
		}
		return responseCode;
	}

	// Customer Service
	public String addLoginInfo(String userId, boolean isSuccess)
			throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!(isSuccess == true || isSuccess == false)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCustomerService().addLoginInfo(userId,
					isSuccess);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "addLoginInfo", e);
		}
		return responseCode;
	}

	public String getLastLoginDate(String userId, boolean isSuccess,
			StringHolder lastLoginDate) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!(isSuccess == true || isSuccess == false)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCustomerService().getLastLoginDate(userId,
					isSuccess, lastLoginDate);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getLastLoginDate", e);
		}
		return responseCode;
	}

	public String customerAuthenticate(String userId, String userPassword,
			ObjectHolder userInfo) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(userPassword, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCustomerService().customerAuthenticate(
					userId, userPassword, userInfo);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "customerAuthenticate", e);
		}
		return responseCode;
	}

	public String addGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!newGroup.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (groupPermissions != null && groupPermissions.length != 0) {
				for (int i = 0; i < groupPermissions.length; i++) {
					if (!groupPermissions[i].isDataValid()) {
						responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
						return responseCode;
					}

				}
				responseCode = this.getGroupService().addGroup(newGroup,
						groupPermissions);
			} else {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
			}

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "addGroup", e);
		}
		return responseCode;
	}

	public String updateGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!newGroup.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (groupPermissions != null && groupPermissions.length != 0) {
				for (int i = 0; i < groupPermissions.length; i++) {
					if (!groupPermissions[i].isDataValid()) {
						responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
						return responseCode;
					}
				}
				responseCode = this.getGroupService().updateGroup(newGroup,
						groupPermissions);
			} else {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
			}

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "updateGroup", e);
		}
		return responseCode;
	}

	public String getAllGroups(String statusGroups, ObjectHolder allGroup)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isEmpty(statusGroups)
					&& !AmbitUtility.isAlphanumeric(statusGroups, 1, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getGroupService().getAllGroups(statusGroups,
					allGroup);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getAllGroups", e);
		}
		return responseCode;
	}

	public String getGroupDetail(String groupId, ObjectHolder group,
			ObjectHolder allPermission) throws Exception {
		Group groupDetail = new Group();

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphanumeric(groupId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			groupDetail.setGroupId(groupId);
			group.value = groupDetail;
			responseCode = this.getGroupService().getGroupDetail(group,
					allPermission);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getGroupDetail", e);
		}
		return responseCode;
	}

	public String addUser(Customer newUser, Group[] groups) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {

			if (!newUser.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (groups != null && groups.length != 0) {
				for (int i = 0; i < groups.length; i++) {
					if (!groups[i].isDataValid()) {
						responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
						return responseCode;
					}
				}
				responseCode = this.getCustomerService().addUser(newUser,
						groups);
			} else {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
			}

		}

		catch (Exception e) {
			Tracer.exception("WebServiceMethods", "addUser", e);
		}
		return responseCode;
	}

	public String updateUser(Customer newUser, Group[] groups) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!newUser.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (groups != null && groups.length != 0) {
				for (int i = 0; i < groups.length; i++) {
					if (!groups[i].isDataValid()) {
						responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
						return responseCode;
					}
				}
				responseCode = this.getCustomerService().updateUser(newUser,
						groups);
			} else {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
			}

		}

		catch (Exception e) {
			Tracer.exception("WebServiceMethods", "updateUser", e);
		}
		return responseCode;
	}

	public String changePassword(String userId, String userPreviousPassword,
			String userNewPassword) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(userPreviousPassword, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(userNewPassword, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCustomerService().changePassword(userId,
					userPreviousPassword, userNewPassword);
		}

		catch (Exception e) {
			Tracer.exception("WebServiceMethods", "changePassword", e);
		}
		return responseCode;
	}

	public String getAllCustomers(String statusCustomers, ObjectHolder allUsers)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isEmpty(statusCustomers)
					&& !AmbitUtility.isAlphanumeric(statusCustomers, 1, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCustomerService().getAllCustomers(
					statusCustomers, allUsers);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getAllCustomers", e);
		}
		return responseCode;
	}

	public String getUserDetail(String userId, ObjectHolder user,
			ObjectHolder allGroups) throws Exception {
		Customer userDetail = new Customer();

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			userDetail.setUserId(userId);
			user.value = userDetail;
			responseCode = this.getCustomerService().getUserDetail(user,
					allGroups);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getUserDetail", e);
		}
		return responseCode;
	}

	// AuditLog Service
	public String logLoginActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logLoginActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logLoginActivity", e);
		}
		return responseCode;
	}

	public String logLogoutActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logLogoutActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logLogoutActivity", e);
		}
		return responseCode;
	}

	public String logPermissionActivationActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logPermissionActivationActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logPermissionActivationActivity", e);
		}
		return responseCode;
	}

	public String logBlackListTransReportActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logBlackListTransReportActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logBlackListTransReportActivity", e);
		}
		return responseCode;
	}

	public String logSearchPanInformationActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logSearchPanInformationActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logSearchPanInformationActivity", e);
		}
		return responseCode;
	}

	public String logGetTransForMonitoringActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logGetTransForMonitoringActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logGetTransForMonitoringActivity", e);
		}
		return responseCode;
	}

	public String logChangeRefreshIntervalInSecActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this
					.getActivityLogService()
					.logChangeRefreshIntervalInSecActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logChangeRefreshIntervalInSecActivity", e);
		}
		return responseCode;
	}

	public String logChangeAlertInfoActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logChangeAlertInfoActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logChangeAlertInfoActivity",
					e);
		}
		return responseCode;
	}

	public String logAddGroupActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logAddGroupActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logAddGroupActivity", e);
		}
		return responseCode;
	}

	public String logUpdateGroupActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logUpdateGroupActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logUpdateGroupActivity", e);
		}
		return responseCode;
	}

	public String logViewGroupActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logViewGroupActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logViewGroupActivity", e);
		}
		return responseCode;
	}

	public String logAddUserActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logAddUserActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logAddUserActivity", e);
		}
		return responseCode;
	}

	public String logUpdateUserActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logUpdateUserActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logUpdateUserActivity", e);
		}
		return responseCode;
	}

	public String logViewUserActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().logViewUserActivity(
					userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logViewUserActivity", e);
		}
		return responseCode;
	}

	public String logChangePasswordActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logChangePasswordActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logChangePasswordActivity",
					e);
		}
		return responseCode;
	}

	public String logSearchBlackListActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logSearchBlackListActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logSearchBlackListActivity",
					e);
		}
		return responseCode;
	}

	public String logAddInBlackListActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logAddInBlackListActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logAddInBlackListActivity",
					e);
		}
		return responseCode;
	}

	public String logEditBlackListActivity(String userId, String activityDesc)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logEditBlackListActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "logEditBlackListActivity", e);
		}
		return responseCode;
	}

	public String logDeleteFromBlackListActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logDeleteFromBlackListActivity(userId, activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logDeleteFromBlackListActivity", e);
		}
		return responseCode;
	}

	public String logUpdateBlackListForLimitationCardActivity(String userId,
			String activityDesc) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphaFarsiNumeric(userId, 50, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphaFarsiNumeric(activityDesc, 500, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.logUpdateBlackListForLimitationCardActivity(userId,
							activityDesc);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"logUpdateBlackListForLimitationCardActivity", e);
		}
		return responseCode;
	}

	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!activityLog.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService().searchLogActivity(
					activityLog, listActivity);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchLogActivity", e);
		}
		return responseCode;
	}

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!activityLog.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getActivityLogService()
					.searchAggregateAllActivities(activityLog,
							aggregateActivityList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"searchAggregateAllActivities", e);
		}
		return responseCode;
	}

	public String searchBlackList(String fromDate, String toDate, String pan,
			String activeFlag, ObjectHolder list) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!AmbitUtility.isAlphanumeric(fromDate, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(toDate, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(pan)
					&& !AmbitUtility.isAlphanumeric(pan, 20, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(activeFlag)
					&& !AmbitUtility.isAlphanumeric(activeFlag, 1, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService().searchBlackList(fromDate,
					toDate, pan, activeFlag, list);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchBlackList", e);
		}

		return responseCode;

	}

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphanumeric(fromDate, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isAlphanumeric(toDate, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(fromTime)
					&& !AmbitUtility.isAlphanumeric(fromTime, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(toTime)
					&& !AmbitUtility.isAlphanumeric(toTime, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(pan)
					&& !AmbitUtility.isAlphanumeric(pan, 20, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(stan)
					&& !AmbitUtility.isAlphanumeric(stan, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			if (!AmbitUtility.isEmpty(rrn)
					&& !AmbitUtility.isAlphanumeric(rrn, 32, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getTransactionLogService()
					.searchBlackListTrans(fromDate, toDate, fromTime, toTime,
							pan, stan, rrn, transactionLogList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchBlackListTrans", e);
		}
		return responseCode;

	}

	public String searchPanInformation(String pan, CardInfo cardInfo)
			throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isAlphanumeric(pan, 20, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getCardInfoService().searchPanInformation(pan,
					cardInfo);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchPanInformation", e);
		}
		return responseCode;

	}

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			if (!AmbitUtility.isNumeric(lastLogId, 10)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getTransactionLogService()
					.getTransForMonitoring(lastLogId, transactionLogList);
		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getTransForMonitoring", e);
		}

		return responseCode;
	}

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			responseCode = this.getTransactionLogService()
					.getDBCurrentDateTime(currentDateTime);
		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getDBCurrentDateTime", e);
		}

		return responseCode;
	}

	public String getMaxLogId(StringHolder maxLogId) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;
		try {
			responseCode = this.getTransactionLogService()
					.getMaxLogId(maxLogId);
		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getMaxLogId", e);
		}

		return responseCode;
	}

	public String searchBlackReason(BlackedReason list) throws Exception {
		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {

			responseCode = this.getBlackReasonService()
					.searchBlackReasons(list);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchBlackReason", e);
		}

		return responseCode;

	}

	public String updateBlackList(BlackList blackList) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!blackList.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService()
					.updateBlackList(blackList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "updateBlackList", e);
		}

		return responseCode;

	}

	public String updateBlackListForLimitationCard(BlackList blackList)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!blackList.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService()
					.updateBlackListForLimitationCard(blackList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods",
					"updateBlackListForLimitationCard", e);
		}

		return responseCode;

	}

	public String searchPanInBlackList(String pan, ObjectHolder blackList)
			throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!AmbitUtility.isAlphanumeric(pan, 20, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService().searchPanInBlackList(pan,
					blackList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "searchPanInBlackList", e);
		}

		return responseCode;

	}

	public String addBlackList(BlackList blackList) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!blackList.isDataValid()) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService().addBlackList(blackList);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "addBlackList", e);
		}

		return responseCode;

	}

	public String inactivatePanInBlackList(String pan) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {
			if (!AmbitUtility.isAlphanumeric(pan, 20, 1)) {
				responseCode = Constants.RESPONSE_CODE.ERROR_IN_PARAMETER;
				return responseCode;
			}
			responseCode = this.getBlackListService().inactivatePanInBlackList(
					pan);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "inactivatePanInBlackList", e);
		}

		return responseCode;

	}

	public String getAllFlags(CodeActiveFlag allFlags) throws Exception {

		String responseCode = Constants.RESPONSE_CODE.INTERNAL_ERROR;

		try {

			responseCode = this.getCodeActiveFlagService()
					.getAllFlags(allFlags);

		} catch (Exception e) {
			Tracer.exception("WebServiceMethods", "getAllFlags", e);
		}

		return responseCode;
	}
}
