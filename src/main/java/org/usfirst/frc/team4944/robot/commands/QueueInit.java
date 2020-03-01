package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class QueueInit extends InstantCommand {

  HopperSubsystem hopper;
  double beltPower;
  double hopperPower;

  public QueueInit(double beltPow, double hopperPow) {
    super();
    this.hopper = new HopperSubsystem();
    this.requires(hopper);
    this.beltPower = beltPow;
    this.hopperPower = hopperPow;
  }

  @Override
  protected void initialize() {
    this.hopper.setBeltMotor(this.beltPower);
    this.hopper.setHopperMotor(this.hopperPower);
  }
}
