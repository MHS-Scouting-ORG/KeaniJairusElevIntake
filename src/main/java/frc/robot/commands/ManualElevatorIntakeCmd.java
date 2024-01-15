package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class ManualElevatorIntakeCmd extends Command {
  private IntakeSubsystem i_subsystem;
  private DoubleSupplier speed;

  public ManualElevatorIntakeCmd(IntakeSubsystem intakeSubs, DoubleSupplier speed) {
    i_subsystem = intakeSubs;
    this.speed = speed;
    addRequirements(intakeSubs);
  }

  @Override
  public void initialize() {

  }

  @Override
  public void execute() {
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
