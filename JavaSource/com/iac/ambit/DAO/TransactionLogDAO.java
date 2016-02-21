package com.iac.ambit.DAO;


import javax.xml.rpc.holders.StringHolder;

import com.iac.ambit.model.TransactionLog;

public interface TransactionLogDAO {
	
	public String searchBlackListTrans(String fromDate,String toDate
			, String fromTime , String toTime, String pan
			, String stan, String rrn , TransactionLog transactionLogList)  throws Exception ;
	
	public String getTransForMonitoring(String lastLogId,TransactionLog transactionLogList)
	  throws Exception;
	
	public String getDBCurrentDateTime(StringHolder currentDateTime) throws Exception;
	
	public String getMaxLogId(StringHolder maxLogId) throws Exception;

}
