package de.yadrone.apps.controlcenter;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.UIManager;

import de.yadrone.base.ARDrone;

public class CCFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public CCFrame(ARDrone ardrone) {
		super("YADrone Control Center");

		setSize(1920, 1050);

		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
		}

		// for Desktop image & calibration
		JDesktopPane desktop = new JDesktopPane() {
			private static final long serialVersionUID = 1L;
			private Image originalImage;
			private Image scaledImage;
			private int width = 0;
			private int height = 0;

			public void paintComponent(Graphics g) {
				super.paintComponent(g);

				if (originalImage == null) { // called only once
					ImageIcon icon = new ImageIcon(CCFrame.class.getResource("img/desktop.jpg"));
					originalImage = icon.getImage();
					scaledImage = originalImage;
				}

				if ((width != getWidth()) || (height != getHeight())) { // called once the user change the frame size
					width = getWidth();
					height = getHeight();
					scaledImage = originalImage.getScaledInstance(getWidth(), getHeight(), Image.SCALE_AREA_AVERAGING);
				}

				g.drawImage(scaledImage, 0, 0, this);
			}
		};

		setContentPane(desktop);

		setVisible(true);
	}

}
