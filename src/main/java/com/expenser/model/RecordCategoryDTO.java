package com.expenser.model;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecordCategoryDTO {

	private String identifier;
	private ClientDTO client;
	private String title;
	private RecordCategoryDTO parent;
	private List<RecordCategoryDTO> children;
	private String icon;
	private String color;
	private boolean hidden;
	private boolean defaultCategory;
	public RecordCategoryDTO(String identifier, ClientDTO client, String title, RecordCategoryDTO parent,
			List<RecordCategoryDTO> children, String icon, String color, boolean hidden, boolean defaultCategory) {
		this.identifier = identifier;
		this.client = client;
		this.title = title;
		this.parent = parent;
		this.children = children;
		this.icon = icon;
		this.color = color;
		this.hidden = hidden;
		this.defaultCategory = defaultCategory;
	}
	public RecordCategoryDTO() {
	}

	
}
