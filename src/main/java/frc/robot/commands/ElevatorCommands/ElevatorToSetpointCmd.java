// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ElevatorToSetpointCmd extends Command {
  
  private ElevatorSubsystem elevSub;
  private double setpoint;

  public ElevatorToSetpointCmd(ElevatorSubsystem newElevSub, double setpoint) {
    elevSub = newElevSub;
    this.setpoint = setpoint;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.setSetpoint(setpoint);
  }

  @Override
  public void end(boolean interrupted) {
    elevSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    return elevSub.isAtSetpoint() || elevSub.getTopLimitSwitch() || elevSub.getBottomLimitSwitch();
  }
}
