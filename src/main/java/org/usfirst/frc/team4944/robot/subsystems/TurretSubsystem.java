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
import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretSubsystem extends Subsystem {
	//Limelight
	public Limelight lm;
	//Motors
	TalonSRX turretMotor;
	Encoder turretEncoder; 
	//Constants
	final int turretOffset = 100;
	final int minEncoder = 0;
	final int neutralEncoder = 0;
	final int maxEncoder = 0;
	final double minAngle = -90;
	final double maxAngle = 90;
	//PID
	BasicPID turretPID;
	final double p = 1;
	final double i = 0;
	final double d = 0;

	public TurretSubsystem(){
		this.turretMotor = new TalonSRX(0);
		this.turretMotor.configSelectedFeedbackSensor(FeedbackDevice.PulseWidthEncodedPosition, 0, 0);
		this.turretPID = new BasicPID(this.p, this.i, this.d);
		this.turretPID.setSetPoint(this.neutralEncoder);
		//this.turretEncoder = new Encoder(0, 0);
		this.lm = new Limelight();
	}

	private void setTurretMotorPower(double power){
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

	public void driveTurretPID(){
		double power = this.turretPID.getPower(this.getTurretEncoderValue());
		this.setTurretMotorPower(power);
	}

	public int getTurretEncoderValue(){
		return this.turretMotor.getSelectedSensorPosition();
	}

	//Returns if the shooter is within lineup range
	public boolean followLimelight(){
		if(!lm.getTargetVisible()){return false;}
		if(Math.abs(lm.getXOffset()) < 1){
			this.setTurretSetPoint(this.getTurretEncoderValue());
			return true;
		}
		this.addAngle(lm.getXOffset());
		return true;
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
