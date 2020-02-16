package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.POVButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
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
	Button rightTrigger, leftTrigger;
	POVButton dpad0, dpadn45, dpadn90,dpadn135,dpadp45,dpadp90,dpadp135;
	Boolean AToggle, BToggle, XToggle, YToggle, RBToggle, LBToggle, RTToggle, LTToggle;
	Boolean prevA, prevB, prevX, prevY, prevRB, prevLB, prevRT, prevLT;
	public XboxController(int port) {
		super(port);
		this.A = new JoystickButton(this, 1);
		this.B = new JoystickButton(this, 2);
		this.X = new JoystickButton(this, 3);
		this.Y = new JoystickButton(this, 4);
		this.rightTrigger = new Button(){
			@Override
			public boolean get() {return getRawAxis(3) > 0.75;}
		};
		this.leftTrigger = new Button(){
			@Override
			public boolean get() {return getRawAxis(2) > 0.75;}
		};
		this.leftBumper = new JoystickButton(this, 5);
		this.rightBumper = new JoystickButton(this, 6);
		this.leftMenu = new JoystickButton(this, 7);
		this.rightMenu = new JoystickButton(this, 8);
		this.leftStick = new JoystickButton(this, 9);
		this.rightStick = new JoystickButton(this, 10);
		this.dpad0 = new POVButton(this, 0);
		this.dpadp45 = new POVButton(this, 45);
		this.dpadp90 = new POVButton(this, 90);
		this.dpadp135 = new POVButton(this, 135);
		this.dpadn45 = new POVButton(this, 315);
		this.dpadn90 = new POVButton(this, 270);
		this.dpadn135 = new POVButton(this, 225);
		this.AToggle = false;
		this.BToggle = false;
		this.XToggle = false;
		this.YToggle = false;
		this.RBToggle = false;
		this.LBToggle = false;
		this.RTToggle = false;
		this.LTToggle = false;
	}
	// A BUTTON
	public void addCommandToA(Command c) {
		A.whenPressed(c);
	}
	public void addWhenReleasedToA(Command c){
		A.whenReleased(c);
	}
	public void addWhenHeldToA(Command c){
		A.whileHeld(c);
	}
	public boolean getAButton() {
		return this.getRawButton(1);
	}
	public void toggleAButton(){
		if(this.AToggle){
			this.AToggle = false;
		}else if(!this.AToggle){
			this.AToggle = true;
		}
	}
	public boolean getAToggle(){
		if(this.AToggle){
			return true;
		}else{
			return false;
		}
	}
	// B BUTTON
	public void addCommandToB(Command c) {
		B.whenPressed(c);
	}
	public void addWhenReleasedToB(Command c){
		B.whenReleased(c);
	}
	public void addWhenHeldToB(Command c){
		B.whileHeld(c);
	}
	public void addWhenPressed(Command c){
		B.whenPressed(c);
	}
	public boolean getBButton() {
		return this.getRawButton(2);
	}
	public void toggleBButton(){
		if(this.BToggle){this.BToggle = false;}
		else if(!this.BToggle){this.BToggle = true;}
	}
	public boolean getBToggle(){
		return this.BToggle;
	}
	public void toggleCommandsB(Command onCommand, Command offCommand){
		//Toggles A Button (Needs to be fully transfered into XboxController Class)
		if(this.getBButton() && !prevB){
			this.toggleBButton();
			this.prevB = true;
		}
		else if(!this.getBButton() && prevB){this.prevB = false;}
		if(!(this.getBToggle())){this.addWhenHeldToB(onCommand);}
		else if(!this.getBToggle()){this.addWhenReleasedToB(offCommand);}
	}
	// X BUTTON
	public void addCommandToX(Command c) {
		X.whenPressed(c);
	}
	public void addWhenReleasedToX(Command c){
		X.whenPressed(c);
	}
	public boolean getXButton() {
		return this.getRawButton(3);
	}
	public void addWhenHeldToX(Command c){
		X.whileHeld(c);
	}
	public void toggleXButton(){
		if(this.XToggle){
			this.XToggle = false;
		}else if(!this.XToggle){
			this.XToggle = true;
		}
	}
	public boolean getXToggle(){
		if(this.XToggle){
			return true;
		}else{
			return false;
		}
	}
	// Y BUTTON
	public void addCommandToY(Command c) {
		Y.whenPressed(c);
	}
	public void addWhenReleasedToY(Command c){
		Y.whenReleased(c);
	}
	public boolean getYButton() {
		return this.getRawButton(4);
	}
	public void addWhenHeldToY(Command c){
		Y.whileHeld(c);
	}
	public void toggleYButton(){
		if(this.YToggle){
			this.YToggle = false;
		}else if(!this.YToggle){
			this.YToggle = true;
		}
	}
	public boolean getYToggle(){
		if(this.YToggle){
			return true;
		}else{
			return false;
		}
	}
	// LEFT BUMPER
	public void addCommandToLeftBumper(Command c) {
		leftBumper.whenPressed(c);
	}
	public void addWhenHeldToLeftBumper(Command c){
		leftBumper.whileHeld(c);
	}
	public void addWhenReleasedToLeftBumper(Command c){
		leftBumper.whenReleased(c);
	}
	public boolean getLeftBumper() {
		return this.getRawButton(5);
	}
	public void toggleLBButton(){
		if(this.LBToggle){this.LBToggle = false;}
		else if(!this.LBToggle){this.LBToggle = true;}
	}
	public boolean getLBToggle(){
		if(this.LBToggle){return true;
		}else{return false;}
	}
	// RIGHT BUMPER
	public void addCommandToRightBumper(Command c) {
		rightBumper.whenPressed(c);
	}
	public void addWhenHeldToRightBumper(Command c){
		rightBumper.whileHeld(c);
	}
	public void addWhenReleasedToRightBumper(Command c){
		rightBumper.whenReleased(c);
	}
	public boolean getRightBumper() {
		return this.getRawButton(6);
	}
	public void toggleRBButton(){
		if(this.RBToggle){this.RBToggle = false;}
		else if(!this.RBToggle){this.RBToggle = true;}
	}
	public boolean getRBToggle(){
		if(this.RBToggle){return true;
		}else{return false;}
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
	public void addWhenHeldToLeftTrigger(Command c){
		
	}
	public void addCommandToLeftTrigger(Command c){
		this.leftTrigger.whenActive(c);
	}
	//public void addWhenReleasedToLeftTrigger(Command c){
	//	
	// }
	// public void toggleLTButton(){
	// 	if(this.LTToggle){this.LTToggle = false;}
	// 	else if(!this.LTToggle){this.LTToggle = true;}
	// }
	// public boolean getLTToggle(){
	// 	if(this.LTToggle){return true;
	// 	}else{return false;}
	// }
	// RIGHT TRIGGER
	public double getRightTriggerAnalog() {
		return this.getRawAxis(3);
	}
	public boolean getRightTriggerDown() {
		return this.getRightTriggerAnalog() >= 0.75;
	}
	public void addCommandToRightTrigger(Command c){
		this.rightTrigger.whenActive(c);
	}
	// Integrate Properly TODO
	public void toggleRTButton(){
		if(this.RTToggle){this.RTToggle = false;}
		else if(!this.RTToggle){this.RTToggle = true;}
	}
	public boolean getRTToggle(){
		if(this.RTToggle){return true;
		}else{return false;}
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
	//DPAD
	public boolean getDpadPressed(){
		return dpadn135.get() || dpadn90.get() || dpadn45.get() || dpad0.get() || dpadp45.get() || dpadp90.get() || dpadp135.get();
	}

	public double getDpadAngle(){
		if(dpadn135.get()){
			return -135;
		}else if(dpadn90.get()){
			return -90;
		}else if(dpadn45.get()){
			return -45;
		}else if(dpad0.get()){
			return 0;
		}else if(dpadp45.get()){
			return 45;
		}else if(dpadp90.get()){
			return 90;
		}else if(dpadp135.get()){
			return 135;
		}else{
			return Double.NaN;
		}
	}
}
