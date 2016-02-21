package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



import com.iac.ambit.DAO.CodeActiveFlagDAO;
import com.iac.ambit.model.CodeActiveFlag;

public class CodeActiveFlagServiceImpl implements CodeActiveFlagService {
	
	
	private CodeActiveFlagDAO codeActiveFlagDAO;
	
	
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
	
	public String getAllFlags(CodeActiveFlag allFlags) throws Exception {
		
		return this.codeActiveFlagDAO.getAllFlags(allFlags);
		
		
	}
	public CodeActiveFlagDAO getCodeActiveFlagDAO() {
		return codeActiveFlagDAO;
	}
	public void setCodeActiveFlagDAO(CodeActiveFlagDAO codeActiveFlagDAO) {
		this.codeActiveFlagDAO = codeActiveFlagDAO;
	}

}
