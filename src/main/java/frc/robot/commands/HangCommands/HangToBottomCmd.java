package frc.robot.commands.HangCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.HangSubsystem;

public class HangToBottomCmd extends Command {

  private HangSubsystem hangSub;

  public HangToBottomCmd(HangSubsystem newElevSub) {
    hangSub = newElevSub;
    addRequirements(hangSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    hangSub.toBottom();
  }

  @Override
  public void end(boolean interrupted) {
    hangSub.stopHang();
  }

  @Override
  public boolean isFinished() {
    return hangSub.getBottomMRS();
  }
}
