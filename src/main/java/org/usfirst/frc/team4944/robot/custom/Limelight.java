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
    final double feetOffGroundLimelight = 1.875;
    final double feetOffGroundTarget = 8.71;
    final double limelightAngle = 12;
    final String tableName = "limelight";

    public Limelight(){
            this.table = NetworkTableInstance.getDefault().getTable(this.tableName);
    }

    public boolean getLimeLightConnected(){
        if(!(table == null)){
            return false;
        }else{
            return true;
        }
    }

    public void setNetworkTable(){
        try{
            this.table = NetworkTableInstance.getDefault().getTable(this.tableName);
        }finally{}
    }
        

    public double getDistInFeet(){
        return (feetOffGroundTarget-feetOffGroundLimelight)/(Math.tan(Math.toRadians(limelightAngle) + Math.toRadians(this.getYOffset())));
    }

    public boolean getTargetVisible(){
        if(NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getValue().getDouble() == 1){
            return true;
        }else{
            return false;
        }
    }

    public double getYOffset(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getValue().getDouble();
    }

    public double getXOffset(){
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getValue().getDouble();
    }

    public double getHeighOffGroudLM(){
        return this.feetOffGroundLimelight;
    }

}
