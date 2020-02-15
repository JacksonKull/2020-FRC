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

import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class ShooterSubsystem extends Subsystem {

	// Motors
	TalonFX shooterMotor1, shooterMotor2;
	// Constants
	final int ticksPerRevolution = 4096;
	final double p = 1;
	final double i = 0;
	final double d = 0;
	// PID
	BasicPID shooterPID;

	public ShooterSubsystem(){
		// Motors
		this.shooterMotor1 = new TalonFX(17);
		//this.shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		this.shooterMotor1.setInverted(true);
		this.shooterMotor2 = new TalonFX(12);
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

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
