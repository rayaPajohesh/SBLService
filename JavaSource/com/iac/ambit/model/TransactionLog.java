package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.xml.rpc.holders.Holder;

public class TransactionLog implements Holder,Serializable { 
	
	private static final long serialVersionUID = 1L;

	//	 jazimagh : 1386/07/16
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
	
	public TransactionLog[] value;
	private String  transactionLog;
	private String  logId;
	private String  receiveTime;
	private String  acquirerNetworkId;
	private String  acqSubNetworkId;
	private String  forwardingNetworkId;
	private String  transactionCode;
	private String  messageType;
	private String  pan;
	private String  amount;
	private String  stan;
	private String  transactionTime;
	private String  transactionDate;
	private String  acquirerIMD;
	private String  forwardingIMD;
	private String  retrievalReferenceNumber;
	private String  responseCode;
	private String  merchantId;
	private String  merchantNameAndLocation;
	private String  additionalData; 
	private String  currencyCode;
	private String  sourceAccount;
	private String  destinationAccount;
	private String  errorCode;
	private String  branchCode;
	private String  settlementDate;
	private String  statusId;
	private String  terminalId;
	private String  terminalTypeId;
	private String  srcActualBal;
	private String  srcAvailableBal;
	private String  feeAmount;
	private String  destActualBal;
	private String  destAvailabeBal;
	private String  pan2;
	private String  receivingNetworkId;
	private String  receivingIMD;
	private String  foreignCurrencyCod;
	private String  foreign_Amount_Tran;
	private String  appearanceStatus;

	
	
	public  TransactionLog() {
		
	}
	
	public TransactionLog(TransactionLog[] val) {
		value = val;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public String getAcqSubNetworkId() {
		return acqSubNetworkId;
	}
	public void setAcqSubNetworkId(String acqSubNetworkId) {
		this.acqSubNetworkId = acqSubNetworkId;
	}
	public String getAcquirerIMD() {
		return acquirerIMD;
	}
	public void setAcquirerIMD(String acquirerIMD) {
		this.acquirerIMD = acquirerIMD;
	}

	public String getAcquirerNetworkId() {
		return acquirerNetworkId;
	}
	public void setAcquirerNetworkId(String acquirerNetworkId) {
		this.acquirerNetworkId = acquirerNetworkId;
	}
	public String getAdditionalData() {
		return additionalData;
	}
	public void setAdditionalData(String additionalData) {
		this.additionalData = additionalData;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public String getReceiveTime() {
		return receiveTime;
	}
	public void setReceiveTime(String receiveTime) {
		this.receiveTime = receiveTime;
	}
	public String getLogId() {
		return logId;
	}
	public void setLogId(String logId) {
		this.logId = logId;
	}
	public String getTransactionLog() {
		return transactionLog;
	}
	public void setTransactionLog(String transactionLog) {
		this.transactionLog = transactionLog;
	}
	public String getCurrencyCode() {
		return currencyCode;
	}
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	public String getDestActualBal() {
		return destActualBal;
	}
	public void setDestActualBal(String destActualBal) {
		this.destActualBal = destActualBal;
	}
	public String getDestAvailabeBal() {
		return destAvailabeBal;
	}
	public void setDestAvailabeBal(String destAvailabeBal) {
		this.destAvailabeBal = destAvailabeBal;
	}

	public String getDestinationAccount() {
		return destinationAccount;
	}
	public void setDestinationAccount(String destinationAccount) {
		this.destinationAccount = destinationAccount;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getFeeAmount() {
		return feeAmount;
	}
	public void setFeeAmount(String feeAmount) {
		this.feeAmount = feeAmount;
	}
	public String getForeign_Amount_Tran() {
		return foreign_Amount_Tran;
	}
	public void setForeign_Amount_Tran(String foreign_Amount_Tran) {
		this.foreign_Amount_Tran = foreign_Amount_Tran;
	}
	public String getForeignCurrencyCod() {
		return foreignCurrencyCod;
	}
	public void setForeignCurrencyCod(String foreignCurrencyCod) {
		this.foreignCurrencyCod = foreignCurrencyCod;
	}
	public String getForwardingIMD() {
		return forwardingIMD;
	}
	public void setForwardingIMD(String forwardingIMD) {
		this.forwardingIMD = forwardingIMD;
	}

	public String getForwardingNetworkId() {
		return forwardingNetworkId;
	}
	public void setForwardingNetworkId(String forwardingNetworkId) {
		this.forwardingNetworkId = forwardingNetworkId;
	}

	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getMerchantNameAndLocation() {
		return merchantNameAndLocation;
	}
	public void setMerchantNameAndLocation(String merchantNameAndLocation) {
		this.merchantNameAndLocation = merchantNameAndLocation;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	public String getPan2() {
		return pan2;
	}
	public void setPan2(String pan2) {
		this.pan2 = pan2;
	}
	public String getReceivingIMD() {
		return receivingIMD;
	}
	public void setReceivingIMD(String receivingIMD) {
		this.receivingIMD = receivingIMD;
	}
	public String getReceivingNetworkId() {
		return receivingNetworkId;
	}
	public void setReceivingNetworkId(String receivingNetworkId) {
		this.receivingNetworkId = receivingNetworkId;
	}
	public String getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}
	public String getRetrievalReferenceNumber() {
		return retrievalReferenceNumber;
	}
	public void setRetrievalReferenceNumber(String retrievalReferenceNumber) {
		this.retrievalReferenceNumber = retrievalReferenceNumber;
	}
	public String getSettlementDate() {
		return settlementDate;
	}
	public void setSettlementDate(String settlementDate) {
		this.settlementDate = settlementDate;
	}
	public String getSourceAccount() {
		return sourceAccount;
	}
	public void setSourceAccount(String sourceAccount) {
		this.sourceAccount = sourceAccount;
	}
	public String getSrcActualBal() {
		return srcActualBal;
	}
	public void setSrcActualBal(String srcActualBal) {
		this.srcActualBal = srcActualBal;
	}
	public String getSrcAvailableBal() {
		return srcAvailableBal;
	}
	public void setSrcAvailableBal(String srcAvailableBal) {
		this.srcAvailableBal = srcAvailableBal;
	}

	public String getStan() {
		return stan;
	}
	public void setStan(String stan) {
		this.stan = stan;
	}
	public String getStatusId() {
		return statusId;
	}
	public void setStatusId(String statusId) {
		this.statusId = statusId;
	}
	public String getTerminalId() {
		return terminalId;
	}
	public void setTerminalId(String terminalId) {
		this.terminalId = terminalId;
	}
	public String getTerminalTypeId() {
		return terminalTypeId;
	}
	public void setTerminalTypeId(String terminalTypeId) {
		this.terminalTypeId = terminalTypeId;
	}
	public String getTransactionCode() {
		return transactionCode;
	}
	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}
	public String getTransactionDate() {
		return transactionDate;
	}
	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}
	public String getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(String transactionTime) {
		this.transactionTime = transactionTime;
	}
	public String getAppearanceStatus() {
		return appearanceStatus;
	}
	public void setAppearanceStatus(String appearanceStatus) {
		this.appearanceStatus = appearanceStatus;
	}
	
	}
