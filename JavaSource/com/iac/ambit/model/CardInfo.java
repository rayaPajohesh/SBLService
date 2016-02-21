package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.rpc.holders.Holder;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.EscapeInputUtility;


public class CardInfo implements Serializable ,Holder{

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

	public CardInfo value;
	
	private String pan;

	private int cardStatusId;

	private String cardStatusDesc;

	private String nameAndFamilyName;

	public CardInfo() {

	}
	
	public CardInfo(CardInfo val) {
		value = val;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getPan() {
		return pan;
	}

	public void setPan(String pan) {
		this.pan = pan;
	}

	public int getCardStatusId() {
		return cardStatusId;
	}

	public void setCardStatusId(int cardStatusId) {
		this.cardStatusId = cardStatusId;
	}

	public String getCardStatusDesc() {
		return cardStatusDesc;
	}

	public void setCardStatusDesc(String cardStatusDesc) {
		this.cardStatusDesc = cardStatusDesc;
	}

	public String getNameAndFamilyName() {
		return nameAndFamilyName;
	}

	public void setNameAndFamilyName(String nameAndFamilyName) {
		this.nameAndFamilyName = nameAndFamilyName;
	}
	
	public boolean isDataValid() {

		if (!AmbitUtility.isEmpty(Integer.toString(this.cardStatusId))
				&& !AmbitUtility.isNumeric(Integer.toString(this.cardStatusId), 10))
			return false;
		if (!AmbitUtility.isNumeric(this.pan, 20) )
			return false;
		if (!AmbitUtility.isAlphanumeric(this.cardStatusDesc, 25, 1) || !EscapeInputUtility.isValideInput(this.cardStatusDesc))
			return false;
		if (!AmbitUtility.isEmpty(this.nameAndFamilyName)
				&& (!EscapeInputUtility.isValideInput(this.nameAndFamilyName)))
			return false;
	
		return true;

	}

}
