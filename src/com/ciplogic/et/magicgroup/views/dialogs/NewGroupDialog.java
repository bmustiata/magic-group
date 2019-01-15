package com.ciplogic.et.magicgroup.views.dialogs;


import org.eclipse.swt.widgets.Shell;

import com.ciplogic.et.magicgroup.model.Group;

public class NewGroupDialog extends BaseGroupDialog {

	public NewGroupDialog(Shell parentShell, Group parentGroup) {
		super(parentShell, parentGroup, true);
	}

	@Override
	protected String getWindowTitle() {
		return "Add group...";
	}

	@Override
	protected String getDefaultText() {
		return "Default";
	}

	@Override
	protected void doAction() {
		((Group)treeItem).createChildGroup( nameEdit.getText(), shortDescriptionEdit.getText(), descriptionEdit.getText() );
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
