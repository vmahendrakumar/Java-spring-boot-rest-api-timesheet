package com.thinkfect.timesheet.domain;

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
import org.springframework.data.redis.core.index.Indexed;

import com.thinkfect.timesheet.constants.TimesheetConstants;

/**
 * @author Ratul Bhattacharya
 */

@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TimesheetReport {

	private String project;
	
	private String task;
	
	private String comment;
	
	private String memberId;
	
	private Float hours;
	
	private Date entryDate;
	
	
}
