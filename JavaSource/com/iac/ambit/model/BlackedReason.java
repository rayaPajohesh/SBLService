package com.iac.ambit.model;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.rpc.holders.Holder;

public class BlackedReason implements  Holder,Serializable { 

	private String blackedReasonId;
	
	private String blackedReasonDescription;
	
	private String blackedReasonDescriptionFA;
	
	public BlackedReason[] value;
	
	
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

	public String getBlackedReasonDescription() {
		return blackedReasonDescription;
	}


	public void setBlackedReasonDescription(String blackedReasonDescription) {
		this.blackedReasonDescription = blackedReasonDescription;
	}


	public String getBlackedReasonDescriptionFA() {
		return blackedReasonDescriptionFA;
	}


	public void setBlackedReasonDescriptionFA(String blackedReasonDescriptionFA) {
		this.blackedReasonDescriptionFA = blackedReasonDescriptionFA;
	}


	public String getBlackedReasonId() {
		return blackedReasonId;
	}


	public void setBlackedReasonId(String blackedReasonId) {
		this.blackedReasonId = blackedReasonId;
	}
	
	public BlackedReason (){
		
	}
	
	public BlackedReason (BlackedReason[] val){
		value = val;
	}
}
