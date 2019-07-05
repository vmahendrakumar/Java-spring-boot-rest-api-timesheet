package com.thinkfect.timesheet.endpoints.request;

import java.util.List;

import com.thinkfect.timesheet.domain.Timesheet;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class UserEffortRequest {
	
	private String project;
	
	private String task;
	
	private String comment;
	
	private List<Float> hours;
	
	private Float total;
	
	/*
	@NotNull 	
	private String hours;

	@NotNull 
	private String type;
	
	@NotNull 
	private String description;
	
	@NotNull 
	private String projectId;
	
	@NotNull 
	private String taskId;
	
	@NotNull 
	private String week;
	*/

}
