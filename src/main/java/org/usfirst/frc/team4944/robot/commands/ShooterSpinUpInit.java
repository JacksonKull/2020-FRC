package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ShooterSpinUpInit extends InstantCommand {

  ShooterSubsystem shooter;
  double shooterPower;

  public ShooterSpinUpInit(double shooterPow) {
    super();
    this.shooter = new ShooterSubsystem();
    this.requires(shooter);
    this.shooterPower = shooterPow;
  }

  @Override
  public void initialize() {
    this.shooter.setManualShooterPower(this.shooterPower);
  }
}
