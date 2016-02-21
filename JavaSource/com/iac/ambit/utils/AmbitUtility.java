package com.iac.ambit.utils;

import java.io.File;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import java.text.DecimalFormat;

import java.lang.reflect.Array;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;

import org.apache.commons.lang.StringUtils;

import org.apache.commons.mail.EmailException;

import org.apache.commons.mail.EmailAttachment;

import org.apache.commons.mail.HtmlEmail;

import com.iac.ambit.utils.Config;

public class AmbitUtility {
	public static String FARSI_CHARS = ":«¬»Å ÀÃçÕŒœ–—“é”‘’÷ÿŸ⁄€›ﬁﬂê·„‰ÊƒÂÌ∆¡…√";
	public static String NUMBERS = "0123456789";

	public static String FARSI_NOT_JOIN_CHARS = "«¬œ–—“éÊ";

	public static String Alpha_Numeric_Farsi="[«¬»Å ÀÃçÕŒœ–—“é”‘’÷ÿŸ⁄€›ﬁòê·„‰ÊƒÂÌ∆¡…√ a-zA-Z0-9]*";

	// jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	// jazimagh : 1386/07/16
	public AmbitUtility() {

	}

	/**
	 * input: posArray: array of indices totalPos: Bitmap length
	 * 
	 * output: return: bitmap (eg: 0010101100010)
	 */
	public static String createBitmap(Vector posArray, int totalPos) {
		if ((posArray == null) || (posArray.size() == 0) || (totalPos <= 0)) {
			return "";

		}

		StringBuffer sb = new StringBuffer("");

		for (int i = 0; i < totalPos; i++) {
			if (isKeyInVector(posArray, i)) {
				sb.append("1");

			} else {
				sb.append("0");

			}

		}

		return sb.toString();

	}
	public static String leadingZero(String data, int len) {
		// jazimagh : 1386/08/15
		String format = "";
		for (int i = 0; i < len; i++)
			format = format + "0";
		DecimalFormat myFormat = new DecimalFormat(format);
		if (data.length() == 0)
			return format;
		return myFormat.format(new Double(data));

	}

	public static String completeChequeNumber(String chequeNumber) {
		StringBuffer completeChqueNumber = new StringBuffer();

		if (chequeNumber.length() < 11) {
			int length = 11 - chequeNumber.length();

			for (int i = 0; i < length; i++) {
				completeChqueNumber.append("0");

			}

		}

		completeChqueNumber.append(chequeNumber);

		return completeChqueNumber.toString();

	}

	public static boolean isKeyInVector(Vector v, int key) {
		for (int i = 0; i < v.size(); i++)
			if (((Integer) v.get(i)).intValue() == key) {
				return true;

			}

		return false;

	}



	/**
	 * This function creates a new thread to send the email.
	 * 
	 * @param throwable
	 * @param exceptionType
	 */
	private static void sendAlert(String message, String emailAddresses,
			String subject) {
		// Sends Email in a new thread
		Thread thread = new Thread(new MailSender(message, emailAddresses,
				subject));
		thread.start();

	}

	private static void sendMultiPartEMailThread(String message,
			String emailAddress, String subject, EmailAttachment attachment,
			boolean isMultiPartMail) {
		MailSender mailSender = new MailSender(message, emailAddress, subject,
				attachment, isMultiPartMail);
		Thread thread = new Thread(mailSender);
		thread.start();
	}

	/**
	 * This function sends an email to customer notifying him of the
	 * registration
	 */
	public static void alertCustomerOfRegistration(String name,
			String emailAddress) {
		if (Config.getProperty("email.customer.registrataion.send")
				.equalsIgnoreCase("true")) {
			String subject = Config
					.getProperty("email.customer.registrataion.subject");
			String message = "Dear "
					+ name
					+ ",\n"
					+ Config
							.getProperty("email.customer.registrataion.message");

			sendAlert(message, emailAddress, subject);
		}

	}

	// jazimagh : 1386/07/15 change input parameters
	public static void sendMultiPartEmail(String name, String message,
			String subject, String emailaddress, EmailAttachment attachment) {

		sendMultiPartEMailThread(message, emailaddress, subject, attachment,
				true);
	}

	/**
	 * This function sends an email to customer notifying him of the
	 * registration
	 */
	

	public static void alertCustomerOfChangePIN(String name,
			String emailAddress, String password) {
		String subject = Config.getProperty("email.customer.pinChange.subject");
		String url = Config.getProperty("ChangePIN.URL") + password;

		String message = "Dear "
				+ ((name == null || name.equals("null")) ? "Customer" : name)
				+ ",\n"
				+ Config.getProperty("email.customer.pinChange.message") + "\n"
				+ url;

		sendAlert(message, emailAddress, subject);
	}


	
	

	
///
	

	

	
	//***
	
	//
	
	
	

	private static class MailSender implements Runnable {
		private String emailAddresses;

		private String message;

		private String subject;

		private EmailAttachment attachment;

		private boolean isMultiPartMail = false;

		//private MultiPartEmail multiPartEmail;

		protected MailSender(String message, String emailAddresses,
				String subject) {
			this.message = message;
			this.emailAddresses = emailAddresses;
			this.subject = subject;

		}

		protected MailSender(String message, String emailAddress,
				String subject, EmailAttachment attachment,
				boolean isMultiPartMail) {
			this.message = message;
			this.emailAddresses = emailAddress;
			this.subject = subject;
			this.attachment = attachment;
			this.isMultiPartMail = isMultiPartMail;

		}

		public void run() {

			try {
				
				HtmlEmail mail = new HtmlEmail();
				mail.setCharset("UTF-8");
				if (isMultiPartMail) {
					mail.attach(attachment);
				}

				mail.setHostName(Config.getProperty("smtp.server"));
				mail.setSmtpPort(Integer.parseInt(Config
						.getProperty("smtp.server.port")));

				if (Config.getProperty("smtp.server.authenticate")
						.equalsIgnoreCase("true")) {
					mail.setAuthentication(Config
							.getProperty("smtp.server.username"), Config
							.getProperty("smtp.server.password"));

				}

				mail.setBounceAddress(Config
						.getProperty("smtp.server.username"));
				String[] emails = StringUtils.split(this.emailAddresses, ",");

				for (int i = 0; i < emails.length; i++) {
					mail.addTo(emails[i]);

				}

				mail.setFrom(Config.getProperty("ambit.email.address"), Config
						.getProperty("ambit.email.name"));
				mail.setSubject(this.subject);
				mail.setMsg(this.message);
				mail.send();

				
			} catch (EmailException e) {
				Tracer.traceOut(Tracer.Error_Level, "AmbitUtility",
						"Send Mail", e.toString());
				if (isMultiPartMail) {
					// this.multiPartEmail = (MultiPartEmail) mail;

					String path = attachment.getPath();
					File file = new File(path);
					System.out.println("File : " + path + " deleted ===>> "
							+ file.delete());

				}
				Tracer.exception("MailSender", "run", e);

			}

		}

	}

	/**
	 * 
	 * This Function helps to create a Selection list for Stop Cheque Reasons.
	 * 
	 * 
	 * @return reasonList
	 * @param configProperty
	 */
	

	

	// jazimagh : 1386/05/30
	public static String addAttributes(String target, Object[] attributes) {
		String result = "";

		for (int i = 0; i < attributes.length; i++) {
			result = target.substring(0, target.indexOf("{"));
			result += (String) attributes[i];
			result += target
					.substring(target.indexOf("}") + 1, target.length());
			target = result;
		}

		return target;
	}

	public static String createPasswordFormat(String[] sArr, Vector rndPos) {
		if ((sArr == null) || (rndPos == null) || (rndPos.size() == 0)) {
			return "";

		}

		Collections.sort((List) rndPos);

		StringBuffer sb = new StringBuffer("");
		boolean flag = false;
		int count = 0;

		for (int i = (sArr.length - 1); i >= 0; i--) {
			if ((sArr[i] != null) && !sArr[i].equals("")) {
				flag = true;
				sb.insert(0, sArr[i]);

			} else {
				if (flag) {
					sb.insert(0, "@");

				} else {
					count++;

				}

			}

		}

		int tempSZ = rndPos.size();

		if (count != 0) {
			for (int j = (rndPos.size() - 1); j >= (tempSZ - count); j--) {
				rndPos.remove(j);

			}

		}

		// sb.insert( 0, ( createBitmap( rndPos, 15 ) +
		// IntegrationUtils.getDelimiter( ) ) );

		return sb.toString();

	}

	// jazimagh : 1385/05/30
	



	
	


	public static boolean isEmpty(Object str) {

		if (str == null || ((String) str).length() == 0) {
			return true;

		}
		if ((String) str.toString().trim() == null) {

			return true;

		}

		else {

			return false;
		}

	}

	public static String nvl(String val) {
		if (val == null) {
			return "";
		} else {
			return val;
		}
	}

	public static String charVal(String str) {
		if (str == null) {
			return "";
		}

		String val = trimConvert(str);
		// val = val.replaceAll("[^" + FARSI_CHARS + "]", " ");
		// val = removeWithSpaces(val);
		// val = ReplaceSpecialCharacter(val);
		return val;
	}

	public static String charVal2(String str) {
		if (str == null) {
			return "";
		}

		String val = trimConvert(str);
		val = removeWithSpaces(val);
		return val;
	}

	public static String charVal3(String str) {
		String val = removeWithSpaces(str);

		if (!(val.matches("[1234567890]*"))) {
			val = "";
		}
		return val;
	}

	public static String removeWithSpaces(String str) {
		if (str == null) {
			return "";
		}

		String val = str.trim();
		int len;

		do {
			len = val.length();
			val = val.replaceAll("\\s\\s", " ");
		} while (len > val.length());

		val = val.replaceAll("\\s", "ù"); // "" is not empty, it contains
		// nulSpace
		for (int i = 0; i < FARSI_NOT_JOIN_CHARS.length(); i++) {
			String ch = strMid(FARSI_NOT_JOIN_CHARS, i, 1);
			val = val.replaceAll(ch + "ù", ch);
		}
		return val;
	}
	public static String trimConvert(String str) {
		try {
			str = str.trim();

			String c = "Ì";
			String c1 = "" + (char) 1740;
			str = str.replaceAll("&#1740;", c);
			str = str.replaceAll(c1, c);
			return str;
		} catch (Exception e) {
			return str;
		}

	}
	public static String strMid(String str, int pos) {
		if (str == null) {
			return null;
		}

		if (pos < 0 || pos >= str.length()) {
			return "";
		}
		return str.substring(pos, str.length());
	}

	public static String strMid(String str, int pos, int len) {
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



	public static String formatRecordNumber(int iRecordNumber, int iRequiredSize) {
		String strRecordNumber = new Integer(iRecordNumber).toString();

		while (strRecordNumber.length() < iRequiredSize) {
			strRecordNumber = "0" + strRecordNumber;

		}

		return strRecordNumber;

	}

	/**
	 * Returns the current time
	 */
	public static String getCurrentTime() {
		Calendar calendar = Calendar.getInstance();

		String timeValue = ""
				+ formatRecordNumber(calendar.get(Calendar.HOUR_OF_DAY), 2)
				+ formatRecordNumber(calendar.get(Calendar.MINUTE), 2)
				+ formatRecordNumber(calendar.get(Calendar.SECOND), 2);

		return timeValue;

	}

	/**
	 * Returns the current Date
	 */
	public static String getCurrentDate() {
		Calendar calendar = Calendar.getInstance();
		String dateValue = calendar.get(Calendar.YEAR) + ""
				+ formatRecordNumber((calendar.get(Calendar.MONTH) + 1), 2)
				+ ""
				+ formatRecordNumber(calendar.get(Calendar.DAY_OF_MONTH), 2);

		return dateValue;

	}

	public String getHostAddress() {
		String hostAddress = "";
		try {

			hostAddress = InetAddress.getLocalHost().getHostAddress();

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			//nothing
			
		}
		return hostAddress;
	}


	public static String addTrailing(String data, int len, String trailingChar) {
		int dataLength = data.length();

		for (int i = dataLength; i < len; i++)
			data = data + trailingChar;
		return data;
	}
	
	/*public static boolean validateMethodParameters(Class<?> yourClass,
			String methodName, final Hashtable params) throws Exception {

		final Class<?>[] parameterTypes = getMethod(yourClass, methodName)
				.getParameterTypes();
		if (params.size() != parameterTypes.length) {
			return false;
		}
		Iterator itr = params.values().iterator();

		MethodParameterInfo object = null;
		Object obj;
		
		 
		
		while (itr.hasNext()) {
			object = (MethodParameterInfo) itr.next();
			final Class<?> paramType1 = object.getFieldType();

			if (paramType1.isArray()) {
				obj = object.getFieldValue();
				Class<?> c2 = Array.get(obj, 0).getClass();
				
				if (!isCompatible(object, c2)) {
					return false;

				}
			} 

			else  {
				final Class<?> paramType2 = object.getFieldType();
				if (!isCompatible(object, paramType2))
				return false;
			}
		}

		return true;
	}

	private static boolean isCompatible(MethodParameterInfo param,
			final Class<?> paramType) throws Exception {

		Object object = param.getFieldValue();
		String inOutParam = param.getInOutParam();
		
		if (inOutParam.equalsIgnoreCase(Constants.PARAMETER_MODE.INPUT)) {
			if (object == null) {

				return false;
			}
			if (object.getClass().isArray() ) {
				for (int i = 0; i < Array.getLength(object); i++) {
					if (!paramType.isInstance(Array.get(object, i))) {
						return false;
					}
				}
			} else if (!paramType.isInstance(object)) {
				return false;
			}
			if (paramType.isPrimitive()) {
				return isWrapperTypeOf(object.getClass(), paramType);
			}

			if (object.getClass().isArray()) {
				for (int i = 0; i < Array.getLength(object); i++) {
					if (Array.get(object, i).toString().length() > Integer
							.parseInt(param.getFieldLength().toString())) {
						return false;
					}
				}
			}

			else if (object.toString().length() > Integer
					.parseInt(param.getFieldLength().toString())) {
				return false;
			}
		}
		return true;
	}

	private static boolean isWrapperTypeOf(final Class<?> candidate,
			final Class<?> primitiveType) throws Exception {
		try {
			return !candidate.isPrimitive()
					&& candidate.getDeclaredField("TYPE").get(null)
							.equals(primitiveType);
		} catch (final NoSuchFieldException e) {
			return false;
		} catch (final Exception e) {
			throw e;
		}
	}

	public static class MethodParameterInfo {

		private Object fieldValue;
		private Object fieldLength;
		private String inoutParam;
		private Class<?> fieldType;

		public MethodParameterInfo(Object fieldValue, Object fieldLength,
				String inoutParam, Class<?> fieldType) {
			this.fieldValue = fieldValue;
			this.fieldLength = fieldLength;
			this.inoutParam = inoutParam;
			this.fieldType = fieldType;
		}

		Object getFieldValue() {
			return this.fieldValue;

		}

		void setFieldValue(Object val) {
			this.fieldValue = val;

		}

		Object getFieldLength() {
			return this.fieldLength;

		}

		void setFieldLength(Object val) {
			this.fieldLength = val;

		}

		String getInOutParam() {
			return this.inoutParam;
		}

		void setInOutParam(String val) {
			this.inoutParam = val;

		}

		Class<?> getFieldType() {
			return this.fieldType;
		}

		void setFieldType(Class<?> val) {
			this.fieldType = val;

		}
	}

	private static Method getMethod(Class<?> yourClass, String methodName) {
		Method method = null;
		Method[] methodList = yourClass.getDeclaredMethods();
		for (int i = 0; i < methodList.length; i++) {
			if (methodList[i].getName().equalsIgnoreCase(methodName)) {
				method = methodList[i];
				break;
			}
		}
		return method;
	}*/

	public static boolean isAlphaFarsiNumeric(String str, int maxLen,int minLength) {
		if (str == null || (str.length() == 0 || str.length() > maxLen || str.length() < minLength)) {
			return false;
		} else {
			return str.matches(Alpha_Numeric_Farsi);
		}
	}

	public static boolean isAlphanumeric(String str, int maxLen,int minLength) {
		if (str == null || (str.length() == 0 || str.length() > maxLen || str.length() < minLength)) {
			return false;
		} else {
			return str.matches("[a-zA-Z0-9]*");
		}
	}
	
	public static boolean isNumeric(String str, int maxLen) {
		if (str == null || (str.length() == 0 || str.length() > maxLen)) {
			return false;
		} else {
			return str.matches("[0-9]*");
		}
	}
}
