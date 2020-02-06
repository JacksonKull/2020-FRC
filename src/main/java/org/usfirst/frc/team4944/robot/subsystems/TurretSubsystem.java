/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.frc.team4944.robot.custom.Motor;
import org.usfirst.frc.team4944.robot.custom.MotorType;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretSubsystem extends Subsystem {
	//Limelight
	public Limelight lm;
	//Motors
	TalonSRX turretMotor;
	Encoder turretEncoder;
	//Servo
	Servo hoodServo; 
	//Constants
	final int turretOffset = 100;
	final int minEncoder = -1312;
	final int neutralEncoder = 0;
	final int maxEncoder = 1220;
	final double maxServo = 1;
	final double minServo = 0;
	final double maxServoAngle = 90;
	final double minServoAngle = -90;
	//Total Range of 2532
	final double minAngle = -90;
	final double maxAngle = 90;
	final double maxPow = 0.15;
	final double visionMaxPow = 0.15;
	//PID
	BasicPID turretPID;
	//final double visionP = .58;
	final double visionP = .28;
	final double p = .05;
	final double i = 0;
	final double d = 0;

	public TurretSubsystem(){
		this.turretMotor = new TalonSRX(6);
		this.turretMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute, 0, 0);
		this.turretMotor.setSelectedSensorPosition(neutralEncoder);
		this.turretPID = new BasicPID(this.p, this.i, this.d);
		this.turretPID.setSetPoint(this.neutralEncoder);
		this.hoodServo = new Servo(0);
		this.lm = new Limelight();
	}

	private void setHoodServo(double angle){
		this.hoodServo.set(angle);
	}

	private double convertHoodAngle(double desiredAngle){
		double valuesPerAngle = (maxServo - minServo)/(maxServoAngle - minServoAngle);
		return (desiredAngle*valuesPerAngle);
	}

	public double getHoodAngle(){
		return this.hoodServo.getAngle();
	}

	public void setHoodAngle(double desiredAngle){
		this.setHoodServo(this.convertHoodAngle(desiredAngle));
	}

	public void setTurretMotorPower(double power){
		this.turretMotor.set(ControlMode.PercentOutput,power);
	}

	private void setTurretSetPoint(int encoderSetPoint){
		if(encoderSetPoint<minEncoder){
			this.turretPID.setSetPoint(minEncoder);
		}else if(encoderSetPoint>maxEncoder){
			this.turretPID.setSetPoint(maxEncoder);
		}else{
			this.turretPID.setSetPoint(encoderSetPoint);
		}
	}

	public double getTurretSetPoint(){
		return this.turretPID.getSetPoint();
	}

	public double getTurretPower(){
		return this.turretMotor.getMotorOutputPercent();
	}

	public void driveTurretPID(){
		System.out.println(Math.abs(Math.abs(this.getTurretSetPoint()) - Math.abs(this.getTurretEncoderValue() )) + " Difference");
		if(Math.abs(Math.abs(this.getTurretSetPoint()) - Math.abs(this.getTurretEncoderValue() )) > 0.5){
			double power = this.turretPID.getPower(this.getTurretEncoderValue());
			System.out.println((-power)*visionMaxPow + " Power");
			this.setTurretMotorPower((-power)*maxPow);
		}else{
			System.out.println("Turret Within Range");
			this.setTurretMotorPower(0);
		}
	}

	public void followLimelightNoEncoder(){
		if(lm.getTargetVisible()){
			double turPow = lm.getXOffset()*visionP;
			System.out.println(turPow*maxPow);
			this.setTurretMotorPower((-turPow)*this.maxPow);
		}
	}

	public int getTurretEncoderValue(){
		return this.turretMotor.getSelectedSensorPosition();
	}

	//Returns if the shooter is within lineup range
	public boolean followLimelight(){
		System.out.println(lm.getTargetVisible());
		if(!lm.getTargetVisible()){
			return false;
		}else if(Math.abs(lm.getXOffset()) < 1){
			this.setTurretSetPoint(this.getTurretEncoderValue());
			return true;
		}else{
			this.addAngle(lm.getXOffset());
			return false;
		}
	}

	private int convertAngleToEncoder(double desiredAngle){
		double ticksPerDegree = (maxEncoder - minEncoder)/(maxAngle - minAngle);
		return (int) (desiredAngle * ticksPerDegree);
	}

	public void setAngle(double desiredAngle){
		// 0 is neutral
		// 90 is 90 to the right
		// -90 is 90 to the left
		int encoderGoal = neutralEncoder + this.convertAngleToEncoder(desiredAngle);
		this.setTurretSetPoint(encoderGoal);
	}

	public void addAngle(double desiredAngle){
		int encoderGoal = this.getTurretEncoderValue() + this.convertAngleToEncoder(desiredAngle);
		this.setTurretSetPoint(encoderGoal);
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
