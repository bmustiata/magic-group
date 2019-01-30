package com.ciplogic.et.magicgroup.views.dialogs;


import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.ciplogic.et.magicgroup.model.File;
import com.ciplogic.et.magicgroup.model.FileLocation;
import com.ciplogic.et.magicgroup.model.TreeItem;

public class EditTreeItemDescriptionDialog extends BaseGroupDialog {

	public EditTreeItemDescriptionDialog(Shell shell, TreeItem treeItem) {
		super(shell, treeItem, !(treeItem instanceof File));
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Control result = super.createDialogArea(parent);
		
		if (this.treeItem instanceof FileLocation) {
			this.nameLabel.setText("Location:");
		} else if (this.treeItem instanceof File) {
			this.nameLabel.setText("Resource:");
		}
		
		return result;
	}
	
	
	@Override
	protected void doAction() {
		treeItem.setName(nameEdit.getText());
		treeItem.setDescription(descriptionEdit.getText());
		treeItem.setShortDescription(shortDescriptionEdit.getText());
		
		treeItem.getParent().refreshChildren();
	}

	@Override
	protected String getDefaultText() {
		return treeItem.getName();
	}

	@Override
	protected String getWindowTitle() {
		return "Edit \"" + treeItem.getName() + "\"...";
	}

	@Override
	protected String getDefaultDescription() {
		String groupDescription = treeItem.getDescription();
		return groupDescription != null ? groupDescription : "";
	}

	@Override
	protected String getDefaultShortDescription() {
		String groupShortDescription = treeItem.getShortDescription();
		return groupShortDescription != null ? groupShortDescription : "";
	}
}
