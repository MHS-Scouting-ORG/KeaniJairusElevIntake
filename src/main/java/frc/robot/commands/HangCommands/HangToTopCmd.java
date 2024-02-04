package frc.robot.commands.HangCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangSubsystem;

public class HangToTopCmd extends Command {

  private HangSubsystem hangSub;

  public HangToTopCmd(HangSubsystem newHangSub) {

    hangSub = newHangSub;

    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toTopPID();
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    // Add limit switch condiiton
    return hangSub.isAtSetpoint() || hangSub.getTopLimitSwitch();
  }
}
