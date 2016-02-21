package com.iac.ambit.service;

import com.iac.ambit.model.CardInfo;

public interface CardInfoService{
	
	public String searchPanInformation(String pan,CardInfo cardInfo )
	  throws Exception;
	
	public boolean existPanInODSATMCard(String pan) throws Exception;
	
}
