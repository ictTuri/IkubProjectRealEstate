package com.realestate.app.filter;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PropertyFilter {

	private String category;
	
	private Integer minPrice;
	
	private Integer maxPrice;
	
	private String city;
	
	private String sortBy;
	
	private String order;
}
