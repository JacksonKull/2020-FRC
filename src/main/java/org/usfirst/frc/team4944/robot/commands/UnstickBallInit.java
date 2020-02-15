/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class UnstickBallInit extends InstantCommand {
  /**
   * Add your docs here.
   */
  IntakeSubsystem intake;
  //Doubles
  double intakePower;
  public UnstickBallInit(double intakePow) {
    super();
    this.intake = new IntakeSubsystem();
    requires(intake);
    this.intakePower = intakePow;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.intake.setIntakeMotor(this.intakePower);
  }

}
