package com.course.kafka;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WordCount {
	private String word;

	private long count;

	private Date start;

	private Date end;
}
