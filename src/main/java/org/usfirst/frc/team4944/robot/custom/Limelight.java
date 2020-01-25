/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

/**
 * Add your docs here.
 */
public class Limelight {
    // Network Table
    NetworkTable table;
    // Constants
    final double feetOffGroundLimelight = 2.083;
    final double feetOffGroundTarget = 8.71;
    final double limelightAngle = 45;

    public Limelight(){
        this.table = NetworkTableInstance.getDefault().getTable("limelight");
    
    }

    public double getDistInFeet(){
        return (feetOffGroundTarget-feetOffGroundLimelight)/(Math.tan(Math.toRadians(limelightAngle) + Math.toRadians(this.getYOffset())));
    }

    public boolean getTargetVisible(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getValue().getBoolean();
    }

    public double getYOffset(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getValue().getDouble();
    }

    public double getXOffset(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getValue().getDouble();
    }

}
