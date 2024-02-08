// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class IntakeCmd extends Command {
  
  //private IntakeSubsystem i_subsystem;
  private UnderIntakeSubsystem u_subsystem;

  //Runs the elevator intake to "intake" notes
  
  public IntakeCmd(UnderIntakeSubsystem uSubs) {
    u_subsystem = uSubs;
    addRequirements(u_subsystem);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    u_subsystem.intake();
  }

  @Override
  public void end(boolean interrupted) {
    u_subsystem.stopIntake();
  }
    
  @Override
  public boolean isFinished() {
    return u_subsystem.getOpticalSensor();
  }
}
