package org.usfirst.frc.team4944.robot.subsystems;

import org.usfirst.frc.team4944.robot.custom.Motor;
import org.usfirst.frc.team4944.robot.custom.MotorType;
import org.usfirst.team4944.robot.PID.BasicPID;
import org.usfirst.team4944.robot.PID.DrivePID;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class DriveSystem {

	// CHASSIS SIDES
	ChassisSide leftSide;
	ChassisSide rightSide;
	// MOTORS
	Motor leftMotor1;
	Motor rightMotor1;
	// ENCODERS
	Encoder leftEncoder;
	Encoder rightEncoder;
	// GYRO
	AHRS gyro;
	// DRIVE PIDS
	DrivePID leftPID;
	DrivePID rightPID;
	// GYRO PID
	BasicPID anglePID;
	
	public DriveSystem() {
		// MOTORS
		leftMotor1 = new Motor(MotorType.Talon, 0);
		rightMotor1 = new Motor(MotorType.Talon, 0);
		// ENCODERS
		leftEncoder = new Encoder(0, 0);
		rightEncoder = new Encoder(0, 0);
		// GYRO
		gyro = new AHRS(Port.kUSB1);
		// CHASSIS SIDE
		leftSide = new ChassisSide(leftEncoder);
		leftSide.addMotor(leftMotor1);
		rightSide = new ChassisSide(rightEncoder);
		rightSide.addMotor(rightMotor1);
		// DRIVE PIDS
		leftPID = new DrivePID(1/500, 1/500, 1/500, -50, 50);
		rightPID = new DrivePID(1/500, 1/500, 1/500, -50, 50);
		// ANGLE PID
		anglePID = new BasicPID(1/500, 1/500, 1/500);
	
	}
	
	public void setLeftPower(double power) {
		leftSide.setPower(power);
	}
	
	public void setRightPower(double power) {
		rightSide.setPower(power);
	}
	
	public void setPower(double lPower, double rPower) {
		setLeftPower(lPower);
		setRightPower(rPower);
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public int getLeftEncoder() {
		return leftEncoder.get();
	}
	
	public int getRightEncoder() {
		return rightEncoder.get();
	}
	
	public double getLeftSpeed() {
		return 0;
	}
	
	public double getRightSpeed() {
		return 0;
	}
	
}
