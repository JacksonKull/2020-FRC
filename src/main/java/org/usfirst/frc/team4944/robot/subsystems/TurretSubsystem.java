/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.frc.team4944.robot.custom.Motor;
import org.usfirst.frc.team4944.robot.custom.MotorType;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;

public class TurretSubsystem extends Subsystem {
	//Limelight
	Limelight lm;
	//Motors
	TalonSRX turretMotor;
	Encoder turretEncoder; 

	public TurretSubsystem(){
		this.turretMotor = new TalonSRX(0);
		this.turretEncoder = new Encoder(0, 0);
		this.lm = new Limelight();
	}

	public void setTurretMotorPower(double power){
		this.turretMotor.set(ControlMode.PercentOutput,power);
	}

	public int getTurretEncoderValue(){
		return this.turretMotor.getSelectedSensorPosition();
	}

	@Override
	protected void initDefaultCommand() {
		
	}
}
