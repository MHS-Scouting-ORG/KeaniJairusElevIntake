package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.IntakeConstants;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class IntakeCmd extends Command {

  private UnderIntakeSubsystem iSubs;

  public IntakeCmd(UnderIntakeSubsystem newISubs) {
    iSubs = newISubs;
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    iSubs.intake(IntakeConstants.INTAKE_MAXSPEED);
  }

  @Override
  public void end(boolean interrupted) {
    iSubs.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return iSubs.getOpticalSensor();
  }
}
