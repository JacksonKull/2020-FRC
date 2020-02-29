/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class ArmSubsystem extends Subsystem {
  TalonFX leftArmMotor;
  TalonFX rightArmMotor;

  public ArmSubsystem() {
    this.leftArmMotor = new TalonFX(5);
    this.rightArmMotor = new TalonFX(6);
    this.leftArmMotor.setInverted(true);
    this.leftArmMotor.setNeutralMode(NeutralMode.Brake);
    this.rightArmMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void setLeftArmMotor(double power) {
    this.leftArmMotor.set(ControlMode.PercentOutput, power);
  }

  public void setRightArmMotor(double power) {
    this.rightArmMotor.set(ControlMode.PercentOutput, power);
  }

  public void applyBreak() {
    this.leftArmMotor.setNeutralMode(NeutralMode.Brake);
    this.rightArmMotor.setNeutralMode(NeutralMode.Brake);
  }

  public void disableBreak() {
    this.leftArmMotor.setNeutralMode(NeutralMode.Coast);
    this.rightArmMotor.setNeutralMode(NeutralMode.Coast);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
