package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import java.sql.Types;

import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.ObjectHolder;

import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;

import com.iac.ambit.utils.Constants;
import com.iac.ambit.utils.AmbitUtility;
import org.apache.axis.utils.JavaUtils;

public class GroupDAOImpl implements GroupDAO {

	private JdbcTemplate template;

	

	private final String GROUP_ID = DBConfig.getProperty("GROUP_ID");

	private final String GROUP_NAME = DBConfig.getProperty("GROUP_NAME");

	private final String GROUP_NAME_FA = DBConfig.getProperty("GROUP_NAME_FA");

	private final String GROUP_DESCRIPTION = DBConfig
			.getProperty("GROUP_DESCRIPTION");

	private final String GROUP_DESCRIPTION_FA = DBConfig
			.getProperty("GROUP_DESCRIPTION_FA");

	private final String GROUP_ACTIVE = DBConfig.getProperty("GROUP_ACTIVE");

	private final String SBL_GROUPS = DBConfig.getProperty("SBL_GROUPS");

	private final String SBL_PERMISSIONS = DBConfig
			.getProperty("SBL_PERMISSIONS");

	private final String SBL_GROUPS_PERMISSIONS = DBConfig
			.getProperty("SBL_GROUPS_PERMISSIONS");

	private final String PERMISSION_ID = DBConfig.getProperty("PERMISSION_ID");

	private final String PERMISSION_ACTIVE = DBConfig
			.getProperty("PERMISSION_ACTIVE");

	private final String PERMISSION_TITLE = DBConfig
			.getProperty("PERMISSION_TITLE");

	private final String PERMISSION_TITLE_FA = DBConfig
			.getProperty("PERMISSION_TITLE_FA");

	/*
	 * private final String PERMISSION_PARENT_ID = DBConfig
	 * .getProperty("PERMISSION_PARENT_ID");
	 */

	private int[] groupPermissionArgTypes = new int[2];

	private int[] getGroupPermissionArgTypes() {
		groupPermissionArgTypes[0] = Types.VARCHAR;
		groupPermissionArgTypes[1] = Types.INTEGER;
		return groupPermissionArgTypes;
	}

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	

	public String addGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		int result = 0;

		if (!existGroup(newGroup.getGroupId())) {
			String sQuery = "INSERT INTO " + SBL_GROUPS + " (" + GROUP_ID + ","
					+ GROUP_NAME + "," + GROUP_NAME_FA + ","
					+ GROUP_DESCRIPTION + "," + GROUP_DESCRIPTION_FA + ","
					+ GROUP_ACTIVE + ")" + " VALUES (?,?,?,?,?,?)";
			result = this.template.update(sQuery,
					new Object[] { newGroup.getGroupId(),
							newGroup.getGroupName(), newGroup.getGroupNameFA(),
							newGroup.getGroupDescription(),
							newGroup.getGroupDescriptionFA(),
							newGroup.getGroupActive() });

			if (result == 1) {
				//List parentIdList = new ArrayList();
				if (groupPermissions != null) {
					for (int i = 0; i < groupPermissions.length; i++) {
						String permissionId = Integer
								.toString(groupPermissions[i].getPermissionId());
						/*int parentId = this.sysPermissionService
								.getParentPermissionId(permissionId);*/
						/*if (!parentIdList.contains(String.valueOf(parentId))
								&& parentId != 0) {
							parentIdList.add(String.valueOf(parentId));
						}*/
						sQuery = "INSERT INTO " + SBL_GROUPS_PERMISSIONS + " ("
								+ GROUP_ID + "," + PERMISSION_ID + ")"
								+ " VALUES (?,?)";
						result = this.template.update(sQuery, new Object[] {
								newGroup.getGroupId(), permissionId },
								getGroupPermissionArgTypes());
						if (result != 1) {
							break;
						}
					}

				/*	if (result == 1) {
						for (int i = 0; i < parentIdList.size(); i++) {
							sQuery = "INSERT INTO " + SBL_GROUPS_PERMISSIONS
									+ " (" + GROUP_ID + "," + PERMISSION_ID
									+ ")" + " VALUES (?,?)";
							result = this.template.update(sQuery,
									new Object[] { newGroup.getGroupId(),
											parentIdList.get(i) },
									getGroupPermissionArgTypes());
							if (result != 1) {
								break;
							}
						}
					}*/
				}

			}
		} else {
			return Constants.RESPONSE_CODE.DUPLICATE_RECORD;
		}

		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	public String updateGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		String sQuery = "UPDATE " + SBL_GROUPS + " SET " + GROUP_NAME + " = ?,"
				+ GROUP_NAME_FA + " = ?," + GROUP_DESCRIPTION + " = ?,"
				+ GROUP_DESCRIPTION_FA + " = ?," + GROUP_ACTIVE + " = ? "
				+ " WHERE " + GROUP_ID + "  = ? ";

		int result = this.template.update(sQuery, new Object[] {
				newGroup.getGroupName(), newGroup.getGroupNameFA(),
				newGroup.getGroupDescription(),
				newGroup.getGroupDescriptionFA(), newGroup.getGroupActive(),
				newGroup.getGroupId() });

		if (result == 1) {
			String deleteQuery = "DELETE FROM " + SBL_GROUPS_PERMISSIONS
					+ " WHERE " + GROUP_ID + " = '" + newGroup.getGroupId()
					+ "'";
			this.template.update(deleteQuery);

			// List parentIdList = new ArrayList();
			if (groupPermissions != null) {
				for (int i = 0; i < groupPermissions.length; i++) {
					String permissionId = Integer.toString(groupPermissions[i]
							.getPermissionId());
					/*
					 * int parentId = this.sysPermissionService
					 * .getParentPermissionId(permissionId); if
					 * (!parentIdList.contains(String.valueOf(parentId)) &&
					 * parentId != 0) {
					 * 
					 * parentIdList.add(String.valueOf(parentId)); }
					 */
					sQuery = "INSERT INTO " + SBL_GROUPS_PERMISSIONS + " ("
							+ GROUP_ID + "," + PERMISSION_ID + ")"
							+ " VALUES (?,?)";
					result = this.template.update(sQuery, new Object[] {
							newGroup.getGroupId(), permissionId },
							getGroupPermissionArgTypes());
					if (result != 1) {
						break;
					}
				}
				/*
				 * if (result == 1) { for (int i = 0; i < parentIdList.size();
				 * i++) {
				 * if(!existGroupPermission(parentIdList.get(i).toString(),newGroup.getGroupId())){
				 * sQuery = "INSERT INTO " + SBL_GROUPS_PERMISSIONS + " (" +
				 * GROUP_ID + "," + PERMISSION_ID + ")" + " VALUES (?,?)";
				 * result = this.template.update(sQuery, new Object[] {
				 * newGroup.getGroupId(), parentIdList.get(i) },
				 * getGroupPermissionArgTypes()); if (result != 1) { break; } } } }
				 */
			}

		} else {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}
		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	/*
	 * private boolean existGroupPermission(String permissionId,String groupId)
	 * throws Exception { String sQuery = "SELECT * " + " FROM " +
	 * SBL_GROUPS_PERMISSIONS + " WHERE " + GROUP_ID + " = '" + groupId + "' And " +
	 * PERMISSION_ID + " = '" + permissionId + "'" ; List list =
	 * template.queryForList(sQuery);
	 * 
	 * return (list.size() > 0); }
	 */
	public String getAllGroups(String statusGroups, ObjectHolder allGroup)
			throws Exception {
		String sQuery = "";
		sQuery = "SELECT " + GROUP_ID + "," + GROUP_NAME + "," + GROUP_NAME_FA
				+ "," + GROUP_DESCRIPTION + "," + GROUP_DESCRIPTION_FA + ","
				+ GROUP_ACTIVE + " FROM " + SBL_GROUPS;
		if (!AmbitUtility.isEmpty(statusGroups))
			sQuery = sQuery + " Where " + GROUP_ACTIVE + "='" + statusGroups
					+ "'";

		List rows = template.queryForList(sQuery);
		if (rows.size() == 0)
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		allGroup.value = this.extractGroups(rows);
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	public String getGroupDetail(ObjectHolder group, ObjectHolder allPermission)
			throws Exception {
		String sQuery = "";
		Group groupDetail = (Group) JavaUtils.convert(group, Group.class);

		sQuery = "SELECT " + GROUP_ID + ", " + GROUP_NAME + ", "
				+ GROUP_NAME_FA + ", " + GROUP_DESCRIPTION + ", "
				+ GROUP_DESCRIPTION_FA + ", " + GROUP_ACTIVE + " FROM "
				+ SBL_GROUPS + " WHERE " + GROUP_ID + " = '"
				+ groupDetail.getGroupId() + "'";

		List rows = template.queryForList(sQuery);
		if (rows.size() > 0) {
			groupDetail = this.extractGroups(rows)[0];
		} else {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		sQuery = "SELECT " + SBL_PERMISSIONS + "." + PERMISSION_ID + ", "
				+ SBL_PERMISSIONS + "." + PERMISSION_TITLE + ", "
				+ SBL_PERMISSIONS + "." + PERMISSION_TITLE_FA + ", "
				+ SBL_PERMISSIONS + "." + PERMISSION_ACTIVE + " FROM "
				+ SBL_GROUPS_PERMISSIONS + ", " + SBL_PERMISSIONS + " WHERE "
				+ SBL_GROUPS_PERMISSIONS + "." + PERMISSION_ID + " = "
				+ SBL_PERMISSIONS + "." + PERMISSION_ID + " AND "
				+ SBL_PERMISSIONS + "." + PERMISSION_ACTIVE + "='"
				+ Constants.CODE_ACTIVE_FLAG.ACTIVE + "'" + " AND "
				+ SBL_GROUPS_PERMISSIONS + "." + GROUP_ID + " = '"
				+ groupDetail.getGroupId() + "'" + " ORDER BY "
				+ SBL_GROUPS_PERMISSIONS + "." + PERMISSION_ID;

		rows = template.queryForList(sQuery);
		Permissions[] permissions = null;
		if (rows.size() > 0) {
			permissions = this.extractGroupPermissions(rows);
		}
		group.value = groupDetail;
		allPermission.value = permissions;
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	private boolean existGroup(String groupId) throws Exception {
		String sQuery = "SELECT " + GROUP_ID + " FROM " + SBL_GROUPS
				+ " WHERE " + GROUP_ID + " = '" + groupId + "'";
		List list = template.queryForList(sQuery);

		return (list.size() > 0);
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

	private Permissions[] extractGroupPermissions(List rows) {
		Permissions[] permissions = new Permissions[rows.size()];
		Map map;

		Permissions permission;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			permission = new Permissions();
			permission.setPermissionId(Integer.parseInt(AmbitUtility.nvl(map
					.get(PERMISSION_ID).toString())));
			permission.setPermissionStatus(AmbitUtility.nvl(map.get(
					PERMISSION_ACTIVE).toString()));
			permission.setPermissionTitle(AmbitUtility.nvl(map.get(
					PERMISSION_TITLE).toString()));
			permission.setPermissionTitleFA(AmbitUtility.nvl(map.get(
					PERMISSION_TITLE_FA).toString()));

			permissions[i] = permission;
		}
		return permissions;
	}

}
