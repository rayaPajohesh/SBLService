package com.iac.ambit.DAO;

import java.util.List;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;

public interface SysPermissionDAO {

	public String isPermissionAvailableToCustomer(String sUserName,
			String moduleName, BooleanHolder isPermissionAvailable)
			throws Exception;

	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception;

	public String getAllActivity(ObjectHolder allActivity) throws Exception;

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURIs)
			throws Exception;

	public String getCustomerSysPermissionsIds(String userId,
			ObjectHolder CustomerSysPermissionsIds) throws Exception;

	public String activationPermission(List permissionId) throws Exception;

	public int getParentPermissionId(String permissionId) throws Exception;
}