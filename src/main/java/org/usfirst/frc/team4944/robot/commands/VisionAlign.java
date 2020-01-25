package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.subsystems.DriveSystem;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Command;

public class VisionAlign extends Command {
	//GYRO
	
	//SERIAL INPUT
	Port kUSB;
	SerialPort serialInput;
	byte targetVal;
	byte currentVal;
	//PID
	BasicPID visionPID;
	//SUBSYSTEM
	DriveSystem driveSystem;
	
	public VisionAlign() {
		serialInput = new SerialPort(0, kUSB);
		targetVal = 0;
		visionPID = new BasicPID(1/500, 1/500, 1/500);
		driveSystem = new DriveSystem();
	}

	@Override
	protected void initialize() {
		
	}

	@Override
	protected void execute() {
	
		currentVal = 10;
		
		if(currentVal > targetVal) {
			driveSystem.setPower(visionPID.getPower(currentVal), -visionPID.getPower(currentVal));
		}else if(currentVal < targetVal) {
			driveSystem.setPower(-visionPID.getPower(currentVal), visionPID.getPower(currentVal));
		}else {
			driveSystem.setPower(0.0, 0.0);
		}
	}

	@Override
	protected void end() {
	}

	@Override
	protected void interrupted() {
	}

	@Override
	protected boolean isFinished() {
		if((currentVal > targetVal - 10) && (currentVal < targetVal + 10)) {
			return true;
		}else {
			return false;
		}
	}
}
