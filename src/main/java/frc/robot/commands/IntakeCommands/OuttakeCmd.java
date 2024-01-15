package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeCmd extends Command {
  private IntakeSubsystem i_subsystem;

  public OuttakeCmd(IntakeSubsystem intakeSubs) {
    i_subsystem = intakeSubs;
    addRequirements(intakeSubs);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
    i_subsystem.outtake();
  }

  @Override
  public void end(boolean interrupted) {

  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
