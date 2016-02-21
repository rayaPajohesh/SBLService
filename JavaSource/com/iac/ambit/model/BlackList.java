package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.EscapeInputUtility;

import com.iac.ambit.utils.AmbitUtility;

public class BlackList implements Serializable {

	private String blackedReasonId;

	private String pan;

	private String blackedActiveFlag;

	private String blackedDate;

	private String blackedTime;

	private String allowedShetab;

	private String allowedTerminalTypes;

	private String allowedTrans;

	private String allowedTerminals;

	private String comments;

	private String highlight;

	public CardInfo cardInfo;

	private static final long serialVersionUID = 1L;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public BlackList() {

	}

	public String getPan() {
		return this.pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public String getBlackedReasonId() {
		return this.blackedReasonId;
	}

	public void setBlackedReasonId(String blackedReasonId) {
		this.blackedReasonId = blackedReasonId;
	}

	public String getBlackedActiveFlag() {
		return this.blackedActiveFlag;
	}

	public void setBlackedActiveFlag(String blackedActiveFlag) {
		this.blackedActiveFlag = blackedActiveFlag;
	}

	public String getBlackedDate() {
		return this.blackedDate;
	}

	public void setBlackedDate(String blackedDate) {
		this.blackedDate = blackedDate;
	}

	public String getBlackedTime() {
		return this.blackedTime;
	}

	public void setBlackedTime(String blackedTime) {
		this.blackedTime = blackedTime;
	}

	public String getAllowedShetab() {
		return allowedShetab;
	}

	public void setAllowedShetab(String allowedShetab) {
		this.allowedShetab = allowedShetab;
	}

	public String getAllowedTerminals() {
		return this.allowedTerminals;
	}

	public void setAllowedTerminals(String allowedTerminals) {
		this.allowedTerminals = allowedTerminals;
	}

	public String getAllowedTerminalTypes() {
		return this.allowedTerminalTypes;
	}

	public void setAllowedTerminalTypes(String allowedTerminalTypes) {
		this.allowedTerminalTypes = allowedTerminalTypes;
	}

	public String getAllowedTrans() {
		return this.allowedTrans;
	}

	public void setAllowedTrans(String allowedTrans) {
		this.allowedTrans = allowedTrans;
	}

	public String getComments() {
		return this.comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getHighlight() {
		return this.highlight;
	}

	public void setHighlight(String val) {
		this.highlight = val;
	}

	public CardInfo getCardInfo() {
		return this.cardInfo;
	}

	public void setCardInfo(CardInfo cardInfo) {
		this.cardInfo = cardInfo;
	}

	public boolean isDataValid() {

		if (!AmbitUtility.isNumeric(this.pan, 20))
			return false;
		if (!AmbitUtility.isEmpty(this.blackedReasonId)
				&& !(AmbitUtility.isAlphanumeric(this.blackedReasonId, 3, 1)|| !EscapeInputUtility.isValideInput(this.blackedReasonId)))
			return false;
		if (!AmbitUtility.isEmpty(this.blackedActiveFlag)
				&& !AmbitUtility.isAlphanumeric(this.blackedActiveFlag, 1, 1))
			return false;
		if (!AmbitUtility.isEmpty(this.blackedDate)
				&& (!AmbitUtility.isAlphanumeric(this.blackedDate, 10, 1) || !EscapeInputUtility.isValideInput(this.blackedDate)))
			return false;
		if (!AmbitUtility.isEmpty(this.blackedTime)
				&& (!AmbitUtility.isAlphanumeric(this.blackedTime, 8, 1) || !EscapeInputUtility.isValideInput(this.blackedTime)))
			return false;
		if (!AmbitUtility.isEmpty(this.allowedShetab)
				&& !AmbitUtility.isAlphanumeric(this.allowedShetab, 1, 1))
			return false;
		String tempAllowedTerminalTypes ="";
		if (!AmbitUtility.isEmpty(this.allowedTerminalTypes)) {
			tempAllowedTerminalTypes = this.allowedTerminalTypes;
			tempAllowedTerminalTypes = tempAllowedTerminalTypes.replaceAll(
					Constants.semicolonSign, "");
		}
		if (!AmbitUtility.isEmpty(tempAllowedTerminalTypes)
				&& (!AmbitUtility.isAlphanumeric(tempAllowedTerminalTypes, 255,
						1) || !EscapeInputUtility.isValideInput(tempAllowedTerminalTypes)))
			return false;
		String tempAllowedTrans ="";
		if (!AmbitUtility.isEmpty(this.allowedTrans)) {
			tempAllowedTrans = this.allowedTrans;
			tempAllowedTrans = tempAllowedTrans.replaceAll(
					Constants.semicolonSign, "");
		}
		if (!AmbitUtility.isEmpty(tempAllowedTrans)
				&& (!AmbitUtility.isAlphanumeric(tempAllowedTrans, 255, 1) || !EscapeInputUtility.isValideInput(tempAllowedTrans)))
			return false;
		if (!AmbitUtility.isEmpty(this.allowedTerminals)
				&& (!AmbitUtility.isAlphanumeric(this.allowedTerminals, 8, 1) || !EscapeInputUtility.isValideInput(this.allowedTerminals)))
			return false;
		if (!AmbitUtility.isEmpty(this.comments)
				&&   !EscapeInputUtility.isValideInput(this.comments))
			return false;
		if (cardInfo!=null &&!cardInfo.isDataValid()) {
			return false;
		}
		return true;

	}
}
