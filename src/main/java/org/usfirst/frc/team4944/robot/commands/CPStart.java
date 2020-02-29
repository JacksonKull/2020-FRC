package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.OI;
import org.usfirst.frc.team4944.robot.subsystems.IntakeSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;


public class CPStart extends InstantCommand {
    
    IntakeSubsystem intake;
    double cpPower;
    OI oi;
    boolean m;

    public CPStart(double power, boolean mode) {
        super();
        this.intake = new IntakeSubsystem();
        this.oi = new OI();
        this.cpPower = power;
        requires(intake);
        requires(oi);
        this.m = mode;
      }

    @Override
    protected void initialize() {
      if(this.m == true){
        this.intake.setIntakeMotor(this.cpPower);
        System.out.println("Running CP Control 1");
      }else{
        System.out.println("Running CP Control 2");
      }
      
    }
}