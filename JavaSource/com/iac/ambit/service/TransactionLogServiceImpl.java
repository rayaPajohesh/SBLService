package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import com.iac.ambit.DAO.TransactionLogDAO;
import com.iac.ambit.model.TransactionLog;
import javax.xml.rpc.holders.StringHolder;

public class TransactionLogServiceImpl implements TransactionLogService {

	private TransactionLogDAO tranDAO;

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

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception {
		return this.tranDAO.searchBlackListTrans(fromDate, toDate, fromTime,
				toTime, pan, stan, rrn, transactionLogList);
	}

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception {
		return this.tranDAO.getTransForMonitoring(lastLogId,transactionLogList);
	}

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception {
		return this.tranDAO.getDBCurrentDateTime(currentDateTime);
	}
	
	public String getMaxLogId(StringHolder maxLogId) throws Exception {
		return this.tranDAO.getMaxLogId(maxLogId);
	}
	
	public void setTranDAO(TransactionLogDAO tranDAO) {
		this.tranDAO = tranDAO;
	}

}
