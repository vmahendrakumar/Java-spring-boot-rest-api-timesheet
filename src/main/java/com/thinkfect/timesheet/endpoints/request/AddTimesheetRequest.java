package com.thinkfect.timesheet.endpoints.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;

import com.thinkfect.timesheet.constants.TimesheetConstants;
import com.thinkfect.timesheet.domain.UserEffort;

/**
 * @author Ratul Bhattacharya
 */

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class AddTimesheetRequest {

	//@NotNull
	//private Map<String, List<DayEffort>> efforts;

	private String id;
	
	private boolean active = true;
	
	@NotNull
	private String memberId;
	
	private float totalHours=0;
	
	private String sheetStatus=TimesheetConstants.TIMESHEET_STATUS_DRAFT;
	
	private Date startDate;
	
	private Date endDate;
	
	private String selectedPeriodDisplayText;
	
	private List<UserEffortRequest> userEfforts;

}
