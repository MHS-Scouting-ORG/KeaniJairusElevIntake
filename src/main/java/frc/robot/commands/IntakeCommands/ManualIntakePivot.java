package frc.robot.commands.IntakeCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class ManualIntakePivot extends Command {

  private IntakeSubsystem i_subsystem;
  private DoubleSupplier speed;

  //Runs the intake pivot manually using an Xbox Controller

  public ManualIntakePivot(IntakeSubsystem iSubs, DoubleSupplier speed) {
    i_subsystem = iSubs;
    this.speed = speed;
    addRequirements(iSubs);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    SmartDashboard.putNumber("Manual Intake Speed", speed.getAsDouble());
    i_subsystem.manualIntake(speed.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
