package de.yadrone.apps.controlcenter;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import de.yadrone.apps.controlcenter.plugins.battery.BatteryPanel;
import de.yadrone.apps.controlcenter.plugins.keyboard.KeyboardLayoutPanel;
import de.yadrone.apps.controlcenter.plugins.speed.SpeedPanel;
import de.yadrone.apps.controlcenter.plugins.video.VideoPanel;
import de.yadrone.base.ARDrone;

public class CCFrame extends JFrame {

	private static final long serialVersionUID = 1L;
	private ARDrone drone = null;

	public CCFrame(ARDrone ardrone) {
		super("YADrone Control Center");
		ARDrone drone = ardrone;
		setSize(920, 550);

		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setUndecorated(true);

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

		// Title
		JLabel title = new JLabel("Geier Controller");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		title.setFont(new Font("Lucida Console", Font.BOLD, 30));
		title.setForeground(Color.GREEN);

		// JPanel
		JPanel panelMain = new JPanel();
		JPanel panelLeft = new JPanel();
		JPanel panelRight = new JPanel();
		JPanel panelRightTop = new JPanel();
		JPanel panelRightBottom = new JPanel();
		JPanel panelCenter = new JPanel();
		JPanel panelVideoTop = new JPanel();
		JPanel panelVideo = new JPanel();
		JPanel panelBattery = new JPanel();

		// Size
		panelMain.setPreferredSize(new Dimension(1920, 1080));
		panelLeft.setPreferredSize(new Dimension(600, 1070));
		panelRight.setPreferredSize(new Dimension(600, 1070));
		panelRightTop.setPreferredSize(new Dimension(600, 535));
		panelRightBottom.setPreferredSize(new Dimension(600, 535));
		panelCenter.setPreferredSize(new Dimension(700, 1070));
		panelVideo.setPreferredSize(new Dimension(700, 400));
		panelVideoTop.setPreferredSize(new Dimension(700, 300));

		// Color
		panelMain.setBackground(new Color(0, 0, 0));
		panelLeft.setBackground(new Color(255, 0, 0));
		panelRight.setBackground(Color.BLACK);
		panelRightTop.setBackground(Color.BLACK);
		panelRightBottom.setBackground(Color.BLACK);
		panelCenter.setBackground(Color.BLACK);
		panelVideo.setBackground(Color.GRAY);
		panelVideoTop.setBackground(Color.BLACK);

		panelBattery.setBackground(Color.GREEN);

		// Layout
		panelVideoTop.setLayout(new BorderLayout());

		// Keyboard stearing
		KeyboardLayoutPanel keyboard = new KeyboardLayoutPanel();
		keyboard.activate(drone);

		// Video output
		VideoPanel video = new VideoPanel();
		video.setPreferredSize(new Dimension(700, 400));

		// Plugin
		// final PluginManager pluginManager = new PluginManager();
		// pluginManager.setDesktop(desktop);
		// pluginManager.activate(ardrone);
		// setContentPane(pluginManager);

		SpeedPanel speedPanel = new SpeedPanel();

		// Battery
		BatteryPanel battery = new BatteryPanel();
		battery.setPreferredSize(new Dimension(100, 50));
		battery.activate(drone);

		// setContentPane(desktop);

		// Add to JPanel
		panelMain.add(panelLeft);
		panelMain.add(panelCenter);
		panelMain.add(panelRight);

		// Center
		panelCenter.add(panelVideoTop);
		panelCenter.add(panelVideo);

		panelVideo.add(video);

		panelVideoTop.add(title);

		// Right
		panelRight.add(panelRightTop);
		panelRight.add(panelRightBottom);

		panelRightBottom.add(panelBattery);

		panelBattery.add(battery);

		// Left
		panelLeft.add(speedPanel);

		getContentPane().add(panelMain);
		setVisible(true);
		pack();
	}

}
