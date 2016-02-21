package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.text.SimpleDateFormat;
import java.util.*;



public class DateFarsi {
	private static final String CHR_SLASH = "/";

	//private static final String CHR_DASH = "-";

	private static final String CHR_COLON = ":";

	private static String sLDate;

	private static String sFDate;

	private SimpleDateFormat dateFormatter;
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
	public DateFarsi() {

	}

	protected class DateType {
		int nYear;

		int nMonth;

		long nDay;

	}

	public String CurFdate() {
		String curFdate = null;

		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyMMdd");
		Date dt = new Date();
		String sYear = "20";

		curFdate = LDateConv(sYear + dateFormatter.format(dt));

		return curFdate;

	}

	public String LDateConv(String siDate) {
		int nLDay;
		int nLMonth;
		int nLYear;
		int n;
		int lDays;

		if (!isValidLDate(siDate)) {
			return "";
		}
		if (sLDate == siDate) {
			return sFDate;
		}
		sLDate = siDate;

		nLDay = Integer.parseInt(strMid(sLDate, 6, 2));
		nLMonth = Integer.parseInt(strMid(sLDate, 4, 2));
		nLYear = Integer.parseInt(strMid(sLDate, 0, 4));

		lDays = 0;
		for (n = 1999; n <= nLYear - 1; n++) {
			lDays = lDays + LYearDays(n);
		}

		for (n = 1; n <= nLMonth - 1; n++) {
			lDays = lDays + LMonthDays(nLYear, n);
		}

		lDays = lDays + nLDay + 285;
		sFDate = FDateAddDay("13770101", lDays);

		return sFDate;
	}

	public String NewLDateConv(String siDate) {
		int nLDay;
		int nLMonth;
		int nLYear;
		int n;
		int lDays;

		if (!isValidLDate(siDate)) {
			return "";
		}
		if (sLDate == siDate) {
			return sFDate;
		}
		sLDate = siDate;

		nLDay = Integer.parseInt(strMid(sLDate, 6, 2));
		nLMonth = Integer.parseInt(strMid(sLDate, 4, 2));
		nLYear = Integer.parseInt(strMid(sLDate, 0, 4));

		lDays = 0;
		for (n = 1952; n <= nLYear - 1; n++) {
			lDays = lDays + LYearDays(n);
		}

		for (n = 1; n <= nLMonth - 1; n++) {
			lDays = lDays + LMonthDays(nLYear, n);
		}

		lDays = lDays + nLDay + 284;
		sFDate = FDateAddDay("13300101", lDays);

		return sFDate;
	}	
	
	public String FDateConv(String siDate) {
		int nFDay;
		int nFMonth;
		int nFYear;
		int n;
		int lDays;
		if (!isValidFDate(siDate)) {
			return "";
		}
		if (sFDate == siDate) {
			return sLDate;
		}
		sFDate = siDate;

		nFDay = Integer.parseInt(strMid(siDate, 6, 2));
		nFMonth = Integer.parseInt(strMid(siDate, 4, 2));
		nFYear = Integer.parseInt(strMid(siDate, 0, 4));

		lDays = 0;
		for (n = 1378; n <= nFYear - 1; n++) {
			lDays = lDays + FYearDays(n);
		}

		for (n = 1; n <= nFMonth - 1; n++) {
			lDays = lDays + FMonthDays(nFYear, n);
		}

		lDays = lDays + nFDay;
		
		//ashrafi commented it, because that is  incorrect 87/02/28
		//sLDate = LDateAddDay("19990320", lDays);
		
		//ashrafi added 87/02/28
		sLDate = LDateAddDay("19990101", lDays + 78);
		
		return sLDate;
	}

	public String unformatLdate(String englishDate) {
		String fDate = null;
		String date[] = englishDate.split(CHR_SLASH);
		String day = date[0];
		String month = date[1];
		String year = date[2];
		fDate = year + month + day;
		return fDate;
	}
	
	
	
	
	
	
	public boolean isValidLDate(String siDate) {
		DateType dt;
		boolean isValidLdate = false;

		
		if (siDate.length() != 8) {
			return isValidLdate;
		}
		
		
		// *
		if (!isNumeric(siDate, 8)) {
			return isValidLdate;
		}

		dt = DateSplit(siDate);

		if (dt.nYear <= 0) {
			return isValidLdate;
		}
		if ((dt.nMonth <= 0) || (dt.nMonth > 12)) {
			return isValidLdate;
		}
		if (dt.nDay <= 0 || (dt.nDay > LMonthDays(dt.nYear, dt.nMonth))) {
			return isValidLdate;
		}

		isValidLdate = true;
		return isValidLdate;
	}

	public boolean isValidFDate(String siDate) {
		DateType dt;
		boolean isValidFDate = false;

		if (siDate.length() != 8) {
			return isValidFDate;
		}

		if (!isNumeric(siDate, 8)) {
			return isValidFDate;
		}

		dt = DateSplit(siDate);

		if (dt.nYear <= 0 ||dt.nYear >1410) {
			return isValidFDate;
		}
		if ((dt.nMonth <= 0) || (dt.nMonth > 12)) {
			return isValidFDate;
		}
		if (dt.nDay <= 0 || (dt.nDay > FMonthDays(dt.nYear, dt.nMonth))) {
			return isValidFDate;
		}

		isValidFDate = true;
		return isValidFDate;
	}

	private DateType DateSplit(String siDate) {
		DateType dt = new DateType();

		dt.nYear = Integer.parseInt(strLeft(siDate, 4));
		// dt.nYear = Integer.parseInt(strLeft(siDate, 4));
		dt.nMonth = Integer.parseInt(strMid(siDate, 4, 2));
		dt.nDay = Integer.parseInt(strRight(siDate, 2));
		return dt;
	}

	public static boolean isNumeric(String str, int maxLen) {
		if (str == null || (str.length() == 0 || str.length() > maxLen)) {
			return false;
		} else {
			return str.matches("[0-9]*");
		}
	}

	private static String strLeft(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len <= 0) {
			return "";
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(0, len);
		}
	}

	private static String strRight(String str, int len) {
		if (str == null) {
			return null;
		}

		if (len <= 0) {
			return "";
		}

		if (str.length() <= len) {
			return str;
		} else {
			return str.substring(str.length() - len, str.length());
		}
	}

	private static String strMid(String str, int pos) {
		if (str == null) {
			return null;
		}

		if (pos < 0 || pos >= str.length()) {
			return "";
		}
		return str.substring(pos, str.length());
	}

	private static String strMid(String str, int pos, int len) {
		if (str == null) {
			return null;
		}

		if (pos < 0 || pos >= str.length() || len <= 0) {
			return "";
		}

		if (str.length() <= (pos + len)) {
			return strMid(str, pos);
		} else {
			return str.substring(pos, pos + len);
		}
	}

	private int LMonthDays(int niYear, int niMonth) {
		int lMonthDays;
		switch (niMonth) {
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12:
			lMonthDays = 31;
			break;
		case 4:
		case 6:
		case 9:
		case 11:
			lMonthDays = 30;
			break;
		case 2:
			if (IsLLeapYear(niYear)) {
				lMonthDays = 29;
			} else {
				lMonthDays = 28;
			}
			break;
		default:
			lMonthDays = 0;

		}
		return lMonthDays;
	}

	private int FMonthDays(int niYear, int niMonth) {
		int fMonthDays = 0;

		if (niMonth == 1 || niMonth == 2 || niMonth == 3 || niMonth == 4
				|| niMonth == 5 || niMonth == 6) {
			fMonthDays = 31;
		} else if (niMonth == 7 || niMonth == 8 || niMonth == 9
				|| niMonth == 10 || niMonth == 11) {
			fMonthDays = 30;
		} else if (niMonth == 12) {

			if (IsFLeapYear(niYear)) {
				fMonthDays = 30;
			} else {
				fMonthDays = 29;
			}
		}
		return fMonthDays;

	}

	private int FYearDays(int niYear) {
		if (IsFLeapYear(niYear)) {
			return 366;
		} else {
			return 365;
		}

	}

	private boolean IsLLeapYear(int niYear) {

		if ((niYear % 4) == 0 && (niYear % 100) != 0 || (niYear % 400) == 0) {
			return true;
		} else {
			return false;
		}

	}

	private boolean IsFLeapYear(int niYear) {
		niYear = (niYear - 22) % 33;

		if (niYear == 32) {
			return false;
		} else if ((niYear % 4) == 0) {
			return true;
		} else {
			return false;
		}

	}

	private int LYearDays(int niYear) {
		if (IsLLeapYear(niYear)) {
			return 366;
		} else {
			return 365;
		}
	}

	public String FDateAddDay(String siDate, long liDays) {
		DateType dt = null;
		if (!isValidFDate(siDate)) {
			return "";
		}

		/*
		 * if (liDays < 0) { return ""; }
		 */

		dt = DateSplit(siDate);
		if (dt.nDay > 1) {
			liDays = liDays + dt.nDay - 1;
			dt.nDay = 1;
		}

		while (liDays >= FYearDays(dt.nYear)) {
			liDays = liDays - FYearDays(dt.nYear);
			dt.nYear = dt.nYear + 1;

		}

		while (liDays >= FMonthDays(dt.nYear, dt.nMonth)) {
			liDays = liDays - FMonthDays(dt.nYear, dt.nMonth);
			dt.nMonth = dt.nMonth + 1;
			if (dt.nMonth > 12) {
				dt.nMonth = 1;
				dt.nYear = dt.nYear + 1;
			}

		}
		dt.nDay = liDays + dt.nDay;

		return DateMerge(dt);

	}

	public String LDateAddDay(String siDate, long liDays) {
		DateType dt = null;
		if (!isValidLDate(siDate)) {
			return "";
		}

		if (liDays < 0) {
			return "";
		}

		dt = DateSplit(siDate);
		if (dt.nDay > 1) {
			liDays = liDays + dt.nDay - 1;
			dt.nDay = 1;
		}

		while (liDays >= LYearDays(dt.nYear)) {
			liDays = liDays - LYearDays(dt.nYear);
			dt.nYear = dt.nYear + 1;

		}

		while (liDays >= LMonthDays(dt.nYear, dt.nMonth)) {
			liDays = liDays - LMonthDays(dt.nYear, dt.nMonth);
			dt.nMonth = dt.nMonth + 1;
			if (dt.nMonth > 12) {
				dt.nMonth = 1;
				dt.nYear = dt.nYear + 1;
			}

		}
		dt.nDay = liDays + dt.nDay;

		return DateMerge(dt);

	}

	public String DateMerge(DateType dtiDate) {
		String DateMerge = "";
		String year = Integer.toString(dtiDate.nYear);
		String Month = Integer.toString(dtiDate.nMonth);
		String Day = Long.toString(dtiDate.nDay);
		switch (year.length()) {
		case 1:
			year = "000" + year;
			break;
		case 2:
			year = "00" + year;
			break;
		case 3:
			year = "0" + year;
			break;
		case 4:
			year = "" + year;
			break;
		}
		switch (Month.length()) {
		case 1:
			Month = "0" + Month;
			break;
		case 2:
			Month = "" + Month;
			break;

		}

		switch (Day.length()) {
		case 1:
			Day = "0" + Day;
			break;
		case 2:
			Day = "" + Day;
			break;

		}

		DateMerge = year + Month + Day;
		return DateMerge;
	}

	// jazimagh: 1386/05/22
	public String formatedLDateConv(Object latinDate) {

		String fDate = null;
		if (latinDate == null)
			return "";
		dateFormatter = new SimpleDateFormat(Config
				.getProperty("shortdateDecorator.dateFormat"));

		fDate = LDateConv(dateFormatter.format(latinDate).toString());
		fDate = strMid(fDate, 6, 2) + CHR_SLASH + strMid(fDate, 4, 2)
				+ CHR_SLASH + strMid(fDate, 0, 4);

		return fDate;

	}

	public String formatedLDateConv(String latinDate) {

		String fDate = null;
		if (latinDate == null)
			return "";
	
		fDate = LDateConv(latinDate);
		fDate = strMid(fDate, 6, 2) + CHR_SLASH + strMid(fDate, 4, 2)
				+ CHR_SLASH + strMid(fDate, 0, 4);

		return fDate;

	}
	
	// jazimagh: 1386/05/22
	public String formatedFdateTime(Object latinDate) {
		String fDateTime = null;

		if (latinDate == null)
			return "";
		String fdate = formatedLDateConv(latinDate);
		String time = strMid(latinDate.toString(), 10, 9);

		fDateTime = time + "	" + fdate;

		return fDateTime;

	}

	public String unformatFdate(String farsiDate) {
		String fDate = null;
		String date[] = farsiDate.split(CHR_SLASH);
		String day = date[0];
		String month = date[1];
		String year = date[2];
		fDate = year + month + day;

		return fDate;

	}
	

	
	public  String unformatFormattedFdate(String val)  {
		String date[] = val.split(CHR_SLASH);

		String year = date[0];
		String month = date[1];
		String day = date[2];

		return joinDate(year, month, day);
	}

	public String formatFdate(String farsiDate) {
		String fDate = null;
		fDate = strMid(farsiDate, 6, 2) + CHR_SLASH + strMid(farsiDate, 4, 2)
				+ CHR_SLASH + strMid(farsiDate, 0, 4);

		return fDate;

	}
	
	//ashrafi added 86/11/07
	public String revFormatedFdate(String farsiDate) {
		String fDate = null;
		fDate = strMid(farsiDate, 0, 4) + CHR_SLASH + strMid(farsiDate, 4, 2) + CHR_SLASH + strMid(farsiDate, 6, 2); 
		return fDate;

	}

	public String revFormatedLdate(String latinDate) {

		String fDate = null;
		fDate = strMid(latinDate, 6, 2) + CHR_SLASH + strMid(latinDate, 4, 2)
				+ CHR_SLASH + strMid(latinDate, 0, 4);

		return fDate;

	}

	public  String unformatRevFormattedFdate(String val)  {
		String date[] = val.split(CHR_SLASH);

		String day = date[0];
		String month = date[1];
		String year = date[2];

		return joinDate(year, month, day);
	}
	
	public boolean isValidUnFormattedLDate(String LDate) {
		try {
			return isValidLDate(unformatLdate(LDate));
		} catch (Exception ex) {
			return false;
		}
	}	
	public boolean isValidRevFormattedFDate(String fDate) {
		try {
			return isValidFDate(unformatRevFormattedFdate(fDate));
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean isValidFormattedFDate(String fDate)  {
		try {
			return isValidFDate(unformatFormattedFdate(fDate));
		} catch (Exception ex) {
			return false;
		}
	}

	private  String joinDate(String year, String month, String day)
			 {

		year = year.trim();
		month = month.trim();
		day = day.trim();

		switch (year.length()) {
		case 0:
			year = "0000";
			break;
		case 1:
			year = "130" + year;
			break;
		case 2:
			year = "13" + year;
			break;
		case 3:
			year = "1" + year;
			break;
		}

		month = strRight("00".concat(month), 2);
		day = strRight("00".concat(day), 2);

		return year + month + day;
	}
	// jazimagh: 1386/05/22
	//rooh 1387/01/22
	public String formatShortFdate(String farsiDate) {
		String fDate = null;
		fDate = strMid(farsiDate, 6, 2) + CHR_SLASH + strMid(farsiDate, 4, 2)
				+ CHR_SLASH + strMid(farsiDate, 2, 2);

		return fDate;

	}	public String formatTime(String time) {
		String fTime = null;
		fTime = strMid(time, 0, 2) + CHR_COLON + strMid(time, 2, 2)
				+ CHR_COLON + strMid(time, 4, 2);

		return fTime;

	}
	
	public String revformatDateExcel(String date) {
		String reDate = null;
		//String unformatDate=null;
		if(date==null || date=="" || date.length()!= 10 )
		{
			return "";
		}
		else
		{
		reDate =date.substring(6,10)+ CHR_SLASH +date.substring(3,5)+ CHR_SLASH +date.substring(0,2);
		return reDate;
		}	
	}
	
	public String revDate(String farsiDate) {
		String fDate = null;
		String date[] = farsiDate.split(CHR_SLASH);
		String day = date[0];
		String month = date[1];
		String year = date[2];
		fDate = year + CHR_SLASH + month + CHR_SLASH + day;
		return fDate;
	}	
}
