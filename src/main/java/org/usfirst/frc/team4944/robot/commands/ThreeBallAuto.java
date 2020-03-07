/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ThreeBallAuto extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ThreeBallAuto() {
    addSequential(new VisionAlign());
    addSequential(new setShooterByLm());
    addSequential(new WaitCommand(2));
    addSequential(new QueueInit(0.7, 0.7));
    addSequential(new WaitCommand(2));
    addSequential(new QueueInit(0.0, 0.0));
  }
}
