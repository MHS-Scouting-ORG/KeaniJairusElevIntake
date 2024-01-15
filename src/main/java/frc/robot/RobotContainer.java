package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.ManualIntakePivot;
import frc.robot.commands.ManualElevatorCommand;
import frc.robot.subsystems.ElevatorSubsystem;

public class RobotContainer {
  private final IntakeSubsystem i_subsystem = new IntakeSubsystem();
  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();

  private final XboxController xboxController = new XboxController(0);

  public RobotContainer() {
    i_subsystem.setDefaultCommand(new ManualIntakePivot(i_subsystem, () -> xboxController.getLeftY()));
    elevSub.setDefaultCommand(new ManualElevatorCommand(elevSub, () -> xboxController.getRightY()));
    configureBindings();
  }

  private void configureBindings() {
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
