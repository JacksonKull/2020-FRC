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
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class OutTakeInit extends InstantCommand {

  IntakeSubsystem intake;
  HopperSubsystem hopper;
  double intakePower, hopperPower, feederPower, shooterPower;
  ArmSubsystem arms;
  ShooterSubsystem shooter;

  public OutTakeInit(double intakePow,double hopperPow,double feederPow, double shooterPow) {
    super();
    this.intake = new IntakeSubsystem();
    this.hopper = new HopperSubsystem();
    this.shooter = new ShooterSubsystem();
    requires(intake);
    requires(hopper);
    requires(shooter);
    this.intakePower = intakePow;
    this.hopperPower = hopperPow;
    this.feederPower = hopperPow;
    this.shooterPower = shooterPow;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    // this.arms.disableBreak();
    this.intake.setIntakeMotor(this.intakePower);
    this.hopper.setHopperMotor(this.hopperPower);
    this.hopper.setFeedMotor(this.hopperPower);
    this.shooter.setManualShooterPower(this.shooterPower);;
  }

}
