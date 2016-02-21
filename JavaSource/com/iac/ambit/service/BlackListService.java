package com.iac.ambit.service;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.model.BlackList;

public interface BlackListService {
	
	public String searchBlackList(String fromDate,String toDate
			,String pan	,String activeFlag , ObjectHolder list) throws Exception ;
	
	public String updateBlackList(BlackList blackList) throws Exception;
	
	public String updateBlackListForLimitationCard(BlackList blackList) throws Exception;
	
	public String searchPanInBlackList(String pan,ObjectHolder blackList) throws Exception;
	
	public String addBlackList(BlackList blackList) throws Exception;
	
	public String inactivatePanInBlackList(String pan) throws Exception;
}
