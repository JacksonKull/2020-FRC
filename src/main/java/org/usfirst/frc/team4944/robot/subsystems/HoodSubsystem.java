
package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HoodSubsystem extends Subsystem {

  // Motors
  TalonSRX hoodMotor;
  // PID
  BasicPID hoodPID;
  // PID Values
  final double hood_p = 0.05;
  final double hood_i = 0;
  final double hood_d = 0;
  // Constants
  final int hoodOffset = 100;
  final int minHoodEncoder = 0;
  final int neutralHoodEncoder = 2900;
  final int maxHoodEncoder = 4880;
  final double minHoodAngle = 0;
  final double maxHoodAngle = 45;
  final double maxHoodPow = 0.10;

  public HoodSubsystem() {
    // Motors
    this.hoodMotor = new TalonSRX(2);
    // Encoder
    this.hoodMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    this.hoodMotor.setSelectedSensorPosition(0);
    // PID
    this.hoodPID = new BasicPID(this.hood_p, this.hood_i, this.hood_d);

  }

  public void setHoodMotorPower(double power) {
    this.hoodMotor.set(ControlMode.PercentOutput, power * this.maxHoodPow);
  }

  public double getHoodMotorPower() {
    return this.hoodMotor.getMotorOutputPercent();
  }

  private void setHoodSetPoint(int encoderSetPoint) {
    if (encoderSetPoint < minHoodEncoder) {
      this.hoodPID.setSetPoint(minHoodEncoder);
    } else if (encoderSetPoint > maxHoodEncoder) {
      this.hoodPID.setSetPoint(maxHoodEncoder);
    } else {
      this.hoodPID.setSetPoint(encoderSetPoint);
    }
  }

  public double getHoodSetPoint() {
    return this.hoodPID.getSetPoint();
  }

  public void driveHoodPID() {
    if (Math.abs(Math.abs(this.getHoodSetPoint()) - Math.abs(this.getHoodEncoderValue())) > 0.5) {
      double power = this.hoodPID.getPower(this.getHoodEncoderValue());
      this.setHoodMotorPower((-power) * maxHoodPow);
    } else {
      System.out.println("Hood Within Range");
      this.setHoodMotorPower(0);
    }
  }

  public int getHoodEncoderValue() {
    return this.hoodMotor.getSelectedSensorPosition();
  }

  private int convertHoodAngleToEncoder(double desiredAngle) {
    double ticksPerDegree = (maxHoodEncoder - minHoodEncoder) / (maxHoodAngle - minHoodAngle);
    return (int) (desiredAngle * ticksPerDegree);
  }

  public void setHoodAngle(double desiredAngle) {
    int encoderGoal = neutralHoodEncoder + this.convertHoodAngleToEncoder(desiredAngle);
    this.setHoodSetPoint(encoderGoal);
  }

  @Override
  public void initDefaultCommand() {
  }
}
