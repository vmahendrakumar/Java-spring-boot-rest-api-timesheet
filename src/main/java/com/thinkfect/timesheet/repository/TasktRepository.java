package com.thinkfect.timesheet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.thinkfect.timesheet.domain.Task;

public interface TasktRepository extends CrudRepository<Task, String>, QueryByExampleExecutor<Task> {

	List<Task> findByName(String name);

	List<Task> findAll();
	
	List<Task> findByProjectId(String projectId);

}
