package com.ciplogic.et.magicgroup.views.actions;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.ciplogic.et.magicgroup.Activator;
import com.ciplogic.et.magicgroup.model.File;
import com.ciplogic.et.magicgroup.model.Group;
import com.ciplogic.et.magicgroup.model.TreeItem;
import com.ciplogic.et.magicgroup.model.factory.GroupFactory;
import com.ciplogic.et.magicgroup.views.dialogs.NewFileLineDialog;
import com.ciplogic.et.magicgroup.views.dialogs.NewGroupDialog;

public class AddNewGroupAction extends BaseTreeAction {

	public AddNewGroupAction(TreeViewer treeViewer) {
		super(treeViewer);
		
		this.setText("Add new group");
		this.setToolTipText("Add new group");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/plus.png"));
	}
	
	public void run() {
		TreeItem selectedTreeItem;
		
		if (viewer.getSelection().isEmpty()) { 
			selectedTreeItem = GroupFactory.instance.getRootNode();					
		} else {
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			selectedTreeItem = (TreeItem) selection.getFirstElement();
		}
		viewer.setSelection(null, true);

		if (selectedTreeItem instanceof Group) {
			NewGroupDialog dialog = new NewGroupDialog(viewer.getControl().getShell(), (Group) selectedTreeItem);
			dialog.setBlockOnOpen(true);
			dialog.open();
		} else if (selectedTreeItem instanceof File) {
			NewFileLineDialog dialog = new NewFileLineDialog(viewer.getControl().getShell(), (File) selectedTreeItem);
			dialog.setBlockOnOpen(true);
			dialog.open();
		}

		viewer.refresh();
	}
}
