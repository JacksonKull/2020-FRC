/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class UnstickBall extends CommandGroup {
  public UnstickBall() {
    addSequential(new UnstickBallInit(-0.75));
    addSequential(new WaitCommand(10));
    addSequential(new UnstickBallMid(0.75));
    addSequential(new WaitCommand(0.75));
    addSequential(new UnstickBallFinish());
  }
}
