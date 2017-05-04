package de.yadrone.apps.controlcenter;

import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

import de.yadrone.base.IARDrone;

public interface ICCPlugin {

	public void activate(IARDrone drone);
}