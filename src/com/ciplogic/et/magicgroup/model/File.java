package com.ciplogic.et.magicgroup.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class File extends TreeItem implements Serializable {
	private static final long serialVersionUID = 6338147889812685113L;

	private String location;
	private String name = "";
	
	private List<FileLocation> lineNumbers = new ArrayList<FileLocation>();
	
	@Deprecated
	public File() {}
	
	public File(TreeItem parent, String location, String name) {
		super(parent);
		this.location = location;
		this.name = name;
	}
	
	@Override
	public void refreshChildren() {
		Collections.sort(lineNumbers);		
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return getName();
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void removeChild(TreeItem treeItem) {
		lineNumbers.remove(treeItem);
	}

	public void createChildLine(int lineNumber, String shortDescription, String description) {
		lineNumbers.add( new FileLocation(this, lineNumber, shortDescription, description) );
	}

	@Override
	public List<? extends TreeItem> getChilds() {
		return Collections.unmodifiableList(lineNumbers);
	}
}
