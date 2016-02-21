package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.iac.ambit.DAO.BlackedReasonDAO;
import com.iac.ambit.model.BlackedReason;

public class BlackedReasonServiceImpl implements BlackedReasonService{

	private BlackedReasonDAO blackReasonDAO;
	
	
	
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	

	public BlackedReasonDAO getBlackReasonDAO() {
		return blackReasonDAO;
	}

	public void setBlackReasonDAO(BlackedReasonDAO blackReasonDAO) {
		this.blackReasonDAO = blackReasonDAO;
	}
	
	public String searchBlackReasons(BlackedReason list) throws Exception {
		return this.blackReasonDAO.searchBlackReasons(list);
	}
	
	
}
