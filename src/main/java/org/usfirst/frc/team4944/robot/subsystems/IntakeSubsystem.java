/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class IntakeSubsystem extends Subsystem {
    // Motors
    TalonSRX intakeMotor;

    public IntakeSubsystem() {
        this.intakeMotor = new TalonSRX(15);
        this.intakeMotor.setInverted(true);
    }

    public void setIntakeMotor(double power) {
        this.intakeMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub
    }
}
