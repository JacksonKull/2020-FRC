package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
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
public class XboxController extends Joystick{
	Button A, B, X, Y, leftBumper, rightBumper, leftMenu, rightMenu, leftStick, rightStick;
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
	}
	// A BUTTON
	public void addCommandToA(Command c) {
		A.whenPressed(c);
	}
	public boolean getAButton() {
		return this.getRawButton(1);
	}
	// B BUTTON
	public void addCommandToB(Command c) {
		B.whenPressed(c);
	}
	public boolean getBButton() {
		return this.getRawButton(2);
	}
	// X BUTTON
	public void addCommandToX(Command c) {
		X.whenPressed(c);
	}
	
	public boolean getXButton() {
		return this.getRawButton(3);
	}
	// Y BUTTON
	public void addCommandToY(Command c) {
		Y.whenPressed(c);
	}
	public boolean getYButton() {
		return this.getRawButton(4);
	}
	// LEFT BUMPER
	public void addCommandToLeftBumper(Command c) {
		leftBumper.whenPressed(c);
	}
	public boolean getLeftBumper() {
		return this.getRawButton(5);
	}
	// RIGHT BUMPER
	public void addCommandToRightBumper(Command c) {
		rightBumper.whenPressed(c);
	}
	public boolean getRightBumper() {
		return this.getRawButton(6);
	}
	// LEFT MENU BUTTON
	public void addCommandToLeftMenu(Command c) {
		leftMenu.whenPressed(c);
	}
	public boolean getLeftMenu() {
		return this.getRawButton(7);
	}
	// RIGHT MENU BUTTON
	public void addCommandToRightMenu(Command c) {
		rightMenu.whenPressed(c);
	}
	public boolean getRightMenu() {
		return this.getRawButton(8);
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
	// RIGHT TRIGGER
	public double getRightTriggerAnalog() {
		return this.getRawAxis(3);
	}
	
	public boolean getRightTriggerDown() {
		return this.getRightTriggerAnalog() >= 0.75;
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
