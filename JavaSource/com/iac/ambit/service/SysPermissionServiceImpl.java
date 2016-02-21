package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.DAO.SysPermissionDAO;

public class SysPermissionServiceImpl implements SysPermissionService {
	private SysPermissionDAO sysPermissionDAO;

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
	public SysPermissionServiceImpl() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iac.ambit.service.SysPermissionServiceImpl#isPermissionAvailable(java.lang.String)
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iac.ambit.service.SysPermissionService#isPermissionAvailable(java.lang.String)
	 */

	public String isPermissionAvailableToCustomer(String sUserName,
			String moduleName, BooleanHolder isPermissionAvailable)
			throws Exception {
		return this.sysPermissionDAO.isPermissionAvailableToCustomer(sUserName,
				moduleName, isPermissionAvailable);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iac.ambit.service.SysPermissionServiceImpl#getPermissibleXML()
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see com.iac.ambit.service.SysPermissionService#getPermissibleXML()
	 */

	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception {
		return this.sysPermissionDAO.getAllPermissions(statusPermissions,
				allPermission);
	}
	public String getAllActivity(ObjectHolder allActivity) throws Exception {
		return this.sysPermissionDAO.getAllActivity(	allActivity);
	}
	
	public void setSysPermissionDAO(SysPermissionDAO sysPermissionDAO) {
		this.sysPermissionDAO = sysPermissionDAO;
	}

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURIs)
			throws Exception {

		return this.sysPermissionDAO.loadSysPermissionURIs(sysPermissionURIs);
	}

	public String getCustomerSysPermissionsIds(String userId,
			ObjectHolder CustomerSysPermissionsIds) throws Exception {
		return this.sysPermissionDAO.getCustomerSysPermissionsIds(userId,
				CustomerSysPermissionsIds);
	}

	public String activationPermission(List permissionId) throws Exception {
		return this.sysPermissionDAO.activationPermission(permissionId);
	}

	public int getParentPermissionId(String permissionId) throws Exception {
		return this.sysPermissionDAO.getParentPermissionId(permissionId);
	}
}
