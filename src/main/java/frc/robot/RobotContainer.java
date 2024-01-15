package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.commands.ManualIntakePivot;
import frc.robot.commands.ElevatorToBottomCommand;
import frc.robot.commands.ElevatorToTopCommand;
import frc.robot.commands.ManualElevatorCommand;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.IntakeCmd;
import frc.robot.commands.OuttakeCmd;

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
    new JoystickButton(xboxController, 3).onTrue(new ElevatorToTopCommand(elevSub));
    new JoystickButton(xboxController, 4).onTrue(new ElevatorToBottomCommand(elevSub));
    new JoystickButton(xboxController, 1).onTrue(new IntakeCmd(i_subsystem));
    new JoystickButton(xboxController, 2).onTrue(new OuttakeCmd(i_subsystem));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
