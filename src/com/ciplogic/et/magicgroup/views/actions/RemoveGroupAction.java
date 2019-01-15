package com.ciplogic.et.magicgroup.views.actions;

import java.util.List;

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.ciplogic.et.magicgroup.Activator;
import com.ciplogic.et.magicgroup.model.TreeItem;

public class RemoveGroupAction extends BaseTreeAction {

	public RemoveGroupAction(TreeViewer parentTree) {
		super(parentTree);
		
		this.setText("Remove group");
		this.setToolTipText("Remove group");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/minus.png"));
	}

	public void run() {
		if (!viewer.getSelection().isEmpty()) { 
			IStructuredSelection selection = (IStructuredSelection) viewer.getSelection();
			List<?> selectionList = selection.toList();
			for (Object o : selectionList) {
				if (o instanceof TreeItem) {
					TreeItem f = (TreeItem) o;
					f.getParent().removeChild(f);
				}
			}
		}
		viewer.refresh();
	}
}
