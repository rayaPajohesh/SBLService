package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.xml.rpc.holders.BooleanHolder;
import javax.xml.rpc.holders.ObjectHolder;

import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;

import com.iac.ambit.model.Permissions;
import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.IntegrationUtils;
import com.iac.ambit.utils.AmbitUtility;

public class SysPermissionDAOJdbcImpl implements SysPermissionDAO {

	private static final String SBL_PERMISSIONS = DBConfig
			.getProperty("SBL_PERMISSIONS");

	private static final String SBL_USERS = DBConfig.getProperty("SBL_USERS");

	private static final String SBL_GROUPS = DBConfig.getProperty("SBL_GROUPS");

	private static final String SBL_GROUPS_PERMISSIONS = DBConfig
			.getProperty("SBL_GROUPS_PERMISSIONS");

	private static final String SBL_USERS_GROUPS = DBConfig
			.getProperty("SBL_USERS_GROUPS");

	private static final String USER_ID = DBConfig.getProperty("USER_ID");

	private static final String USER_ACTIVE = DBConfig
			.getProperty("USER_ACTIVE");

	private static final String GROUP_ID = DBConfig.getProperty("GROUP_ID");

	private static final String GROUP_ACTIVE = DBConfig
			.getProperty("GROUP_ACTIVE");

	private static final String PERMISSION_TITLE = DBConfig
			.getProperty("PERMISSION_TITLE");

	private static final String PERMISSION_TITLE_FA = DBConfig
			.getProperty("PERMISSION_TITLE_FA");

	private static final String PERMISSION_ACTIVE = DBConfig
			.getProperty("PERMISSION_ACTIVE");

	private static final String PERMISSION_PARENT_ID = DBConfig
			.getProperty("PERMISSION_PARENT_ID");

	private static final String PERMISSION_ID = DBConfig
			.getProperty("PERMISSION_ID");

	private static final String PERMISSION_ORDER = DBConfig
			.getProperty("PERMISSION_ORDER");

	private static final String PERMISSION_URI = DBConfig
			.getProperty("PERMISSION_URI");

	private static final String PERMISSION_IS_ACTIVITY = DBConfig
			.getProperty("PERMISSION_IS_ACTIVITY");

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
	public SysPermissionDAOJdbcImpl() {

	}

	public String isPermissionAvailableToCustomer(String sUserName,
			String moduleName, BooleanHolder isPermissionAvailable)
			throws Exception {

		String selectQuery = " Select " + SBL_USERS + "." + USER_ID + " From "
				+ SBL_USERS + "," + SBL_PERMISSIONS + ","
				+ SBL_GROUPS_PERMISSIONS + "," + SBL_GROUPS + ","
				+ SBL_USERS_GROUPS + " Where " + SBL_USERS + "." + USER_ID
				+ " = '" + sUserName + "' And " + SBL_PERMISSIONS + "."
				+ PERMISSION_TITLE + " ='" + moduleName + "' AND " + SBL_USERS
				+ "." + USER_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' AND "
				+ SBL_PERMISSIONS + "." + PERMISSION_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' AND "
				+ SBL_PERMISSIONS + "." + PERMISSION_ID + "="
				+ SBL_GROUPS_PERMISSIONS + "." + PERMISSION_ID + " AND "
				+ SBL_GROUPS_PERMISSIONS + "." + GROUP_ID + "=" + SBL_GROUPS
				+ "." + GROUP_ID + " AND " + SBL_GROUPS + "." + GROUP_ID + "="
				+ SBL_USERS_GROUPS + "." + GROUP_ID + " AND " + SBL_USERS + "."
				+ USER_ID + "=" + SBL_USERS_GROUPS + "." + USER_ID;
		if (template.queryForList(selectQuery).size() > 0)
			isPermissionAvailable.value = true;
		else
			isPermissionAvailable.value = false;

		return Constants.RESPONSE_CODE.SUCCEED;

	}

	public String getAllActivity(ObjectHolder allActivity) throws Exception {
		String selectQuery = "";
		selectQuery = "SELECT " + PERMISSION_TITLE + "," + PERMISSION_TITLE_FA
				+ "," + PERMISSION_ACTIVE + "," + PERMISSION_ID + ","
				+ PERMISSION_PARENT_ID + "," + PERMISSION_ORDER + " FROM "
				+ SBL_PERMISSIONS;

		selectQuery = selectQuery + " Where " + PERMISSION_IS_ACTIVITY + " ='"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' Order By "
				+ PERMISSION_ID;

		List rows = template.queryForList(selectQuery);
		allActivity.value = this.extractPermissions(rows);
		return Constants.RESPONSE_CODE.SUCCEED;

	}

	public String getAllPermissions(String statusPermissions,
			ObjectHolder allPermission) throws Exception {
		String selectQuery = "";
		selectQuery = "SELECT " + PERMISSION_TITLE + "," + PERMISSION_TITLE_FA
				+ "," + PERMISSION_ACTIVE + "," + PERMISSION_ID + ","
				+ PERMISSION_PARENT_ID + "," + PERMISSION_ORDER + " FROM "
				+ SBL_PERMISSIONS;
		if (!AmbitUtility.isEmpty(statusPermissions))
			selectQuery = selectQuery + " Where " + PERMISSION_ACTIVE + " ='"
					+ statusPermissions + "'" + "  Order By PERMISSION_ID ";
		else
			selectQuery = selectQuery + " Where "
					+ "   Order By PERMISSION_ID ";

		List rows = template.queryForList(selectQuery);
		allPermission.value = this.extractPermissions(rows);
		return Constants.RESPONSE_CODE.SUCCEED;

	}

	private Permissions[] extractPermissions(List rows) {
		Permissions[] menus = new Permissions[rows.size()];
		Permissions menu;
		Map map;
		// Permissions menu;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			// for (Map map : rows) {
			menu = new Permissions();
			menu.setPermissionTitleFA(((String) map.get(PERMISSION_TITLE_FA))
					.trim());
			menu
					.setPermissionTitle(((String) map.get(PERMISSION_TITLE))
							.trim());
			if (map.get(PERMISSION_ACTIVE) != null) {

				String permission = (String) map.get(PERMISSION_ACTIVE);
				if (permission.equals(Constants.CODE_ACTIVE_FLAG.ACTIVE))
					menu.setPermissionStatus(Constants.CODE_ACTIVE_FLAG.ACTIVE);
				else
					menu
							.setPermissionStatus(Constants.CODE_ACTIVE_FLAG.INACTIVE);

			}
			if (map.get(PERMISSION_ID) != null) {

				menu.setPermissionId(Integer.parseInt(map.get(PERMISSION_ID)
						.toString()));
			}
			menus[i] = menu;

		}
		return menus;
	}

	public String getCustomerSysPermissionsIds(String userId,
			ObjectHolder CustomerSysPermissionsIds) throws Exception {

		List items = new Vector();
		String selectMenu = "";

		selectMenu = "SELECT " + SBL_PERMISSIONS + "." + PERMISSION_ID
				+ " FROM " + SBL_PERMISSIONS + "," + SBL_GROUPS_PERMISSIONS
				+ "," + SBL_GROUPS + "," + SBL_USERS_GROUPS + "," + SBL_USERS
				+ " WHERE " + SBL_PERMISSIONS + "." + PERMISSION_ACTIVE
				+ " = '1' AND " + SBL_USERS + "." + USER_ID + " = '" + userId
				+ "' AND " + SBL_USERS + "." + USER_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' AND " + SBL_GROUPS
				+ "." + GROUP_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' AND "
				+ SBL_PERMISSIONS + "." + PERMISSION_ACTIVE + " = '"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "' AND "
				+ SBL_PERMISSIONS + "." + PERMISSION_ID + "="
				+ SBL_GROUPS_PERMISSIONS + "." + PERMISSION_ID + " AND "
				+ SBL_GROUPS_PERMISSIONS + "." + GROUP_ID + "=" + SBL_GROUPS
				+ "." + GROUP_ID + " AND " + SBL_GROUPS + "." + GROUP_ID + "="
				+ SBL_USERS_GROUPS + "." + GROUP_ID + " AND " + SBL_USERS + "."
				+ USER_ID + "=" + SBL_USERS_GROUPS + "." + USER_ID
				+ " Order by " + SBL_PERMISSIONS + "." + PERMISSION_ID;
		// + "(" + SBL_PERMISSIONS + "." + PERMISSION_URI + " IS NOT NULL OR " +
		// SBL_PERMISSIONS + "." + PERMISSION_PARENT_ID + " IS NOT NULL)";

		List rows = this.template.queryForList(selectMenu);

		if (rows.size() > 0) {
			Map map = null;

			for (int j = 0; j < rows.size(); j++) {

				map = (Map) rows.get(j);
				items.add(map.get(PERMISSION_ID).toString());
			}
			// items.add(Constants.MenuItemFixed.FIRST_PAGE);
		}
		CustomerSysPermissionsIds.value = items;

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String loadSysPermissionURIs(ObjectHolder sysPermissionURIs)
			throws Exception {

		String selectQuery = "SELECT " + PERMISSION_TITLE + ","
				+ PERMISSION_URI + " FROM " + SBL_PERMISSIONS + " Where "
				+ PERMISSION_ACTIVE + "='" + Constants.CODE_ACTIVE_FLAG.ACTIVE
				+ "'";

		List resultList = this.template.queryForList(selectQuery);

		Map returnMap = new Hashtable();
		Map map;

		for (int i = 0; i < resultList.size(); i++) {

			map = (Map) resultList.get(i);

			String[] uriArr = IntegrationUtils
					.convertMsgToArrayNoLastDelimiter((String) map
							.get(PERMISSION_URI), ",");

			for (int j = 0; j < uriArr.length; j++) {

				returnMap.put(uriArr[j], map.get(PERMISSION_TITLE));
			}
		}
		sysPermissionURIs.value = returnMap;

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	public String activationPermission(List permissionId) throws Exception {

		Map map;
		String updateQuery;
		String selectQuery;
		if (permissionId.size() > 0) {
			for (int k = 0; k < permissionId.size(); k++) {
				selectQuery = "SELECT " + PERMISSION_ACTIVE + ","
						+ PERMISSION_ID + " FROM " + SBL_PERMISSIONS
						+ " WHERE " + PERMISSION_ID + " = "
						+ permissionId.get(k);

				List rows = this.template.queryForList(selectQuery);

				for (int i = 0; i < rows.size(); i++) {
					map = (Map) rows.get(i);
					String presentValue = ((String) map.get(PERMISSION_ACTIVE));

					int id = (Integer.parseInt(map.get(PERMISSION_ID)
							.toString()));

					if (presentValue.equals(Constants.CODE_ACTIVE_FLAG.ACTIVE)) {
						updateQuery = "UPDATE " + SBL_PERMISSIONS + " SET "
								+ PERMISSION_ACTIVE + " = '"
								+ Constants.CODE_ACTIVE_FLAG.INACTIVE + "'";
					} else {
						updateQuery = "UPDATE " + SBL_PERMISSIONS + " SET "
								+ PERMISSION_ACTIVE + " = '"
								+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'";
					}
					updateQuery = updateQuery + " WHERE " + PERMISSION_ID
							+ " = " + id;
					this.template.update(updateQuery);

				}

			}
		}
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public int getParentPermissionId(String permissionId) throws Exception {
		String selectQuery = "SELECT " + PERMISSION_PARENT_ID + " FROM "
				+ SBL_PERMISSIONS + " WHERE " + PERMISSION_ID + " = "
				+ permissionId;

		return this.template.queryForInt(selectQuery);

	}

}
