package org.usfirst.frc.team4944.robot.commands;

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


	public DriveStraight(double driveGoal, double headingGoal, DrivePID leftPID, DrivePID rightPID, BasicPID anglePID) {
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
		leftPID.setSetPoint(leftInit + driveGoal);
		rightPID.setSetPoint(rightInit + driveGoal);
		anglePID.setSetPoint(angleInit + headingGoal);
	}

	public void execute() {
		// POWERS
		double leftPower = leftPID.getPower(driveSystem.getLeftEncoder());
		double rightPower = rightPID.getPower(driveSystem.getRightEncoder());
		double anglePower = anglePID.getPower(driveSystem.getAngle());
		// SETTING DRIVE TRAIN POWERS
		driveSystem.setPower(leftPower + anglePower, rightPower - anglePower);
	}

	public boolean isFinished() {
		if (this.driveSystem.getDoneDriveing()) {
			System.out.println("Exited");
			driveSystem.setLeftPower(0);
			driveSystem.setRightPower(0);
			return true;
		}else{
			return false;	
		}
	}
}
