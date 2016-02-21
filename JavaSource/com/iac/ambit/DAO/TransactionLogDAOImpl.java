package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.TransactionLog;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;
import javax.xml.rpc.holders.StringHolder;

public class TransactionLogDAOImpl implements TransactionLogDAO {

	private JdbcTemplate template;

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
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	private final String SBL_TRANSACTION_LOG = DBConfig
			.getProperty("SBL_TRANSACTION_LOG");

	private final String TRANSACTION_TIME = DBConfig
			.getProperty("TRANSACTION_TIME");

	private final String TRANSACTION_DATE = DBConfig
			.getProperty("TRANSACTION_DATE");

	private final String STAN = DBConfig.getProperty("STAN");

	private final String RETRIEVAL_REFERENCE_NUMBER = DBConfig
			.getProperty("RETRIEVAL_REFERENCE_NUMBER");

	private final String AMOUNT = DBConfig.getProperty("AMOUNT");

	private final String PAN = DBConfig.getProperty("PAN");

	private final String TRANSACTION_CODE = DBConfig
			.getProperty("TRANSACTION_CODE");

	private final String RESPONSE_CODE = DBConfig.getProperty("RESPONSE_CODE");

	private final String TERMINAL_ID = DBConfig.getProperty("TERMINAL_ID");

	

	private final String SBL_LOG_ID = DBConfig.getProperty("SBL_LOG_ID");

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception {

		String sQuery = "";

		sQuery = "SELECT " + TRANSACTION_DATE + "," + TRANSACTION_TIME + ","
				+ PAN + "," + TRANSACTION_CODE + "," + STAN + ","
				+ RETRIEVAL_REFERENCE_NUMBER + "," + RESPONSE_CODE + ","
				+ TERMINAL_ID + "," + AMOUNT + " FROM " + SBL_TRANSACTION_LOG
				+ " WHERE " + TRANSACTION_DATE + " BETWEEN " + " '" + fromDate
				+ "' AND '" + toDate + "' ";

		if (!fromTime.equals("") || !toTime.equals("")) {

			if (toTime.equals("")) {
				toTime = AmbitUtility.addTrailing(toTime, 6, "9");
			} else {
				toTime = AmbitUtility.addTrailing(AmbitUtility.leadingZero(toTime,2), 6, "0");
			}

			fromTime = AmbitUtility.addTrailing(AmbitUtility.leadingZero(fromTime,2), 6, "0");

			sQuery = sQuery + " AND " + TRANSACTION_TIME + " BETWEEN " + " '"
					+ fromTime + "' AND '" + toTime + "' ";
		}

		if (!AmbitUtility.isEmpty(pan)) {
			sQuery = sQuery + " AND " + PAN + " = '" + pan + "' ";
		}

		if (!AmbitUtility.isEmpty(stan)) {
			sQuery = sQuery + " AND " + STAN + " = '" + stan + "' ";
		}

		if (!AmbitUtility.isEmpty(rrn)) {
			sQuery = sQuery + " AND " + RETRIEVAL_REFERENCE_NUMBER + " = '"
					+ rrn + "' ";

		}

		sQuery = sQuery + " order by " + TRANSACTION_DATE + " DESC,"+TRANSACTION_TIME + " DESC";
		
	

		List rows = template.queryForList(sQuery);
		transactionLogList.value = extractBlackListTrans(rows);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		// check if the records are more than a specified value
		if (rows.size() > Integer.parseInt(Config
				.getProperty("maxRecordsOfCssTran"))) {
			transactionLogList.value = null;
			return Constants.RESPONSE_CODE.MAX_RECORD_EXCEEDED;
		}

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception {

		String sQuery = "";

		sQuery = "SELECT " + SBL_LOG_ID + "," + TRANSACTION_DATE + ","
				+ TRANSACTION_TIME + "," + PAN + "," + TRANSACTION_CODE + ","
				+ STAN + "," + RETRIEVAL_REFERENCE_NUMBER + "," + RESPONSE_CODE
				+ "," + TERMINAL_ID + "," + AMOUNT + " FROM "
				+ SBL_TRANSACTION_LOG + " WHERE  " + SBL_LOG_ID + " > "
				+ lastLogId;

		sQuery = sQuery + " order by " + SBL_LOG_ID + " desc";

		List rows = template.queryForList(sQuery);
		transactionLogList.value = extractTransForMonitoring(rows);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception {

		String sQuery = "";
		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "SELECT TO_CHAR(CURRENT_DATE ,'YYYYMMDDHH24MISS') AS CURRENTDATETIME FROM DUAL";
		} else {
			sQuery = "SELECT  CONVERT(varchar(8), GETDATE(), 112) + REPLACE(CONVERT(varchar(10), GETDATE(), 108), ':', '') AS CURRENTDATETIME";
		}
		List rows = template.queryForList(sQuery);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		Map map;
		map = (Map) rows.get(0);
		currentDateTime.value = (AmbitUtility.nvl((String) map
				.get("CURRENTDATETIME"))).trim();

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getMaxLogId(StringHolder maxLogId) throws Exception {

		String sQuery = "";
		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "SELECT NVL(MAX(SBL_LOG_ID),0) AS MAX_LOG_ID FROM SBL_TRANSACTION_LOG";			
			/* sQuery = "SELECT MAX(SBL_LOG_ID) AS MAX_LOG_ID FROM
			 SBL_TRANSACTION_LOG WHERE SBL_RECEIVE_TIME > (SELECT
			 TO_CHAR(CURRENT_DATE ,'YYYYMMDD') FROM DUAL)";*/

		} else {
			sQuery = "SELECT ISNULL(MAX(SBL_LOG_ID),0) AS MAX_LOG_ID FROM SBL_TRANSACTION_LOG";
			/* sQuery = "SELECT MAX(SBL_LOG_ID) AS MAX_LOG_ID FROM
			 SBL_TRANSACTION_LOG WHERE SBL_RECEIVE_TIME > (SELECT
			 CONVERT(varchar(8), GETDATE(), 112))";*/
		}
		List rows = template.queryForList(sQuery);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		Map map;
		map = (Map) rows.get(0);
		maxLogId.value = AmbitUtility.nvl(map.get("MAX_LOG_ID").toString())
				.trim();
		/*
		 * maxLogId.value = (AmbitUtility.nvl((String) map.get("MAX_LOG_ID")))
		 * .trim();
		 */

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	private TransactionLog[] extractBlackListTrans(List rows) {

		TransactionLog[] blackListTrans = new TransactionLog[rows.size()];
		Map map;

		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			TransactionLog Tran = new TransactionLog();

			Tran.setTransactionDate((AmbitUtility.nvl((String) map
					.get(TRANSACTION_DATE))).trim());

			Tran.setTransactionTime((AmbitUtility.nvl((String) map
					.get(TRANSACTION_TIME))).trim());

			Tran.setPan((AmbitUtility.nvl((String) map.get(PAN))).trim());

			Tran.setTransactionCode((AmbitUtility.nvl((String) map
					.get(TRANSACTION_CODE))).trim());

			Tran.setStan((AmbitUtility.nvl((String) map.get(STAN))).trim());

			Tran.setRetrievalReferenceNumber((AmbitUtility.nvl((String) map
					.get(RETRIEVAL_REFERENCE_NUMBER))).trim());

			Tran.setResponseCode((AmbitUtility.nvl((String) map
					.get(RESPONSE_CODE))).trim());

			Tran
					.setTerminalId((AmbitUtility.nvl((String) map
							.get(TERMINAL_ID))).trim());

			Tran.setAmount((AmbitUtility.nvl((String) map.get(AMOUNT))).trim());

			blackListTrans[i] = Tran;
		}
		return blackListTrans;
	}

	private TransactionLog[] extractTransForMonitoring(List rows) {

		TransactionLog[] monitorTrans = new TransactionLog[rows.size()];
		Map map;

		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			TransactionLog Tran = new TransactionLog();

			Tran.setLogId(AmbitUtility.nvl(map.get(
					SBL_LOG_ID).toString()));
			
			Tran.setTransactionDate((AmbitUtility.nvl((String) map
					.get(TRANSACTION_DATE))).trim());

			Tran.setTransactionTime((AmbitUtility.nvl((String) map
					.get(TRANSACTION_TIME))).trim());

			Tran.setPan((AmbitUtility.nvl((String) map.get(PAN))).trim());

			Tran.setTransactionCode((AmbitUtility.nvl((String) map
					.get(TRANSACTION_CODE))).trim());

			Tran.setStan((AmbitUtility.nvl((String) map.get(STAN))).trim());

			Tran.setRetrievalReferenceNumber((AmbitUtility.nvl((String) map
					.get(RETRIEVAL_REFERENCE_NUMBER))).trim());

			Tran.setResponseCode((AmbitUtility.nvl((String) map
					.get(RESPONSE_CODE))).trim());

			Tran
					.setTerminalId((AmbitUtility.nvl((String) map
							.get(TERMINAL_ID))).trim());

			Tran.setAmount((AmbitUtility.nvl((String) map.get(AMOUNT))).trim());

			monitorTrans[i] = Tran;
		}
		return monitorTrans;
	}

}
