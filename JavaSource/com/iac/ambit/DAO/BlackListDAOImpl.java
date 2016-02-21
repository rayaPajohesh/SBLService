package com.iac.ambit.DAO;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import javax.xml.rpc.holders.ObjectHolder;

import org.springframework.jdbc.core.JdbcTemplate;

import com.iac.ambit.dbutil.DBConfig;
import com.iac.ambit.model.BlackList;
import com.iac.ambit.model.CardInfo;

import com.iac.ambit.service.CardInfoService;
import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.Constants;

public class BlackListDAOImpl implements BlackListDAO {

	private JdbcTemplate template;

	private CardInfoService cardInfoService;

	public CardInfoService getCardInfoService() {
		return cardInfoService;
	}

	public void setCardInfoService(CardInfoService cardInfoService) {
		this.cardInfoService = cardInfoService;
	}

	public JdbcTemplate getTemplate() {
		return template;
	}

	public void setTemplate(JdbcTemplate template) {
		this.template = template;
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

	private final String SBL_BLACKLIST = DBConfig.getProperty("SBL_BLACKLIST");

	private final String PAN = DBConfig.getProperty("PAN");

	private final String BLACKED_REASON_ID = DBConfig
			.getProperty("BLACKED_REASON_ID");

	private final String BLACKED_ACTIVE_FLAG = DBConfig
			.getProperty("BLACKED_ACTIVE_FLAG");

	private final String BLACKED_TIME = DBConfig.getProperty("BLACKED_TIME");

	private final String ALLOWED_SHETAB = DBConfig
			.getProperty("ALLOWED_SHETAB");

	private final String ALLOWED_TERMINAL_TYPES = DBConfig
			.getProperty("ALLOWED_TERMINAL_TYPES");

	private final String ALLOWED_TRANS = DBConfig.getProperty("ALLOWED_TRANS");

	private final String ALLOWED_TERMINALS = DBConfig
			.getProperty("ALLOWED_TERMINALS");

	private final String BLACKED_DATE = DBConfig.getProperty("BLACKED_DATE");

	private final String COMMENTS = DBConfig.getProperty("COMMENTS");

	private final String ODS_ATMCARD = DBConfig.getProperty("ODS_ATMCARD");

	private final String CARD_STATUS_ID = DBConfig
			.getProperty("CARD_STATUS_ID");

	private final String CARD_NAME = DBConfig.getProperty("CARD_NAME");

	private final String ODS_ATM_CARD_STATUS = DBConfig
			.getProperty("ODS_ATM_CARD_STATUS");

	private final String CARD_STATUS_NAME = DBConfig
			.getProperty("CARD_STATUS_NAME");

	private final String SBL_BLACKED_REASON = DBConfig
			.getProperty("SBL_BLACKED_REASON");

	private final String BLACKED_REASON_DESCRIPTION_FA = DBConfig
			.getProperty("BLACKED_REASON_DESCRIPTION_FA");

	private final String SBL_CODE_ACTIVE_FLAG = DBConfig
			.getProperty("SBL_CODE_ACTIVE_FLAG");

	private final String CODE_ACTIVE_FLAG = DBConfig
			.getProperty("CODE_ACTIVE_FLAG");

	private final String CODE_ACTIVE_DESCRIPTION_FA = DBConfig
			.getProperty("CODE_ACTIVE_DESCRIPTION_FA");

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

	public String searchBlackList(String fromDate, String toDate, String pan,
			String activeFlag, ObjectHolder list) throws Exception {

		String sQuery = "";
		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "SELECT " + SBL_BLACKLIST + "." + BLACKED_DATE + ","
					+ SBL_BLACKLIST + "." + BLACKED_TIME + "," + SBL_BLACKLIST
					+ "." + PAN + "," + SBL_BLACKED_REASON + "."
					+ BLACKED_REASON_DESCRIPTION_FA + ","
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_DESCRIPTION_FA
					+ "," + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_NAME + ","
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_NAME + " FROM "
					+ SBL_BLACKLIST + " , " + SBL_BLACKED_REASON + " , "
					+ SBL_CODE_ACTIVE_FLAG + " , " + ODS_ACCT_INFO_TABLE
					+ " , " + ODS_ACCT_TYPE_DESC + " , " + ODS_CURRENCY_TABLE
					+ " , " + ODS_ATMCARD + " , " + ODS_CARD_ACCOUNT + " , "
					+ ODS_ATM_CARD_STATUS + " WHERE " + SBL_BLACKLIST + "."
					+ BLACKED_REASON_ID + " = " + SBL_BLACKED_REASON + "."
					+ BLACKED_REASON_ID + " AND " + SBL_BLACKLIST + "." + PAN
					+ " = " + ODS_ATMCARD + "." + PAN + " AND " + SBL_BLACKLIST
					+ "." + BLACKED_ACTIVE_FLAG + " = " + SBL_CODE_ACTIVE_FLAG
					+ "." + CODE_ACTIVE_FLAG + " AND " + ODS_ACCT_INFO_TABLE
					+ "." + ACCT_TYPE_ID + " = " + ODS_ACCT_TYPE_DESC + "."
					+ ACCT_TYPE_ID + " AND " + ODS_ACCT_INFO_TABLE + "."
					+ CURR_CODE_ID + " = " + ODS_CURRENCY_TABLE + "."
					+ CURR_CODE_ID + " AND " + ODS_CARD_ACCOUNT + "."
					+ ACCOUNT_INTERNAL_ID + " = " + ODS_ACCT_INFO_TABLE + "."
					+ ACCOUNT_INTERNAL_ID + " AND " + ODS_CARD_ACCOUNT + "."
					+ CARD_ID + " = " + ODS_ATMCARD + "." + CARD_ID + " AND "
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID + " = "
					+ ODS_ATMCARD + "." + CARD_STATUS_ID + " AND "
					+ ODS_CARD_ACCOUNT + "." + IS_DEFAULT_ACCT + " = '"
					+ Constants.IS_DEFAULT_ACCT.DEFAULT_ACCT + "'" + " AND "
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_MODEID + " = '"
					+ Constants.ACCOUNT_MODEID.CUSTOMER + "'" + " AND ";

		} else {
			sQuery = "SELECT " + SBL_BLACKLIST + "." + BLACKED_DATE + ","
					+ SBL_BLACKLIST + "." + BLACKED_TIME + "," + SBL_BLACKLIST
					+ "." + PAN + "," + SBL_BLACKED_REASON + "."
					+ BLACKED_REASON_DESCRIPTION_FA + ","
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_DESCRIPTION_FA
					+ "," + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ATMCARD + "." + CARD_NAME + "," + ODS_ATM_CARD_STATUS
					+ "." + CARD_STATUS_NAME + " FROM " + SBL_BLACKLIST
					+ " INNER JOIN " + SBL_BLACKED_REASON + " ON "
					+ SBL_BLACKLIST + "." + BLACKED_REASON_ID + "="
					+ SBL_BLACKED_REASON + "." + BLACKED_REASON_ID
					+ " INNER JOIN " + SBL_CODE_ACTIVE_FLAG + " ON "
					+ SBL_BLACKLIST + "." + BLACKED_ACTIVE_FLAG + "="
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_FLAG
					+ " INNER JOIN " + ODS_ATMCARD + " ON " + SBL_BLACKLIST
					+ "." + PAN + "=" + ODS_ATMCARD + "." + PAN
					+ " LEFT OUTER JOIN " + ODS_ATM_CARD_STATUS + " ON "
					+ ODS_ATMCARD + "." + CARD_STATUS_ID + "="
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID + " WHERE ";
		}

		String whereClause = "";

		if (!AmbitUtility.isEmpty(pan)) {
			whereClause = SBL_BLACKLIST + "." + PAN + " = '" + pan + "' ";
		}

		if (!activeFlag.equals("")) {
			if (!AmbitUtility.isEmpty(whereClause)) {
				whereClause = whereClause + " AND ";
			}

			whereClause = whereClause + SBL_BLACKLIST + "."
					+ BLACKED_ACTIVE_FLAG + " = '" + activeFlag + "' ";
		}

		if (!fromDate.equals("") && !toDate.equals("")) {
			if (!AmbitUtility.isEmpty(whereClause)) {
				whereClause = whereClause + " AND ";
			}

			whereClause = whereClause + SBL_BLACKLIST + "." + BLACKED_DATE
					+ " BETWEEN " + " '" + fromDate + "' AND '" + toDate + "' ";
		}

		sQuery = sQuery + whereClause + " ORDER BY " + SBL_BLACKLIST + "."
				+ BLACKED_DATE + " desc";

		List rows = template.queryForList(sQuery);
		list.value = extractBlackList(rows);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		return Constants.RESPONSE_CODE.SUCCEED;
	}

	private BlackList[] extractBlackList(List rows) {

		BlackList[] blacks = new BlackList[rows.size()];
		Map map;

		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);
			BlackList black = new BlackList();

			black.setBlackedActiveFlag((AmbitUtility.nvl((String) map
					.get(CODE_ACTIVE_DESCRIPTION_FA))).trim());

			black.setBlackedDate((AmbitUtility.nvl((String) map
					.get(BLACKED_DATE))).trim());

			black.setBlackedReasonId((AmbitUtility.nvl((String) map
					.get(BLACKED_REASON_DESCRIPTION_FA))).trim());

			black.setBlackedTime((AmbitUtility.nvl((String) map
					.get(BLACKED_TIME))).trim());

			black.setPan((AmbitUtility.nvl((String) map.get(PAN))).trim());

			black.setAllowedShetab((AmbitUtility.nvl((String) map
					.get(ALLOWED_SHETAB))).trim());

			black.setAllowedTerminals((AmbitUtility.nvl((String) map
					.get(ALLOWED_TERMINALS))).trim());

			black.setAllowedTerminalTypes((AmbitUtility.nvl((String) map
					.get(ALLOWED_TERMINAL_TYPES))).trim());

			black.setAllowedTrans((AmbitUtility.nvl((String) map
					.get(ALLOWED_TRANS))).trim());

			black.setComments((AmbitUtility.nvl((String) map.get(COMMENTS)))
					.trim());
			black.setHighlight(Constants.CODE_ACTIVE_FLAG.INACTIVE);
			CardInfo cardInfo = new CardInfo();
			cardInfo.setCardStatusId(Integer.parseInt(AmbitUtility.nvl(map.get(
					CARD_STATUS_ID).toString())));
			cardInfo.setCardStatusDesc((AmbitUtility.nvl((String) map
					.get(CARD_STATUS_NAME))).trim());

			String dataSource = Config.getProperty("CURRENT_DATABASE");
			if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
				cardInfo.setNameAndFamilyName((AmbitUtility.nvl((String) map
						.get(ACCOUNT_NAME))).trim());
			} else {
				cardInfo.setNameAndFamilyName((AmbitUtility.nvl((String) map
						.get(CARD_NAME))).trim());

			}

			black.setCardInfo(cardInfo);

			blacks[i] = black;
		}
		return blacks;
	}

	private BlackList extractBlackList2(List rows) {

		BlackList bl = new BlackList();
		Map map;

		for (int i = 0; i < rows.size(); i++) {
			map = (Map) rows.get(i);

			bl.setBlackedActiveFlag((AmbitUtility.nvl((String) map
					.get(BLACKED_ACTIVE_FLAG))).trim());

			bl
					.setBlackedDate((AmbitUtility.nvl((String) map
							.get(BLACKED_DATE))).trim());

			bl.setBlackedReasonId((AmbitUtility.nvl((String) map
					.get(BLACKED_REASON_ID))).trim());

			bl
					.setBlackedTime((AmbitUtility.nvl((String) map
							.get(BLACKED_TIME))).trim());

			bl.setPan((AmbitUtility.nvl((String) map.get(PAN))).trim());

			bl.setAllowedShetab((AmbitUtility.nvl((String) map
					.get(ALLOWED_SHETAB))).trim());

			bl.setAllowedTerminals((AmbitUtility.nvl((String) map
					.get(ALLOWED_TERMINALS))).trim());

			bl.setAllowedTerminalTypes((AmbitUtility.nvl((String) map
					.get(ALLOWED_TERMINAL_TYPES))).trim());

			bl.setAllowedTrans((AmbitUtility.nvl((String) map
					.get(ALLOWED_TRANS))).trim());

			bl.setComments((AmbitUtility.nvl((String) map.get(COMMENTS)))
					.trim());

			CardInfo cardInfo = new CardInfo();
			cardInfo.setCardStatusId(Integer.parseInt(AmbitUtility.nvl(map.get(
					CARD_STATUS_ID).toString())));
			cardInfo.setCardStatusDesc((AmbitUtility.nvl((String) map
					.get(CARD_STATUS_NAME))).trim());
			cardInfo.setNameAndFamilyName((AmbitUtility.nvl((String) map
					.get(ACCOUNT_NAME))).trim());
			bl.setCardInfo(cardInfo);

		}
		return bl;
	}

	public String updateBlackList(BlackList blackList) throws Exception {

		String sQuery = "UPDATE " + SBL_BLACKLIST + " SET " + BLACKED_REASON_ID
				+ " = ?," + BLACKED_ACTIVE_FLAG + " = ?," + BLACKED_DATE
				+ " = ?," + BLACKED_TIME + " = ?," + ALLOWED_SHETAB + " = ?,"
				+ ALLOWED_TERMINAL_TYPES + " = ?," + ALLOWED_TRANS + " = ?,"
				+ ALLOWED_TERMINALS + " = ?," + COMMENTS + " = ? " + " WHERE "
				+ PAN + " = ? ";

		int result = this.template.update(sQuery, new Object[] {
				AmbitUtility.nvl(blackList.getBlackedReasonId()),
				AmbitUtility.nvl(blackList.getBlackedActiveFlag()),
				AmbitUtility.nvl(blackList.getBlackedDate()),
				AmbitUtility.nvl(blackList.getBlackedTime()),
				AmbitUtility.nvl(blackList.getAllowedShetab()),
				AmbitUtility.nvl(blackList.getAllowedTerminalTypes()),
				AmbitUtility.nvl(blackList.getAllowedTrans()),
				AmbitUtility.nvl(blackList.getAllowedTerminals()),
				AmbitUtility.nvl(blackList.getComments()),
				AmbitUtility.nvl(blackList.getPan()) });

		if (result == 1)
			return Constants.RESPONSE_CODE.SUCCEED;
		else
			return Constants.RESPONSE_CODE.INTERNAL_ERROR;

	}

	public String updateBlackListForLimitationCard(BlackList blackList)
			throws Exception {

		String sQuery = "UPDATE " + SBL_BLACKLIST + " SET " + ALLOWED_SHETAB
				+ " = ?," + ALLOWED_TERMINAL_TYPES + " = ?," + ALLOWED_TRANS
				+ " = ?," + ALLOWED_TERMINALS + " = ?," + COMMENTS + " = ? "
				+ " WHERE " + PAN + " = ? ";

		int result = this.template.update(sQuery, new Object[] {
				AmbitUtility.nvl(blackList.getAllowedShetab()),
				AmbitUtility.nvl(blackList.getAllowedTerminalTypes()),
				AmbitUtility.nvl(blackList.getAllowedTrans()),
				AmbitUtility.nvl(blackList.getAllowedTerminals()),
				AmbitUtility.nvl(blackList.getComments()),
				AmbitUtility.nvl(blackList.getPan()) });

		if (result == 1)
			return Constants.RESPONSE_CODE.SUCCEED;
		else
			return Constants.RESPONSE_CODE.INTERNAL_ERROR;

	}

	public String searchPanInBlackList(String pan, ObjectHolder blackList)
			throws Exception {

		String sQuery = "";

		String dataSource = Config.getProperty("CURRENT_DATABASE");
		if (dataSource.equalsIgnoreCase(Constants.CURRENT_DATABASE.ORACLE)) {
			sQuery = "SELECT " + SBL_BLACKLIST + "." + BLACKED_DATE + ","
					+ SBL_BLACKLIST + "." + ALLOWED_SHETAB + ","
					+ SBL_BLACKLIST + "." + ALLOWED_TERMINAL_TYPES + ","
					+ SBL_BLACKLIST + "." + ALLOWED_TRANS + "," + SBL_BLACKLIST
					+ "." + ALLOWED_TERMINALS + "," + SBL_BLACKLIST + "."
					+ COMMENTS + "," + SBL_BLACKLIST + "." + BLACKED_TIME + ","
					+ SBL_BLACKLIST + "." + PAN + "," + SBL_BLACKLIST + "."
					+ BLACKED_ACTIVE_FLAG + "," + SBL_BLACKLIST + "."
					+ BLACKED_REASON_ID + "," + SBL_BLACKED_REASON + "."
					+ BLACKED_REASON_DESCRIPTION_FA + ","
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_DESCRIPTION_FA
					+ "," + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_NAME + ","
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_NAME + " FROM "
					+ SBL_BLACKLIST + "," + SBL_BLACKED_REASON + ","
					+ SBL_CODE_ACTIVE_FLAG + "," + ODS_ACCT_INFO_TABLE + ","
					+ ODS_ACCT_TYPE_DESC + "," + ODS_CURRENCY_TABLE + ","
					+ ODS_ATMCARD + "," + ODS_CARD_ACCOUNT + ","
					+ ODS_ATM_CARD_STATUS + " WHERE " + SBL_BLACKLIST + "."
					+ PAN + " = " + ODS_ATMCARD + "." + PAN + " AND "
					+ SBL_BLACKLIST + "." + BLACKED_REASON_ID + " = "
					+ SBL_BLACKED_REASON + "." + BLACKED_REASON_ID + " AND "
					+ SBL_BLACKLIST + "." + BLACKED_ACTIVE_FLAG + " = "
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_FLAG + " AND "
					+ SBL_BLACKLIST + "." + PAN + " = " + ODS_ATMCARD + "."
					+ PAN + " AND " + ODS_ATMCARD + "." + CARD_STATUS_ID
					+ " = " + ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID
					+ " AND " + ODS_ACCT_INFO_TABLE + "." + ACCT_TYPE_ID
					+ " = " + ODS_ACCT_TYPE_DESC + "." + ACCT_TYPE_ID + " AND "
					+ ODS_ACCT_INFO_TABLE + "." + CURR_CODE_ID + " = "
					+ ODS_CURRENCY_TABLE + "." + CURR_CODE_ID + " AND "
					+ ODS_CARD_ACCOUNT + "." + ACCOUNT_INTERNAL_ID + " = "
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_INTERNAL_ID + " AND "
					+ ODS_CARD_ACCOUNT + "." + CARD_ID + " = " + ODS_ATMCARD
					+ "." + CARD_ID + " AND " + ODS_ATM_CARD_STATUS + "."
					+ CARD_STATUS_ID + " = " + ODS_ATMCARD + "."
					+ CARD_STATUS_ID + " AND " + SBL_BLACKLIST + "." + PAN
					+ " ='" + pan + "' " + " AND " + ODS_CARD_ACCOUNT + "."
					+ IS_DEFAULT_ACCT + " = '"
					+ Constants.IS_DEFAULT_ACCT.DEFAULT_ACCT + "'" + " AND "
					+ ODS_ACCT_INFO_TABLE + "." + ACCOUNT_MODEID + " = '"
					+ Constants.ACCOUNT_MODEID.CUSTOMER + "'";
		} else {
			sQuery = "SELECT " + SBL_BLACKLIST + "." + BLACKED_DATE + ","
					+ SBL_BLACKLIST + "." + ALLOWED_SHETAB + ","
					+ SBL_BLACKLIST + "." + ALLOWED_TERMINAL_TYPES + ","
					+ SBL_BLACKLIST + "." + ALLOWED_TRANS + "," + SBL_BLACKLIST
					+ "." + ALLOWED_TERMINALS + "," + SBL_BLACKLIST + "."
					+ COMMENTS + "," + SBL_BLACKLIST + "." + BLACKED_TIME + ","
					+ SBL_BLACKLIST + "." + PAN + "," + SBL_BLACKLIST + "."
					+ BLACKED_ACTIVE_FLAG + "," + SBL_BLACKLIST + "."
					+ BLACKED_REASON_ID + "," + SBL_BLACKED_REASON + "."
					+ BLACKED_REASON_DESCRIPTION_FA + ","
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_DESCRIPTION_FA
					+ "," + ODS_ATMCARD + "." + CARD_STATUS_ID + ","
					+ ODS_ATMCARD + "." + CARD_NAME + "," + ODS_ATM_CARD_STATUS
					+ "." + CARD_STATUS_NAME + " FROM " + SBL_BLACKLIST
					+ " INNER JOIN " + SBL_BLACKED_REASON + " ON "
					+ SBL_BLACKLIST + "." + BLACKED_REASON_ID + "="
					+ SBL_BLACKED_REASON + "." + BLACKED_REASON_ID
					+ " INNER JOIN " + SBL_CODE_ACTIVE_FLAG + " ON "
					+ SBL_BLACKLIST + "." + BLACKED_ACTIVE_FLAG + "="
					+ SBL_CODE_ACTIVE_FLAG + "." + CODE_ACTIVE_FLAG
					+ " INNER JOIN " + ODS_ATMCARD + " ON " + SBL_BLACKLIST
					+ "." + PAN + "=" + ODS_ATMCARD + "." + PAN
					+ " LEFT OUTER JOIN " + ODS_ATM_CARD_STATUS + " ON "
					+ ODS_ATMCARD + "." + CARD_STATUS_ID + "="
					+ ODS_ATM_CARD_STATUS + "." + CARD_STATUS_ID + " WHERE "
					+ SBL_BLACKLIST + "." + PAN + " ='" + pan + "' ";
		}

		List rows = template.queryForList(sQuery);
		blackList.value = extractBlackList2(rows);

		if (rows.size() == 0) {
			return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
		}

		return Constants.RESPONSE_CODE.SUCCEED;

	}

	private boolean existPanInBlackListr(String pan) throws Exception {
		String sQuery = "SELECT " + PAN + " FROM " + SBL_BLACKLIST + " WHERE "
				+ PAN + " = '" + pan + "'";
		List list = template.queryForList(sQuery);

		return (list.size() > 0);
	}

	public String addBlackList(BlackList blackList) throws Exception {
		if (!existPanInBlackListr(blackList.getPan())
				&& this.getCardInfoService().existPanInODSATMCard(
						blackList.getPan())) {
			String sQuery = "INSERT INTO " + SBL_BLACKLIST + " (" + PAN + ","
					+ BLACKED_REASON_ID + "," + BLACKED_ACTIVE_FLAG + ","
					+ BLACKED_DATE + "," + BLACKED_TIME + "," + ALLOWED_SHETAB
					+ "," + ALLOWED_TERMINAL_TYPES + "," + ALLOWED_TRANS + ","
					+ ALLOWED_TERMINALS + "," + COMMENTS + ")"
					+ " VALUES (?,?,?,?,?,?,?,?,?,?)";
			this.template.update(sQuery, new Object[] {
					AmbitUtility.nvl(blackList.getPan()),
					AmbitUtility.nvl(blackList.getBlackedReasonId()),
					AmbitUtility.nvl(blackList.getBlackedActiveFlag()),
					AmbitUtility.nvl(blackList.getBlackedDate()),
					AmbitUtility.nvl(blackList.getBlackedTime()),
					AmbitUtility.nvl(blackList.getAllowedShetab()),
					AmbitUtility.nvl(blackList.getAllowedTerminalTypes()),
					AmbitUtility.nvl(blackList.getAllowedTrans()),
					AmbitUtility.nvl(blackList.getAllowedTerminals()),
					AmbitUtility.nvl(blackList.getComments()) });
		} else {

			if (existPanInBlackListr(blackList.getPan())) {

				return Constants.RESPONSE_CODE.DUPLICATE_RECORD;

			} else {
				return Constants.RESPONSE_CODE.RECORD_NOT_FOUND;
			}

		}

		return (Constants.RESPONSE_CODE.SUCCEED);
	}

	public String inactivatePanInBlackList(String pan) throws Exception {

		String sQuery = "UPDATE " + SBL_BLACKLIST + " SET "
				+ BLACKED_ACTIVE_FLAG + " = "
				+ Constants.CODE_ACTIVE_FLAG.INACTIVE + " WHERE " + PAN
				+ " = ? ";

		int result = this.template.update(sQuery, new Object[] { AmbitUtility
				.nvl(pan) });
		if (result == 1)
			return Constants.RESPONSE_CODE.SUCCEED;
		else
			return Constants.RESPONSE_CODE.INTERNAL_ERROR;

	}
}
