package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class HopperSubsystem extends Subsystem {
    // Motors
    TalonSRX hopperMotor;
    TalonSRX feederMotor;

    public HopperSubsystem() {
        this.hopperMotor = new TalonSRX(13);
        this.hopperMotor.setInverted(true);
        this.feederMotor = new TalonSRX(14);
        // this.feederMotor.setInverted(true);
    }

    public void setFeedMotor(double power) {
        this.feederMotor.set(ControlMode.PercentOutput, power);
    }

    public void setHopperMotor(double power) {
        this.hopperMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
