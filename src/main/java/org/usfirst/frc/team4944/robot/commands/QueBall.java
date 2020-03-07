package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.command.Command;

public class QueBall extends Command {

  HopperSubsystem hopper;

  public QueBall() {
    this.hopper = new HopperSubsystem();
  }

  @Override
  public void initialize() {

  }

  @Override
  protected void execute() {
    if (!this.hopper.getBallVisible()) {
      this.hopper.setBeltMotor(0.7);
      this.hopper.setHopperMotor(0.7);
    } else {
      this.hopper.setBeltMotor(0);
      this.hopper.setHopperMotor(0);
    }

  }

  @Override
  protected boolean isFinished() {
    // TODO Auto-generated method stub
    if (this.hopper.getBallVisible()) {
      return true;
    } else {
      return false;
    }
  }

  @Override 
  protected void end(){
    this.hopper.setBeltMotor(0);
    this.hopper.setHopperMotor(0);
  }

  @Override 
  protected void interrupted(){
    this.hopper.setBeltMotor(0);
    this.hopper.setHopperMotor(0);
  }

}