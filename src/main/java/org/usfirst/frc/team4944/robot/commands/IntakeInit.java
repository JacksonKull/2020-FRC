/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class IntakeInit extends InstantCommand {

  IntakeSubsystem intake;
  double intakePower;
  ArmSubsystem arms;

  public IntakeInit(double power) {
    super();
    this.intake = new IntakeSubsystem();
    this.intakePower = power;
    requires(intake);
    this.arms = new ArmSubsystem();
    requires(arms);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.arms.disableBreak();
    this.intake.setIntakeMotor(this.intakePower);
  }

}
