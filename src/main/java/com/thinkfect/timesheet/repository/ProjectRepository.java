package com.thinkfect.timesheet.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import com.thinkfect.timesheet.domain.Project;

public interface ProjectRepository extends CrudRepository<Project, String>, QueryByExampleExecutor<Project> {

	List<Project> findByName(String name);

	List<Project> findAll();

}
