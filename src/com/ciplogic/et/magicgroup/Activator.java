package com.ciplogic.et.magicgroup;

import java.io.File;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IPath;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import com.ciplogic.et.magicgroup.model.factory.GroupFactory;

/**
 * The activator class controls the plug-in life cycle
 */
public class Activator extends AbstractUIPlugin {

	private static final String CET_MAGIC_GROUP_FILE = ".cetMagicGroup";

	// The plug-in ID
	public static final String PLUGIN_ID = "com.ciplogic.cet.MagicGroup";

	// The shared instance
	private static Activator plugin;
	
	/**
	 * The constructor
	 */
	public Activator() {
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		super.start(context);
		plugin = this;
		
		GroupFactory.instance.load( getWorkspaceFile(CET_MAGIC_GROUP_FILE) );
	}

	/*
	 * (non-Javadoc)
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		GroupFactory.instance.save( getWorkspaceFile(CET_MAGIC_GROUP_FILE) );
		
		plugin = null;
		super.stop(context);
	}

	private File getWorkspaceFile(String fileName) {
		String workspacePath = ResourcesPlugin.getWorkspace().getRoot().getLocation().toString();
		return new File(workspacePath + File.separator + fileName);
	}

	/**
	 * Returns the shared instance
	 *
	 * @return the shared instance
	 */
	public static Activator getDefault() {
		return plugin;
	}

	/**
	 * Returns an image descriptor for the image file at the given
	 * plug-in relative path
	 *
	 * @param path the path
	 * @return the image descriptor
	 */
	public static ImageDescriptor getImageDescriptor(String path) {
		return imageDescriptorFromPlugin(PLUGIN_ID, path);
	}
}
