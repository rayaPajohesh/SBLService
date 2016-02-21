package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.xml.rpc.holders.Holder;

public class CodeActiveFlag implements  Holder,Serializable {
	
private String codeActiveFlag;
	
	private String codeActiveDescription;
	
	private String codeActiveDescriptionFA;
	
	public CodeActiveFlag[] value;
	
	
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

	public String getCodeActiveDescription() {
		return codeActiveDescription;
	}

	public void setCodeActiveDescription(String codeActiveDescription) {
		this.codeActiveDescription = codeActiveDescription;
	}

	public String getCodeActiveDescriptionFA() {
		return codeActiveDescriptionFA;
	}

	public void setCodeActiveDescriptionFA(String codeActiveDescriptionFA) {
		this.codeActiveDescriptionFA = codeActiveDescriptionFA;
	}

	public String getCodeActiveFlag() {
		return codeActiveFlag;
	}

	public void setCodeActiveFlag(String codeActiveFlag) {
		this.codeActiveFlag = codeActiveFlag;
	}
	
	public CodeActiveFlag (CodeActiveFlag[] val){
		value = val;
	}
	
	public CodeActiveFlag() {
		
	}

}
