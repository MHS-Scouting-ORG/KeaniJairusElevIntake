package frc.robot.commands.IntakeCommands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.UnderIntakeSubsystem;

public class IntakeCmd extends Command {

  private UnderIntakeSubsystem u_Subsystem;

  public IntakeCmd(UnderIntakeSubsystem uSubs) {
    u_Subsystem = uSubs;
    addRequirements(u_Subsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    u_Subsystem.intake();
  }

  @Override
  public void end(boolean interrupted) {
    u_Subsystem.toEncoder(3.825);
  }

  @Override
  public boolean isFinished() {
    return u_Subsystem.getOpticalSensor();
  }
}
