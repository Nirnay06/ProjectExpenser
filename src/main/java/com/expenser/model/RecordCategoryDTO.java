package com.expenser.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import com.expenser.Entity.RecordUserCategory;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RecordCategoryDTO implements Serializable {

	
	private String identifier;
	private ClientDTO client;
	private String clientIdentifier;
	private String title;
	private RecordCategoryDTO parent;
	private String parentIdentifier;
	private List<RecordCategoryDTO> children;
	private String icon;
	private String color;
	private boolean hidden;
	private boolean defaultCategory;
	private Integer order;
	private boolean selectable;
	
	public RecordCategoryDTO(String identifier, ClientDTO client, String title, String parentIdentifier,
			List<RecordCategoryDTO> children, String icon, String color, boolean hidden, boolean defaultCategory, Integer order,
			boolean selectable) {
		this.identifier = identifier;
		this.client = client;
		this.title = title;
		this.parentIdentifier = parentIdentifier;
		this.children = children;
		this.icon = icon;
		this.color = color;
		this.hidden = hidden;
		this.defaultCategory = defaultCategory;
		this.order= order;
		this.selectable= selectable;
	}
	public RecordCategoryDTO() {
	}
	public RecordCategoryDTO(RecordUserCategory r, String parentIdentifier) {
		this.identifier = r.getIdentifier();
		this.title = r.getTitle();
		this.children = mapCategoryListToDTO(r.getChildren(), r.getIdentifier());
		this.icon = r.getIcon();
		this.color = r.getColor();
		this.hidden = r.isHidden();
		this.defaultCategory = r.isDefaultCategory();
		this.order = r.getOrder();
		this.selectable = r.isSelectable();
		this.parentIdentifier = parentIdentifier;
	}
	public static List<RecordCategoryDTO> mapCategoryListToDTO(List<RecordUserCategory> records, String parentIdentifier) {
		List<RecordCategoryDTO> list = new ArrayList<>();
		for(RecordUserCategory r : records) {
			list.add(new RecordCategoryDTO(r, parentIdentifier));
		}
		list.sort(CATEGORY_COMPARATOR);
		return list;
	}

	static Comparator<RecordCategoryDTO> CATEGORY_COMPARATOR = new Comparator<RecordCategoryDTO>() {
		
		@Override
		public int compare(RecordCategoryDTO o1, RecordCategoryDTO o2) {
			return o1.getOrder().compareTo(o2.getOrder());
		}
	}; 
	
}
