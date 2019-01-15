package com.ciplogic.et.magicgroup.views.dialogs;

import org.eclipse.swt.widgets.Shell;

import com.ciplogic.et.magicgroup.model.File;

public class NewFileLineDialog extends BaseGroupDialog {
	public NewFileLineDialog(Shell parentShell, 
			File parentFile) {
		super(parentShell, parentFile, true);
	}

	@Override
	protected String getWindowTitle() {
		return "Add line number...";
	}

	@Override
	protected String getDefaultText() {
		return "1";
	}

	@Override
	protected void doAction() {
		((File)treeItem).createChildLine( Integer.parseInt(nameEdit.getText()), 
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
