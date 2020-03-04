package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.SensorInitializationStrategy;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

public class ShooterSubsystem extends Subsystem {

	// Motors
	TalonFX shooterMotor1, shooterMotor2;
	// Limelight
	Limelight lm;
	// Constants
	final int ticksPerRevolution = 4096;
	//final double p = 0.00085;
	final double p = 0.001;
	final double i = 0.001;
	final double d = 0;
	final double shooterHeight = 1;
	final double limelightAngle = 10;
	// PID
	BasicPID shooterPID;
	double vx, vy, lmSpeed;

	// motor = new CANTalon(address);
	// motor.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
	// motor.changeControlMode(TalonControlMode.Speed);
	// motor.setPID(p, i, d, f, 0, ramp, 0);
	public ShooterSubsystem() {
		// Motors
		this.shooterMotor1 = new TalonFX(10);
		this.shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
		this.shooterMotor1.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
		this.shooterMotor1.setInverted(true);

		this.shooterMotor2 = new TalonFX(8);
		this.shooterMotor2.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
		this.shooterMotor2.configIntegratedSensorInitializationStrategy(SensorInitializationStrategy.BootToZero);
		// Limelight
		this.lm = new Limelight();
		// PID
		this.shooterPID = new BasicPID(this.p, this.i, this.d);
	}

	public void setShooterPower(double rpm) {
		this.shooterPID.setSetPoint(rpm);
		double errorRPM = rpm - this.getRPM();
		if (errorRPM < 0) {
			double power = 0;
			this.shooterMotor1.set(ControlMode.PercentOutput, power);
			this.shooterMotor2.set(ControlMode.PercentOutput, power);
			System.out.println(power);
		} else {
			double power = this.shooterPID.getPower(this.getRPM());
			// double power = errorRPM * this.p;
			this.shooterMotor1.set(ControlMode.PercentOutput, power);
			this.shooterMotor2.set(ControlMode.PercentOutput, power);
			System.out.println(power);
		}

		// double power = rpm/(600/2048);
		// double velocity = (rpm/0.292968);
		// System.out.println(velocity + " Velocity");
		// this.shooterMotor1.set(TalonFXControlMode.Velocity, velocity);
		// this.shooterMotor2.set(TalonFXControlMode.Velocity, velocity);

	}

	public void setManualShooterPower(double power) {
		this.shooterMotor1.set(ControlMode.PercentOutput, power);
		this.shooterMotor2.set(ControlMode.PercentOutput, power);
	}


	public double getRPM() {
		double motorTPMS = this.shooterMotor1.getSelectedSensorVelocity(0);
		return this.shooterMotor1.getSelectedSensorVelocity(0) * (0.292968);
	}

	public double getVx() {
		this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
		return this.vx;
	}

	public double getVy() {
		this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
		return this.vy;
	}

	public double getRequiredVelocity() {
		this.lmSpeed = Math.sqrt((this.vy * this.vy) + (this.vx * this.vx));
		return this.lmSpeed + 8;
	}

	public void setSpeedBasedOffLM() {
		this.setManualShooterPower(this.convertFPStoPercentOutput(this.lmSpeed));
	}

	public void updateValues() {
		this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
		this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
	}

	public double convertFPStoPercentOutput(double FPS) {
		return ((FPS - 15.19) / 12.97);
	}

	public void initDefaultCommand() {
	}
}