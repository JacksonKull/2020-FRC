/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

/**
 * Add your docs here.
 */
public class FeederBegin extends InstantCommand {

  HopperSubsystem hopper;
  double feederPower;
  double hopperPower;

  public FeederBegin(double feederPow, double hopperPow) {
    super();
    this.hopper = new HopperSubsystem();
    this.requires(hopper);
    this.hopperPower = hopperPow;
    this.feederPower = feederPow;
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.hopper.setFeedMotor(this.feederPower);
    this.hopper.setHopperMotor(this.hopperPower);
  }

}
