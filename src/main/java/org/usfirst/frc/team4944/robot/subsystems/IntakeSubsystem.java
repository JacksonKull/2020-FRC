package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

public class IntakeSubsystem extends Subsystem {
    // Motors
    TalonSRX intakeMotor;

    public IntakeSubsystem() {
        this.intakeMotor = new TalonSRX(15);
        this.intakeMotor.setInverted(false);
    }

    public void setIntakeMotor(double power) {
        this.intakeMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected void initDefaultCommand() {
    }
}
