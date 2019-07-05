package com.thinkfect.timesheet.endpoints.request;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* @author Ratul Bhattacharya
*/

@Data
@NoArgsConstructor
@ToString
public class AddProjectRequest {


	@NotNull @NotBlank
	private String name;
	
	@NotNull @NotBlank
	private String description;
	
	
	
}
