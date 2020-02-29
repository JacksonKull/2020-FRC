package org.usfirst.frc.team4944.robot.commands;

import org.usfirst.frc.team4944.robot.OI;
import org.usfirst.frc.team4944.robot.subsystems.ArmSubsystem;

import edu.wpi.first.wpilibj.command.InstantCommand;

public class ArmsDown extends InstantCommand {

    ArmSubsystem arms;
    double armSpeed;
    OI oi;

    public ArmsDown(double armSpeed) {
        super();
        this.oi = new OI();
        requires(oi);
        this.arms = new ArmSubsystem();
        requires(arms);
        this.armSpeed = armSpeed;
    }

    @Override
    protected void initialize() {
        if (this.oi.getControlMode() == true) {
            this.arms.disableBreak();
            this.arms.setLeftArmMotor(-this.armSpeed);
            this.arms.setRightArmMotor(-this.armSpeed);
        } else {

        }
    }
}