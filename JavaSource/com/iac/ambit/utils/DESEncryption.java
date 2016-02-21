package com.iac.ambit.utils;

import cryptix.util.core.Hex;
import java.security.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.crypto.Cipher;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;


public class DESEncryption {

    public static final String MODULE_NAME = "DES";

	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	

   /**
     * Constructor of DES
     * @Params: None
     * @Returns: None
     */
    public DESEncryption() {
     }

    /**
     * This method will Encrypt the Provided Data by applying Triple DES Encryption Algo.
     *
     * @Param : String Key
     * @Param : String Data
     *
     * @return : String Encrypted Data
     */
    public static String Encrypt(String sKey,String sData){
    
      
        //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"Start");
        byte[] encryptedData ;
        String sEncryptedData = "";
        try {

            Cipher algorithm = Cipher.getInstance("DES/ECB/NoPadding");
            Key key = new SecretKeySpec( (new DESKeySpec(Hex.fromString(sKey))).getKey(), "DES" );
            algorithm.init(Cipher.ENCRYPT_MODE, key);
            byte[] temparr = Hex.fromString(sData.substring(0, sData.length()));
            encryptedData = algorithm.doFinal(temparr, 0, temparr.length);
            sEncryptedData =  Hex.toString(encryptedData);
            //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"Encrypted Data:"+sEncryptedData);

        } catch (Exception e) {
            System.out.println("Error Occured :" + e);
            e.printStackTrace();
        }

        return sEncryptedData;
    }

    /**
     * This method will Decrypt the Provided Data by applying Triple DES Encryption Algo.
     *
     * @Param : String Key
     * @Param : String Data
     *
     * @return : String Decrypted Data
     */
    public static String Decrypt(String sKey,String sData){
        
        byte[] decryptedData ;
        String sDecryptedData = "";
        try {

            Cipher algorithm = Cipher.getInstance("DES/ECB/NoPadding");
            Key key = new SecretKeySpec( (new DESKeySpec(Hex.fromString(sKey))).getKey(), "DES" );
            algorithm.init(Cipher.DECRYPT_MODE, key);

            decryptedData = algorithm.doFinal(Hex.fromString(sData));
            sDecryptedData = Hex.toString(decryptedData);

            //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"Decrypted Data:"+sDecryptedData);

        } catch (Exception e) {
            System.out.println("Error Occured :" + e);
            //throw new ContactCenterException(MODULE_NAME,sMethod,e.getMessage());
            e.printStackTrace();
        }


        //Tracer.traceOut(Tracer.Tracing_Level,MODULE_NAME,sMethod,"End");
        return sDecryptedData;
    }
    
    public static void main(String [] args) 
    {
        //asdf
        try {
        /*
      String input = "0200|0024|20051117162620|6278730101011031|1234|meezan1|";
      String key = "11111111";
      String hexString = toHexString(input);
      String hexkey = toHexString(key);
    
      int padingRequired = hexString.length() % 16;
      padingRequired = 16 - padingRequired;
      
      for (int y=0; y<padingRequired; y++) 
      {
          hexString += "0";
      }
      
      //String encrypted = (new DESEncryption()).Encrypt(hexkey, hexString);
      
      String encrypted = (new DESEncryption()).Encrypt("12345678", 
        "303230307c303030387c32303035313131383138323532307c363237383733303130313031313033317c313233347c3132337c303130317c30333630303030303037377c32303035313131383138323531397c32303035313131383138323531397c000000000000");
              
      
      System.out.println( hexString );
      */
      String input = "0200|0024|20051117162620|6278730101011031|1234|meezan1|";
      System.out.println(input);
      String encrypted = DESEncryption.Encrypt("1234567812345678",DESEncryption.toHexStringAddPadding( 
        "0200|0024|20051117162620|6278730101011031|1234|meezan1|"));
        
      System.out.println( encrypted );
      
      String decrypted = DESEncryption.Decrypt("1234567812345678","A0767164C91FAA8457858C3A3AA3F5E2859F84D727077F7D7A4F72501456A1CD1E2DE4B24C369FB269203B9BF35A86BE5D3E72D667E06468");
      System.out.println(decrypted);
      System.out.println( DESEncryption.toStringFromHEXString(decrypted) );
        }
        catch(Exception e) 
        {
            e.printStackTrace();
        }
    }

    public static String toHexString(String input) 
    {
      String hexString = "";
      int integerTemp;
      for (int i=0; i<input.length(); i++) 
      {
          integerTemp = (int)input.charAt(i);
          hexString += Integer.toHexString(integerTemp);
      }
      return hexString;
    }
    
    public static String toHexStringAddPadding(String input) 
    {
      String hexString = "";
      int integerTemp;
      for (int i=0; i<input.length(); i++) 
      {
          integerTemp = (int)input.charAt(i);
          hexString += Integer.toHexString(integerTemp);
      }
      
      int padingRequired = hexString.length() % 16;
      if (padingRequired != 0) {
        padingRequired = 16 - padingRequired;      
        for (int y=0; y<padingRequired; y++) 
        {
            hexString += "0";
        }
      }
      
      return hexString;
    }
    
    public static String toStringFromHEXString(String input) 
    {
      String output = "";
      input.toLowerCase();
      int k = 0;
      for (int i=0; i<input.length(); i=i+2) {
        k = Integer.parseInt( input.substring(i, i+2), 16 );
        if (k != 0)
          output += ( (char) k );
      }
      return output;
    }
}