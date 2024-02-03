package frc.robot.commands.IntakeCommands.PivotCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class RestingPositionCmd extends Command {
  private IntakeSubsystem i_Subsystem;

  //Sets the intake pivot position to its "resting" position

  public RestingPositionCmd(IntakeSubsystem iSubs) {
    i_Subsystem = iSubs;
    addRequirements(iSubs);
  }

  @Override
  public void initialize() {
    i_Subsystem.turnPIDOn();
  }

  @Override
  public void execute() {
    i_Subsystem.newSetpoint(50);
  }
  
  @Override
  public void end(boolean interrupted) {
    i_Subsystem.turnPIDOff();
    i_Subsystem.stopPivotIntake();
  }

  @Override
  public boolean isFinished() {
    return i_Subsystem.isAtSetpoint();
  }
}
