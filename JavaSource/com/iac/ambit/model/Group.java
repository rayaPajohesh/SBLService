package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.EscapeInputUtility;

public class Group implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String groupId;

	private String groupName;

	private String groupNameFA;

	private String groupDescription;

	private String groupDescriptionFA;

	private String groupActive;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public Group() {

	}

	public void setGroupId(String val) {
		this.groupId = val;

	}

	public String getGroupId() {
		return groupId;

	}
	
	public void setGroupName(String val) {
		this.groupName = val;

	}

	public String getGroupName() {
		return groupName;

	}
	
	public void setGroupNameFA(String val) {
		this.groupNameFA = val;

	}

	public String getGroupNameFA() {
		return groupNameFA;

	}
	
	public void setGroupDescription(String val) {
		this.groupDescription = val;

	}

	public String getGroupDescription() {
		return groupDescription;

	}	
	
	public void setGroupDescriptionFA(String val) {
		this.groupDescriptionFA = val;

	}

	public String getGroupDescriptionFA() {
		return groupDescriptionFA;

	}	

	public void setGroupActive(String val) {
		this.groupActive = val;

	}

	public String getGroupActive() {
		return groupActive;

	}	
	public boolean isDataValid() {

		if (!AmbitUtility.isAlphanumeric(this.groupId, 50, 1) || !EscapeInputUtility.isValideInput(this.groupId))
			return false;
		if (!AmbitUtility.isEmpty(this.groupName)
				&& (!AmbitUtility.isAlphanumeric(this.groupName, 50, 1) || !EscapeInputUtility.isValideInput(this.groupName)))
			return false;
		if (!AmbitUtility.isEmpty(this.groupNameFA)
				&& (!AmbitUtility.isAlphaFarsiNumeric(this.groupNameFA, 50, 1) || !EscapeInputUtility.isValideInput(this.groupNameFA)))
			return false;
		if (!AmbitUtility.isEmpty(this.groupDescription)
				&& (!AmbitUtility.isAlphanumeric(this.groupDescription, 255, 1) || !EscapeInputUtility.isValideInput(this.groupDescription)))
			return false;
		if (!AmbitUtility.isEmpty(this.groupDescriptionFA)
				&& (!AmbitUtility.isAlphaFarsiNumeric(this.groupDescriptionFA, 255, 1) || !EscapeInputUtility.isValideInput(this.groupDescriptionFA)))
			return false;
		if (!AmbitUtility.isAlphanumeric(this.groupActive, 1, 1))
			return false;

		return true;

	}

}
