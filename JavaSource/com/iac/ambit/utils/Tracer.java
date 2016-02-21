package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;


/**
 * The basic functionality of this class is to Trace and Log the execution of code. This class can log
 * code on console as well as in file.
 *
 * @author Hashir Ahmed
 *
 */
public final class Tracer
{
    private static Logger ambitLogger = Logger.getLogger( "ambitLogger" );
    public static final Level Debug_Level = Level.DEBUG;
    public static final Level Tracing_Level = Level.INFO;
    public static final Level Error_Level = Level.ERROR;
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
    private Tracer (  )
    {

    }

    /**
     * Use this method for logging exceptions
     * @param throwable
     * @param sourceMethod
     * @param sourceClass
     */
    public static void exception ( String sourceClass, String sourceMethod, Throwable throwable )
    {
        ambitLogger.error( sourceClass + " " + sourceMethod, throwable );

    }

    /**
     * Use this method for logging exceptions
     * @param throwable
     * @param sourceMethod
     * @param sourceClass
     */
    public static void exception ( 
        String sourceClass, String sourceMethod, String message, Throwable throwable )
    {
        ambitLogger.error( sourceClass + " " + sourceMethod + " " + message, throwable );

    }

    /**
     * Use this method for logging at the begging of a method with arguments
     * @param args
     * @param method
     * @param clazz
     */
    public static void enteringMethod ( String clazz, String method, Object[] args )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, clazz + " " + method + " <ENTER>" );

        }

    }

    /**
     * Use this method for logging at the begging of a method with no arguments
     * @param method
     * @param clazz
     */
    public static void enteringMethod ( String clazz, String method )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, clazz + " " + method + " <ENTER>" );

        }

    }

    /**
     * Use this method for logging at the begging of a method with no arguments
     * @param method
     * @param clazz
     */
    public static void enteringMethod ( String signature )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, signature + " <ENTER>" );

        }

    }

    /**
     * Use this method for logging at the end of a method with a void return type
     * @param method
     * @param clazz
     */
    public static void exitingMethod ( String clazz, String method )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, clazz + " " + method + " <EXIT>" );

        }

    }

    /**
     * Use this method for logging at the end of a method with a void return type
     * @param method
     * @param clazz
     */
    public static void exitingMethod ( String signature )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, signature + " <EXIT>" );

        }

    }

    /**
     * Use this method for logging at the end of a method with a non void return type
     * @param result
     * @param method
     * @param clazz
     */
    public static void exitingMethod ( String clazz, String method, Object result )
    {
        if ( ambitLogger.isEnabledFor( Level.INFO ) )
        {
            ambitLogger.log( Level.INFO, clazz + " " + method + " Returning: " + result );

        }

    }

    /**
     * This method should be used to log information in between
     * @param sMessage
     * @param functionName
     * @param moduleName
     * @param level
     */
    public static void traceOut ( 
        Level level, String moduleName, String functionName, String sMessage )
    {
        ambitLogger.log( level, moduleName + " " + functionName + " " + sMessage );

    }

}
