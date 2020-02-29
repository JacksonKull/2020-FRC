/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4944.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;
import org.usfirst.frc.team4944.robot.OI;

public class ControllerMode extends InstantCommand {
  public static final boolean Mode = false;
  /**
   * Creates a new ControllerMode.
   */
  OI oi;

  public ControllerMode() {
    oi = new OI();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    if(this.oi.getControlMode() == true){
      this.oi.setControlMode(false);

    }else{
      this.oi.setControlMode(true);
    }
  }
}
