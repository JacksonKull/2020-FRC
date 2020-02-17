
package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import org.usfirst.frc.team4944.robot.custom.Limelight;
import org.usfirst.team4944.robot.PID.BasicPID;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class HoodSubsystem extends Subsystem {

  // Motors
  TalonSRX hoodMotor;
  // Limelight
  Limelight lm;
  // PID
  BasicPID hoodPID;
  // PID Values
  final double hood_p = 0.0007;
  final double hood_i = 0;
  final double hood_d = 0;
  // Constants
  final int hoodOffset = 100;
  final int minHoodEncoder = 0;
  final int maxHoodEncoder = 4880;
  final double minHoodAngle = 15;
  final double maxHoodAngle = 165;
  final double maxHoodPow = 0.3;
  // Double
  double vx, vy, lmAngle;

  public HoodSubsystem() {
    // Motors
    this.hoodMotor = new TalonSRX(2);
    // Encoder
    this.hoodMotor.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Absolute);
    this.hoodMotor.setSelectedSensorPosition(0);
    // Limelight
    this.lm = new Limelight();
    // PID
    this.hoodPID = new BasicPID(this.hood_p, this.hood_i, this.hood_d);
  }

  public void setHoodMotorPower(double power) {
    this.hoodMotor.set(ControlMode.PercentOutput, Math.min(Math.max(power, -this.maxHoodPow), this.maxHoodPow));
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
    System.out.println(Math.abs(Math.abs(this.getHoodSetPoint())) - Math.abs(this.getHoodEncoderValue()) + " Error");
    if (Math.abs(Math.abs(this.getHoodSetPoint()) - Math.abs(this.getHoodEncoderValue())) > 0.5) {
      //double power = this.hoodPID.getPower(this.getHoodEncoderValue());
      double power = this.hood_p*(Math.abs(Math.abs(this.getHoodSetPoint())) - Math.abs(this.getHoodEncoderValue()));
      System.out.println(power + " Power");
      this.setHoodMotorPower(power);
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
    int encoderGoal = (this.convertHoodAngleToEncoder(desiredAngle));
    this.setHoodSetPoint(encoderGoal);
  }

  public double getVx() {
    this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
    return this.vx;
  }

  public double getVy() {
    this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
    return this.vy;
  }

  public double getRequiredAngle() {
    this.lmAngle = Math.atan((this.vy) / (this.vx));
    return Math.toDegrees(this.lmAngle);
  }

  public void updateValues() {
    this.vx = 32 * ((this.lm.getDistInFeet() + 2.5) / (this.vy));
    this.vy = 8 * (Math.sqrt(8.2 - this.lm.getHeighOffGroudLM()));
    this.lmAngle = Math.toDegrees(Math.atan((this.vx) / (this.vy)));
  }

  public void setAngleByLM() {
    this.setHoodAngle(this.getRequiredAngle());
  }

  @Override
  public void initDefaultCommand() {
  }
}
