package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.UnderIntakeSubsystem;
public class DeliverCmd extends Command {

  //IntakeSubsystem intakeSubs;
  UnderIntakeSubsystem intakeSubs;

  public DeliverCmd(UnderIntakeSubsystem newIntakeSubs) {
    intakeSubs = newIntakeSubs;
    addRequirements(intakeSubs);
  }

  @Override
  public void initialize() {}

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
    return false;
  }
}
