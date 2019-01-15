package com.ciplogic.et.magicgroup.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class FileLocation extends TreeItem implements Serializable, Comparable<FileLocation> {
	private static final long serialVersionUID = -6257068791902041066L;

	private String lineLocation;
	
	@Deprecated
	public FileLocation() {}
	
	public FileLocation(TreeItem parent, 
					    String lineLocation,
					    String shortDescription,
					    String description) {
		super(parent);
		
		this.lineLocation = lineLocation;
		
		setShortDescription(shortDescription);
		setDescription(description);
	}

	public String getLineLocation() {
		return lineLocation;
	}

	public void setLineLocation(String lineNumber) {
		this.lineLocation = lineNumber;
	}

	public int compareTo(FileLocation o) {
		return lineLocation.compareTo(o.lineLocation);
	}

	@Override
	public void setName(String value) {
		this.lineLocation = value;
	}

	@Override
	public String getName() {
		return lineLocation;
	}

	@Override
	public void refreshChildren() {
	}

	@Override
	public void removeChild(TreeItem treeItem) {
	}

	@Override
	public List<? extends TreeItem> getChilds() {
		return Collections.emptyList();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + lineLocation.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		FileLocation other = (FileLocation) obj;
		if (lineLocation != other.lineLocation)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return lineLocation;
	}
}
