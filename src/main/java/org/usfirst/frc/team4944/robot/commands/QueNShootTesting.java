/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class QueNShootTesting extends CommandGroup {
  
  final double shootDelay = 0.5; 
  AnalogInput ballSensor = new AnalogInput(3);

  public QueNShootTesting() {
    // addSequential(new setShooterByLm());
    // addSequential(new VisionAlign());
    // addSequential(new QueNShoot(3, 15000));
    // addSequential(new ShooterSpinDown());
    addSequential(new setShooterByLm());
    addSequential(new VisionAlign());
    // Que Ball One
    addSequential(new QueBall(this.ballSensor));
    addSequential(new QueueInit(0.7,0.7));
    addSequential(new WaitCommand(this.shootDelay));
    addSequential(new QueueFinished());
    System.out.println("Ball One");
    // Que Ball Two
    addSequential(new QueBall(this.ballSensor));
    addSequential(new QueueInit(0.7,0.7));
    addSequential(new WaitCommand(this.shootDelay));
    addSequential(new QueueFinished());
    System.out.println("Ball Two");
    // Que Ball Three
    addSequential(new QueBall(this.ballSensor));
    addSequential(new QueueInit(0.7,0.7));
    addSequential(new WaitCommand(this.shootDelay + 0.25));
    addSequential(new QueueFinished());
    System.out.println("Ball Three");
    addSequential(new ShooterSpinDown());
  }
}
