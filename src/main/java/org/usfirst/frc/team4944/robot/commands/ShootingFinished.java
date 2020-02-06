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

public class ShootingFinished extends InstantCommand {

  ShooterSubsystem shooter;
  HopperSubsystem hopper;
  IntakeSubsystem intake;

  public ShootingFinished() {
    super();
    this.shooter = new ShooterSubsystem();
    this.hopper = new HopperSubsystem();
    this.intake = new IntakeSubsystem();
    requires(shooter);
    requires(hopper);
    requires(intake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    System.out.println("Setting Motors To Zero");
    this.shooter.setManualShooterPower(0);
    this.hopper.setFeedMotor(0);
    this.hopper.setHopperMotor(0);
    this.intake.setIntakeMotor(0);
  }

}
