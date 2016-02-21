package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Constants {

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	private Constants() {

	}

	public static String INTERNAL_Key = "0E329232EA6D0D73";

	public static String equalSign = "=";

	public static String pipeSign = "|";

	public static String semicolonSign = ";";

	public static String commaSign = ",";

	public static String colonSign = ":";

	public static String slashSign = "/";

	public static String caretSign = "^";

	public static String dashSign = "-";

	public static String underLineSign = "_";

	public static String hashSign = "#";

	public static String dollarSign = "$";

	public static String starSign = "*";
	
	

	public class CODE_ACTIVE_FLAG {

		public static final String ACTIVE = "1";

		public static final String INACTIVE = "2";

	}

	public static class CURRENT_DATABASE {
		public static final String ORACLE = "ORACLE";

		public static final String SQLSERVER = "SQLSERVER";
	}

	public static class RESPONSE_CODE {
		public static String SUCCEED = "00";

		public static String ERROR_IN_PARAMETER = "01";

		public static String DUPLICATE_RECORD = "02";

		public static String INTERNAL_ERROR = "03";

		public static String RECORD_NOT_FOUND = "04";

		public static String MAX_RECORD_EXCEEDED = "05";
	}

	public static class MenuItemFixed {
		public static final String FIRST_PAGE = "3";
		// other items are 998,997,...
	}
	
	public static class ACCOUNT_MODEID {
		public static final String CUSTOMER = "1";
	}
	
	public static class IS_DEFAULT_ACCT {
		public static final String DEFAULT_ACCT = "1";
	}
	public static class PARAMETER_MODE {
		 public static final String INPUT = "IN";
		 public static final String OUTPUT = "OUT";
		 public static final String INPUT_OUTPUT = "INOUT";
	 }
}
