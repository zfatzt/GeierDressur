package de.yadrone.apps.controlcenter.plugins.pluginmanager;

import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import org.reflections.Reflections;

import de.yadrone.apps.controlcenter.CCPropertyManager;
import de.yadrone.apps.controlcenter.ICCPlugin;
import de.yadrone.base.IARDrone;

public class PluginManager extends JPanel implements ICCPlugin {
	private IARDrone drone;
	private JDesktopPane desktop;
	private CCPropertyManager pluginProperties;

	/**
	 * This frame is used to get screen size and location upon initialization
	 * and finalization
	 */
	private Map<ICCPlugin, JInternalFrame> activePluginFrames;

	public PluginManager() {
		super(new GridBagLayout());

		pluginProperties = CCPropertyManager.getInstance();

		activePluginFrames = new HashMap<ICCPlugin, JInternalFrame>();
	}

	private void init() {

		// look for all classes implementing the Plugin interface
		Reflections reflections = new Reflections();
		Set subTypes = reflections.getSubTypesOf(ICCPlugin.class); // this set
																	// contains
																	// Class
																	// objects

		// we need a String list, because we want to sort the list
		// alphabetically
		List<String> sortedList = new ArrayList<String>();
		Iterator iter = subTypes.iterator();
		while (iter.hasNext()) {
			sortedList.add(((Class) iter.next()).getName());
		}
	}

	public void activate(IARDrone drone) {
		this.drone = drone;
	}

}
