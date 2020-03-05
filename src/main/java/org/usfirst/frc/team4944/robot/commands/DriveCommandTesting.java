/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.team4944.robot.PID.BasicPID;
import org.usfirst.team4944.robot.PID.DrivePID;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveCommandTesting extends CommandGroup {
 
  	// CONSTANTS

    double left_P = 0;
    double left_I = 0;
    double left_D = 0;

    double right_P = 0;
    double right_I = 0;
    double right_D = 0;

    double angle_P = 0;
    double angle_I = 0;
    double angle_D = 0;

    double maxPower = 0.25;
    double minPower = -0.25;

  // PIDS
  DrivePID leftPID = new DrivePID(this.left_P, this.left_I, this.left_D, this.minPower, this.maxPower);
  DrivePID rightPID = new DrivePID(this.right_P, this.right_I, this.right_D, this.minPower, this.maxPower);
  BasicPID anglePID = new BasicPID(this.angle_P, this.angle_I, this.angle_D);

  // GOALS
  final double headingGoal = 0;
  final double driveGoal = 0;
  
  // DRIVE COMMANDS
  
  DriveStraight drive = new DriveStraight(this.driveGoal,this.headingGoal,this.leftPID,this.rightPID,this.anglePID);

  public DriveCommandTesting() {
    addSequential(this.drive);
  }
}
