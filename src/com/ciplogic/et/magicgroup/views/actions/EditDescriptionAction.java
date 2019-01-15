package com.ciplogic.et.magicgroup.views.actions;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;

import com.ciplogic.et.magicgroup.Activator;
import com.ciplogic.et.magicgroup.model.TreeItem;
import com.ciplogic.et.magicgroup.views.dialogs.EditTreeItemDescriptionDialog;

public class EditDescriptionAction extends BaseTreeAction {
	public EditDescriptionAction(TreeViewer parentTree) {
		super(parentTree);

		this.setText("Edit description");
		this.setToolTipText("Edit description of the file/group/line");
		this.setImageDescriptor(Activator.getImageDescriptor("icons/edit.png"));
	}

	public void run() {
		ISelection selection = viewer.getSelection();
		TreeItem file = (TreeItem) ((IStructuredSelection)selection).getFirstElement();

		if (file == null) {
			return;
		}
		
		EditTreeItemDescriptionDialog dialog = new EditTreeItemDescriptionDialog(
				viewer.getControl().getShell(), file);
		dialog.setBlockOnOpen(true);
		dialog.open();

		viewer.refresh();
	}
}
