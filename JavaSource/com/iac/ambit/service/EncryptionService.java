package com.iac.ambit.service;

public interface EncryptionService {

	public String Encrypt(String sData);
	
	public String Decrypt(String sData);
	
	public String EncryptWithoutHexStringAddPadding(String key ,String sData );

	public String EncryptWithDesKey(String key ,String sData );
//	jazimagh  1389/03/08 
	public String DecryptWithDesKey(String key ,String sData );		
	
}
