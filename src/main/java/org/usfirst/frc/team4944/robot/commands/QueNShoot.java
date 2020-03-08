package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.HopperSubsystem;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.RamseteController;
import frc.robot.Robot;

public class QueNShoot extends Command {

  HopperSubsystem hopper;
  boolean ballQueued;
  int counter;
  int countGoal;
  double timeoutVal;
  double startTime;
  double elapsedTime;
  double elapsedQueTime;
  double elapsedShootTime;
  double shootingStartTime;
  double lastQue;
  final double queDelay = 500;
  final double shootingDelay = 500;
  AnalogInput ballSensor;

  public QueNShoot(int ballGoal, double timeout) {
    this.hopper = new HopperSubsystem();
    // this.ballSensor = new AnalogInput(3);
    this.ballQueued = false;
    this.counter = 0;
    this.countGoal = ballGoal;
    this.timeoutVal = timeout;
    this.lastQue = System.currentTimeMillis();
    requires(hopper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.startTime = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute(){
    // if (!(this.ballSensor.getVoltage() >= 3.75) && this.elapsedShootTime >= this.shootingDelay) {
    //   this.hopper.setBeltMotor(0.7);
    //   this.hopper.setHopperMotor(0.7);
    //   System.out.println("Queing");
    //   this.ballQueued = false;
    // } else if((this.ballSensor.getVoltage() >= 3.75) && !this.ballQueued) {
    //   this.hopper.setBeltMotor(0);
    //   this.hopper.setHopperMotor(0);
    //   System.out.println("Qeued!");
    //   this.ballQueued = true;
    //   this.counter += 1;
    //   this.lastQue = System.currentTimeMillis();
    // }else if((this.ballSensor.getVoltage() >= 3.75) && this.ballQueued  && (this.elapsedQueTime > this.queDelay)){
    //   this.hopper.setBeltMotor(0.7);
    //   this.hopper.setHopperMotor(0.7);
    //   this.ballQueued = false;
    //   System.out.println("Shooting");
    //   this.shootingStartTime = System.currentTimeMillis();
    // }else if((this.ballSensor.getVoltage() >= 3.75) && !this.ballQueued  && (this.elapsedQueTime > this.queDelay)){

    // }
    
    if(!(this.ballSensor.getVoltage() >= 3.75) && !this.ballQueued){
      this.hopper.setBeltMotor(0.7);
      this.hopper.setHopperMotor(0.7);
      System.out.println("Queing");
    }else if(this.ballSensor.getVoltage() >= 3.75 && !this.ballQueued){
        this.ballQueued = true;
        this.hopper.setBeltMotor(0);
        this.hopper.setHopperMotor(0);
        this.lastQue = System.currentTimeMillis();
    }else if(this.ballSensor.getVoltage() >= 3.75 && this.ballQueued){
      if(this.ballQueued && this.elapsedQueTime >= this.queDelay){
        this.hopper.setBeltMotor(0.7);
        this.hopper.setHopperMotor(0.7);
        this.shootingStartTime = System.currentTimeMillis();
      }else if(this.ballQueued && this.elapsedShootTime >= this.shootingDelay){
        this.hopper.setBeltMotor(0);
        this.hopper.setHopperMotor(0);
        this.counter += 1;
        this.ballQueued = false;
      }
    }
    System.out.println(this.counter + " Count");
    this.elapsedQueTime = System.currentTimeMillis() - this.lastQue;
    this.elapsedTime = System.currentTimeMillis() - this.startTime;
    this.elapsedShootTime = System.currentTimeMillis() - this.shootingStartTime;
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
