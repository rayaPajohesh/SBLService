package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.iac.ambit.utils.DESEncryption;

import com.iac.ambit.utils.Constants;

public class DESEncryptionService implements EncryptionService {	
	
	private String key =Constants.INTERNAL_Key;
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * sData is unencrypted data (non Hex)
	 */
	
	public String Encrypt(String sData) {
		
		return DESEncryption.Encrypt(getKey(), DESEncryption.toHexStringAddPadding(sData));
	}

	/**
	 * sData is Encrypted Hex data.
	 */
	
	public String Decrypt(String sData) {
		
		return DESEncryption.toStringFromHEXString(DESEncryption.Decrypt(getKey(), sData));
	}
	/**
	 * sData is unencrypted data (non Hex)
	 */
	
	public String EncryptWithoutHexStringAddPadding(String key ,String sData ) {
		
		return DESEncryption.Encrypt(key,sData);
	}
	
	/**
	 * this function convert a data to hex and encrypt it with des key
	 */	
	public String EncryptWithDesKey(String key ,String sData) {
		
		return DESEncryption.Encrypt(key, DESEncryption.toHexStringAddPadding(sData));
	}
//	jazimagh 1389/03/08
	public String DecryptWithDesKey(String key ,String sData) {
		
		return  DESEncryption.toStringFromHEXString(DESEncryption.Decrypt(key, sData));
	}		
}
