package com.iac.ambit.service;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;

public interface GroupService {

	public String addGroup(Group newGroup,Permissions[] groupPermissions) throws Exception;

	public String updateGroup(Group newGroup,Permissions[] groupPermissions) throws Exception;

	public String getAllGroups(String statusGroups,ObjectHolder allGroup) throws Exception;
	
	public String getGroupDetail(ObjectHolder group,ObjectHolder allPermission) throws Exception;

}