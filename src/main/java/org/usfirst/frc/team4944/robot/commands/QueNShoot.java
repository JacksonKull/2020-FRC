package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class QueNShoot extends Command {

  HopperSubsystem hopper;
  boolean ballQueued;
  int counter;
  int countGoal;
  double timeoutVal;
  double startTime;
  double elapsedTime;

  public QueNShoot(int ballGoal, double timeout) {
    this.hopper = new HopperSubsystem();
    this.ballQueued = false;
    this.counter = 0;
    this.countGoal = ballGoal;
    this.timeoutVal = timeout;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.startTime = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (!this.hopper.getBallVisible()) {
      this.hopper.setBeltMotor(0.7);
      this.hopper.setHopperMotor(0.7);
      this.ballQueued = false;
    } else if(this.hopper.getBallVisible() && !this.ballQueued) {
      this.hopper.setBeltMotor(0);
      this.hopper.setHopperMotor(0);
      this.ballQueued = true;
      this.counter += 1;
    }else if(this.hopper.getBallVisible() && this.ballQueued){
      this.hopper.setBeltMotor(0.7);
      this.hopper.setHopperMotor(0.7);
    }
    this.elapsedTime = System.currentTimeMillis() - this.startTime;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(this.counter == this.countGoal){
      return true;
    }else if(this.elapsedTime >= this.timeoutVal){
      return true;
    }else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    this.hopper.setBeltMotor(0);
    this.hopper.setHopperMotor(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    this.hopper.setBeltMotor(0);
    this.hopper.setHopperMotor(0);
  }
}
