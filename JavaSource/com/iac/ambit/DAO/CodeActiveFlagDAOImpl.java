package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;



import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;

import com.iac.ambit.model.CodeActiveFlag;
import com.iac.ambit.utils.Constants;

public class CodeActiveFlagDAOImpl implements CodeActiveFlagDAO {
	
private JdbcTemplate template;

	

	private final String CODE_ACTIVE_FLAG = DBConfig.getProperty("CODE_ACTIVE_FLAG");
	private final String CODE_ACTIVE_DESCRIPTION = DBConfig.getProperty("CODE_ACTIVE_DESCRIPTION");
	private final String CODE_ACTIVE_DESCRIPTION_FA = DBConfig.getProperty("CODE_ACTIVE_DESCRIPTION_FA");
	private final String SBL_CODE_ACTIVE_FLAG = DBConfig.getProperty("SBL_CODE_ACTIVE_FLAG");
	
	
	public String getAllFlags(CodeActiveFlag allFlags) throws Exception {
		
		String sQuery = "";
		sQuery = "SELECT " + CODE_ACTIVE_FLAG + "," + CODE_ACTIVE_DESCRIPTION + "," +
		CODE_ACTIVE_DESCRIPTION_FA 
		
		+ " FROM " + SBL_CODE_ACTIVE_FLAG ;
		
		sQuery = sQuery +  " order by " +
		CODE_ACTIVE_FLAG + " desc";
		

		List rows = template.queryForList(sQuery);
		allFlags.value = extractFlags(rows);
		
		
		return Constants.RESPONSE_CODE.SUCCEED;
		
	}
	
	private CodeActiveFlag[] extractFlags(List rows) {
		CodeActiveFlag[] flagList = new CodeActiveFlag[rows.size()];
		Map map;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			CodeActiveFlag flag = new CodeActiveFlag();
			flag.setCodeActiveDescription((map.get(CODE_ACTIVE_DESCRIPTION).toString()));
			flag.setCodeActiveDescriptionFA((map.get(CODE_ACTIVE_DESCRIPTION_FA).toString()));
			flag.setCodeActiveFlag((map.get(CODE_ACTIVE_FLAG).toString()));
			flagList[i] = flag;
		}
		return flagList;

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


}
