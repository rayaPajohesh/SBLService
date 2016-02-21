package com.iac.ambit.DAO;

import com.iac.ambit.model.CardInfo;

public interface CardInfoDAO {
	
	public String searchPanInformation(String pan,CardInfo  cardInfo )
	  throws Exception;
	
	public boolean existPanInODSATMCard(String pan) throws Exception;
}
