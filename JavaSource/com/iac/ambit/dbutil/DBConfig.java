package com.iac.ambit.dbutil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;

import com.iac.ambit.utils.AmbitException;
import com.iac.ambit.utils.Tracer;


public class DBConfig
{
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
    private static Properties defaultProps;
   
    /**
     * This function is used to initialise database configurations
     * @Params:   NONE
     * @Return:   NONE
     */
    public static void init ( String fileName )
        throws AmbitException
    {
        Tracer.enteringMethod( "DBConfig", "init" );
 
        try
        {
            defaultProps = new Properties(  );
            	
            BufferedInputStream in = new BufferedInputStream( new FileInputStream( fileName ) );
            defaultProps.load( in );
            in.close(  );
            
        }
        catch ( FileNotFoundException e )
        {
            new AmbitException( "DBConfig", "init", e );

        }
        catch ( IOException e )
        {
            new AmbitException( "DBConfig", "init", e );

        }

        Tracer.exitingMethod( "DBConfig", "init" );

    }

    /**
     * This function is used to get a value against property name in DBConfig
     * properties file
     * @Params:   Name
     * @Return:   Value
     */ 
    public static String getProperty ( String name )
    {
        String str = "";
        str = defaultProps.getProperty( name );

        if ( ( str != null ) && !str.equalsIgnoreCase( "" ) )
        {
            return str.trim(  );

        }

        return str;

    }

    public static void destroy (  )
    {
        defaultProps = null;

    }

}
