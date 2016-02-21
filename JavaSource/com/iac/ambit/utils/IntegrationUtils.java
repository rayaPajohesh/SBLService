package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Vector;


public class IntegrationUtils
{
    private static String sDelimiter = Config.getProperty( "Delimiter" );
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
    public static String getDelimiter (  )
    {
        return sDelimiter;

    }

    public static void setDelimiter ( String s )
    {
        sDelimiter = s;

    }

    public static String[] convertMsgToArray ( String msg, String delimiter )
    {
        if ( msg != null )
        {
            Vector tokenList = new Vector(  );

            int currentIndex = 0;
            int nextIndex = msg.indexOf( delimiter );

            while ( nextIndex != -1 )
            {
                if ( currentIndex != nextIndex )
                {
                    tokenList.add( msg.substring( currentIndex, nextIndex ) );

                }
                else
                {
                    tokenList.add( null );

                }

                currentIndex = nextIndex + 1;
                nextIndex = msg.indexOf( delimiter, currentIndex );

            }

            String[] arr = new String[tokenList.size(  )];
            tokenList.copyInto( arr );

            return arr;

        }

        return ( new String[0] );

    }


    /**
     * This function will convert a message into a string array
     *
     * @return
     * @param msg
     */
  /*  public static String[] convertMsgToArray ( String msg )
    {
        if ( msg != null )
        {
            Vector tokenList = new Vector(  );

            int currentIndex = 0;
            int nextIndex = msg.indexOf( getDelimiter(  ) );

            while ( nextIndex != -1 )
            {
                if ( currentIndex != nextIndex )
                {
                    tokenList.add( msg.substring( currentIndex, nextIndex ) );

                }
                else
                {
                    tokenList.add( null );

                }

                currentIndex = nextIndex + 1;
                nextIndex = msg.indexOf( getDelimiter(  ), currentIndex );

            }

            String[] arr = new String[tokenList.size(  )];
            tokenList.copyInto( arr );

            return arr;

        }

        return ( new String[0] );

    }*/
    public static String[] convertMsgToArray ( String msg )
    {
        if ( msg != null )
        {
            Vector tokenList = new Vector(  );

           /* int currentIndex = 0;
            int nextIndex = msg.indexOf( getDelimiter(  ) );

            while ( nextIndex != -1 )
            {
                if ( currentIndex != nextIndex )
                {
                    tokenList.add( msg.substring( currentIndex, nextIndex ) );

                }
                else
                {
                    tokenList.add( null );

                }

                currentIndex = nextIndex + 1;
                nextIndex = msg.indexOf( getDelimiter(  ), currentIndex );

            }*/
            
            for (int i=0;i< msg.length();i++){
            	tokenList.add( msg.substring(i,i+1));	
            }

            String[] arr = new String[tokenList.size(  )];
            tokenList.copyInto( arr );

            return arr;

        }

        return ( new String[0] );

    }    
     
    public static String[] convertMsgToArrayNoLastDelimiter( String msg, String delimiter )
    {
        if ( msg != null )
        {
            Vector tokenList = new Vector(  );

            int currentIndex = 0;
            int nextIndex = msg.indexOf( delimiter );

            while ( nextIndex != -1)
            {
                if ( currentIndex != nextIndex )
                {
                    tokenList.add( msg.substring( currentIndex, nextIndex ) );

                }
                else
                {
                    tokenList.add( null );

                }

                currentIndex = nextIndex + 1;
                nextIndex = msg.indexOf( delimiter, currentIndex );

            }

            if ( currentIndex < msg.length() ) tokenList.add(msg.substring(currentIndex, msg.length()));

            String[] arr = new String[tokenList.size(  )];
            tokenList.copyInto( arr );

            return arr;

        }

        return ( new String[0] );

    }



    /**
     * Generates Delimiter separated Message for given fields in String array
     *
     * @return
     * @param arr
     */
    public static String generateMessage ( String[] arr )
    {
        if ( ( arr != null ) && ( arr.length > 0 ) )
        {
            String strMsg = "";

            for ( int i = 0; i < arr.length; i++ )
            {
                strMsg = strMsg + arr[i] + getDelimiter(  );

            }

            return strMsg;

        }

        return "";

    }
 
    /**
    *  This function takes BranchAccount input in format of "BBBBAAAAAAAAAA"
    *  where B is a branch digit and A is a Account digit.
    * @return
    * @param sBranchCodeAccount
    */
    public static String getBranchCodeFromAccount ( String sBranchCodeAccount )
    {
        if ( sBranchCodeAccount != null )
        {
            return sBranchCodeAccount.substring( 0, 0 );
        }
        else
        {
            return null;
        }

    }

    /**
    *  This function takes BranchAccount input in format of "BBBBAAAAAAAAAA"
    *  where B is a branch digit and A is a Account digit.
    * @return
    * @param sBranchCodeAccount
    */
    public static String getAccountCodeFromAccount ( String sBranchCodeAccount )
    {
        if ( sBranchCodeAccount != null )
        {
            return sBranchCodeAccount.substring( 4, sBranchCodeAccount.length(  ) ).trim(  );
        }
        else
        {
            return null;
        }

    }

    /**
    *  This function takes BranchAccount input in format of "BBBBAAAAAAAAAA"
    *  where B is a branch digit and A is a Account digit.
    * @return
    * @param sBranchCodeAccount
    */
    public static String skipPINAndPAN ( String str )
    {
    	try {
	        int i = 0;
	        if (str.substring(2, 6).equals("0200")) {
		        if ( str != null)
		        {
		          i = str.indexOf("|", 40);
	
		          if (i >= 0) {
		        	  return str.substring(0, i);
		          }
		          else return "";
		        }
		        else
		        {
		            return "";
		        }
	        }
	        else {
	        	if (!str.substring(5, 9).equals("0016"))
	        		return str;
	        	else 
	        		return "";
	        }
    	}
    	catch (Exception e) {
    		    	
    		Tracer.traceOut(Tracer.Error_Level, "Utils", "skipPINAndPAN", e.getMessage());
    		return "";
    	}
    }
    

    
}
