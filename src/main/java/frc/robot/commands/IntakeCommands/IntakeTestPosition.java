package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeTestPosition extends Command {
  private IntakeSubsystem i_Subsystem;

  public IntakeTestPosition(IntakeSubsystem iSubs) {
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
