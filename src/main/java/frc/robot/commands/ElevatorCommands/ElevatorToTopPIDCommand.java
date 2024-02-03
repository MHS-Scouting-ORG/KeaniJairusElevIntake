package frc.robot.commands.ElevatorCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants.HangConstants;
import frc.robot.subsystems.HangSubsystem;

public class ElevatorToTopPIDCommand extends Command {

  private HangSubsystem hangSub;

  public ElevatorToTopPIDCommand(HangSubsystem newHangSub) {

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
    return hangSub.getEnc() > HangConstants.TOP_ENC_LIMIT || hangSub.getTopLimitSwitch();
  }
}
