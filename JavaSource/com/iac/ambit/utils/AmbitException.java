package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;



public class AmbitException extends RuntimeException
{
	private static final long serialVersionUID = 1L;
//	jazimagh : 1386/07/16 
	public final Object clone() throws CloneNotSupportedException{
		throw new CloneNotSupportedException();
	}	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}	
//	jazimagh : 1386/07/16 		
    public AmbitException ( String sModule, String sMethod, String sMessage )
    {
        super(  sModule + "--> " + sMethod + "-->" + sMessage );
    }

    public AmbitException ( String sModule, String sMethod, String sMessage, Throwable t )
    {
        super( t );
    }

    public AmbitException ( String sModule, String sMethod, Throwable e )
    {
        super( e );
    }

}
