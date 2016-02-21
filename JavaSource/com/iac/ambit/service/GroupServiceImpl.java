package com.iac.ambit.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.DAO.GroupDAO;
import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;

public class GroupServiceImpl implements GroupService {

	private GroupDAO groupDAO;

	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}

	public String addGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		return this.groupDAO.addGroup(newGroup, groupPermissions);
	}

	public String updateGroup(Group newGroup, Permissions[] groupPermissions)
			throws Exception {
		return this.groupDAO.updateGroup(newGroup, groupPermissions);
	}

	public String getAllGroups(String statusGroups,ObjectHolder allGroup) throws Exception {
		return this.groupDAO.getAllGroups(statusGroups,allGroup);
	}
	
	public String getGroupDetail(ObjectHolder group,ObjectHolder allPermission) throws Exception {
		return this.groupDAO.getGroupDetail(group,allPermission);
	}


	public void setGroupDAO(GroupDAO groupDAO) {
		this.groupDAO = groupDAO;
	}

}
