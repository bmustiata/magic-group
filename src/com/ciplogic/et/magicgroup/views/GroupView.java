package com.ciplogic.et.magicgroup.views;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.viewers.ColumnViewerToolTipSupport;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.ViewerComparator;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ResourceTransfer;
import org.eclipse.ui.part.ViewPart;

import com.ciplogic.et.magicgroup.model.GroupComparator;
import com.ciplogic.et.magicgroup.views.actions.AddNewGroupAction;
import com.ciplogic.et.magicgroup.views.actions.EditDescriptionAction;
import com.ciplogic.et.magicgroup.views.actions.NodeDoubleClickAction;
import com.ciplogic.et.magicgroup.views.actions.RemoveGroupAction;
import com.ciplogic.et.magicgroup.views.dnd.GroupViewerDropAdaptor;
import com.ciplogic.et.magicgroup.views.providers.ViewContentProvider;
import com.ciplogic.et.magicgroup.views.providers.ViewLabelProvider;
import com.ciplogic.et.magicgroup.views.sorters.NameComparator;

public class GroupView extends ViewPart {
	private TreeViewer treeViewer;
	private DrillDownAdapter drillDownAdapter;
	private Action addNewGroupAction;
	private Action removeNewGroupAction;
	private Action doubleClickAction;
	private Action editDescriptionAction;

	/**
	 * The constructor.
	 */
	public GroupView() {
	}

	/**
	 * This is a callback that will allow us
	 * to create the viewer and initialize it.
	 */
	public void createPartControl(Composite parent) {
		treeViewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);
		drillDownAdapter = new DrillDownAdapter(treeViewer);
		treeViewer.setContentProvider( new ViewContentProvider(this) );
		treeViewer.setLabelProvider(new ViewLabelProvider());
		treeViewer.setComparator(new ViewerComparator(new GroupComparator()));
		treeViewer.setInput(getViewSite());
		
		// XXX: for crying out loud, who designed this API?
		
		ColumnViewerToolTipSupport.enableFor(treeViewer);
		
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();
		addDropListener();
	}

	public void addDropListener() {

		GroupViewerDropAdaptor adaptor = new GroupViewerDropAdaptor(treeViewer);
		treeViewer.addDropSupport(DND.DROP_COPY, new Transfer[] {
				ResourceTransfer.getInstance(),
			}, adaptor );

	}
	
	private void hookContextMenu() {
		MenuManager menuMgr = new MenuManager("#PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				GroupView.this.fillContextMenu(manager);
			}
		});
		Menu menu = menuMgr.createContextMenu(treeViewer.getControl());
		treeViewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, treeViewer);
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(addNewGroupAction);
		manager.add(new Separator());
		manager.add(removeNewGroupAction);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(addNewGroupAction);
		manager.add(removeNewGroupAction);
		manager.add(new Separator());
		manager.add(editDescriptionAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}
	
	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(addNewGroupAction);
		manager.add(removeNewGroupAction);
		manager.add(new Separator());
		manager.add(editDescriptionAction);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		addNewGroupAction = new AddNewGroupAction(treeViewer);
		removeNewGroupAction = new RemoveGroupAction(treeViewer);
		doubleClickAction = new NodeDoubleClickAction(treeViewer);
		editDescriptionAction = new EditDescriptionAction(treeViewer);
	}

	private void hookDoubleClickAction() {
		treeViewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		treeViewer.getControl().setFocus();
	}
}