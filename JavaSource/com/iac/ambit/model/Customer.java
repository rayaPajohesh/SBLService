package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.Hashtable;
import java.util.List;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.EscapeInputUtility;

/**
 * 
 * @author hmirza
 * 
 * There Main Customer Class
 * 
 */
public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String GMTOffset;

	private String FAX;

	private String terminalId;

	private String emailAddress;

	private String userName;

	private String userNameFA;

	private String userId;

	private List messages;

	private String language;

	private String country;

	// jazimagh : 1386/07/16

	private String isLogoutMsg;

	private String userPassword;

	private String userActive;

	private Hashtable accesses;

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
	public String getLanguage() {
		return language;
	}

	public void setLanguage(String lng) {
		this.language = lng;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Customer() {
		this.accesses = new Hashtable();

	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;

	}

	public String getEmailAddress() {
		return emailAddress;

	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;

	}

	public String getTerminalId() {
		return terminalId;

	}

	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;

	}

	public String getFAX() {
		return FAX;

	}

	public void setFAX(String FAX) {
		this.FAX = FAX;

	}

	public String getIsLogoutMsg() {
		return isLogoutMsg;
	}

	public void setIsLogoutMsg(String isLogoutMsg) {
		this.isLogoutMsg = isLogoutMsg;
	}

	public List getMessages() {
		return messages;

	}

	public void setMessages(List messages) {
		this.messages = messages;

	}

	public String getGMTOffset() {
		return GMTOffset;

	}

	public void setGMTOffset(String GMTOffset) {
		this.GMTOffset = GMTOffset;

	}

	public String getUserNameFA() {
		return userNameFA;
	}

	public void setUserNameFA(String userNameFA) {
		this.userNameFA = userNameFA;

	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;

	}

	public String getUserActive() {
		return userActive;
	}

	public void setUserActive(String userActive) {
		this.userActive = userActive;

	}

	public void setAccess(int permissionId, int permissionParentId,
			String permissionActionType) {
		Permissions permission = new Permissions();
		permission.setPermissionId(permissionId);
		if (permissionParentId != 0)
			permission.setPermissionParentId(permissionParentId);
		permission.setPermissionActionTypeId(permissionActionType);

		this.accesses.put(String.valueOf(permissionId), permission);

	}

	public Hashtable getAccess() {

		return this.accesses;
	}

	public boolean isDataValid() {

		if (!AmbitUtility.isAlphanumeric(this.userId, 50, 1) || !EscapeInputUtility.isValideInput(this.userId))
			return false;
		if (!AmbitUtility.isEmpty(this.userName)
				&& (!AmbitUtility.isAlphaFarsiNumeric(this.userName, 50, 1) || !EscapeInputUtility.isValideInput(this.userName)))
			return false;
		if (!AmbitUtility.isEmpty(this.userNameFA)
				&& (!AmbitUtility.isAlphaFarsiNumeric(this.userNameFA, 50, 1) || !EscapeInputUtility.isValideInput(this.userNameFA)))
			return false;
		if (!AmbitUtility.isEmpty(this.userPassword)
				&& (!AmbitUtility.isAlphanumeric(this.userPassword, 50, 1) || !EscapeInputUtility.isValideInput(this.userPassword)))
			return false;
		if (!AmbitUtility.isAlphanumeric(this.userActive, 1, 1))
			return false;

		return true;
	}

}
