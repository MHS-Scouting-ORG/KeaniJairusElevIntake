package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class DeliverCmd extends Command {

  UnderIntakeSubsystem intakeSubs;

  // Runs the under the bumper intake to transfer the notes to the indexer

  public DeliverCmd(UnderIntakeSubsystem newIntakeSubs) {
    intakeSubs = newIntakeSubs;
    addRequirements(intakeSubs);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    intakeSubs.intake();
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubs.stopIntake();
  }

  @Override
  public boolean isFinished() {
    // FIXME Change end cond. to use indexer optical sensor
    return !intakeSubs.getOpticalSensor();
  }
}
