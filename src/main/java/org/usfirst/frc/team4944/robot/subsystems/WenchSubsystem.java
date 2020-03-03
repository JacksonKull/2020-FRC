package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class WenchSubsystem extends Subsystem {
  // Motors
  TalonSRX wenchMotor;

  public WenchSubsystem() {
    this.wenchMotor = new TalonSRX(12); 
    this.wenchMotor.setInverted(true);
  }

  public void setWenchMotor(double power) {
    this.wenchMotor.set(ControlMode.PercentOutput, power);
  }

  @Override
  public void initDefaultCommand() {
  }
}
