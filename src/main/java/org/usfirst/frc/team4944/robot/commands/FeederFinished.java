/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class FeederFinished extends InstantCommand {
  
  HopperSubsystem hopper;

  public FeederFinished() {
    super();
    this.hopper = new HopperSubsystem();
    this.requires(this.hopper);
  }

  // Called once when the command executes
  @Override
  protected void initialize() {
    this.hopper.setFeedMotor(0.0);
    this.hopper.setHopperMotor(0.0);
  }

}
