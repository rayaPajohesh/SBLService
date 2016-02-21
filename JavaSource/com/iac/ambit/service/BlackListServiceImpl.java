package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.DAO.BlackListDAO;
import com.iac.ambit.model.BlackList;

public class BlackListServiceImpl implements BlackListService {
	
	private BlackListDAO blackListDAO;
	
	
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
	
	public BlackListDAO getBlackListDAO() {
		return blackListDAO;
	}

	public void setBlackListDAO(BlackListDAO blackListDAO) {
		this.blackListDAO = blackListDAO;
	}
	
	public String searchBlackList(String fromDate,String toDate
			,String pan	,String activeFlag , ObjectHolder list) throws Exception {
		return this.blackListDAO.searchBlackList(fromDate,
				toDate,pan, activeFlag ,list);
	}

	public String updateBlackList(BlackList blackList) throws Exception{
		return this.blackListDAO.updateBlackList(blackList);
	}
	
	public String updateBlackListForLimitationCard(BlackList blackList) throws Exception{
		return this.blackListDAO.updateBlackListForLimitationCard(blackList);
	}
	
	public String searchPanInBlackList(String pan,ObjectHolder blackList) throws Exception{
		return this.blackListDAO.searchPanInBlackList(pan,blackList);
	}

	public String addBlackList(BlackList blackList) throws Exception{
		return this.blackListDAO.addBlackList(blackList);
	}
	
	public String inactivatePanInBlackList(String pan) throws Exception{
		return this.blackListDAO.inactivatePanInBlackList(pan);
	}

}
