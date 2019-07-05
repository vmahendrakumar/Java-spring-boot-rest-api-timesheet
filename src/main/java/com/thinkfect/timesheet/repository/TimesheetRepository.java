package com.thinkfect.timesheet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.thinkfect.timesheet.domain.UserEffort;
import com.thinkfect.timesheet.domain.Timesheet;

public interface TimesheetRepository extends CrudRepository<Timesheet, String>, QueryByExampleExecutor<UserEffort> {

	List<Timesheet> findByMemberId(String name);

	List<Timesheet> findAll();
	
	Optional<Timesheet> findById(String id);
	
	List<Timesheet> findByProjectId(String projectId);
	
	List<Timesheet> findByTaskId(String projectId);

	//void save(UserEfforts build);
	

}
