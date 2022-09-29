package com.qa.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocalUser {
	String name;
	String location;
	long ph_no;
	String[] courses;
	int id;
}
