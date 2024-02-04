package frc.robot.commands.HangCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangSubsystem;

public class HangToBottomPIDCmd extends Command {

  private HangSubsystem hangSub;

  public HangToBottomPIDCmd(HangSubsystem newElevSub) {

    hangSub = newElevSub;

    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toBottomPID();
  }

  @Override
  public void end(boolean interrupted) {
    hangSub.elevStop();
  }

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return hangSub.isAtSetpoint();
  }
}
