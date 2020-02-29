package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.OI;
import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ArmsUp extends InstantCommand {

    ArmSubsystem arms;
    double armSpeed;
    OI oi;

    public ArmsUp(double armSpeed) {
        super();
        this.arms = new ArmSubsystem();
        requires(arms);
        this.oi = new OI();
        this.armSpeed = armSpeed;
    }

    @Override
    protected void initialize() {
        if (this.oi.getControlMode() == true) {
            this.arms.disableBreak();
            this.arms.setLeftArmMotor(this.armSpeed);
            this.arms.setRightArmMotor(this.armSpeed);
            } else {

        }

        
    }
}