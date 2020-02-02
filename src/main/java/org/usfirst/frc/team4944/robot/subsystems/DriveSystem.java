package org.usfirst.frc.team4944.robot.subsystems;

import org.usfirst.team4944.robot.PID.BasicPID;
import org.usfirst.team4944.robot.PID.DrivePID;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SerialPort.Port;

public class DriveSystem {
	//Cus your a loser
	// MOTORS
	TalonSRX leftMotor1;
	TalonSRX leftMotor2;
	TalonSRX rightMotor1;
	TalonSRX rightMotor2;
	// GYRO
	AHRS gyro;
	// DRIVE PIDS
	DrivePID leftPID;
	DrivePID rightPID;
	// GYRO PID
	BasicPID anglePID;
	
	public DriveSystem() {
		// MOTORS
		leftMotor1 = new TalonSRX(0);
		leftMotor2 = new TalonSRX(1);
		rightMotor1 = new TalonSRX(2);
		rightMotor2 = new TalonSRX(3);
		// ENCODERS
		leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// GYRO
		gyro = new AHRS(Port.kUSB1);
		// ANGLE PID
		anglePID = new BasicPID(1/500, 1/500, 1/500);
	}
	
	public void setLeftPower(double power) {
		leftMotor1.set(ControlMode.PercentOutput, power);
		leftMotor2.set(ControlMode.PercentOutput, power);
	}
	
	public void setRightPower(double power) {
		rightMotor1.set(ControlMode.PercentOutput, power);
		rightMotor2.set(ControlMode.PercentOutput, power);
	}
	
	public void setPower(double lPower, double rPower) {
		setLeftPower(lPower);
		setRightPower(rPower);
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	public int getLeftEncoder() {
		return leftMotor1.getSelectedSensorPosition();
	}
	
	public int getRightEncoder() {
		return rightMotor1.getSelectedSensorPosition();
	}
	
	public double getLeftSpeed() {
		return 0;
	}
	
	public double getRightSpeed() {
		return 0;
	}
	
}
