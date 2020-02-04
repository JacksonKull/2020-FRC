/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class UnstickBallFinish extends InstantCommand {
  
  IntakeSubsystem intake;

  public UnstickBallFinish() {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
  } 

  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(0);
  }

}
