package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import org.apache.commons.lang.StringUtils;
import javax.xml.rpc.holders.Holder;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.EscapeInputUtility;

public class Permissions implements Serializable ,Holder{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String permissionTitle;

	private String permissionTitleFA;

	private String permissionStatus;

	private int permissionId;
	
	private int permissionParentId;
	
	private String permissionActionTypeId;
	
	

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

	public Permissions() {

	}
	


	public void setPermissionTitle(String title) {
		this.permissionTitle = title;

	}

	public String getPermissionTitle() {
		return permissionTitle;

	}

	public void setPermissionTitleFA(String title) {
		this.permissionTitleFA = title;

	}

	public String getPermissionTitleFA() {
		return permissionTitleFA;

	}

	public void setPermissionId(int id) {
		this.permissionId = id;

	}

	public int getPermissionId() {
		return permissionId;

	}

	public void setPermissionStatus(String active) {
		this.permissionStatus = active;

	}

	public String getPermissionStatus() {
		return this.permissionStatus;

	}
	
	public String getPermissionActionTypeId() {
		return this.permissionActionTypeId;

	}
	public void setPermissionActionTypeId(String actionType) {
		this.permissionActionTypeId = actionType;

	}
	public int getPermissionParentId() {
		return this.permissionParentId;

	}
	public void setPermissionParentId(int id) {
		this.permissionParentId = id;

	}
	
	public boolean isDataValid() {

		if (!AmbitUtility.isNumeric(Integer.toString(this.permissionId), 10) )
			return false;
		if (!AmbitUtility.isEmpty(this.permissionTitle)
				&& !EscapeInputUtility.isValideInput(this.permissionTitle))
			return false;
		if (!AmbitUtility.isEmpty(this.permissionTitleFA)
				&& !EscapeInputUtility.isValideInput(this.permissionTitleFA))
			return false;
		if (!AmbitUtility.isEmpty(this.permissionStatus)
				&& (!AmbitUtility.isAlphanumeric(this.permissionStatus, 1, 1) || !EscapeInputUtility.isValideInput(this.permissionStatus)))
			return false;
		if (!AmbitUtility.isEmpty(Integer.toString(this.permissionParentId))
				&& !AmbitUtility.isNumeric(Integer.toString(this.permissionParentId), 10))
			return false;
		if (!AmbitUtility.isEmpty(this.permissionActionTypeId)
				&& !AmbitUtility.isAlphanumeric(this.permissionActionTypeId, 1, 1))
			return false;

		return true;

	}
}
