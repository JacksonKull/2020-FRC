/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.OI;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class ShooterSpinDown extends InstantCommand {

  ShooterSubsystem shooter;
  OI oi;

  public ShooterSpinDown() {
    super();
    this.oi = new OI();
    this.shooter = new ShooterSubsystem();
    this.requires(shooter);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.oi.setDriverAButton(false);
    this.shooter.setManualShooterPower(0.0);
  }

}
