package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;

import com.iac.ambit.model.BlackedReason;



import com.iac.ambit.utils.Constants;
public class BlackedReasonDAOImpl implements BlackedReasonDAO{
	
	
	private JdbcTemplate template;

	
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	private final void writeObject(ObjectOutputStream out) throws IOException {
		throw new IOException("Object cannot be serialized");
	}

	private final void readObject(ObjectInputStream in) throws IOException {
		throw new IOException("Class cannot be Deserialized");
	}
	
	
	private final String SBL_BLACKED_REASON = DBConfig.getProperty("SBL_BLACKED_REASON");
	
	private final String BLACKED_REASON_ID = DBConfig.getProperty("BLACKED_REASON_ID");
	
	private final String BLACKED_REASON_DESCRIPTION = DBConfig.getProperty("BLACKED_REASON_DESCRIPTION");
	
	private final String BLACKED_REASON_DESCRIPTION_FA = DBConfig.getProperty("BLACKED_REASON_DESCRIPTION_FA");
	
	

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	
	}
	
	public String searchBlackReasons(BlackedReason list) throws Exception {
		String sQuery = "";
		
		
		sQuery = "SELECT " + BLACKED_REASON_ID + "," + BLACKED_REASON_DESCRIPTION + "," +
		BLACKED_REASON_DESCRIPTION_FA 
		
		+ " FROM " + SBL_BLACKED_REASON ;
		
		sQuery = sQuery +  " order by " +
		BLACKED_REASON_ID + " desc";
		

		List rows = template.queryForList(sQuery);
		list.value = extractBlackReason(rows);
		return Constants.RESPONSE_CODE.SUCCEED;
	}

	
	private BlackedReason[] extractBlackReason(List rows) {
		BlackedReason[] reasonList = new BlackedReason[rows.size()];
		Map map;
		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			BlackedReason br = new BlackedReason();
			br.setBlackedReasonId((map.get(BLACKED_REASON_ID).toString()));
			br.setBlackedReasonDescription((map.get(BLACKED_REASON_DESCRIPTION).toString()));
			br.setBlackedReasonDescriptionFA((map.get(BLACKED_REASON_DESCRIPTION_FA).toString()));
			reasonList[i] = br;
		}
		return reasonList;

	}
}
