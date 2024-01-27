package frc.robot.commands.IntakeCommands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class TransferPositionCmd extends Command {

  private IntakeSubsystem intakeSubs;

  public TransferPositionCmd(IntakeSubsystem newIntakeSubs) {
    intakeSubs = newIntakeSubs;

    addRequirements(intakeSubs);
 }
    
  @Override
  public void initialize() {
    intakeSubs.turnPIDOn();
  }

  @Override
  public void execute() {
    intakeSubs.newSetpoint(50);
  }

  @Override
  public void end(boolean interrupted) {
    intakeSubs.turnPIDOff();
    intakeSubs.stopPivotIntake();
  }

  @Override
  public boolean isFinished() {
    return intakeSubs.isAtSetpoint(); //intakeSubs.getRestingLS() || intakeSubs.getEnc() <= 0;
  }
}
