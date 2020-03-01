package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.WenchSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class WenchFinished extends InstantCommand {

  WenchSubsystem wench;

  public WenchFinished() {
    super();
    this.wench = new WenchSubsystem();
    requires(wench);
  }

  @Override
  protected void initialize() {
    this.wench.setWenchMotor(0);
  }
}
