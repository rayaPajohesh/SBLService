package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.ObjectHolder;
import javax.xml.rpc.holders.StringHolder;

import org.apache.axis.utils.JavaUtils;
import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.Customer;
import com.iac.ambit.model.Group;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.DateUtils;

public class CustomerDAOImpl implements CustomerDAO {

	private JdbcTemplate template;

	private static final String SBL_PERMISSIONS = DBConfig
			.getProperty("SBL_PERMISSIONS");

	private static final String PERMISSION_ACTIVE = DBConfig
			.getProperty("PERMISSION_ACTIVE");

	private static final String PERMISSION_ID = DBConfig
			.getProperty("PERMISSION_ID");

	private static final String PERMISSION_ACTION_TYPE_ID = DBConfig
			.getProperty("PERMISSION_ACTION_TYPE_ID");

	private static final String SBL_GROUPS_PERMISSIONS = DBConfig
			.getProperty("SBL_GROUPS_PERMISSIONS");

	private static final String PERMISSION_PARENT_ID = DBConfig
			.getProperty("PERMISSION_PARENT_ID");

	private final String USER_ID = DBConfig.getProperty("USER_ID");

	private final String LAST_LOGIN_ATTEMPT_TIME = DBConfig
			.getProperty("LAST_LOGIN_ATTEMPT_TIME");

	private final String IS_SUCCESSFULL = DBConfig
			.getProperty("IS_SUCCESSFULL");

	private final String SBL_USER_LOGIN_INFO = DBConfig
			.getProperty("SBL_USER_LOGIN_INFO");

	private final String USER_NAME = DBConfig.getProperty("USER_NAME");

	private final String USER_NAME_FA = DBConfig.getProperty("USER_NAME_FA");

	private final String USER_PASSWORD = DBConfig.getProperty("USER_PASSWORD");

	private final String USER_ACTIVE = DBConfig.getProperty("USER_ACTIVE");

	private final String SBL_USERS = DBConfig.getProperty("SBL_USERS");

	private final String GROUP_ID = DBConfig.getProperty("GROUP_ID");

	private final String GROUP_NAME = DBConfig.getProperty("GROUP_NAME");

	private final String GROUP_NAME_FA = DBConfig.getProperty("GROUP_NAME_FA");

	private final String GROUP_DESCRIPTION = DBConfig
			.getProperty("GROUP_DESCRIPTION");

	private final String GROUP_DESCRIPTION_FA = DBConfig
			.getProperty("GROUP_DESCRIPTION_FA");

	private final String GROUP_ACTIVE = DBConfig.getProperty("GROUP_ACTIVE");

	private final String SBL_USERS_GROUPS = DBConfig
			.getProperty("SBL_USERS_GROUPS");

	private final String SBL_GROUPS = DBConfig.getProperty("SBL_GROUPS");

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

	public String addLoginInfo(String userId, boolean isSuccess)
			throws Exception {

		String currentDateTime = DateUtils.datetimeToString(new Date(),
				DateUtils.UNISON_DATE_FORMAT_FARSI);
		String sQuery = "insert into " + SBL_USER_LOGIN_INFO + " (" + USER_ID
				+ "," + LAST_LOGIN_ATTEMPT_TIME + "," + IS_SUCCESSFULL + ")"
				+ "values (?,?,?)";
		this.template.update(sQuery, new Object[] {
				userId,
				currentDateTime,
				(isSuccess ? Constants.CODE_ACTIVE_FLAG.ACTIVE
						: Constants.CODE_ACTIVE_FLAG.INACTIVE) });
		return Constants.RESPONSE_CODE.SUCCEED;

	}

	public String getLastLoginDate(String userId, boolean isSuccess,
			StringHolder lastLoginDate) throws Exception {
		// jazimagh change for oracle 1389/07/05
		String dataSource = Config.getProperty("CURRENT_DATABASE");
		String sQuery;
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "select  LAST_LOGIN_ATTEMPT_TIME from "
					+ SBL_USER_LOGIN_INFO
					+ " where "
					+ USER_ID
					+ " = '"
					+ userId
					+ "' and "
					+ IS_SUCCESSFULL
					+ " = "
					+ (isSuccess ? Constants.CODE_ACTIVE_FLAG.ACTIVE
							: Constants.CODE_ACTIVE_FLAG.INACTIVE)
					+ " order by " + LAST_LOGIN_ATTEMPT_TIME + " desc";
		} else {
			sQuery = "select LAST_LOGIN_ATTEMPT_TIME from "
					+ SBL_USER_LOGIN_INFO
					+ " where "
					+ USER_ID
					+ " = '"
					+ userId
					+ "' and "
					+ IS_SUCCESSFULL
					+ " = "
					+ (isSuccess ? Constants.CODE_ACTIVE_FLAG.ACTIVE
							: Constants.CODE_ACTIVE_FLAG.INACTIVE)
					+ " order by " + LAST_LOGIN_ATTEMPT_TIME + " desc";
		}
		List ls = this.template.queryForList(sQuery, String.class);
		if (ls.size() == 0)
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;

		if (isSuccess) {
			if (ls.isEmpty())
				lastLoginDate.value = null;
			else
				lastLoginDate.value = ls.get(1).toString();
		} else {
			if (ls.isEmpty())
				lastLoginDate.value = null;
			else
				lastLoginDate.value = ls.get(0).toString();
		}
		return Constants.RESPONSE_CODE.SUCCEED;

	}

	public String addUser(Customer newUser, Group[] groups) throws Exception {
		int result = 0;
		if (!existUser(newUser.getUserId())) {
			String sQuery = "INSERT INTO " + SBL_USERS + " (" + USER_ID + ","
					+ USER_NAME + "," + USER_NAME_FA + "," + USER_PASSWORD
					+ "," + USER_ACTIVE + ")" + " VALUES (?,?,?,?,?)";
			result = this.template.update(sQuery, new Object[] {
					newUser.getUserId(), newUser.getUserName(),
					newUser.getUserNameFA(), newUser.getUserPassword(),
					newUser.getUserActive() });

			if (result == 1) {
				for (int i = 0; i < groups.length; i++) {
					String groupId = groups[i].getGroupId();
					sQuery = "INSERT INTO " + SBL_USERS_GROUPS + " ("
							+ GROUP_ID + "," + USER_ID + ")" + " VALUES (?,?)";
					result = this.template.update(sQuery, new Object[] {
							groupId, newUser.getUserId() });
					if (result != 1) {
						break;
					}
				}
			}
		} else {
			return Constants.RESPONSE_CODE.DUPLICATE_RECORD;
		}

		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	public String updateUser(Customer user, Group[] groups) throws Exception {
		String sQuery = "SELECT *  FROM " + SBL_USERS + " WHERE " + SBL_USERS
				+ "." + USER_ID + " = '" + user.getUserId() + "'";

		List rows = this.template.queryForList(sQuery);
		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}
		int result;
	
		if (AmbitUtility.isEmpty(user.getUserPassword())) {
			sQuery = "UPDATE " + SBL_USERS + " SET " + USER_NAME + " = ?,"
					+ USER_NAME_FA + " = ?," + USER_ACTIVE + " = ?" + " WHERE "
					+ USER_ID + " = ? ";
			result = this.template.update(sQuery, new Object[] {
					user.getUserName(), user.getUserNameFA(),
					user.getUserActive(), user.getUserId() });

		} else {
			sQuery = "UPDATE " + SBL_USERS + " SET " + USER_NAME + " = ?,"
					+ USER_NAME_FA + " = ?," + USER_ACTIVE + " = ?,"
					+ USER_PASSWORD + " =? " + " WHERE " + USER_ID + " = ? ";
			result = this.template.update(sQuery, new Object[] {
					user.getUserName(), user.getUserNameFA(),
					user.getUserActive(), user.getUserPassword(),
					user.getUserId() });
		}

		if (result == 1) {
			String deleteQuery = "DELETE FROM " + SBL_USERS_GROUPS + " WHERE "
					+ USER_ID + " = '" + user.getUserId() + "'";

			this.template.update(deleteQuery);

			for (int i = 0; i < groups.length; i++) {
				String groupId = groups[i].getGroupId();
				sQuery = "INSERT INTO " + SBL_USERS_GROUPS + " (" + USER_ID
						+ "," + GROUP_ID + ")" + " VALUES (?,?)";
				result = this.template.update(sQuery, new Object[] {
						user.getUserId(), groupId });
				if (result != 1) {
					break;
				}
			}
		}
		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	public String changePassword(String userId, String userPreviousPassword,
			String userNewPassword) throws Exception {

		String sQuery = "SELECT " + USER_ID + " FROM " + SBL_USERS + " WHERE "
				+ USER_ID + " = '" + userId + "'" + " AND " + USER_PASSWORD
				+ " = '" + userPreviousPassword + "'";
		List list = template.queryForList(sQuery);

		if (list.size() > 0) {
			sQuery = "UPDATE " + SBL_USERS + " SET " + USER_PASSWORD + " = ?"
					+ " WHERE " + USER_ID + " = ?" + " AND " + USER_PASSWORD
					+ " = ?";

			this.template.update(sQuery, new Object[] { userNewPassword,
					userId, userPreviousPassword });
		} else {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	private boolean existUser(String userId) throws Exception {
		String sQuery = "SELECT " + USER_ID + " FROM " + SBL_USERS + " WHERE "
				+ USER_ID + " = '" + userId + "'";
		List list = template.queryForList(sQuery);

		return (list.size() > 0);
	}

	public String customerAuthenticate(String userId, String userPassword,
			ObjectHolder userInfo) throws Exception {

		Customer user = new Customer();
		String selectQuery = "Select " + SBL_USERS + "." + USER_ID + ","
				+ SBL_USERS + "." + USER_NAME_FA + " from " + SBL_USERS
				+ " WHERE " + SBL_USERS + "." + USER_ID + " = '" + userId + "'"
				+ " AND " + USER_PASSWORD + " = '" + userPassword + "' AND "
				+ SBL_USERS + "." + USER_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'";

		List rowsUser = this.template.queryForList(selectQuery);
		if (rowsUser.size() > 0) {
			// check exist group active for user
			selectQuery = "Select * " + " From " + SBL_USERS + ","
					+ SBL_USERS_GROUPS + "," + SBL_GROUPS + " WHERE "
					+ SBL_USERS + "." + USER_ID + " = '" + userId + "'"
					+ " AND " + SBL_USERS_GROUPS + "." + USER_ID + " = "
					+ SBL_USERS + "." + USER_ID + " AND " + SBL_USERS_GROUPS
					+ "." + GROUP_ID + " = " + SBL_GROUPS + "." + GROUP_ID
					+ " AND " + SBL_GROUPS + "." + GROUP_ACTIVE + " ='"
					+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'";
			List rows = this.template.queryForList(selectQuery);
			if (rows.size() > 0) {
				Map map = (Map) rows.get(0);

				user.setUserId(map.get(USER_ID).toString());
				user.setUserNameFA(map.get(USER_NAME_FA).toString());
				// get all user permission sql is compatible with oracle
				selectQuery = "Select " + SBL_PERMISSIONS + "." + PERMISSION_ID
						+ "," + SBL_PERMISSIONS + "." + PERMISSION_PARENT_ID
						+ "," + SBL_PERMISSIONS + "."
						+ PERMISSION_ACTION_TYPE_ID + " From "
						+ SBL_PERMISSIONS + " Where " + PERMISSION_ID
						+ " IN ( " + "SELECT  distinct  " + SBL_PERMISSIONS
						+ "." + PERMISSION_ID + " FROM "
						+ SBL_GROUPS_PERMISSIONS + "," + SBL_GROUPS + ","
						+ SBL_USERS_GROUPS + "," + SBL_USERS + ","
						+ SBL_PERMISSIONS + " Where " + SBL_USERS_GROUPS + "."
						+ GROUP_ID + "=" + SBL_GROUPS + "." + GROUP_ID
						+ " And " + SBL_GROUPS + "." + GROUP_ID + "="
						+ SBL_GROUPS_PERMISSIONS + "." + GROUP_ID + " And "
						+ SBL_PERMISSIONS + "." + PERMISSION_ID + "="
						+ SBL_GROUPS_PERMISSIONS + "." + PERMISSION_ID
						+ " AND " + SBL_GROUPS + "." + GROUP_ACTIVE + "='"
						+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'" + " And "
						+ SBL_USERS + "." + USER_ACTIVE + "='"
						+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'" + " And "
						+ SBL_PERMISSIONS + "." + PERMISSION_ACTIVE + " = '"
						+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'" + " And "
						+ SBL_USERS_GROUPS + "." + USER_ID + "=" + SBL_USERS
						+ "." + USER_ID + " And " + SBL_USERS + "." + USER_ID
						+ "='" + userId + "'" + ")";
				rows = this.template.queryForList(selectQuery);
				if (rows.size() > 0) {
					for (int i = 0; i < rows.size(); i++) {
						map = (Map) rows.get(i);
						int permissionParentId = 0;

						int permissionId = (Integer.parseInt(map.get(
								PERMISSION_ID).toString()));

						String actionTypeId;
						if (map.get(PERMISSION_PARENT_ID) != null)

							permissionParentId = (Integer.parseInt(map.get(
									PERMISSION_PARENT_ID).toString()));

						actionTypeId = AmbitUtility.nvl((String) map
								.get(PERMISSION_ACTION_TYPE_ID));
						user.setAccess(permissionId, permissionParentId,
								actionTypeId);
					}
				}
			} else
				return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;

		} else {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}
		userInfo.value = user;
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getAllCustomers(String statusCustomers,
			ObjectHolder allCustomers) throws Exception {
		String sQuery = "";

		String whereClause = "";
		whereClause = AmbitUtility.isEmpty(statusCustomers) ? "" : " Where "
				+ USER_ACTIVE + "='" + statusCustomers + "'";
		sQuery = "SELECT " + USER_ID + "," + USER_NAME + "," + USER_NAME_FA
				+ "," + USER_ACTIVE + " FROM " + SBL_USERS + whereClause;

		List rows = template.queryForList(sQuery);
		if (rows.size() == 0)
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		allCustomers.value = this.extractUsers(rows);
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getUserDetail(ObjectHolder user, ObjectHolder allGroups)
			throws Exception {
		String sQuery = "";
		Customer userDetail = (Customer) JavaUtils
				.convert(user, Customer.class);

		sQuery = "SELECT " + SBL_USERS + "." + USER_ID + "," + SBL_USERS + "."
				+ USER_NAME + "," + SBL_USERS + "." + USER_NAME_FA + ","
				+ SBL_USERS + "." + USER_ACTIVE + " FROM " + SBL_USERS
				+ " WHERE " + SBL_USERS + "." + USER_ID + " = '"
				+ userDetail.getUserId() + "'";

		List rows = template.queryForList(sQuery);
		if (rows.size() > 0) {
			userDetail = this.extractUsers(rows)[0];
		} else {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		sQuery = "SELECT " + SBL_GROUPS + "." + GROUP_ID + ", " + SBL_GROUPS
				+ "." + GROUP_NAME + ", " + SBL_GROUPS + "." + GROUP_NAME_FA
				+ ", " + SBL_GROUPS + "." + GROUP_DESCRIPTION + ", "
				+ SBL_GROUPS + "." + GROUP_DESCRIPTION_FA + ", " + SBL_GROUPS
				+ "." + GROUP_ACTIVE + " FROM " + SBL_USERS_GROUPS + ", "
				+ SBL_GROUPS + " WHERE " + SBL_GROUPS + "." + GROUP_ID + " = "
				+ SBL_USERS_GROUPS + "." + GROUP_ID + " AND "
				+ SBL_USERS_GROUPS + "." + USER_ID + " = '"
				+ userDetail.getUserId() + "'" + " ORDER BY " + SBL_GROUPS
				+ "." + GROUP_ID;

		rows = template.queryForList(sQuery);
		Group[] groups = null;
		if (rows.size() > 0) {
			groups = this.extractGroups(rows);
		}
		user.value = userDetail;
		allGroups.value = groups;
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	private Customer[] extractUsers(List rows) {
		Customer[] customers = new Customer[rows.size()];
		Map map;
		Customer customer;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			customer = new Customer();
			customer.setUserId((AmbitUtility.nvl((String) map.get(USER_ID)))
					.trim());
			customer
					.setUserName((AmbitUtility.nvl((String) map.get(USER_NAME)))
							.trim());
			customer.setUserNameFA((AmbitUtility.nvl((String) map
					.get(USER_NAME_FA))).trim());
			customer.setUserActive((AmbitUtility.nvl((String) map
					.get(USER_ACTIVE))).trim());
			customers[i] = customer;

		}
		return customers;
	}

	private Group[] extractGroups(List rows) {
		Group[] groups = new Group[rows.size()];
		Map map;
		Group group;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			group = new Group();
			group.setGroupId((AmbitUtility.nvl((String) map.get(GROUP_ID)))
					.trim());
			group.setGroupName((AmbitUtility.nvl((String) map.get(GROUP_NAME)))
					.trim());
			group.setGroupNameFA((AmbitUtility.nvl((String) map
					.get(GROUP_NAME_FA))).trim());
			group.setGroupDescription((AmbitUtility.nvl((String) map
					.get(GROUP_DESCRIPTION))).trim());
			group.setGroupDescriptionFA((AmbitUtility.nvl((String) map
					.get(GROUP_DESCRIPTION_FA))).trim());
			group.setGroupActive((AmbitUtility.nvl((String) map
					.get(GROUP_ACTIVE))).trim());
			groups[i] = group;

		}
		return groups;
	}
}
