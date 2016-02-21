package com.iac.ambit.service;

import com.iac.ambit.model.TransactionLog;
import javax.xml.rpc.holders.StringHolder;

public interface TransactionLogService {

	public String searchBlackListTrans(String fromDate, String toDate,
			String fromTime, String toTime, String pan, String stan,
			String rrn, TransactionLog transactionLogList) throws Exception;;

	public String getTransForMonitoring(String lastLogId,
			TransactionLog transactionLogList) throws Exception;

	public String getDBCurrentDateTime(StringHolder currentDateTime)
			throws Exception;
	
	public String getMaxLogId(StringHolder maxLogId) throws Exception;
}
