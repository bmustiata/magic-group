package com.ciplogic.et.magicgroup.views.dnd;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerDropAdapter;
import org.eclipse.swt.dnd.TransferData;
import org.eclipse.ui.part.ResourceTransfer;

import com.ciplogic.et.magicgroup.model.File;
import com.ciplogic.et.magicgroup.model.Group;

public class GroupViewerDropAdaptor extends ViewerDropAdapter {

	Group target;
	
	public GroupViewerDropAdaptor(Viewer viewer) {
		super(viewer);
	}

	@Override
	public boolean performDrop(Object data) {
		if (target == null) {
			return false;
		}
		
		IResource[] arr = (IResource[]) data;
		for (IResource r : arr) {
			IFile file = (IFile) r.getAdapter(IFile.class);
			if (file != null) {				
				target.createChildFile( file.getLocation().toPortableString(), file.getName() );
			}
		}
		
		this.getViewer().refresh();
		
		return true;
	}

	@Override
	public boolean validateDrop(Object target, int operation,
			TransferData transferType) {
		if ((! (target instanceof Group)) && target instanceof File) {
			target = ((File)target).getParent();
		}
		this.target = (Group) target;
		
		return ResourceTransfer.getInstance().isSupportedType(transferType);
	}

}
