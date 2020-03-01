package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ShooterSpinDown extends InstantCommand {

  ShooterSubsystem shooter;

  public ShooterSpinDown() {
    super();
    this.shooter = new ShooterSubsystem();
    this.requires(shooter);
  }

  @Override
  protected void initialize() {
    this.shooter.setManualShooterPower(0.0);
  }
}
