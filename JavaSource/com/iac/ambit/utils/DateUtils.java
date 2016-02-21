package com.iac.ambit.utils;




import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Timestamp;

import java.text.*;

import java.util.*;



/**
 * Title: This utility class includes several util methods regarding to Date management
 * Description:
 * Copyright:    Copyright (c) 2002
 * Company:      Avanza Solutions (Pvt) Ltd.
 * @author Muhammad Irfan
 * @version 1.0
 */
public class DateUtils
{
	public static final String DATE_FORMAT_MMDDYYYY = "MM/dd/yyyy";
    public static final String DATE_FORMAT = "dd/MM/yyyy";
    public static final String DATE_FORMAT_SHORT = "dd/MM/yy";
    public static final String DATE_FORMAT_SHORT2 = "dd-MM-yy";
    public static final String SQL_DATE_FORMAT = "dd-MM-yyyy";
    public static final String DATETIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static final String SQL_DATETIME_FORMAT = "dd-MM-yy HH:mm:ss";
    public static final String UNISON_DATE_FORMAT = "yyyy/MM/dd HH:mm:ss";
    public static final String SQL_TIME_FORMAT = "HH:mm:ss";
    public static final String TRANSACTION_TIME_FORMAT = "HHmmss";
    public static final String TIME_FORMAT = "h:mm a";
    private static final String CHR_SLASH = "/";
    public static final String UNISON_DATE_FORMAT_FARSI = "yyyyMMddHHmmss";
  //  private static final String CHR_COLON = ":";
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
    /**
     * Returns a string representing the given date in the format DATE_FORMAT
     *
     * @param dtDate A {@link java.util.Date} object. The date to format
     * @return A string representing the input date in the format specified by DATE_FORMAT
     * @version 0.1
     */
    public static String dateToString ( Date dtDate )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( DateUtils.DATE_FORMAT );
        String sDate = dateFormatter.format( dtDate );

        return sDate;

    }

    public static Timestamp getFromTimeStamp ( Date date )
    {
        Calendar calendar = Calendar.getInstance(  );
        calendar.setTime( date );
        calendar.set( Calendar.HOUR_OF_DAY, calendar.getActualMinimum( Calendar.HOUR_OF_DAY ) );
        calendar.set( Calendar.MINUTE, calendar.getActualMinimum( Calendar.MINUTE ) );
        calendar.set( Calendar.SECOND, calendar.getActualMinimum( Calendar.SECOND ) );
        calendar.set( Calendar.MILLISECOND, calendar.getActualMinimum( Calendar.MILLISECOND ) );

        return new Timestamp( calendar.getTime(  ).getTime(  ) );

    }

    public static Timestamp getToTimeStamp ( Date date )
    {
        Calendar calendar = Calendar.getInstance(  );
        calendar.setTime( date );
        calendar.set( Calendar.HOUR_OF_DAY, calendar.getActualMaximum( Calendar.HOUR_OF_DAY ) );
        calendar.set( Calendar.MINUTE, calendar.getActualMaximum( Calendar.MINUTE ) );
        calendar.set( Calendar.SECOND, calendar.getActualMaximum( Calendar.SECOND ) );
        calendar.set( Calendar.MILLISECOND, calendar.getActualMaximum( Calendar.MILLISECOND ) );

        return new Timestamp( calendar.getTime(  ).getTime(  ) );

    }

    public static String getCurrentDateSeparatedByChar ( char separator )
    {
        Calendar calendar = Calendar.getInstance(  );
        String dateValue = calendar.get( Calendar.DAY_OF_MONTH ) + String.valueOf( separator ) +
            calendar.get( Calendar.MONTH ) + String.valueOf( separator ) +
            calendar.get( Calendar.YEAR );

        return dateValue;

    }

    public static String getCurrentTimeWithAM_PM (  )
    {
        SimpleDateFormat df = new SimpleDateFormat( DateUtils.TIME_FORMAT );

        return df.format( new Date(  ) );

    }

    public static String getTimeWithAM_PM ( Date date )
    {
        SimpleDateFormat df = new SimpleDateFormat( DateUtils.TIME_FORMAT );
     //   Properties prop = new Properties();
        
        return df.format( date );

    }

    public static String timeToString ( Date dtDate )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( DateUtils.SQL_TIME_FORMAT );
        String sDate = dateFormatter.format( dtDate );

        return sDate;

    }

    /**
     * Overloaded dateToString funciton which takes date and date format as param
     */
    public static String dateToString ( Date dtDate, String dateFormat )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( dateFormat );
        String sDate = dateFormatter.format( dtDate );

        return sDate;

    }

    /**
     * Returns a string representing the given date and time in the format DATETIME_FORMAT
     *
     * @param dtDate A {@link java.util.Date} object. The date to format
     * @return A string representing the input date in the format specified by DATE_FORMAT
     * @version 0.1
     */
    public static String datetimeToString ( Date dtDate,String format )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( format );
        String sDate = dateFormatter.format( dtDate );

        return sDate;

    }

    /**
     * Parses a string formatted as specified by DATE_FORMAT and returns the corresponding
     * {@link java.util.Date} object.
     *
     * @param sDate A string in the format specified by DATE_FORMAT
     * @return A {@link java.util.Date} object. The date represented by the given string.
     * Returns null if the string is not in the correct format
     * @version 0.1
     */
    public static Date stringToDate ( String sDate )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( DATE_FORMAT );

        Date dtDate = null;

        try
        {
            dtDate = dateFormatter.parse( sDate );

        }
        catch ( ParseException e )
        {
            return null;

        }

        return dtDate;

    }

    /**
     * Parses a string formatted as specified by DATETIME_FORMAT and returns the corresponding
     * {@link java.util.Date} object.
     *
     * @param sDate A string in the format specified by DATETIME_FORMAT
     * @return A {@link java.util.Date} object. The date represented by the given string.
     * Returns null if the string is not in the correct format
     * @version 0.1
     */
    public static Date stringToDatetime ( String sDate )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( DATETIME_FORMAT );

        Date dtDate = null;

        try
        {
            dtDate = dateFormatter.parse( sDate );

        }
        catch ( ParseException e )
        {
            return null;

        }

        return dtDate;

    }

    /**
     * Overloaded function which takes Dateformat as Param
     */
    public static Date stringToDatetime ( String sDate, String dateFormat )
    {
        SimpleDateFormat dateFormatter = new SimpleDateFormat( dateFormat );

        Date dtDate = null;

        try
        {
            dtDate = dateFormatter.parse( sDate );

        }
        catch ( Exception e )
        {
            Tracer.traceOut( 
                Tracer.Debug_Level, "DateUtils", "stringToDatetime()",
                "Exception occured while converting string to date/time: " + e );

            return null;

        }

        return dtDate;

    }

    /**
     * Returns the next date to the given one
     *
     * @param sDate The {@link java.util.Date} to increment one day.
     * @return A {@link java.util.Date} object. The date incremented by one day
     * @version 0.1
     */
    public static Date getNextDate ( Date dtDate )
    {
        Calendar calDate = Calendar.getInstance(  );

        calDate.setTime( dtDate );

        calDate.add( Calendar.DATE, 1 );

        return calDate.getTime(  );

    }

    /**
     * Compare the dates given to see whether its less then the given years
     * @return
     * @param fromDate
     */
    public static boolean isLessThanYears ( Date fromDate, Date toDate, int years )
    {
        boolean result = false;

        Calendar toCalendar = Calendar.getInstance(  );
        Calendar fromCalendar = Calendar.getInstance(  );

        toCalendar.setTime( toDate );
        fromCalendar.setTime( fromDate );

        int fromYear = fromCalendar.get( Calendar.YEAR );
        int toYear = toCalendar.get( Calendar.YEAR );
        int yearDifference = toYear - fromYear;

        if ( yearDifference == years )
        {
            int toDay = toCalendar.get( Calendar.DAY_OF_YEAR );
            int fromDay = 365 - fromCalendar.get( Calendar.DAY_OF_YEAR );
            result = ( toDay + fromDay ) <= 365;

        }
        else if ( yearDifference < years )
        {
            result = true;

        }
        else
        {
            result = false;

        }

        return result;

    }

    /**
     * Returns the previous date to the given one
     *
     * @param sDate The {@link java.util.Date} to decrement one day.
     * @return A {@link java.util.Date} object. The date decremented by one day
     * @version 0.1
     */
    public static Date getPreviousDate ( Date dtDate )
    {
        Calendar calDate = Calendar.getInstance(  );

        calDate.setTime( dtDate );

        calDate.add( Calendar.DATE, -1 );

        return calDate.getTime(  );

    }

    /**
     * Evaluates if the first given date is before the second one
     *
     * @param dtFirst A {@link java.util.Date} object.
     * @param dtSecond A {@link java.util.Date} object.
     * @return True if the first date is before second one. Otherwise false.
     * @version 0.1
     * @since 12/06/2001
     */
    public static boolean isBefore ( Date dtFirst, Date dtSecond )
    {
        Calendar calFirst = Calendar.getInstance(  );
        Calendar calSecond = Calendar.getInstance(  );

        calFirst.setTime( dtFirst );
        calSecond.setTime( dtSecond );

        return calFirst.before( calSecond );

    }

    /**
     * Evaluates if the first given date is after the second one
     *
     * @param dtFirst A {@link java.util.Date} object.
     * @param dtSecond A {@link java.util.Date} object.
     * @return True if the first date is after second one. Otherwise false.
     * @version 0.1
     * @since 12/06/2001
     */
    public static boolean isAfter ( Date dtFirst, Date dtSecond )
    {
        Calendar calFirst = Calendar.getInstance(  );
        Calendar calSecond = Calendar.getInstance(  );

        calFirst.setTime( dtFirst );
        calSecond.setTime( dtSecond );

        return calFirst.after( calSecond );

    }

    public static boolean isEqual ( Date dtFirst, Date dtSecond )
    {
        Calendar calFirst = Calendar.getInstance(  );
        Calendar calSecond = Calendar.getInstance(  );
        Calendar currentDate = Calendar.getInstance(  );
        currentDate.setTime( stringToDate( getCurrentDate(  ) ) );

        calFirst.setTime( dtFirst );
        calSecond.setTime( dtSecond );

        if ( calFirst.equals( currentDate ) && calSecond.equals( currentDate ) )
        {
            return true;

        }

        return false;

    }

    /**
     * Returns the current time
     */
    public static String getCurrentTime (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        
        String timeValue = formatRecordNumber(calendar.get( Calendar.HOUR_OF_DAY ),2) + ":" +
        formatRecordNumber(calendar.get( Calendar.MINUTE ),2) + ":" + formatRecordNumber(calendar.get( Calendar.SECOND ),2);

        return timeValue;

    }
	public static String formatRecordNumber(int iRecordNumber, int iRequiredSize) {
		String strRecordNumber = new Integer(iRecordNumber).toString();

		while (strRecordNumber.length() < iRequiredSize) {
			strRecordNumber = "0" + strRecordNumber;

		}

		return strRecordNumber;

	}	   
    /**
     * Returns the current Date
     */
    public static String getCurrentDate (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        
        String dateValue = calendar.get( Calendar.DAY_OF_MONTH ) + "/" +
            ( calendar.get( Calendar.MONTH ) + 1 ) + "/" + calendar.get( Calendar.YEAR );

        return dateValue;

    }

    public static int getAddedTimeStamp (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        int dateValue = calendar.get( Calendar.YEAR ) + ( calendar.get( Calendar.MONTH ) + 1 ) +
            calendar.get( Calendar.DAY_OF_MONTH );
        dateValue = dateValue + calendar.get( Calendar.HOUR ) + calendar.get( Calendar.MINUTE ) +
            calendar.get( Calendar.SECOND );

        return dateValue;

    }

    public static String getCurrentDateForATMBatch (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        String dateValue = calendar.get( Calendar.YEAR ) + "" +
            AmbitUtility.formatRecordNumber( ( calendar.get( Calendar.MONTH ) + 1 ), 2 ) + "" +
            AmbitUtility.formatRecordNumber( calendar.get( Calendar.DAY_OF_MONTH ), 2 );

        return dateValue;

    }

    public static String getFileNameStamp (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        String dateValue = "" + calendar.get( Calendar.DAY_OF_MONTH ) +
            ( calendar.get( Calendar.MONTH ) + 1 ) + calendar.get( Calendar.YEAR );
        String timeValue = "" + calendar.get( Calendar.HOUR ) + calendar.get( Calendar.MINUTE ) +
            calendar.get( Calendar.SECOND );

        return dateValue + timeValue;

    }
    
    /**
     * Evaluates if the current date is between toDate and fromDate
     *
     * @param dtTo A {@link java.util.Date} object.
     * @param dtFrom A {@link java.util.Date} object.
     * @return True if the current date is between toDate and fromDate. Otherwise false.
     * @version 0.1
     * @since 14/10/2002
     */
    public static boolean isBetween ( Date dtTo, Date dtFrom )
    {
        Date currentDate;
        boolean isAfter;
        boolean isBefore;
       // boolean isEqual;

        currentDate = stringToDate( getCurrentDate(  ) );

        if ( !( isEqual( currentDate, dtFrom ) ) )
        {
            isBefore = isBefore( currentDate, dtFrom );

        }
        else
        {
            isBefore = true;

        }

        if ( !( isEqual( currentDate, dtTo ) ) )
        {
            isAfter = isAfter( currentDate, dtTo );

        }
        else
        {
            isAfter = true;

        }

        if ( ( isAfter ) && ( isBefore ) )
        {
            return true;

        }

        if ( !isAfter && !isBefore )
        {
            return true;

        }

        return false;

    }

    public static String checkDateBefore ( Date date )
    {
        if ( date == null )
        {
            return "";

        }
        else if ( date.before( DateUtils.stringToDate( "31/12/1900" ) ) )
        {
            return "";

        }

        return DateUtils.dateToString( date, DateUtils.DATE_FORMAT );

    }

    public static String checkDateBefore ( String date )
    {
        if ( date == null )
        {
            return "";

        }
        else if ( stringToDate( date ).before( DateUtils.stringToDate( "31/12/1900" ) ) )
        {
            return "";

        }

        return DateUtils.dateToString( stringToDate( date ), DateUtils.DATE_FORMAT );

    }

    public static String getCurrentDateTimeForPHX (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        String dateValue = calendar.get( Calendar.YEAR ) + "" +
        AmbitUtility.formatRecordNumber( ( calendar.get( Calendar.MONTH ) + 1 ), 2 ) + "" +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.DAY_OF_MONTH ), 2 );
        String timeValue = "" +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.HOUR_OF_DAY ), 2 ) +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.MINUTE ), 2 ) +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.SECOND ), 2 );

        return dateValue + timeValue;

    }

    public static String getCurrentDateForPHX (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        String dateValue = calendar.get( Calendar.YEAR ) + "" +
        AmbitUtility.formatRecordNumber( ( calendar.get( Calendar.MONTH ) + 1 ), 2 ) + "" +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.DAY_OF_MONTH ), 2 );

        return dateValue;

    }

    public static String getDateTimeForUNISON ( String date )
    {
        if ( ( date != null ) && !date.equals( "" ) )
        {
            Calendar c = Calendar.getInstance(  );
            c.setTime( stringToDate( date ) );

            return formatDataTimeForRDV( c );

        }
        else
        {
            return "";

        }

    }

    public static String getCurrentTimeForPHX (  )
    {
        Calendar calendar = Calendar.getInstance(  );
        String timeValue = "" +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.HOUR_OF_DAY ), 2 ) +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.MINUTE ), 2 ) +
        AmbitUtility.formatRecordNumber( calendar.get( Calendar.SECOND ), 2 );

        return timeValue;

    }

    public static String getCurrentDateTimeForRDV (  )
    {
        Calendar calendar = Calendar.getInstance(  );

        return formatDataTimeForRDV( calendar );

    }

    public static String formatDataTimeForRDV ( Calendar calendar )
    {
        String retValue = calendar.get( Calendar.YEAR ) + "" +
            ( ( ( calendar.get( Calendar.MONTH ) + 1 ) < 10 ) ? "0" : "" ) +
            ( calendar.get( Calendar.MONTH ) + 1 ) + "" +
            ( ( calendar.get( Calendar.DAY_OF_MONTH ) < 10 ) ? "0" : "" ) +
            calendar.get( Calendar.DAY_OF_MONTH ) +
            ( ( calendar.get( Calendar.HOUR_OF_DAY ) < 10 ) ? "0" : "" ) +
            calendar.get( Calendar.HOUR_OF_DAY ) +
            ( ( calendar.get( Calendar.MINUTE ) < 10 ) ? "0" : "" ) +
            calendar.get( Calendar.MINUTE ) +
            ( ( calendar.get( Calendar.SECOND ) < 10 ) ? "0" : "" ) +
            calendar.get( Calendar.SECOND );

        return retValue;

    }

    public static String formatDataTimeForRDV ( Date dt )
    {
        Calendar c = Calendar.getInstance(  );
        c.setTime( dt );

        return formatDataTimeForRDV( c );

    }

    public static String getGreetingMoment ( Date dt )
    {
       
    	Calendar c = Calendar.getInstance(  );
        c.setTimeInMillis( dt.getTime(  ) );
     //jazimagh : 1386/05/23
     /*   if ( ( c.get( c.HOUR_OF_DAY ) >= Integer.parseInt( Config.getProperty("GREET_MORNING_START") ) ) 
          && ( c.get( c.HOUR_OF_DAY ) < Integer.parseInt( Config.getProperty("GREET_MORNING_END") ) ) )
        {
            return Config.getProperty("GREET_MORNING");
        }

        if ( ( c.get( c.HOUR_OF_DAY ) >= Integer.parseInt( Config.getProperty("GREET_AFTERNOON_START") ) ) 
          && ( c.get( c.HOUR_OF_DAY ) < Integer.parseInt( Config.getProperty("GREET_AFTERNOON_END") ) ) )
        {
            return Config.getProperty("GREET_AFTERNOON");
        }

        if ( ( c.get( c.HOUR_OF_DAY ) >= Integer.parseInt( Config.getProperty("GREET_EVENING_START") ) ) 
          || ( c.get( c.HOUR_OF_DAY ) < Integer.parseInt( Config.getProperty("GREET_EVENING_END") ) ) )
        {
            return Config.getProperty("GREET_EVENING");
        } */

        return Config.getProperty("GREET_DEFAULT");
    }

    public static Date getDateStringForAmbit ( String sDate )
    {
        Date rtDate = new Date(  );
        Calendar c = Calendar.getInstance(  );

            c.set( Calendar.YEAR, ( Integer.parseInt( sDate.substring( 0, 4 ) ) ) );
            c.set( Calendar.MONTH, ( Integer.parseInt( sDate.substring( 4, 6 ) ) - 1 ) );
            c.set( Calendar.DAY_OF_MONTH, Integer.parseInt( sDate.substring( 6, 8 ) ) );

            if (sDate.length() >= 10) {
	            c.set( Calendar.HOUR_OF_DAY, Integer.parseInt( sDate.substring( 8, 10 ) ) );
	            
	            if (sDate.length() >= 12) {
	            	c.set( Calendar.MINUTE, Integer.parseInt( sDate.substring( 10, 12 ) ) );
	            
	            	if (sDate.length() >= 14) {
	            		c.set( Calendar.SECOND, Integer.parseInt( sDate.substring( 12, 14 ) ) );
	            	}
	            }
            }

        rtDate.setTime( c.getTime(  ).getTime(  ) );

        return rtDate;

    }
    
  

    public static Date concatDateAndTime ( Date time, Date date )
    {
        Calendar cDate = Calendar.getInstance(  );
        Calendar cTime = Calendar.getInstance(  );
        cDate.setTime( date );
        cTime.setTime( time );

        Calendar cRet = Calendar.getInstance(  );
        cRet.set( Calendar.YEAR, cDate.get( Calendar.YEAR ) );
        cRet.set( Calendar.MONTH, cDate.get( Calendar.MONTH ) );
        cRet.set( Calendar.DATE, cDate.get( Calendar.DATE ) );
        cRet.set( Calendar.HOUR_OF_DAY, cTime.get( Calendar.HOUR_OF_DAY ) );
        cRet.set( Calendar.MINUTE, cTime.get( Calendar.MINUTE ) );
        cRet.set( Calendar.SECOND, cTime.get( Calendar.SECOND ) );

        Date retDate = cRet.getTime(  );

        return retDate;

    }

    public static Date getYesterYear (  )
    {
        Calendar c = Calendar.getInstance(  );
        c.set( Calendar.YEAR, ( c.get( Calendar.YEAR ) - 1 ) );

        Date retDate = new Date( c.getTimeInMillis(  ) );

        return retDate;

    }

    public static Date getYesterMonth (  )
    {
        Calendar c = Calendar.getInstance(  );
        c.set( Calendar.DAY_OF_YEAR, ( c.get( Calendar.DAY_OF_YEAR ) - 31 ) );

        Date retDate = new Date( c.getTimeInMillis(  ) );

        return retDate;

    }

    public static Date getYesterWeek (  )
    {
        Calendar c = Calendar.getInstance(  );
        c.set( Calendar.DAY_OF_YEAR, ( c.get( Calendar.DAY_OF_YEAR ) - 7 ) );

        Date retDate = new Date( c.getTimeInMillis(  ) );

        return retDate;

    }

    public static Date getYesterDate ( int numberOfDays )
    {
        Calendar c = Calendar.getInstance(  );
        c.set( Calendar.DAY_OF_YEAR, ( c.get( Calendar.DAY_OF_YEAR ) - numberOfDays ) );

        Date retDate = new Date( c.getTimeInMillis(  ) );

        return retDate;

    }

    public static String getSQLFormattedDate ( Date date ,String format)
    {
    	
        String strDate = "";
        strDate = "CONVERT(datetime,'" + datetimeToString( date,format ) + "',103)";

        return strDate;

    }
    /* public static void main ( String[] args )
     {
         System.out.println( getDateTimeForUNISON( "01/12/2004" ) );

     }*/
    
    public static void setTimeToMidNight(Date date) {
    	date.setTime(date.getTime() + 86400000-1);
    }
    
    public static int getDaysBetween(Date initDate, Date finalDate)
    {
    	final int msperday = 1000 * 60 *60 * 24;
    	
    	int dbd = (int) ((finalDate.getTime() - initDate.getTime()) / msperday);
    	
    	return dbd;
    		
    }
    public static String getGreenWichDate (String dateTime ) throws Exception
    {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
		Calendar cal = Calendar.getInstance();
		Date processDate = dateFormat.parse(dateTime);
		cal.setTime(processDate);	 
		cal.add(Calendar.HOUR, -3);
		cal.add(Calendar.MINUTE, -30);
		
        return dateFormat.format(cal.getTime());

    }  
   
    public static Date getDateStringForSPG ( String sDate )
    {
        Date rtDate = new Date(  );
        Calendar c = Calendar.getInstance(  );

            c.set( Calendar.YEAR, ( Integer.parseInt( sDate.substring( 0, 4 ) ) ) );
            c.set( Calendar.MONTH, ( Integer.parseInt( sDate.substring( 4, 6 ) ) - 1 ) );
            c.set( Calendar.DAY_OF_MONTH, Integer.parseInt( sDate.substring( 6, 8 ) ) );

            if (sDate.length() >= 10) {
	            c.set( Calendar.HOUR_OF_DAY, Integer.parseInt( sDate.substring( 8, 10 ) ) );
	            
	            if (sDate.length() >= 12) {
	            	c.set( Calendar.MINUTE, Integer.parseInt( sDate.substring( 10, 12 ) ) );
	            
	            	if (sDate.length() >= 14) {
	            		c.set( Calendar.SECOND, Integer.parseInt( sDate.substring( 12, 14 ) ) );
	            	}
	            }
            }

        rtDate.setTime( c.getTime(  ).getTime(  ) );

        return rtDate;

    }   
    public static String getYear(String curDate){
    	String yearValue="";
    	Calendar calendar = Calendar.getInstance(  );
    	yearValue = AmbitUtility.leadingZero(Integer.toString(calendar.get( Calendar.YEAR )),4);
    	
    	return yearValue;
    }
    public static String getCurLDate(){
        Calendar calendar = Calendar.getInstance(  );
        
        String dateValue = AmbitUtility.leadingZero(Integer.toString(calendar.get( Calendar.YEAR )),4) +
        AmbitUtility.leadingZero(( Integer.toString(calendar.get( Calendar.MONTH ) + 1 )),2) +
        AmbitUtility.leadingZero(Integer.toString(calendar.get( Calendar.DAY_OF_MONTH )),2) ;
        

        return dateValue;

    }
    public static String getMonth(String curDate){
    	String monthValue="";
    	
    	monthValue = AmbitUtility.strMid(curDate,4,2);
    	
    	return monthValue;
    }
    public static String completeLMonthDay(String monthDay ){
    	String returnValue="";
    	
    	String curDate = getCurLDate();
    	String curYear = getYear(curDate);
    	String curMonth = getMonth(curDate);
    	
    	int m = Integer.parseInt(AmbitUtility.strMid(monthDay,0,2)) - Integer.parseInt(AmbitUtility.strMid(curMonth,0,2));
    	int year = Integer.parseInt(curYear);
    	if (m == 0) // month = curMonth
    	{
    		returnValue = curYear + monthDay;
    	}
    	else if (m <0) // month < curMonth
    	{
    		int n = Integer.parseInt(AmbitUtility.strMid(monthDay,0,2)) - 1;
    		int p = Integer.parseInt(AmbitUtility.strMid(curMonth,0,2)) - 12;
    		if (n == 0 && p == 0) // month=1 and curMonth = 12
    		{
    			returnValue = (year + 1) + monthDay;
    		}
    		else{ 	    			
    			returnValue = year + monthDay;
    		}
    	}
    	else{// month > curMonth
    		int e = Integer.parseInt(AmbitUtility.strMid(monthDay,0,2)) -12;
    		int f = Integer.parseInt(AmbitUtility.strMid(curMonth,0,2)) - 1; 
    		if (e ==0 && f == 0) // month=12 and curMonth=1
    		{
    			returnValue = (year - 1) + monthDay;
    		}
    		else{
    			returnValue = year + monthDay;
    		}
    	}
    	return returnValue;
    }  
    
    
    public static String formatedLdate(String latinDate) {

		String fDate = null;
		fDate =  AmbitUtility.strMid(latinDate, 0, 4) + CHR_SLASH + AmbitUtility.strMid(latinDate, 4, 2)
				+ CHR_SLASH + AmbitUtility.strMid(latinDate, 6, 2);

		return fDate;

	}
    
    public static long getDiferenceTime(String formatTime,String time1) throws ParseException{
    	long diff=0;
    	SimpleDateFormat sdf = new SimpleDateFormat(formatTime);
    	
    	Date inputTime1 = sdf.parse(time1);
    	diff = System.currentTimeMillis() - inputTime1.getTime();
    	
    	return diff;
    }
}
