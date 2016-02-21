package com.iac.ambit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import org.apache.commons.lang.StringUtils;

import com.iac.ambit.utils.AmbitUtility;
import com.iac.ambit.utils.Config;
import com.iac.ambit.utils.EscapeInputUtility;


public class ActivityLog implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String activityDate;
	private String activityFromDate;
	private String activityToDate;

	private String activityTime;	

	private String activityDescription;
	
	private String activityDescriptionFa;


	private String activityTypeName;
	private String activityTypeNameFa;

	private String userId;

	private String activityTypeId;

	
	//becuase do not allow convert nuul to integer 
	private String aggregate;
	
	
//	jazimagh : 1386/07/16
	public final Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
	
	private final void writeObject(ObjectOutputStream out) throws IOException{
		throw new IOException("Object cannot be serialized");
	}
	
	private final void readObject(ObjectInputStream in) throws IOException{
		throw new IOException ("Class cannot be Deserialized");
	}
//	jazimagh : 1386/07/16 
	public ActivityLog() {

	}



	public void setActivityDescription(String activityDescription) {
		this.activityDescription = activityDescription;

	}

	public String getActivityDescription() {
		return activityDescription;

	}
	
	public void setActivityDescriptionFa(String activityDescriptionFa) {
		this.activityDescriptionFa = activityDescriptionFa;

	}

	public String getActivityDescriptionFa() {
		return this.activityDescriptionFa;

	}

	public void setActivityTypeId(String activityTypeId) {
		this.activityTypeId = activityTypeId;

	}

	public String getActivityTypeId() {
		return activityTypeId;

	}

	public void setActivityTypeName(String activityTypeName) {
		this.activityTypeName = activityTypeName;

	}

	public String getActivityTypeName() {
		return activityTypeName;

	}
	
	public void setActivityTypeNameFa(String activityTypeNameFa) {
		this.activityTypeNameFa = activityTypeNameFa;

	}
	public String getActivityTypeNameFa() {
		return activityTypeNameFa;

	}
	
	public void setActivityToDate(String activityToDate) {
		this.activityToDate = activityToDate;

	}
	public String getActivityToDate() {
		return activityToDate;

	}
	
	public void setActivityFromDate(String activityFromDate) {
		this.activityFromDate = activityFromDate;

	}
	public String getActivityFromDate() {
		return activityFromDate;

	}

	public void setActivityDate(String activityDate) {
		this.activityDate = activityDate;

	}

	public String getActivityDate() {
		return activityDate;

	}

	public void setActivityTime(String activityTime) {
		this.activityTime = activityTime;

	}

	public String getActivityTime() {
		return activityTime;

	}

	

	public void setUserId(String userId) {
		this.userId = userId;

	}

	public String getUserId() {
		return this.userId;

	}

	public String getAggregate() {
		return aggregate;
	}

	public void setAggregate(String aggregate) {
		this.aggregate = aggregate;
	}


	public boolean isDataValid() {

		if (!AmbitUtility.isEmpty(this.activityTypeId)
				&& (!AmbitUtility.isAlphanumeric(this.activityTypeId, 10, 1) || !EscapeInputUtility.isValideInput(this.activityTypeId)))
			return false;
		if (!AmbitUtility.isAlphanumeric(this.userId, 50, 1) || !EscapeInputUtility.isValideInput(this.userId))
			return false;
		if (!AmbitUtility.isEmpty(this.activityFromDate)
				&& (!AmbitUtility.isAlphanumeric(this.activityFromDate, 10, 1) || !EscapeInputUtility.isValideInput(this.activityFromDate)))
			return false;
		if (!AmbitUtility.isEmpty(this.activityToDate)
				&& (!AmbitUtility.isAlphanumeric(this.activityToDate, 10, 1) || !EscapeInputUtility.isValideInput(this.activityToDate)))
			return false;
		if (!AmbitUtility.isEmpty(this.activityDescription)
				&&  !EscapeInputUtility.isValideInput(this.activityDescription))
			return false;
		if (!AmbitUtility.isEmpty(this.activityDescriptionFa)
				&&  !EscapeInputUtility.isValideInput(this.activityDescriptionFa))
			return false;
		if (!AmbitUtility.isEmpty(this.activityTypeName)
				&& !EscapeInputUtility.isValideInput(this.activityTypeName))
			return false;
		if (!AmbitUtility.isEmpty(this.activityTypeNameFa)
				&& !EscapeInputUtility.isValideInput(this.activityTypeNameFa))
			return false;
		
		return true;

	}

}
