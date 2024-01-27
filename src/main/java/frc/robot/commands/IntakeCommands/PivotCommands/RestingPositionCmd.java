package frc.robot.commands.IntakeCommands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class RestingPositionCmd extends Command {

  private IntakeSubsystem intakeSubs;

  public RestingPositionCmd(IntakeSubsystem newIntakeSubs) {
    intakeSubs = newIntakeSubs;

    addRequirements(intakeSubs);
 }
    
  @Override
  public void initialize() {}

  @Override
  public void execute() {}

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return intakeSubs.getRestingLS() || intakeSubs.getEnc() <= 0;
  }
}
