package com.thinkfect.timesheet.endpoints;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.thinkfect.timesheet.domain.Effort;
import com.thinkfect.timesheet.domain.Project;
import com.thinkfect.timesheet.domain.Task;
import com.thinkfect.timesheet.domain.Timesheet;
import com.thinkfect.timesheet.domain.TimesheetReport;
import com.thinkfect.timesheet.domain.UserEffort;
import com.thinkfect.timesheet.endpoints.request.AddEffortRequest;
import com.thinkfect.timesheet.endpoints.request.AddProjectRequest;
import com.thinkfect.timesheet.endpoints.request.AddTaskRequest;
import com.thinkfect.timesheet.endpoints.request.AddTimesheetRequest;
import com.thinkfect.timesheet.endpoints.request.DayEffort;
import com.thinkfect.timesheet.endpoints.request.TimesheetReportRequest;
import com.thinkfect.timesheet.endpoints.request.UserEffortRequest;
import com.thinkfect.timesheet.repository.EffortRepository;
import com.thinkfect.timesheet.repository.ProjectRepository;
import com.thinkfect.timesheet.repository.TasktRepository;
import com.thinkfect.timesheet.repository.TimesheetRepository;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@CrossOrigin
@RequestMapping(value = "/timesheet")
public class GlobalController {

	@Autowired
	ProjectRepository projectRepository;

	@Autowired
	TasktRepository taskRepository;

	@Autowired
	EffortRepository effortRepository;
	
	@Autowired
	TimesheetRepository timesheetRepository;

	@Autowired
	ResponseBuilder responseBuilder;

	private static final Logger logger = LoggerFactory.getLogger(GlobalController.class);

	@RequestMapping(value = "projects", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@CrossOrigin
	@ApiOperation(value = "Returns all active project", response = Response.class, httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> projects() {
		logger.info("Inside get all projects");

		return responseBuilder.buildResponse(Response.builder().projects(projectRepository.findAll()).build(),
				HttpStatus.OK);

	}

	@RequestMapping(value = "tasks", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@CrossOrigin
	@ApiOperation(value = "Returns all active project", response = Response.class, httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> tasks() {
		logger.info("Inside get all tasks");

		return responseBuilder.buildResponse(Response.builder().tasks(taskRepository.findAll()).build(),
				HttpStatus.OK);

	}

	@RequestMapping(value = "efforts", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@CrossOrigin
	@ApiOperation(value = "Returns all  efforts", response = Response.class, httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> efforts() {
		logger.info("Inside get all efforts");

		return responseBuilder.buildResponse(Response.builder().efforts(effortRepository.findAll()).build(),
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "timesheets", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@CrossOrigin
	@ApiOperation(value = "Returns all active timesheets", response = Response.class, httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> timesheets() {
		logger.info("Inside get all timesheets");

		return responseBuilder.buildResponse(Response.builder().timesheets(timesheetRepository.findAll()).build(),
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "timesheets/{id}", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.GET)
	@CrossOrigin
	@ApiOperation(value = "Returns timesheet by id", response = Response.class, httpMethod = "GET")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> getTimesheetById(@PathVariable("id") String id) {
		logger.info("Inside get timesheet by id.");

		return responseBuilder.buildResponse(Response.builder().timesheetsOptional(timesheetRepository.findById(id)).build(),
				HttpStatus.OK);

	}
	
	@RequestMapping(value = "reports", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
	@CrossOrigin
	@ApiOperation(value = "Returns all active timesheets matching the given criteria", response = Response.class, httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Response.class) })
	public ResponseEntity<Response> timesheetReport(@RequestBody @Valid TimesheetReportRequest request) {
		logger.info("Inside get timesheetReport");
		
		List<Timesheet> timesheets = timesheetRepository.findAll();
		List<TimesheetReport> timesheetReports = new ArrayList<TimesheetReport>();
		Date requestStartDate = request.getStartDate();
		Date requestEndDate = request.getEndDate();
		
		/*
		 * Logic:
		 *  Each timesheet has a startDate, endDate, list of UserEffort etc.
		 *  UserEffort has a list of Hours. 
		 *  	Hour.get(0) represents hours entered by user for startDate.
		 *  	Hour.get(1) represents hours entered by user for startDate + 1, and so on until endDate.
		 *   
		 *  For each timesheet, start process all its date from sheetStartDate to sheetEndDate
		 *  Check if this date falls within the date range requested by the user
		 *  If the date matches, see if there are any valid hours (i.e. > 0) available for that date in the timesheet 
		 */
		
		/*
		if(requestStartDate!=null && requestEndDate!=null) {
			
			timesheets.forEach( (timesheet) -> {
				Date sheetStartDate = timesheet.getStartDate();
				Date sheetEndDate = timesheet.getEndDate();
				Date sheetDate = timesheet.getStartDate();
				dateIndex = 0;				
				
				// Process each date inside a timesheet and check if it falls within request start & end date range.				
				if(sheetDate != null) {
					
					while(sheetDate.before(sheetEndDate) || sheetDate.equals(sheetEndDate)) {
						
						boolean isDateAfter = sheetDate.equals(requestStartDate) || sheetDate.after(requestStartDate);
						boolean isDateBefore = sheetDate.before(requestEndDate) || sheetDate.equals(requestEndDate);
						
						if(isDateBefore && isDateAfter)	{
							timesheet.getUserEfforts().forEach( (userEffort) -> {
								List<Float> hours = userEffort.getHours();
								if(hours != null && hours.size() > dateIndex) {
									// This represents the hours entered for date at index=dateIndex 
									Float itemHour = hours.get(dateIndex);
									if(itemHour > 0) {
										TimesheetReport timesheetReport = new TimesheetReport();
										
										timesheetReport.setMemberId(timesheet.getMemberId());
										timesheetReport.setProject(userEffort.getProject());
										timesheetReport.setTask(userEffort.getTask());
										timesheetReport.setComment(userEffort.getComment());
										timesheetReport.setEntryDate(sheetDate);
										timesheetReport.setHours(itemHour);
										
										timesheetReports.add(timesheetReport);
									}
								}
								
							});
						}
						
						//dateIndex++;
						sheetDate = addDays(sheetDate, 1);
					}
				}
			});
			*/
			
			for(Timesheet timesheet : timesheets) {
				Date sheetStartDate = timesheet.getStartDate();
				Date sheetEndDate = timesheet.getEndDate();
				Date sheetDate = sheetStartDate;
				int dateIndex = 0;
				
				// Process each date inside a timesheet and check if it falls within request start & end date range.				
				if(sheetDate != null) {
					
					while(sheetDate.before(sheetEndDate) || sheetDate.equals(sheetEndDate)) {
						
						boolean isDateAfterRequestStartDate = sheetDate.equals(requestStartDate) || sheetDate.after(requestStartDate);
						boolean isDateBeforeRequestEndDate = sheetDate.before(requestEndDate) || sheetDate.equals(requestEndDate);
						
						if(isDateAfterRequestStartDate && isDateBeforeRequestEndDate)	{
							for(UserEffort userEffort :timesheet.getUserEfforts()) {
								List<Float> hours = userEffort.getHours();
								if(hours != null && hours.size() > dateIndex) {
									// This represents the hours entered for date at index=dateIndex 
									Float itemHour = hours.get(dateIndex);
									if(itemHour > 0) {
										TimesheetReport timesheetReport = new TimesheetReport();
										
										timesheetReport.setMemberId(timesheet.getMemberId());
										timesheetReport.setProject(userEffort.getProject());
										timesheetReport.setTask(userEffort.getTask());
										timesheetReport.setComment(userEffort.getComment());
										timesheetReport.setEntryDate(sheetDate);
										timesheetReport.setHours(itemHour);
										
										timesheetReports.add(timesheetReport);
									}
								}
							}
						}
						
						dateIndex++;
						sheetDate = addDays(sheetStartDate, dateIndex);
					}
				}
			}		
		
		

		return responseBuilder.buildResponse(Response.builder().timesheetReports(timesheetReports).build(),
				HttpStatus.OK);

	}
	
	private Date addDays(Date date, int days)
    {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days); //minus number would decrement the days
        return cal.getTime();
    }


	@CrossOrigin
	@RequestMapping(value = "projects", method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new project", response = Project.class, httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Project.class) })
	public ResponseEntity<Response> add(@RequestBody @Valid AddProjectRequest rq) {

		return responseBuilder.buildResponse(Response.builder()
				.project(projectRepository
						.save(Project.builder().description(rq.getDescription()).name(rq.getName()).build()))
				.build(), HttpStatus.CREATED);

	}

	@CrossOrigin
	@RequestMapping(value = "tasks", method = RequestMethod.POST)
	@ApiOperation(value = "Creates a new project task", response = Task.class, httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Task.class) })
	public ResponseEntity<Response> add(@RequestBody @Valid AddTaskRequest rq) {

		return responseBuilder.buildResponse(
				Response.builder()
						.task(taskRepository.save(Task.builder().projectId(rq.getProjectId())
								.description(rq.getDescription()).name(rq.getName()).build()))
						.build(),
				HttpStatus.CREATED);
	}
	
	@CrossOrigin
	@RequestMapping(value = "timesheets", method = RequestMethod.POST)
	@ApiOperation(value = "Adds a new timesheet", response = Timesheet.class, httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Effort.class) })
	public ResponseEntity<Response> add(@RequestBody @Valid AddTimesheetRequest rq) {

		System.out.println("AddUserEffortsRequest-->" + rq);
		
		Timesheet output = timesheetRepository.save(Timesheet.builder()
				.active(rq.isActive())
				.memberId(rq.getMemberId())
				.totalHours(rq.getTotalHours())
				.sheetStatus(rq.getSheetStatus())
				.startDate(rq.getStartDate())
				.endDate(rq.getEndDate())
				.selectedPeriodDisplayText(rq.getSelectedPeriodDisplayText())
				.userEfforts(parseUserEfforts(rq.getUserEfforts()))
				.build());
		
		return responseBuilder.buildResponse(
										Response.builder().timesheet(output).build(),
										HttpStatus.CREATED);

	}

	private List<UserEffort> parseUserEfforts(List<UserEffortRequest> userEfforts) {

		List<UserEffort> output = new ArrayList<>();
		UserEffort userEffort = null;
		
		for(UserEffortRequest item : userEfforts) {
			userEffort = UserEffort.builder()
									.comment(item.getComment())
									.project(item.getProject())
									.task(item.getTask())
									.hours(item.getHours())
									.total(item.getTotal())
									.build();
			output.add(userEffort);
		}
		
		return output;
	}
	
	@CrossOrigin
	@RequestMapping(value = "effort", method = RequestMethod.POST)
	@ApiOperation(value = "Adds a new project timesheet", response = Effort.class, httpMethod = "POST")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Effort.class) })
	public ResponseEntity<Response> add(@RequestBody @Valid AddEffortRequest rq) {

		System.out.println("AddEffortRequest-->" + rq);
		rq.getEfforts().forEach((k, v) -> {
			parseEfforts(k, v, rq.getMemberId());
		});

		return null;
	}
	
	private void parseEfforts(String date, List<DayEffort> efforts, String memberId) {

		efforts.forEach(dayEffort -> {
			effortRepository.save(Effort.builder().projectId(dayEffort.getProjectId()).day(date)
					.description(dayEffort.getDescription()).hours(dayEffort.getHours()).memberId(memberId)
					.projectId(dayEffort.getProjectId()).taskId(dayEffort.getTaskId()).type(dayEffort.getType())
					.week(dayEffort.getWeek())
					.build());
		});
	
	}
	
	@CrossOrigin
	@RequestMapping(value = "timesheets", method = RequestMethod.PUT)
	@ApiOperation(value = "Update a timesheet", response = Timesheet.class, httpMethod = "PUT")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = Timesheet.class) })
	public ResponseEntity<Response> update(@RequestBody @Valid AddTimesheetRequest rq) {

		System.out.println("update Timesheet-->" + rq);
		
		Timesheet output = timesheetRepository.save(Timesheet.builder()
				.id(rq.getId())
				.active(rq.isActive())
				.memberId(rq.getMemberId())
				.totalHours(rq.getTotalHours())
				.sheetStatus(rq.getSheetStatus())
				.startDate(rq.getStartDate())
				.endDate(rq.getEndDate())
				.selectedPeriodDisplayText(rq.getSelectedPeriodDisplayText())
				.userEfforts(parseUserEfforts(rq.getUserEfforts()))
				.build());
		
		return responseBuilder.buildResponse(
										Response.builder().timesheet(output).build(),
										HttpStatus.OK);

	}

}