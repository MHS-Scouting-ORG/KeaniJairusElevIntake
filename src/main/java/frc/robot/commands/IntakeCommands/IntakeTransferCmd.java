// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeTransferCmd extends Command {
  
  private IntakeSubsystem i_Subsystem;

  public IntakeTransferCmd(IntakeSubsystem iSubs) {
    i_Subsystem = iSubs;
    addRequirements(iSubs);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    i_Subsystem.setPos(0, 30, 10);
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return i_Subsystem.getEnc() <= 0;
  }
}
