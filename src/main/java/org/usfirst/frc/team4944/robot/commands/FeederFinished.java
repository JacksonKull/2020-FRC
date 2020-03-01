package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FeederFinished extends InstantCommand {

  HopperSubsystem hopper;

  public FeederFinished() {
    super();
    this.hopper = new HopperSubsystem();
    this.requires(hopper);
  }

  @Override
  protected void initialize() {
    this.hopper.setFeedMotor(0.0);
    this.hopper.setHopperMotor(0.0);
  }

}
