package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ArmSubsystem extends Subsystem {
  TalonFX leftArmMotor;
  TalonFX rightArmMotor;

  public ArmSubsystem() {
    this.leftArmMotor = new TalonFX(5);
    this.rightArmMotor = new TalonFX(6);
    this.leftArmMotor.setInverted(true);
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
  }
}
