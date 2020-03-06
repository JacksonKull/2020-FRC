package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import org.usfirst.frc.team4944.robot.subsystems.HoodSubsystem;
import org.usfirst.frc.team4944.robot.subsystems.TurretSubsystem;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Command;

public class VisionAlign extends Command {
	
	//PID
	BasicPID visionPID;
	//SUBSYSTEM
	TurretSubsystem turret;
	HoodSubsystem hood;
	//GOALS
	double hoodGoal;

	public VisionAlign() {
		//SUBSYSTEMS
		this.turret = new TurretSubsystem();
		this.hood = new HoodSubsystem();
	}

	@Override
	protected void initialize() {
		this.hood.updateValues();
		this.hood.setAngleByLM();
		this.hood.driveHoodPID();
		this.turret.followLimelightNoEncoder();
	}

	@Override
	protected void execute() {
		this.hood.updateValues();
		this.hood.setAngleByLM();
		this.hood.driveHoodPID();
		this.turret.followLimelightNoEncoder();
	}

	@Override
	protected void end() {
		this.turret.stop();
		this.hood.stop();
	}

	@Override
	protected void interrupted() {
		this.hood.stop();
		this.turret.stop();
	}

	@Override
	protected boolean isFinished() {
		if(this.hood.getWithinRange() && this.turret.getWithinRange()){	
			if(this.hood.getHoodDoneMoving() && this.turret.getTuretDoneMoving()) {
				System.out.println("Done! With Limelight");
				return true;
			}else {
				System.out.println("Not Done");
				return false;
			}
		}else{
			System.out.println("Not Done");
			return false;
		}
	}
}
