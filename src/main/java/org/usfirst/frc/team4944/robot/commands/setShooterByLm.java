/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.InstantCommand;

public class setShooterByLm extends InstantCommand {

  ShooterSubsystem shooter;
  Limelight lm;

  public setShooterByLm() {
    this.shooter = new ShooterSubsystem();
    this.lm = new Limelight();
    requires(shooter);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.shooter.setManualShooterPower(0.5211761905 + (0.00835 * this.lm.getDistInFeet()));
  }
}
