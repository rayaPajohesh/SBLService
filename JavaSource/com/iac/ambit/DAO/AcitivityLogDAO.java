package com.iac.ambit.DAO;



import javax.xml.rpc.holders.ObjectHolder;


import com.iac.ambit.model.ActivityLog;


 
public interface AcitivityLogDAO {

	

	public String logActivity(ActivityLog activityLog)throws Exception;

	public String searchLogActivity(ActivityLog activityLog, ObjectHolder listActivity)throws Exception;
	
	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception;
	
	
}