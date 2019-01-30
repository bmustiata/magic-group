package com.ciplogic.et.magicgroup.views.providers;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.StyledCellLabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.jface.viewers.ViewerCell;
import org.eclipse.swt.graphics.Image;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.model.WorkbenchLabelProvider;

import com.ciplogic.et.magicgroup.model.File;
import com.ciplogic.et.magicgroup.model.FileLocation;
import com.ciplogic.et.magicgroup.model.Group;
import com.ciplogic.et.magicgroup.model.TreeItem;

public class ViewLabelProvider extends StyledCellLabelProvider {

	WorkbenchLabelProvider workbenchProvider = new WorkbenchLabelProvider();

	@Override
	public void update(ViewerCell cell) {
		Object obj = cell.getElement();
		StyledString styledString = null;
		
		if (obj instanceof FileLocation) {
			styledString = createFileLocationLabel((FileLocation) obj);			
		} else {
			styledString = createStyledString(obj);			
		}
		
		cell.setText(styledString.toString());
		cell.setStyleRanges(styledString.getStyleRanges());
		cell.setImage(getImage(obj));
		
		super.update(cell);
	}


	private StyledString createFileLocationLabel(FileLocation obj) {
		StyledString styledString = new StyledString();

		TreeItem file = (TreeItem) obj;
		
		if (file.getShortDescription() != null && !"".equals(file.getShortDescription().trim())) {
			styledString.append(file.getShortDescription());
			styledString.append(" - " + obj.toString(), StyledString.DECORATIONS_STYLER);
		} else {
			styledString.append(obj.toString());
		}

		return styledString;
	}

	private StyledString createStyledString(Object obj) {
		StyledString styledString = new StyledString(obj.toString());

		TreeItem file = (TreeItem) obj;
		
		if (obj instanceof Group) {
			Group group = (Group) obj;
			styledString.append(" (" + group.getChilds().size() + ")", StyledString.COUNTER_STYLER);
		}
		
		if (file.getShortDescription() != null && !"".equals(file.getShortDescription().trim())) {
			styledString.append(" - " + file.getShortDescription(), StyledString.DECORATIONS_STYLER);
		}
		return styledString;
	}
	
	public Image getImage(Object obj) {
		String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
		if (obj instanceof Group) {
		   imageKey = ISharedImages.IMG_OBJ_FOLDER;
		   return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		} else if (obj instanceof FileLocation) {
		   imageKey = org.eclipse.ui.ide.IDE.SharedImages.IMG_OPEN_MARKER;		   
		   return PlatformUI.getWorkbench().getSharedImages().getImage(imageKey);
		}
		
		File f = (File) obj;
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(
							Path.fromOSString( f.getLocation() ) );

		return workbenchProvider.getImage( file );
	}
	
	@Override
	public String getToolTipText(Object element) {
		if (element instanceof TreeItem) {
			StringBuilder stringBuilder = new StringBuilder();
			TreeItem file = (TreeItem) element;
			
			stringBuilder.append(file.getName());
			
			if (file.getShortDescription() != null && !"".equals(file.getShortDescription())) {
				stringBuilder.append("\n")
					.append(file.getShortDescription());
			}
			
			if (file.getDescription() != null && !"".equals(file.getDescription())) {
				stringBuilder.append("\n\n")
					.append(file.getDescription());
			}
			
			return stringBuilder.toString();
		} else { 
			return super.getToolTipText(element);
		}
	}
}


