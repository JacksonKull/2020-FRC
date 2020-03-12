package org.usfirst.frc.team4944.robot.commands;

import javax.swing.JList.DropLocation;

import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import org.usfirst.team4944.robot.PID.BasicPID;
import org.usfirst.team4944.robot.PID.DrivePID;

import edu.wpi.first.wpilibj.command.Command;

public class DriveStraight extends Command {
	// SUBSYSTEMS
	DriveSystem driveSystem;
	
	// VARIABLES
	double driveGoal, headingGoal, leftInit, rightInit, angleInit;
	
	// DRIVE PIDS
	DrivePID leftPID;
	DrivePID rightPID;
	
	// BASIC PIDS
	BasicPID anglePID;

	// CONSTANTS
	final double driveThreshold = 100;


	public DriveStraight(double driveGoal, double headingGoal, DrivePID leftPID, DrivePID rightPID, BasicPID anglePID) {
		this.driveSystem = new DriveSystem();
		requires(driveSystem);
		this.driveGoal = driveGoal;
		this.headingGoal = headingGoal;
		this.leftPID = leftPID;
		this.rightPID = rightPID;
		this.anglePID = anglePID;
		this.leftInit = driveSystem.getLeftEncoder();
		this.rightInit = driveSystem.getRightEncoder();
		this.angleInit = driveSystem.getAngle();
		init();
	}

	public void init() {
		System.out.println("Init");
		leftPID.setSetPoint(leftInit + driveGoal);
		rightPID.setSetPoint(rightInit + driveGoal);
		anglePID.setSetPoint(angleInit + headingGoal);
	}

	public void execute() {
		// POWERS
		double leftPower = leftPID.getPower(driveSystem.getLeftEncoder());
		double rightPower = -rightPID.getPower(driveSystem.getRightEncoder());
		double anglePower = anglePID.getPower(driveSystem.getAngle());
		// SETTING DRIVE TRAIN POWERS
		driveSystem.setPower(leftPower + anglePower, rightPower - anglePower);
		System.out.println("Left Power: " + leftPower + " Right Power: " + rightPower + " Angle Power: " + anglePower);
		System.out.println("Left Error: " + this.leftPID.getError() + " Right Error: " + this.rightPID.getError() + " Angle Error: " + this.anglePID.getError());
	}

	public boolean isFinished() {
		if (Math.abs(this.leftPID.getError()) <= this.driveThreshold && Math.abs(this.rightPID.getError()) <= this.driveThreshold) {
			if(this.driveSystem.getDoneDriveing()){
				System.out.println("Exited");
				driveSystem.setLeftPower(0);
				driveSystem.setRightPower(0);
				return true;
			}else{
				return false;
			}
		}else{
			return false;	
		}
	}
}
