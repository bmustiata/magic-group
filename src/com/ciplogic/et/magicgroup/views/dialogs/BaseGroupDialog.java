package com.ciplogic.et.magicgroup.views.dialogs;

import net.miginfocom.swt.MigLayout;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.ciplogic.et.magicgroup.model.TreeItem;

public abstract class BaseGroupDialog extends Dialog {
	protected Shell parent;
	protected TreeItem treeItem;
	
	private boolean nameEditable;

	protected Text  nameEdit;
	protected Text  shortDescriptionEdit;
	protected StyledText  descriptionEdit;
	
	protected BaseGroupDialog(Shell parentShell, TreeItem editedItem, boolean nameEditable) {
		super(parentShell);
		this.parent = parentShell;
		this.treeItem = editedItem;
		this.nameEditable = nameEditable;
	}

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText( getWindowTitle() );
	}
	
	protected abstract String getWindowTitle();
	
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite container = new Composite(parent, SWT.NONE);
		container.setLayout(new MigLayout());
		
		Label label = new Label(container, SWT.NONE);
		label.setText("Group name:");
		
		nameEdit = new Text(container, SWT.BORDER);
		nameEdit.setText( getDefaultText() );
		nameEdit.setSelection(0, 1000);
		nameEdit.setLayoutData("width 200:300:400, wrap");
		
		if (!nameEditable) {
			nameEdit.setEnabled(false);
		}
		
		label = new Label(container, SWT.NONE);
		label.setText("Short description:");
		
		shortDescriptionEdit = new Text(container, SWT.BORDER);
		shortDescriptionEdit.setText(getDefaultShortDescription());
		shortDescriptionEdit.setLayoutData("width 200:300:400, wrap");

		label = new Label(container, SWT.NONE);
		label.setText("Description:");
		
		descriptionEdit = new StyledText(container, SWT.BORDER);
		descriptionEdit.setText(getDefaultDescription());
		descriptionEdit.setLayoutData("height 200!, width 200:300:400, wrap");

		return container;
	}
	
	protected abstract String getDefaultText();
	protected abstract String getDefaultShortDescription();
	protected abstract String getDefaultDescription();
	
	@Override
	protected void okPressed() {
		doAction();
		this.close();
	}
	
	protected abstract void doAction();
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL, true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);		
	}
}
