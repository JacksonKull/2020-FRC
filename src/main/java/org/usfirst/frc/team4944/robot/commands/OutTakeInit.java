/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OutTakeInit extends InstantCommand {
  
  IntakeSubsystem intake;
  double outTakePower;

  public OutTakeInit(double power) {
    super();
    this.intake = new IntakeSubsystem();
    this.outTakePower = power;
    requires(intake);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    System.out.println("OutTake Begining");
    this.intake.setIntakeMotor(this.outTakePower);
  }

}
