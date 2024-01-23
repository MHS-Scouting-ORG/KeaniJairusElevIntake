package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeTestPosition extends Command {
  private IntakeSubsystem i_Subsystem;

  public IntakeTestPosition(IntakeSubsystem iSubs) {
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
