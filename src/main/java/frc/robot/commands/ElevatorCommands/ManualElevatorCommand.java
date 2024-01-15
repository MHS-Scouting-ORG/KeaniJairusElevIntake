package frc.robot.commands.ElevatorCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;

public class ManualElevatorCommand extends Command {

  private ElevatorSubsystem elevSub;
  private DoubleSupplier doubleSupplier;

  public ManualElevatorCommand(ElevatorSubsystem newElevSub, DoubleSupplier newDoubleSupplier) {
    elevSub = newElevSub;
    doubleSupplier = newDoubleSupplier;
    addRequirements(elevSub);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    elevSub.ManualElevator(doubleSupplier.getAsDouble());
    SmartDashboard.putNumber("Manual Elevator Output", doubleSupplier.getAsDouble());
  }

  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
