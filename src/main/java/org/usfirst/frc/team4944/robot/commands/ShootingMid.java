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

public class ShootingMid extends InstantCommand {
  HopperSubsystem hopper;
  IntakeSubsystem intake;
  // Doubles
  double intakePow;
  double shooterPow;

  public ShootingMid() {
    this.hopper = new HopperSubsystem();
    requires(hopper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.hopper.setHopperMotor(0.75);
    this.hopper.setFeedMotor(0.75);
  }
}
