package com.iac.ambit.DAO;

import javax.xml.rpc.holders.ObjectHolder;

import com.iac.ambit.model.Permissions;
import com.iac.ambit.model.Group;

public interface GroupDAO {
	public String addGroup(Group newGroup,Permissions[] groupPermissions) throws Exception;
	
	public String updateGroup(Group newGroup,Permissions[] groupPermissions) throws Exception;

	public String getAllGroups(String statusGroups,ObjectHolder allGroup) throws Exception;
	
	public String getGroupDetail(ObjectHolder group,ObjectHolder allPermission) throws Exception;

}
