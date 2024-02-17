// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToSource extends Command {

  public ElevatorSubsystem elevSub;

  //Runs the elevator to the position where we intake from the source through the shooter

  public ElevatorToSource(ElevatorSubsystem newElevSub) {
    elevSub = newElevSub;
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    //Default Position?? || ??
    elevSub.toSetpoint(0);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    //Or elevSub.getBottomLimitSwitch if its the default position
    return elevSub.isAtSetpoint() || elevSub.getTopLimitSwitch();
  }
}
