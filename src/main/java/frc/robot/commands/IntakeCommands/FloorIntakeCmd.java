package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class FloorIntakeCmd extends Command {
  
  private IntakeSubsystem i_subsystem;

  public FloorIntakeCmd(IntakeSubsystem iSubs) {
    i_subsystem = iSubs;
    addRequirements(iSubs);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    i_subsystem.setPos(0, 30, 10);
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return i_subsystem.getEnc() <= 0;
  }
}
