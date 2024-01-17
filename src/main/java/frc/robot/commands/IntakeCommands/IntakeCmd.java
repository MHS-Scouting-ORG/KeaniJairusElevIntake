// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCmd extends Command {
  
  private IntakeSubsystem i_subsystem;

  //Runs the elevator intake to "intake" notes
  
  public IntakeCmd(IntakeSubsystem intakeSubs) {
    i_subsystem = intakeSubs;
    addRequirements(intakeSubs);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    i_subsystem.intake();
  }

  @Override
  public void end(boolean interrupted) {
    i_subsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
