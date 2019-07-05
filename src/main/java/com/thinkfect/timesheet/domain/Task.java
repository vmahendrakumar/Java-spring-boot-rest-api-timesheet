package com.thinkfect.timesheet.domain;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
* {@link Plant} object stored inside a Redis {@literal HASH}. <br />
* <br />
* Sample (key = plants:9b0ed8ee-14be-46ec-b5fa-79570aadb91d):
*
* <pre>
* <code>
* _class := example.springdata.redis.domain.Site
* id := 9b0ed8ee-14be-46ec-b5fa-79570aadb91d
* firstname := eddard
* lastname := stark
* gender := MALE
* address.city := winterfell
* address.country := the north
* children.[0] := sites:41436096-aabe-42fa-bd5a-9a517fbf0260
* children.[1] := sites:1973d8e7-fbd4-4f93-abab-a2e3a00b3f53
* children.[2] := sites:440b24c6-ede2-495a-b765-2d8b8d6e3995
* children.[3] := sites:85f0c1d1-cef6-40a4-b969-758ebb68dd7b
* children.[4] := sites:73cb36e8-add9-4ec0-b5dd-d820e04f44f0
* children.[5] := sites:9c2461aa-2ef2-469f-83a2-bd216df8357f
* </code>
* </pre>
*
* @author Ratul Bhattacharya
*/

@Data
@Builder
@EqualsAndHashCode(exclude = { "children" })
@RedisHash("tasks")
@ToString
public class Task {

	/**
	 * The {@literal id} and {@link RedisHash#toString()} build up the {@literal key} for the Redis {@literal HASH}.
	 * <br />
	 *
	 * <pre>
	 * <code>
	 * {@link RedisHash#value()} + ":" + {@link Site#id}
	 * //eg. sites:9b0ed8ee-14be-46ec-b5fa-79570aadb91d
	 * </code>
	 * </pre>
	 *
	 * <strong>Note:</strong> empty {@literal id} fields are automatically assigned during save operation.
	 */

	private @Id String id;
	
	private @Indexed String name;
	
	@JsonInclude(Include.NON_NULL)
	private String description;
	
	private @Indexed String projectId;
	
	
	
}
