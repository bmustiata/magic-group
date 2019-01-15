package com.ciplogic.et.magicgroup.views.actions;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.texteditor.ITextEditor;

import com.ciplogic.et.magicgroup.model.File;
import com.ciplogic.et.magicgroup.model.FileLocation;
import com.ciplogic.et.magicgroup.model.Group;
import com.ciplogic.et.magicgroup.model.TreeItem;
import com.ciplogic.et.magicgroup.views.dialogs.EditTreeItemDescriptionDialog;

public class NodeDoubleClickAction extends BaseTreeAction {

	public NodeDoubleClickAction(TreeViewer parentTree) {
		super(parentTree);
	}

	public void run() {
		ISelection selection = viewer.getSelection();
		TreeItem file = (TreeItem) ((IStructuredSelection) selection).getFirstElement();

		if (file instanceof Group) {
			groupDoubleClick(file);
		} else if (file instanceof File) {
			fileDoubleClick((File) file);
		} else {
			lineLocationDoubleClick((FileLocation) file);
		}

		return;
	}

	private void groupDoubleClick(TreeItem file) {
		EditTreeItemDescriptionDialog dialog = new EditTreeItemDescriptionDialog(viewer.getControl().getShell(),
				(Group) file);
		dialog.setBlockOnOpen(true);
		dialog.open();

		viewer.refresh();
	}

	private void lineLocationDoubleClick(FileLocation fileLocation) {
		IEditorPart editor = fileDoubleClick((File) fileLocation.getParent());

		if (editor instanceof ITextEditor) {
			ITextEditor textEditor = (ITextEditor) editor;

			IDocument document = textEditor.getDocumentProvider().getDocument(editor.getEditorInput());

			if (document != null) {
				IRegion lineInfo = null;

				int lineNumber = findLineNumber(document, fileLocation);

				try {
					lineInfo = document.getLineInformation(lineNumber);
				} catch (BadLocationException e) {
					// ignored because line number may not really exist in
					// document, we guess this...
				}

				if (lineInfo != null) {
					textEditor.selectAndReveal(lineInfo.getOffset(), 0);
				}
			}
		}
	}

	private IEditorPart fileDoubleClick(File file) {
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile fileReference = workspace.getRoot().getFileForLocation(Path.fromPortableString(file.getLocation()));

		IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
		IEditorDescriptor editorDescriptor = getEditorDescriptorForFile(file);

		try {
			IEditorPart editor = page.openEditor(new FileEditorInput(fileReference), editorDescriptor.getId(), true,
					IWorkbenchPage.MATCH_INPUT);
			return editor;
		} catch (PartInitException e) {
			e.printStackTrace();
		}

		return null;
	}

	private IEditorDescriptor getEditorDescriptorForFile(File file) {
		IEditorDescriptor desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName());
		if (desc == null) {
			desc = PlatformUI.getWorkbench().getEditorRegistry().getDefaultEditor(file.getName() + ".txt");
		}
		return desc;
	}

	/**
	 * Finds the line number for a given location in the document. If the file location
	 * is a number it will return the line number, else it will return the first line
	 * that contains the searched text.
	 * 
	 * @param document
	 * @param fileLocation
	 * @return
	 */
	private int findLineNumber(IDocument document, FileLocation fileLocation) {
		try {
			return Integer.parseInt(fileLocation.getName()) - 1;
		} catch (Exception e) {
		}

		String[] lines = document.get().split(System.lineSeparator());

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains(fileLocation.getName())) {
				return i;
			}
		}

		return -1;

	}
}
