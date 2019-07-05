package com.thinkfect.timesheet.constants;

import com.google.common.collect.ImmutableList;

public class TimesheetConstants {

	public static final String TIMESHEET_STATUS_DRAFT = "Draft";
	
	public static final String TIMESHEET_STATUS_SUBMITTED = "Submitted";
	
	public static final String TIMESHEET_STATUS_APPROVED = "Approved";
	
	public static final String TIMESHEET_STATUS_REJECTED = "Rejected";
	
	public static final ImmutableList<String> TIMESHEET_STATUS_LIST = 
			ImmutableList.of(TIMESHEET_STATUS_DRAFT, TIMESHEET_STATUS_SUBMITTED, 
					TIMESHEET_STATUS_APPROVED, TIMESHEET_STATUS_REJECTED);
	
	
}
