/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.ShooterSubsystem;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;

public class ShootingCommandGroup extends CommandGroup {
  
  IntakeSubsystem intake;
  ShooterSubsystem shooter;
  HopperSubsystem hopper;
  boolean ended;

  public ShootingCommandGroup() {
    this.ended = false;
    this.intake = new IntakeSubsystem();
    this.hopper = new HopperSubsystem();
    this.shooter = new ShooterSubsystem();
    requires(intake);
    requires(shooter);
    requires(hopper);
    addSequential(new ShootingInit(.75));
    addSequential(new WaitCommand(2));
    addSequential(new ShootingMid());
    addSequential(new WaitCommand(2));
    addSequential(new UnstickBallInit(-0.5));
    addSequential(new WaitCommand(0.25));
    addSequential(new UnstickBallMid(0.75));
    addSequential(new WaitCommand(0.25));
    addSequential(new UnstickBallFinish());
  }

  @Override
	protected boolean isFinished() {
    if(this.ended){
      System.out.println("Is Finished Is True");
    }
		return this.ended;
	}

	@Override
	protected void end() {
    System.out.println("End Activated");
    addSequential(new ShootingFinished());
    this.ended = false;
	}

  @Override
	protected void interrupted() {
    System.out.println("Interrupted");
    cancel();
    this.ended = true;
  }



}
