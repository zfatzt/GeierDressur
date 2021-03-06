package ch.bbcag.controlcenter.plugins.keyboard;

import de.yadrone.base.IARDrone;
import de.yadrone.base.command.FlightAnimation;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class KeyboardCommandManager {
	protected IARDrone drone;

	public KeyboardCommandManager(IARDrone ardrone) {
		this.drone = ardrone;
	}

	public void keyReleased(KeyEvent e) {
		drone.hover();
	}

	public void keyPressed(KeyEvent e) {
		System.out.println("Key pressed: " + e.getCode());
		KeyCode code = e.getCode();
		handleCommand(code);
	}

	protected void handleCommand(KeyCode code) {
		switch (code) {
		case ENTER:
			drone.takeOff();
			break;
		case SPACE:
			drone.landing();
			break;
		case A:
			drone.goLeft();
			break;
		case D:
			drone.goRight();
			break;
		case W:
			drone.forward();
			break;
		case S:
			drone.backward();
			break;
		case E:
			drone.stop();
			break;
		case R:
			drone.reset();
			break;
		case LEFT:
			drone.spinLeft();
			break;
		case RIGHT:
			drone.spinRight();
			break;
		case UP:
			drone.up();
			break;
		case DOWN:
			drone.down();
			break;
		case Y:
			drone.setHorizontalCamera();
			break;
		case X:
			drone.setHorizontalCameraWithVertical();
			break;
		case C:
			drone.setVerticalCamera();
			break;
		case V:
			drone.setVerticalCameraWithHorizontal();
			break;
		case B:
			drone.toggleCamera();
			break;
		case M:
			drone.stop();
			break;
		case PLUS:
			drone.setSpeed(drone.getSpeed() + 1);
			break;
		case MINUS:
			drone.setSpeed(drone.getSpeed() - 1);
			break;
		case F1:
			drone.getCommandManager().animate(FlightAnimation.PHI_M30_DEG);
			break;
		case F2:
			drone.getCommandManager().animate(FlightAnimation.PHI_30_DEG);
			break;
		case F3:
			drone.getCommandManager().animate(FlightAnimation.THETA_M30_DEG);
			break;
		case F4:
			drone.getCommandManager().animate(FlightAnimation.THETA_30_DEG);
			break;
		case F5:
			drone.getCommandManager().animate(FlightAnimation.THETA_20DEG_YAW_200DEG);
			break;
		case F6:
			drone.getCommandManager().animate(FlightAnimation.THETA_20DEG_YAW_M200DEG);
			break;
		case F7:
			drone.getCommandManager().animate(FlightAnimation.TURNAROUND);
			break;
		case F8:
			drone.getCommandManager().animate(FlightAnimation.TURNAROUND_GODOWN);
			break;
		case F9:
			drone.getCommandManager().animate(FlightAnimation.YAW_SHAKE);
			break;
		case F10:
			drone.getCommandManager().animate(FlightAnimation.YAW_DANCE);
			break;
		case DIGIT1:
			drone.getCommandManager().animate(FlightAnimation.PHI_DANCE);
			break;
		case DIGIT2:
			drone.getCommandManager().animate(FlightAnimation.THETA_DANCE);
			break;
		case DIGIT3:
			drone.getCommandManager().animate(FlightAnimation.VZ_DANCE);
			break;
		case DIGIT4:
			drone.getCommandManager().animate(FlightAnimation.WAVE);
			break;
		case DIGIT5:
			drone.getCommandManager().animate(FlightAnimation.PHI_THETA_MIXED);
			break;
		case DIGIT6:
			drone.getCommandManager().animate(FlightAnimation.DOUBLE_PHI_THETA_MIXED);
			break;
		case DIGIT7:
			drone.getCommandManager().animate(FlightAnimation.FLIP_AHEAD);
			break;
		case DIGIT8:
			drone.getCommandManager().animate(FlightAnimation.FLIP_BEHIND);
			break;
		case DIGIT9:
			drone.getCommandManager().animate(FlightAnimation.FLIP_LEFT);
			break;
		case DIGIT0:
			drone.getCommandManager().animate(FlightAnimation.FLIP_RIGHT);
			break;
		default:
			drone.hover();
			break;
		}
	}

}
