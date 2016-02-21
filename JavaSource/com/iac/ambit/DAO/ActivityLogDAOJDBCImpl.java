package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.ObjectHolder;

import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.ActivityLog;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.DateUtils;
import com.iac.ambit.utils.Constants;

public class ActivityLogDAOJDBCImpl implements AcitivityLogDAO {

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

	// Database table & columns
	final static String SBL_USER_AUDIT_LOG = DBConfig
			.getProperty("SBL_USER_AUDIT_LOG");

	final static String SBL_PERMISSIONS = DBConfig
			.getProperty("SBL_PERMISSIONS");

	final static String PERMISSION_ID = DBConfig.getProperty("PERMISSION_ID");

	final static String PERMISSION_TITLE = DBConfig
			.getProperty("PERMISSION_TITLE");

	final static String PERMISSION_TITLE_FA = DBConfig
			.getProperty("PERMISSION_TITLE_FA");

	final static String USER_ID = DBConfig.getProperty("USER_ID");

	final static String USER_AUDIT_DATE = DBConfig
			.getProperty("USER_AUDIT_DATE");

	final static String USER_AUDIT_TIME = DBConfig
			.getProperty("USER_AUDIT_TIME");

	final static String USER_AUDIT_DESCRIPTION = DBConfig
			.getProperty("USER_AUDIT_DESCRIPTION");

	final static String USER_AUDIT_DESCRIPTION_FA = DBConfig
			.getProperty("USER_AUDIT_DESCRIPTION_FA");
	private static final String PERMISSION_IS_ACTIVITY = DBConfig
	.getProperty("PERMISSION_IS_ACTIVITY");

	// jazimagh : 1386/07/16
	private JdbcTemplate template;

	// jazimagh : 1386/05/29
	/* add Activity_Type_Id in each query */
	// jazimagh : 1386/05/29
	public String logActivity(ActivityLog activity) throws Exception {
		int result = 0;
		
		
		String activityName = activity.getActivityTypeName();
		String activityDescription = activity.getActivityDescription();
		// String activityDescriptionFa = activity.getActivityDescriptionFa();
		String customerRNO = activity.getUserId();

		int activityTypeID = getActivityID(activityName);
		String currentDateTime = DateUtils.datetimeToString(new Date(),
				DateUtils.UNISON_DATE_FORMAT_FARSI);

		String date = currentDateTime.substring(0, 8);
		String time = currentDateTime.substring(8, 14);

		String logQuery = "INSERT INTO " + SBL_USER_AUDIT_LOG + " ( "
				+ PERMISSION_ID + "," + USER_ID + "," + USER_AUDIT_DATE + ","
				+ USER_AUDIT_TIME + "," + USER_AUDIT_DESCRIPTION_FA // + "," +
				// USER_AUDIT_DESCRIPTION_FA
				+ " ) VALUES (?,?,?,?,?)";

		result = this.template.update(logQuery, new Object[] {
				new Integer(activityTypeID), customerRNO, date, time,
				activityDescription });
		
		if(result ==1)
			return (Constants.RESPONSE_CODE.SUCCEED);
		else
		return (Constants.RESPONSE_CODE.INTERNAL_ERROR);
	}

	private int getActivityID(String activityName) throws Exception {
		String selectActivityName = "SELECT " + PERMISSION_ID + " FROM "
				+ SBL_PERMISSIONS + " WHERE " + PERMISSION_TITLE + " = '"
				+ activityName + "'";

		return this.template.queryForInt(selectActivityName);

	}

	public String searchLogActivity(ActivityLog activityLog,
			ObjectHolder listActivity) throws Exception {
		String searchQuery = "SELECT DISTINCT " + SBL_USER_AUDIT_LOG + "."
				+ PERMISSION_ID + "," + SBL_USER_AUDIT_LOG + "." + USER_ID
				+ "," + SBL_PERMISSIONS + "." + PERMISSION_TITLE + ","
				+ SBL_PERMISSIONS + "." + PERMISSION_TITLE_FA + ","
				+ SBL_USER_AUDIT_LOG + "." + USER_AUDIT_DESCRIPTION + ","
				+ SBL_USER_AUDIT_LOG + "." + USER_AUDIT_DESCRIPTION_FA + ","
				+ SBL_USER_AUDIT_LOG + "." + USER_AUDIT_DATE + ","
				+ SBL_USER_AUDIT_LOG + "." + USER_AUDIT_TIME + " FROM "
				+ SBL_USER_AUDIT_LOG + "," + SBL_PERMISSIONS + " WHERE "
				+ SBL_USER_AUDIT_LOG + "." + PERMISSION_ID + " = "
				+ SBL_PERMISSIONS + "." + PERMISSION_ID + " AND "
				+ SBL_USER_AUDIT_LOG + "." + USER_ID + " = '"
				+ activityLog.getUserId() + "'";
		if (!AmbitUtility.isEmpty(String.valueOf(activityLog
				.getActivityTypeId())))
			searchQuery = searchQuery + " AND " + SBL_USER_AUDIT_LOG + "."
					+ PERMISSION_ID + " = " + activityLog.getActivityTypeId();
		if (!AmbitUtility.isEmpty(activityLog.getActivityFromDate())
				&& !AmbitUtility.isEmpty(activityLog.getActivityToDate()))
			searchQuery = searchQuery + " AND " + SBL_USER_AUDIT_LOG + "."
					+ USER_AUDIT_DATE + " BETWEEN "
					+ "'" + activityLog.getActivityFromDate() + "'" + " AND "
					+ "'" + activityLog.getActivityToDate()+ "'";
		searchQuery = searchQuery + " Order by " + SBL_PERMISSIONS + "."
				+ PERMISSION_TITLE + "," + SBL_USER_AUDIT_LOG + "."
				+ USER_AUDIT_DATE;
		List rows = template.queryForList(searchQuery);

		if (rows.size() == 0)
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		listActivity.value = extractActivityLog(rows);
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	private List extractActivityLog(List rows) {
		List logs = new ArrayList();
		Map map;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			ActivityLog activityLog = new ActivityLog();
			activityLog.setActivityTypeId((map.get(PERMISSION_ID).toString()));
			activityLog.setUserId(map.get(USER_ID).toString());
			activityLog.setActivityTypeName(AmbitUtility.nvl((String) map
					.get(PERMISSION_TITLE)));
			activityLog.setActivityTypeNameFa(AmbitUtility.nvl((String) map
					.get(PERMISSION_TITLE_FA)));
			activityLog.setActivityDescription((String) map
					.get(USER_AUDIT_DESCRIPTION));
			activityLog.setActivityDescriptionFa(AmbitUtility.nvl((String) map
					.get(USER_AUDIT_DESCRIPTION_FA)));
			activityLog.setActivityDate(map.get(USER_AUDIT_DATE).toString());
			activityLog.setActivityTime(map.get(USER_AUDIT_TIME).toString());

			logs.add(activityLog);
		}
		return logs;
	}

	/** ******************Aggregate Report Section*************************** */

	private String getAggregateQuery(String whereClause) {
		return "SELECT " + PERMISSION_TITLE_FA + "," + PERMISSION_TITLE
				+ ", (Select COUNT(*) From " + SBL_USER_AUDIT_LOG
				+ " b Where a." + PERMISSION_ID + "=b." + PERMISSION_ID + " " 
				+ whereClause + " ) AS SUBTOTAL FROM " + SBL_PERMISSIONS 
				+ " a Where " +   PERMISSION_IS_ACTIVITY + " ='"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' "
				+ "  GROUP BY " +  PERMISSION_ID  + "," + PERMISSION_TITLE_FA + "," + PERMISSION_TITLE     
				+ " Order by " + PERMISSION_ID;
	}

	private List extractAggregateActivityLogs(List rows) {
		List logs = new ArrayList();
		Map map;

		int subtotal = 0;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			ActivityLog activityLog = new ActivityLog();

			activityLog.setActivityTypeName((String) map.get(PERMISSION_TITLE));
			activityLog.setActivityTypeNameFa((String) map
					.get(PERMISSION_TITLE_FA));
			subtotal = (Integer.parseInt(map.get("SUBTOTAL").toString()));

			activityLog.setAggregate(String.valueOf(subtotal));

			logs.add(activityLog);
		}
		return logs;
	}

	public String searchAggregateAllActivities(ActivityLog activityLog,
			ObjectHolder aggregateActivityList) throws Exception {
		String searchQuery = " AND " + USER_ID + " = '"
				+ activityLog.getUserId() + "'";
		if (!AmbitUtility.isEmpty(activityLog.getActivityFromDate())
				&& !AmbitUtility.isEmpty(activityLog.getActivityToDate())) {
			searchQuery = searchQuery + " AND " + USER_AUDIT_DATE + " BETWEEN "
			+ "'" + activityLog.getActivityFromDate() + "'" + " AND "
			+ "'" + activityLog.getActivityToDate() + "'";
		}
		searchQuery = getAggregateQuery(searchQuery);
		List rows = this.template.queryForList(searchQuery);
		if (rows.size() == 0)
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		aggregateActivityList.value = extractAggregateActivityLogs(rows);
		return Constants.RESPONSE_CODE.SUCCEED;
	}

}
