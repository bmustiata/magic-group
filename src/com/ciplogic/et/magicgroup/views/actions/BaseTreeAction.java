package com.ciplogic.et.magicgroup.views.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.TreeViewer;

public class BaseTreeAction extends Action {
	protected TreeViewer viewer;
	
	public BaseTreeAction(TreeViewer parentTree) {
		this.viewer = parentTree;
	}
}
