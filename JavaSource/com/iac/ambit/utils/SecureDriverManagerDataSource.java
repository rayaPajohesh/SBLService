package com.iac.ambit.utils;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.Locale;
import java.util.logging.Logger;

import org.apache.commons.dbcp.BasicDataSource;

import com.iac.ambit.service.DESEncryptionService;

import com.iac.ambit.utils.Constants;

// this class used in Application-Context
public class SecureDriverManagerDataSource extends BasicDataSource {
	// jazimagh : 1386/07/16
	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	// jazimagh : 1386/07/16

	private DESEncryptionService encryptionService;

	public SecureDriverManagerDataSource() {
		String databaseType = Config.getProperty("CURRENT_DATABASE");
		if (Constants.CURRENT_DATABASE.ORACLE.equalsIgnoreCase(databaseType))
			Locale.setDefault(Locale.ENGLISH);
	}

	public synchronized String getPassword() {
		return super.getPassword();

	}

	public synchronized void setPassword(String password) {
		encryptionService = new DESEncryptionService();

		String noneEncrypted = this.encryptionService.DecryptWithDesKey(
				Constants.INTERNAL_Key, password);

		super.setPassword(noneEncrypted);
		encryptionService = null;
	}

	public static void main(String []params){

		DESEncryptionService encryptionService = new DESEncryptionService();

		String noneEncrypted = encryptionService.DecryptWithDesKey(
				Constants.INTERNAL_Key, "F46D1E33CB92A817");

		System.out.println(noneEncrypted);
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return null;
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		return null;
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return false;
	}
}
