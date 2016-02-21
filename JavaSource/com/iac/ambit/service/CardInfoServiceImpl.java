package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.iac.ambit.DAO.CardInfoDAO;
import com.iac.ambit.model.CardInfo;


public class CardInfoServiceImpl implements CardInfoService {
	
	private CardInfoDAO cardInfoDAO;

	// jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}
	
	public String searchPanInformation(String pan,CardInfo cardInfo )
	  throws Exception{		
	return this.cardInfoDAO.searchPanInformation(  pan , cardInfo) ;
	}

	public void setCardInfoDAO(CardInfoDAO cardInfoDAO) {
		this.cardInfoDAO = cardInfoDAO;
	}

	public boolean existPanInODSATMCard(String pan) throws Exception {
		return this.cardInfoDAO.existPanInODSATMCard(pan);
		
	}


}
