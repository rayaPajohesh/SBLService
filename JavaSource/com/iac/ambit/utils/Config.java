package com.iac.ambit.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;



/**
 * This Class configures settings for DB.
 * 
 * By: Haris Mirza
 */
public class Config {
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	/**
	 * Fetches the variable values
	 * 
	 * @param name :
	 *            name of the varible, whose value wants to be fetched.
	 * 
	 * @return the value of the specified variable
	 */
	private static Properties defaultProps;

	public static synchronized void init(String fileName) {
		try {
			defaultProps = new Properties();
			BufferedInputStream in = new BufferedInputStream(
					new FileInputStream(fileName));
			defaultProps.load(in);
			in.close();

		} catch (FileNotFoundException e) {
			new AmbitException("Config", "init", e);

		} catch (IOException e) {
			new AmbitException("Config", "init", e);

		}

	}

	public static String getProperty(String name) {
		String str = "";
		str = defaultProps.getProperty(name);

		if ((str != null) && !str.equalsIgnoreCase("")) {
			return str.trim();

		}

		return str;
	}

	public static int getPropertyAsInt(String name) {
		String str = "";
		str = defaultProps.getProperty(name);

		if ((str != null) && !str.equalsIgnoreCase("")) {
			return Integer.parseInt(str.trim());
		}

		throw (new AmbitException("Config", "getPropertyAsInt()",
				"int not found for the specified property name in properties file"));
	}

	public static double getPropertyAsDouble(String name) {
		String str = "";
		str = defaultProps.getProperty(name);

		if ((str != null) && !str.equalsIgnoreCase("")) {
			return Double.parseDouble(str.trim());
		}

		throw (new AmbitException("Config", "getPropertyAsDouble()",
				"double not found for the specified property name in properties file"));
	}

	public static boolean getPropertyAsBoolean(String name) {
		String str = "";
		str = defaultProps.getProperty(name);

		return (str.trim().toUpperCase().equalsIgnoreCase("TRUE") ? true
				: false);
	}

	public static void destroy() {
		defaultProps = null;

	}

	

	

	


	

	public static void setProperty(String propertyName, String propertyValue) {
		defaultProps.setProperty(propertyName, propertyValue);
	}

}
