package frc.robot.commands.IntakeCommands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakePositionCmd extends Command {
  
  private IntakeSubsystem i_subsystem;

  public IntakePositionCmd(IntakeSubsystem iSubs) {
    i_subsystem = iSubs;
    addRequirements(i_subsystem);
  }

  @Override
  public void initialize() {
    i_subsystem.turnPIDOn();
  }

  @Override
  public void execute() {
    i_subsystem.newSetpoint(0);
  }

  @Override
  public void end(boolean interrupted) {
    i_subsystem.turnPIDOff();
  }

  @Override
  public boolean isFinished() {
    return i_subsystem.isAtSetpoint();
  }
}
