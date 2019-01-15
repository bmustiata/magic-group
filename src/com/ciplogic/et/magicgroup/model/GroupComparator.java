package com.ciplogic.et.magicgroup.model;

import java.util.Comparator;

public class GroupComparator implements Comparator<TreeItem> {

	public int compare(TreeItem o1, TreeItem o2) {
		// groups have priority
		if ( (o1 instanceof Group) &&
		   (!(o2 instanceof Group))) return -1;
		
		if (!(o1 instanceof Group) &&
		   ( (o2 instanceof Group))) return 1;
		
		// if they both have the same type
		return o1.toString().compareTo(o2.toString());
	}

}
