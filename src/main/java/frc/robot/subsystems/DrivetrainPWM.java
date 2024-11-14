package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainPWM extends SubsystemBase  {
    private static DifferentialDrive robotDrive;
    private PWMSparkMax motorRight = new PWMSparkMax(8);
    private PWMSparkMax motorLeft = new PWMSparkMax(9);
    private XboxController driveHID;

    public DrivetrainPWM(XboxController driveHID) {
        this.driveHID = driveHID;
        motorRight.setInverted(true); // Invert right side motors due to the construction of the frame
        // motorRight.setSafetyEnabled(true);
        // motorLeft.setSafetyEnabled(true);
        robotDrive = new DifferentialDrive(motorLeft::set, motorRight::set);
    }

    
  @Override
  public void periodic() {
    robotDrive.tankDrive(-driveHID.getLeftY(), -driveHID.getRightY());
  }
}
