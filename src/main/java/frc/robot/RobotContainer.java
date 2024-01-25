package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.commands.ElevatorCommands.ElevatorToBottomCommand;
import frc.robot.commands.ElevatorCommands.ElevatorToTopCommand;
import frc.robot.commands.ElevatorCommands.ManualElevatorCommand;
import frc.robot.commands.IntakeCommands.IntakeCmd;
import frc.robot.commands.IntakeCommands.IntakeTestPosition;
import frc.robot.commands.IntakeCommands.ManualIntakePivot;
import frc.robot.commands.IntakeCommands.OuttakeCmd;
import frc.robot.commands.IntakeCommands.IntakeTransferCmd;
import frc.robot.commands.IntakeCommands.FloorIntakeCmd;

public class RobotContainer {
  private final IntakeSubsystem i_subsystem = new IntakeSubsystem();
  private final ElevatorSubsystem elevSub = new ElevatorSubsystem();

  //private final XboxController xboxController = new XboxController(0);
  private final Joystick joystick = new Joystick(1);

  public RobotContainer() {
     i_subsystem.setDefaultCommand(new ManualIntakePivot(i_subsystem, () -> joystick.getY()));
    //elevSub.setDefaultCommand(new ManualElevatorCommand(elevSub, () -> joystick.getY()));

    configureBindings();
  }

  private void configureBindings() {
    new JoystickButton(joystick, 1).onTrue(new IntakeTestPosition(i_subsystem));
    new JoystickButton(joystick, 3).onTrue(new IntakeTransferCmd(i_subsystem));
    new JoystickButton(joystick, 4).onTrue(new FloorIntakeCmd(i_subsystem));
    new JoystickButton(joystick,   6).onTrue(new ElevatorToTopCommand(elevSub));
    new JoystickButton(joystick, 4).onTrue(new ElevatorToBottomCommand(elevSub));
  }

  public Command getAutonomousCommand() {
    return null;
  }
}
