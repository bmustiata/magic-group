package com.ciplogic.et.magicgroup.views.dialogs;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import com.ciplogic.et.magicgroup.model.File;

public class NewFileLineDialog extends BaseGroupDialog {
	public NewFileLineDialog(Shell parentShell, 
			File parentFile) {
		super(parentShell, parentFile, true);
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Control result = super.createDialogArea(parent);
		this.nameLabel.setText("Location:");
		
		return result;
	}
	
	@Override
	protected String getWindowTitle() {
		return "Add file location...";
	}

	@Override
	protected String getDefaultText() {
		return "1";
	}

	@Override
	protected void doAction() {
		((File)treeItem).createChildLine(
				nameEdit.getText(), 
				shortDescriptionEdit.getText(), 
				descriptionEdit.getText() );
	}

	@Override
	protected String getDefaultDescription() {
		return "";
	}

	@Override
	protected String getDefaultShortDescription() {
		return "";
	}
}
