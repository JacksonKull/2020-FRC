package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class QueueFinished extends InstantCommand {

  HopperSubsystem hopper;

  public QueueFinished() {
    super();
    this.hopper = new HopperSubsystem();
    this.requires(hopper);
  }

  @Override
  protected void initialize() {
    this.hopper.setBeltMotor(0.0);
    this.hopper.setHopperMotor(0.0);
  }

}
