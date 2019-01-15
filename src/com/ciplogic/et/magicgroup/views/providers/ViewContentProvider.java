package com.ciplogic.et.magicgroup.views.providers;

import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.part.ViewPart;

import com.ciplogic.et.magicgroup.model.Group;
import com.ciplogic.et.magicgroup.model.TreeItem;
import com.ciplogic.et.magicgroup.model.factory.GroupFactory;

public class ViewContentProvider implements IStructuredContentProvider,
		ITreeContentProvider {
	
	private Group invisibleRoot;
	private ViewPart parentView;

	public ViewContentProvider(ViewPart parentView) {
		this.parentView = parentView;
	}

	public void inputChanged(Viewer v, Object oldInput, Object newInput) {
	}

	public void dispose() {
	}

	public Object[] getElements(Object parent) {
		if (parent.equals( parentView.getViewSite() )) {
			if (invisibleRoot == null)
				initialize();
			return getChildren(invisibleRoot);
		}
		return getChildren(parent);
	}

	public Object getParent(Object child) {
		if (child instanceof TreeItem) {
			return ((TreeItem) child).getParent();
		}
		return null;
	}

	public Object[] getChildren(Object parent) {
		return ((TreeItem) parent).getChilds().toArray();
	}

	public boolean hasChildren(Object parent) {
		return ! ((TreeItem)parent).getChilds().isEmpty();
	}

	/*
	 * We will set up a dummy model to initialize tree hierarchy. In a real
	 * code, you will connect to a real model and expose its hierarchy.
	 */
	private void initialize() {
		invisibleRoot = GroupFactory.instance.getRootNode();
	}
}
