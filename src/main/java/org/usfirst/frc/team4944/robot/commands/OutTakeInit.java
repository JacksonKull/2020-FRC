/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OutTakeInit extends InstantCommand {

  IntakeSubsystem intake;
  HopperSubsystem hopper;
  double intakePow, hopperPow, feederPow;
  ArmSubsystem arms;

  public OutTakeInit(double intakePow,double hopperPow,double feederPow) {
    super();
    this.intake = new IntakeSubsystem();
    this.intakePow = intakePow;
    requires(intake);
    this.hopper = new HopperSubsystem();
    this.hopperPow = hopperPow;
    this.feederPow = hopperPow;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    // this.arms.disableBreak();
    this.intake.setIntakeMotor(this.intakePow);
    this.hopper.setHopperMotor(this.hopperPow);
    this.hopper.setFeedMotor(this.hopperPow);
  }

}
