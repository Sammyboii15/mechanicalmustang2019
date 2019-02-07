/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Counter;
import frc.robot.RobotMap;
import frc.robot.Robot;

public class deployBoom extends Command {

  Counter boomCounter = null;
  boolean isCountReached = false;

  public deployBoom() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.m_boomMotor);
    boomCounter = new Counter();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    boomCounter.setUpSource(RobotMap.BOOM_ARM_DIO);
    boomCounter.setUpDownCounterMode();
    boomCounter.reset();
    isCountReached = false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_boomMotor.dropBoom();
    if (boomCounter.get() >= RobotMap.BOOM_ARM_DEPLOY_COUNT) {
      Robot.m_boomMotor.stopBoomMotor();
      Robot.currentBoomPosition = 0;
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isCountReached;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.m_boomMotor.stopBoomMotor();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}