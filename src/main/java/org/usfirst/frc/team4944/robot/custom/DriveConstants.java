/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.custom;

import edu.wpi.first.wpilibj.kinematics.DifferentialDriveKinematics;

public final class DriveConstants {

    public static final double ksVolts = 0.24;
    public static final double kvVoltSecondsPerMeter = 0.217;
    public static final double kaVoltSecondsSquaredPerMeter = 0.0397;

    public static final double kPDriveVel = 1.87;
    
    public static final double wheelDiameter = 0.1524;
    public static final double kEncoderDistancePerPulse = (wheelDiameter*Math.PI)/2048;

    
    public static final double kTrackwidthMeters = 0.5969;
    public static final DifferentialDriveKinematics kDriveKinematics =
        new DifferentialDriveKinematics(kTrackwidthMeters);

    public static final double kMaxSpeedMetersPerSecond = 3.6576;
    public static final double kMaxAccelerationMetersPerSecondSquared = 1.46304;

    public static final double kRamseteB = 2;
    public static final double kRamseteZeta = 0.7;
}