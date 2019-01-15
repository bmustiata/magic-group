package com.ciplogic.et.magicgroup.model;

import java.io.Serializable;
import java.util.List;

/**
 * A tree item is a thing that can appear in the resource tree. It should have a short description, a description
 * and a name.
 */
public abstract class TreeItem implements Serializable {
	private TreeItem parent;
	
	private String shortDescription = "";
	private String description = "";

	public abstract void setName(String value);
	public abstract String getName();
	
	public abstract void removeChild(TreeItem treeItem);
	public abstract void refreshChildren();
	public abstract List<? extends TreeItem> getChilds();
	
	@Deprecated
	public TreeItem() {}
	
	public TreeItem(TreeItem parent) {
		this.parent = parent;
	}
	
	public TreeItem getParent() {
		return parent;
	}

	public void setParent(TreeItem parent) {
		this.parent = parent;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
