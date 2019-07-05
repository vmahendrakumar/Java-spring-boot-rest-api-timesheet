package com.thinkfect.timesheet.endpoints;

import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonView;
import com.thinkfect.timesheet.domain.Effort;
import com.thinkfect.timesheet.domain.Project;
import com.thinkfect.timesheet.domain.Task;
import com.thinkfect.timesheet.domain.Timesheet;
import com.thinkfect.timesheet.domain.TimesheetReport;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

/**
 * The Response Object returned by the Task Controller. It can contain the Task
 * Resource on an ApiException
 */
@ToString
@Builder
@Data
public class Response {

	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private Task task;

	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private List<Task> tasks;

	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private List<Project> projects;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private Project project;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private List<Effort> efforts;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private Timesheet timesheet;

	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private List<Timesheet> timesheets;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private Optional<Timesheet> timesheetsOptional;
	
	@JsonUnwrapped
	@JsonInclude(Include.NON_NULL)
	@JsonView(ResponseView.Response.class)
	private List<TimesheetReport> timesheetReports;

}
