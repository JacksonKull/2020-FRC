package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.command.Command;

/*
 * When Creating a new XboxController: XboxController controller = new XboxController(Port#);
 * 
 * This class allows you to add commands to every button on the Xbox controller :
 * controller.addCommandToA(Command c);
 * 
 * While also providing the means to check the values for every form of input on the 
 * controller : controller.getBUTTONNAMEButton() (ex: controller.getAButton();)
 * 
 * USE THIS INSTEAD OF A JOYSTICK
 */
public class XboxController extends Joystick {
	Button A, B, X, Y, leftBumper, rightBumper, leftMenu, rightMenu, leftStick, rightStick;
	POVButton dpad0, dpad45, dpad90, dpad135, dpad180, dpad225, dpad270, dpad315;
	Boolean AToggle, BToggle, XToggle, YToggle, RBToggle, LBToggle, RTToggle, LTToggle, LMToggle, RMToggle;
	Boolean prevA, prevB, prevX, prevY, prevRB, prevLB, prevRT, prevLT;
	Button leftTrig, rightTrig;

	public XboxController(int port) {
		super(port);
		this.A = new JoystickButton(this, 1);
		this.B = new JoystickButton(this, 2);
		this.X = new JoystickButton(this, 3);
		this.Y = new JoystickButton(this, 4);
		this.leftBumper = new JoystickButton(this, 5);
		this.rightBumper = new JoystickButton(this, 6);
		this.leftMenu = new JoystickButton(this, 7);
		this.rightMenu = new JoystickButton(this, 8);
		this.leftStick = new JoystickButton(this, 9);
		this.rightStick = new JoystickButton(this, 10);
		this.AToggle = false;
		this.BToggle = false;
		this.XToggle = false;
		this.YToggle = false;
		this.RBToggle = false;
		this.LBToggle = false;
		this.RTToggle = false;
		this.LTToggle = false;
		this.LMToggle = false;
		this.RMToggle = false;
		this.rightTrig = new Button() {
			@Override
			public boolean get() {
				return getRawAxis(3) > 0.75;
			}
		};
		this.leftTrig = new Button() {
			@Override
			public boolean get() {
				return getRawAxis(2) > 0.75;
			}
		};
	}

	// A BUTTON
	public boolean getAButton() {
		return this.getRawButton(1);
	}

	public void toggleAButton() {
		if (this.AToggle) {
			this.AToggle = false;
		} else if (!this.AToggle) {
			this.AToggle = true;
		}
	}

	public boolean getAToggle() {
		if (this.AToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToA(Command c) {
		A.whenPressed(c);
	}

	public void addWhenReleasedToA(Command c) {
		A.whenReleased(c);
	}

	public void addWhenHeldToA(Command c) {
		A.whileHeld(c);
	}

	public void addWhenPressedToA(Command c) {
		A.whenPressed(c);
	}

	// B BUTTON
	public boolean getBButton() {
		return this.getRawButton(2);
	}

	public void toggleBButton() {
		if (this.BToggle) {
			this.BToggle = false;
		} else if (!this.BToggle) {
			this.BToggle = true;
		}
	}

	public boolean getBToggle() {
		if (this.BToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToB(Command c) {
		B.whenPressed(c);
	}

	public void addWhenReleasedToB(Command c) {
		B.whenReleased(c);
	}

	public void addWhenHeldToB(Command c) {
		B.whileHeld(c);
	}

	public void addWhenPressedToB(Command c) {
		B.whenPressed(c);
	}

	// X BUTTON
	public boolean getXButton() {
		return this.getRawButton(3);
	}

	public void toggleXButton() {
		if (this.XToggle) {
			this.XToggle = false;
		} else if (!this.XToggle) {
			this.XToggle = true;
		}
	}

	public boolean getXToggle() {
		if (this.XToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToX(Command c) {
		X.whenPressed(c);
	}

	public void addWhenReleasedToX(Command c) {
		X.whenReleased(c);
	}

	public void addWhenHeldToX(Command c) {
		X.whileHeld(c);
	}

	public void addWhenPressedToX(Command c) {
		X.whenPressed(c);
	}

	// Y BUTTON
	public boolean getYButton() {
		return this.getRawButton(4);
	}

	public void toggleYButton() {
		if (this.YToggle) {
			this.YToggle = false;
		} else if (!this.YToggle) {
			this.YToggle = true;
		}
	}

	public boolean getYToggle() {
		if (this.YToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToY(Command c) {
		Y.whenPressed(c);
	}

	public void addWhenReleasedToY(Command c) {
		Y.whenReleased(c);
	}

	public void addWhenHeldToY(Command c) {
		Y.whileHeld(c);
	}

	public void addWhenPressedToY(Command c) {
		Y.whenPressed(c);
	}

	// LEFT BUMPER
	public boolean getLeftBumper() {
		return this.getRawButton(5);
	}

	public void toggleLeftBumper() {
		if (this.LBToggle) {
			this.LBToggle = false;
		} else if (!this.LBToggle) {
			this.LBToggle = true;
		}
	}

	public boolean getLBToggle() {
		if (this.LBToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToLeftBumper(Command c) {
		leftBumper.whenPressed(c);
	}

	public void addWhenReleasedToLeftBumper(Command c) {
		leftBumper.whenReleased(c);
	}

	public void addWhenHeldToLeftBumper(Command c) {
		leftBumper.whileHeld(c);
	}

	public void addWhenPressedToLeftBumper(Command c) {
		leftBumper.whenPressed(c);
	}

	// RIGHT BUMPER
	public boolean getRightBumper() {
		return this.getRawButton(6);
	}

	public void toggleRightBumper() {
		if (this.RBToggle) {
			this.RBToggle = false;
		} else if (!this.RBToggle) {
			this.RBToggle = true;
		}
	}

	public boolean getRBToggle() {
		if (this.RBToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToRightBumper(Command c) {
		rightBumper.whenPressed(c);
	}

	public void addWhenReleasedToRightBumper(Command c) {
		rightBumper.whenReleased(c);
	}

	public void addWhenHeldToRightBumper(Command c) {
		rightBumper.whileHeld(c);
	}

	public void addWhenPressedToRightBumper(Command c) {
		rightBumper.whenPressed(c);
	}

	// LEFT MENU BUTTON
	public boolean getLeftMenu() {
		return this.getRawButton(7);
	}

	public void toggleLeftMenu() {
		if (this.LMToggle) {
			this.LMToggle = false;
		} else if (!this.LMToggle) {
			this.LMToggle = true;
		}
	}

	public boolean getLMToggle() {
		if (this.LMToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToLeftMenu(Command c) {
		leftMenu.whenPressed(c);
	}

	public void addWhenReleasedToLeftMenu(Command c) {
		leftMenu.whenReleased(c);
	}

	public void addWhenHeldToLeftMenu(Command c) {
		leftMenu.whileHeld(c);
	}

	public void addWhenPressedToLeftMenu(Command c) {
		leftMenu.whenPressed(c);
	}

	// RIGHT MENU BUTTON
	public boolean getRightMenu() {
		return this.getRawButton(8);
	}

	public void toggleRightMenu() {
		if (this.RMToggle) {
			this.RMToggle = false;
		} else if (!this.RMToggle) {
			this.RMToggle = true;
		}
	}

	public boolean getRMToggle() {
		if (this.RMToggle) {
			return true;
		} else {
			return false;
		}
	}

	public void addCommandToRightMenu(Command c) {
		rightMenu.whenPressed(c);
	}

	public void addWhenReleasedToRightMenu(Command c) {
		rightMenu.whenReleased(c);
	}

	public void addWhenHeldToRightMenu(Command c) {
		rightMenu.whileHeld(c);
	}

	public void addWhenPressedToRightMenu(Command c) {
		rightMenu.whenPressed(c);
	}

	// LEFT STICK BUTTON
	public void addCommandToLeftStick(Command c) {
		leftStick.whenPressed(c);
	}

	public boolean getLeftStickButton() {
		return this.getRawButton(9);
	}

	// RIGHT STICK BUTTON
	public void addCommandToRightStick(Command c) {
		rightStick.whenPressed(c);
	}

	public boolean getRightStickButton() {
		return this.getRawButton(10);
	}

	// LEFT TRIGGER
	public double getLeftTriggerAnalog() {
		return this.getRawAxis(2);
	}

	public boolean getLeftTriggerDown() {
		return this.getLeftTriggerAnalog() >= 0.75;
	}

	public void addWhenPressedToLeftTrigger(Command c) {
		this.leftTrig.whenPressed(c);
	}

	public void addWhenReleasedToLeftTrigger(Command c) {
		this.leftTrig.whenReleased(c);
	}

	public void addWhenHeldToLeftTrigger(Command c) {
		this.leftTrig.whileHeld(c);
	}

	// RIGHT TRIGGER
	public double getRightTriggerAnalog() {
		return this.getRawAxis(3);
	}

	public boolean getRightTriggerDown() {
		return this.getRightTriggerAnalog() >= 0.75;
	}

	public void addWhenPressedToRightTrigger(Command c) {
		this.rightTrig.whenPressed(c);
	}

	public void addWhenReleasedToRightTrigger(Command c) {
		this.rightTrig.whenReleased(c);
	}

	public void addWhenHeldRightTrigger(Command c) {
		this.rightTrig.whileHeld(c);
	}

	// LEFT STICK
	public double getLeftStickX() {
		return this.getRawAxis(0);
	}

	public double getLeftStickY() {
		return this.getRawAxis(1);
	}

	// RIGHT STICK
	public double getRightStickX() {
		return this.getRawAxis(4);
	}

	public double getRightStickY() {
		return this.getRawAxis(5);
	}
}
