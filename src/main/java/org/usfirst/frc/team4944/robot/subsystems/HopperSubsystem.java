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
public class HopperSubsystem extends Subsystem{
    //Motors
    TalonSRX hopperMotor;
    TalonSRX feederMotor;

    public HopperSubsystem(){
        this.hopperMotor = new TalonSRX(14);
        this.feederMotor = new TalonSRX(8);
    }


    public void setFeedMotor(double power){
        this.feederMotor.set(ControlMode.PercentOutput, power);
    }

    public void setHopperMotor(double power){
        this.hopperMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

}
