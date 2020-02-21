/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShooterSubsystem extends Subsystem {

	// Motors
	TalonFX shooterMotor1, shooterMotor2;
	//Limelight
	Limelight lm;
	// Constants
	final int ticksPerRevolution = 4096;
	final double p = 1;
	final double i = 0;
	final double d = 0;
	final double shooterHeight = 1;
	final double limelightAngle = 10;
	// PID
	BasicPID shooterPID;
	double vx, vy, lmSpeed;
	
	public ShooterSubsystem(){
		// Motors
		this.shooterMotor1 = new TalonFX(17);
		this.shooterMotor1.setInverted(true);
		this.shooterMotor2 = new TalonFX(12);
		//Limelight
		//this.lm = new Limelight();
		// PID
		this.shooterPID = new BasicPID(p, i, d);
	}

	private void setShooterPower(double rpm){
		double velocity = (rpm*ticksPerRevolution)/600;
		this.shooterMotor1.set(ControlMode.Velocity, velocity);
		this.shooterMotor2.set(ControlMode.Velocity, velocity);
	}

	public void setManualShooterPower(double power){
		this.shooterMotor1.set(ControlMode.PercentOutput, power);
		this.shooterMotor2.set(ControlMode.PercentOutput, power);
	}

	public double getRPM(){
		return 0;//(shooterMotor1.getSelectedSensorVelocity()/ticksPerRevolution)*600;
	}

	public double getVx() {
		this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
		return this.vx;
	  }
	
	public double getVy() {
		this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
		return this.vy;
	}

	public double  getRequiredVelocity(){
		this.lmSpeed = Math.sqrt((this.vy*this.vy) + (this.vx*this.vx));
		return this.lmSpeed + 8;
	}

	public void setSpeedBasedOffLM(){
		this.setManualShooterPower(this.convertFPStoPercentOutput(this.lmSpeed));
	}

	public void updateValues() {
		this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
		this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
	}

	public double convertFPStoPercentOutput(double FPS){
		return ((FPS - 15.19)/12.97);
	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
