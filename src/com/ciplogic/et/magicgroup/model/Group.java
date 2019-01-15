package com.ciplogic.et.magicgroup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A group is similar to a virtual folder, that can contain other groups or files. 
 */
public class Group extends TreeItem implements Serializable {
	private static final long serialVersionUID = -5382440195602524905L;

	private String name;
	
	private List<TreeItem> childs = new ArrayList<TreeItem>();

	@Deprecated
	public Group() {}
	
	public Group(TreeItem parent, String name) {
		super(parent);
		this.name = name;
	}

	@Override
	public List<TreeItem> getChilds() {
		return Collections.unmodifiableList( childs );
	}

	public void setChilds(List<TreeItem> childs) {
		this.childs = childs;
	}

	public void createChildGroup(String name, String shortDescription, String description) {
		Group childGroup = new Group(this, name);
		childGroup.setShortDescription(shortDescription);
		childGroup.setDescription(description);
		addChild(childGroup);
	}
	
	public void addChild(TreeItem file) {
		if (! this.childs.contains(file) ) {
			this.childs.add(file);
			file.setParent(this);
			
			refreshChildren();
		} else {
			throw new IllegalArgumentException("The added File or Group already exists in this Group.");
		}
	}

	public boolean hasChildren() {
		return childs.size() > 0;
	}

	@Override
	public String toString() {
		return this.getName();
	}
	
	public void refreshParent() {
		if (getParent() != null) {
			getParent().refreshChildren();
		}
	}

	public void createChildFile(String location, String name) {
		addChild(new File(this, location, name));
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public void refreshChildren() {
		Collections.sort(this.childs, new GroupComparator());
	}

	@Override
	public void removeChild(TreeItem treeItem) {
		for (int i = 0; i < this.childs.size(); i++) {
			TreeItem fileToRemove = this.childs.get(i);
			if (fileToRemove.equals(treeItem)) {
				this.childs.remove(i);
				treeItem.setParent(null);
				break;
			}
		}
	}
}
