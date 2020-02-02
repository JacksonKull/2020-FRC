package org.usfirst.team4944.robot.PID;

import org.usfirst.frc.team4944.robot.subsystems.ChassisSide;

import com.kauailabs.navx.frc.AHRS;
public class ChassisPID {
	
	AHRS gyro;
	ChassisSide leftSide;
	ChassisSide rightSide;
	DrivePID leftPID;
	DrivePID rightPID;
	BasicPID anglePID;
	double setPoint;
	double headingPoint;
	
	public ChassisPID( ChassisSide leftSide, ChassisSide rightSide, AHRS gyro, DrivePID leftPID, DrivePID rightPID, BasicPID anglePID){
		this.leftSide = leftSide;
		this.rightSide = rightSide;
		this.leftPID = leftPID;
		this.rightPID = rightPID;
		this.anglePID = anglePID;
		this.gyro = gyro;
		this.setPoint = 0;
		this.headingPoint = 0;
	}

	public void setSetPoint(double point) {
		this.setPoint = point;
		leftPID.setSetPoint(point);
		rightPID.setSetPoint(point);
	}
	
	public double getSetPoint() {
		return setPoint;
	}
	
	public void setHeadingPoint(double heading) {
		this.headingPoint = heading;
		anglePID.setSetPoint(heading);
	}
	
	public double getHeadingPoint() {
		return headingPoint;
	}
	
	public double getCurrentHeading() {
		return gyro.getAngle();
	}
	
	public void setPower() {
		leftSide.setPower(leftPID.getPower(leftSide.getEncoder()) + anglePID.getPower(getCurrentHeading()));
		rightSide.setPower(rightPID.getPower(leftSide.getEncoder()) - anglePID.getPower(getCurrentHeading()));
	}
	
	public boolean headingOnTarget() {
		if(Math.abs(getCurrentHeading()) <= getHeadingPoint() + 2.0) {
			return true;
		}
		return false;
	}
	
	public boolean atSetPoint() {
		if((leftPID.getError() <= 15) && (rightPID.getError() <= 15)) {
			return true;
		}
		return false;
	}
	
	public boolean stopped() {
		if(((leftPID.getError() - leftPID.getPrevError()) <= 15) && (rightPID.getError() - rightPID.getPrevError() <= 15)) {
			return true;
		}
		return false;
	}
	public boolean isFinished() {
		if(stopped() && atSetPoint() && headingOnTarget()) {
			return true;
		}
		return false;
	}
	
}
