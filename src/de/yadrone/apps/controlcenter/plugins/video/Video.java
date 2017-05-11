package de.yadrone.apps.controlcenter.plugins.video;

import java.awt.image.BufferedImage;

import de.yadrone.apps.controlcenter.ControlcenterController;
import de.yadrone.base.IARDrone;
import de.yadrone.base.command.VideoCodec;
import de.yadrone.base.video.ImageListener;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

public class Video extends Thread implements ImageListener {

	private IARDrone drone;
	private ControlcenterController controller;
	private boolean showWaiting = true;

	public Video(final IARDrone drone, ControlcenterController controller) {
		this.drone = drone;
		this.controller = controller;

	}

	public void run() {
		drone.getCommandManager().setVideoCodec(VideoCodec.MP4_360P);
		drone.getVideoManager().addImageListener(this);
	}

	public void imageUpdated(BufferedImage bImage) {
		Image image = SwingFXUtils.toFXImage(bImage, null);
		controller.draw(image);
		System.out.println("Image");
	}

}