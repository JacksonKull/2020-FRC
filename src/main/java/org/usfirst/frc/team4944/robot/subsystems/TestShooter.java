package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import org.usfirst.frc.team4944.robot.custom.Constants;

import edu.wpi.first.wpilibj.command.Subsystem;

public class TestShooter extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  TalonFX shooterMotor1;
  TalonFX shooterMotor2;

  public TestShooter() {

    this.shooterMotor1 = new TalonFX(17);
    this.shooterMotor1.setInverted(true);
    this.shooterMotor2 = new TalonFX(12);

    shooterMotor1.configFactoryDefault();
    shooterMotor2.configFactoryDefault();

    /* Config sensor used for Primary PID [Velocity] */
    shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);
    shooterMotor2.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.kPIDLoopIdx,
        Constants.kTimeoutMs);
    /**
     * Phase sensor accordingly. Positive Sensor Reading should match Green
     * (blinking) Leds on Talon
     */
    shooterMotor1.setSensorPhase(true);
    shooterMotor2.setSensorPhase(true);
    /* Config the peak and nominal outputs */
    shooterMotor1.configNominalOutputForward(0, Constants.kTimeoutMs);
    shooterMotor1.configNominalOutputReverse(0, Constants.kTimeoutMs);
    shooterMotor1.configPeakOutputForward(1, Constants.kTimeoutMs);
    shooterMotor1.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    shooterMotor2.configNominalOutputForward(0, Constants.kTimeoutMs);
    shooterMotor2.configNominalOutputReverse(0, Constants.kTimeoutMs);
    shooterMotor2.configPeakOutputForward(1, Constants.kTimeoutMs);
    shooterMotor2.configPeakOutputReverse(-1, Constants.kTimeoutMs);

    /* Config the Velocity closed loop gains in slot0 */
    shooterMotor1.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
    shooterMotor1.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
    shooterMotor1.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
    shooterMotor1.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

    shooterMotor2.config_kF(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kF, Constants.kTimeoutMs);
    shooterMotor2.config_kP(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kP, Constants.kTimeoutMs);
    shooterMotor2.config_kI(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kI, Constants.kTimeoutMs);
    shooterMotor2.config_kD(Constants.kPIDLoopIdx, Constants.kGains_Velocit.kD, Constants.kTimeoutMs);

  }

  public void setShooterVelocity(double velocity) {
    shooterMotor1.set(ControlMode.Velocity, velocity);
    shooterMotor2.set(ControlMode.Velocity, velocity);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}