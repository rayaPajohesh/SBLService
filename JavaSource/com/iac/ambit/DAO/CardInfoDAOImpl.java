package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.CardInfo;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;

public class CardInfoDAOImpl implements CardInfoDAO {

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

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}

	private final String ODS_ATMCARD = DBConfig.getProperty("ODS_ATMCARD");

	private final String ODS_ATM_CARD_STATUS = DBConfig
			.getProperty("ODS_ATM_CARD_STATUS");

	private final String CARD_STATUS_ID = DBConfig
			.getProperty("CARD_STATUS_ID");

	private final String PAN = DBConfig.getProperty("PAN");

	private final String CARD_NAME = DBConfig.getProperty("CARD_NAME");

	private final String CARD_STATUS_NAME = DBConfig
			.getProperty("CARD_STATUS_NAME");

	private final String ODS_ACCT_INFO_TABLE = DBConfig
			.getProperty("ODS_ACCT_INFO_TABLE");

	private final String ACCT_TYPE_ID = DBConfig.getProperty("ACCT_TYPE_ID");

	private final String CURR_CODE_ID = DBConfig.getProperty("CURR_CODE_ID");

	private final String ACCOUNT_MODEID = DBConfig
			.getProperty("ACCOUNT_MODEID");

	private final String ODS_ACCT_TYPE_DESC = DBConfig
			.getProperty("ODS_ACCT_TYPE_DESC");

	private final String ODS_CURRENCY_TABLE = DBConfig
			.getProperty("ODS_CURRENCY_TABLE");

	private final String CARD_ID = DBConfig.getProperty("CARD_ID");

	private final String ODS_CARD_ACCOUNT = DBConfig
			.getProperty("ODS_CARD_ACCOUNT");

	private final String ACCOUNT_INTERNAL_ID = DBConfig
			.getProperty("ACCOUNT_INTERNAL_ID");

	private final String ACCOUNT_NAME = DBConfig.getProperty("ACCOUNT_NAME");

	private final String IS_DEFAULT_ACCT = DBConfig
			.getProperty("IS_DEFAULT_ACCT");

	public String searchPanInformation(String pan, CardInfo cardInfo)
			throws Exception {

		String sQuery = "";

		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "SELECT " + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_NAME + ","
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_NAME + ","
					+ ODS_ATMCARD + "." + PAN + " FROM " + ODS_ACCT_INFO_TABLE
					+ " , " + ODS_ACCT_TYPE_DESC + " , " + ODS_CURRENCY_TABLE
					+ " , " + ODS_ATMCARD + " , " + ODS_CARD_ACCOUNT + " , "
					+ ODS_ATM_CARD_STATUS + " WHERE " + ODS_ACCT_INFO_TABLE
					+ "." + ACCT_TYPE_ID + " = " + ODS_ACCT_TYPE_DESC + "."
					+ ACCT_TYPE_ID + " AND " + ODS_ACCT_INFO_TABLE + "."
					+ CURR_CODE_ID + " = " + ODS_CURRENCY_TABLE + "."
					+ CURR_CODE_ID + " AND " + ODS_CARD_ACCOUNT + "."
					+ ACCOUNT_INTERNAL_ID + " = " + ODS_ACCT_INFO_TABLE + "."
					+ ACCOUNT_INTERNAL_ID + " AND " + ODS_CARD_ACCOUNT + "."
					+ CARD_ID + " = " + ODS_ATMCARD + "." + CARD_ID + " AND "
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID + " = "
					+ ODS_ATMCARD + "." + CARD_STATUS_ID + " AND "
					+ ODS_ATMCARD + "." + PAN + " = '" + pan + "' " + " AND "
					+ ODS_CARD_ACCOUNT + "." + IS_DEFAULT_ACCT + " = '"
					+ Constants.IS_DEFAULT_ACCT.DEFAULT_ACCT + "'" + " AND "
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_MODEID + " = '"
					+ Constants.ACCOUNT_MODEID.CUSTOMER + "'";
		} else {
			sQuery = "SELECT " + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ATMCARD + "." + CARD_NAME + "," + ODS_ATM_CARD_STATUS
					+ "." + CARD_STATUS_NAME + "," + PAN + " FROM "
					+ ODS_ATMCARD + " LEFT OUTER JOIN " + ODS_ATM_CARD_STATUS
					+ " ON " + ODS_ATMCARD + "." + CARD_STATUS_ID + "="
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID + " WHERE "
					+ PAN + " = '" + pan + "'";

		}
		/*
		 * select a.account_name from ods_acct_info_table a,ods_acct_type_desc
		 * at ,ods_currency_table curr,ods_atmcard c ,ods_card_account ca where
		 * a.acct_type_id=at.acct_type_id and a.curr_code_id=curr.curr_code_id
		 * and ca.account_internal_id=a.account_internal_id and
		 * ca.card_id=c.card_id and c.pan='6273531060065826000' and
		 * a.account_modeid='1';
		 */

		List rows = template.queryForList(sQuery);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		cardInfo.value = extractCardInfo(rows);

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	private CardInfo extractCardInfo(List rows) {

		CardInfo cardInfo = new CardInfo();
		Map map;

		map = (Map) rows.get(0);
		CardInfo Tran = new CardInfo();

		Tran.setPan((AmbitUtility.nvl((String) map.get(PAN))).trim());

		Tran.setCardStatusId(Integer.parseInt(map.get(CARD_STATUS_ID)
				.toString()));

		Tran.setCardStatusDesc((AmbitUtility.nvl((String) map
				.get(CARD_STATUS_NAME))).trim());
		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			Tran.setNameAndFamilyName((AmbitUtility.nvl((String) map
					.get(ACCOUNT_NAME))).trim());
		} else {
			Tran.setNameAndFamilyName((AmbitUtility.nvl((String) map
					.get(CARD_NAME))).trim());
		}

		cardInfo = Tran;

		return cardInfo;
	}

	public boolean existPanInODSATMCard(String pan) throws Exception {
		String sQuery = "SELECT " + PAN + " FROM " + ODS_ATMCARD + " WHERE "
				+ PAN + " = '" + pan + "'";
		List list = template.queryForList(sQuery);

		return (list.size() > 0);
	}

}
