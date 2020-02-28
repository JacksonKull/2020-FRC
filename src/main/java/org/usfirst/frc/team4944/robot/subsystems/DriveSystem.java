package org.usfirst.frc.team4944.robot.subsystems;

import org.usfirst.team4944.robot.PID.BasicPID;
import org.usfirst.team4944.robot.PID.DrivePID;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.SerialPort.Port;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	// MOTORS

	// Comp Motors
	// TalonFX leftMotor1;
	// TalonFX leftMotor2;
	// TalonFX rightMotor1;
	// TalonFX rightMotor2;

	// Practice Motors
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
	// Constants
<<<<<<< HEAD
	// final double maxPow = 1;
	final double maxPow = 0.25;
=======
	final double maxPow = 1;
	// final double maxPow = 0.25;
>>>>>>> 0d38c850bd260b6283c672aa8b031f7f4c827002

	public DriveSystem() {
		// MOTORS

		// Comp Boat
		// this.leftMotor1 = new TalonFX(3);
		// this.leftMotor1.setInverted(false);
		// this.leftMotor2 = new TalonFX(4);
		// this.leftMotor2.setInverted(false);
		// this.rightMotor1 = new TalonFX(1);
		// this.rightMotor1.setInverted(false);
		// this.rightMotor2 = new TalonFX(2);
		// this.rightMotor2.setInverted(false);

		// Practice Bot
		this.leftMotor1 = new TalonSRX(3);
		this.leftMotor1.setInverted(false);
		this.leftMotor2 = new TalonSRX(4);
		this.leftMotor2.setInverted(false);
		this.rightMotor1 = new TalonSRX(1);
		this.rightMotor1.setInverted(false);
		this.rightMotor2 = new TalonSRX(2);
		this.rightMotor2.setInverted(false);

		// ENCODERS
		this.leftMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		this.rightMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
		// GYRO
		// this.gyro = new AHRS(Port.kMXP);
		// ANGLE PID
		this.anglePID = new BasicPID(1 / 500, 1 / 500, 1 / 500);
	}

	public void setLeftPower(double power) {
		this.leftMotor1.set(ControlMode.PercentOutput, power);
		this.leftMotor2.set(ControlMode.PercentOutput, power);
	}

	public void setRightPower(double power) {
		rightMotor1.set(ControlMode.PercentOutput, power);
		rightMotor2.set(ControlMode.PercentOutput, power);
	}

	public void setPower(double lPower, double rPower) {
		setLeftPower(lPower * this.maxPow);
		setRightPower(rPower * this.maxPow);
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

	public void stop() {
		setPower(0, 0);
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub

	}

}