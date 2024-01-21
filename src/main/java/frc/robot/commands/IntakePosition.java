package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePosition extends Command {
  public IntakeSubsystem i_Subsystem;

  public IntakePosition(IntakeSubsystem iSubs) {
    i_Subsystem = iSubs;
    addRequirements(iSubs);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    i_Subsystem.setPos(0, 30, 10);
  }
  
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return i_Subsystem.getEnc() >= 0;
  }
}
