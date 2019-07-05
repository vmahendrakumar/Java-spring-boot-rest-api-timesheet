package com.thinkfect.timesheet.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.thinkfect.timesheet.domain.Effort;

public interface EffortRepository extends CrudRepository<Effort, String>, QueryByExampleExecutor<Effort> {

	List<Effort> findByMemberId(String name);

	List<Effort> findAll();
	
	List<Effort> findByProjectId(String projectId);
	
	List<Effort> findByTaskId(String projectId);
	

}
