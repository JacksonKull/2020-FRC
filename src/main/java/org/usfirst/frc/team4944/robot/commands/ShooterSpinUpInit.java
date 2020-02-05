/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.OI;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ShooterSpinUpInit extends InstantCommand {

  ShooterSubsystem shooter;
  OI oi;
  double shooterPower;

  public ShooterSpinUpInit(double shooterPow) {
    super();
    this.oi = new OI();
    this.shooter = new ShooterSubsystem();
    this.requires(shooter);
    this.shooterPower = shooterPow;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.oi.setDriverAButton(true);
    this.shooter.setManualShooterPower(this.shooterPower);
  }
}
