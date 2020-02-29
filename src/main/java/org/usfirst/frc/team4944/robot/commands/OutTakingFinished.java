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

public class OutTakingFinished extends InstantCommand {
  
  IntakeSubsystem intake;
  HopperSubsystem hopper;
  ShooterSubsystem shooter;

  public OutTakingFinished() {
    super();
    this.intake = new IntakeSubsystem();
    this.hopper = new HopperSubsystem();
    this.shooter = new ShooterSubsystem();
    requires(intake); 
    requires(hopper);
    requires(shooter);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(0);
    this.hopper.setFeedMotor(0);
    this.hopper.setHopperMotor(0);
    this.shooter.setManualShooterPower(0);
  }
}
