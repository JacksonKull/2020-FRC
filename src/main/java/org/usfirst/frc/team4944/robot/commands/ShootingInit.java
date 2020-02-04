/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class ShootingInit extends InstantCommand {
  ShooterSubsystem shooter;
  double shooterPow;

  public ShootingInit(double shootingPower) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    this.shooter = new ShooterSubsystem();
    requires(shooter);
    this.shooterPow = shootingPower;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.shooter.setManualShooterPower(this.shooterPow);
  }
}
