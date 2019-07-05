package com.thinkfect.timesheet.endpoints.request;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@NoArgsConstructor
@ToString
public class DayEffort {
	
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

}
