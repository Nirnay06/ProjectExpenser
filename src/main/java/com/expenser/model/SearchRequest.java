package com.expenser.model;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SearchRequest {
	
	public enum Order {
		ASC, DESC
	}
	private int pageNo=1;
	private int pageSize=0;
	private Map<String, Order> orderBy = new LinkedHashMap<>();
	
	public String getLimitQuery() {
		StringBuffer s = new StringBuffer(" ");
		if(pageNo!=1 && pageSize!=0) {
			int offset = pageNo *pageSize;
			s.append(" OFFSET "+offset + " ROWS");
		}
		if(pageSize!=0) {
			s.append("FETCH NEXT "+ pageSize+" ROWS ONLY");
		}
		return s.toString();
	}
	
	public String getOrdeByClause() {
		StringBuffer s = new StringBuffer(" ");
		int count = 0;
		if(!orderBy.isEmpty()) {
			for(Entry<String, Order> clause  : orderBy.entrySet()) {
				if(count==0) {
					s.append("order by "+ clause.getKey());
				}else {
					s.append(" , "+ clause.getKey());
				}
				s.append(" " + clause.getValue().name());
			}
		}
		return s.toString();
	}
	
	public void addOrderBy(String key, Order order) {
		orderBy.put(key, order);
	}
}
