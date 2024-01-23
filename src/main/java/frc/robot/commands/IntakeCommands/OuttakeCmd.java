package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class OuttakeCmd extends Command {

  private IntakeSubsystem i_subsystem;

  //Runs the elevator intake to "outtake" notes
  
  public OuttakeCmd(IntakeSubsystem iSubs) {
    i_subsystem = iSubs;
    addRequirements(iSubs);
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
    i_subsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
