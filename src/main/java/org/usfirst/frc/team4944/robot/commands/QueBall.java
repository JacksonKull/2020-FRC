package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.Robot;
import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;

public class QueBall extends Command {

  HopperSubsystem hopper;
  AnalogInput ballSensor;
  Robot robot;

  public QueBall(AnalogInput input) {
    this.hopper = new HopperSubsystem();
    this.robot = new Robot();
    this.ballSensor = input;
    // this.ballSensor = new AnalogInput(3);
  }

  @Override
  public void initialize() {
  }

  @Override
  protected void execute() {
    if (!(this.ballSensor.getVoltage() >= 3.75)) {
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
    if (this.ballSensor.getVoltage() >= 3.75) {
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