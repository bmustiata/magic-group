package com.ciplogic.et.magicgroup.views.sorters;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

import com.ciplogic.et.magicgroup.model.GroupComparator;
import com.ciplogic.et.magicgroup.model.TreeItem;

public class NameComparator extends ViewerComparator {
	private GroupComparator groupComparator = new GroupComparator();
	
	public int compare(Viewer viewer, Object e1, Object e2) {
		return groupComparator.compare((TreeItem) e1, (TreeItem) e2);		
	}
}
