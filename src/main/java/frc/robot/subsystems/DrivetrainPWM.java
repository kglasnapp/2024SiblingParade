package frc.robot.subsystems;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DrivetrainPWM extends SubsystemBase {
  private static DifferentialDrive robotDrive;
  private PWMSparkMax motorRight = new PWMSparkMax(8);
  private PWMSparkMax motorLeft = new PWMSparkMax(9);
  private XboxController driveHID;

  public double speedRatio = 0.5;
  public boolean straightMode = false;
  public DriveMode mode = DriveMode.CurvatureDrive;

  public enum DriveMode {
    TankDrive,
    ArcadeDrive,
    CurvatureDrive,
  }

  public DrivetrainPWM(XboxController driveHID) {
    this.driveHID = driveHID;
    motorRight.setInverted(true); // Invert right side motors due to the construction of the frame
    motorRight.setSafetyEnabled(true);
    motorLeft.setSafetyEnabled(true);
    robotDrive = new DifferentialDrive((speed) -> motorLeft.set(speed * speedRatio),
        (speed) -> motorRight.set(speed * speedRatio * 0.90));
  }

  @Override
  public void periodic() {

    switch (mode) {
      case TankDrive: {
        double left = -driveHID.getLeftY();
        double right = -driveHID.getRightY();

        if (!straightMode) {
          robotDrive.tankDrive(left, right);
        } else {
          if (Math.abs(left) >= Math.abs(right)) {
            robotDrive.tankDrive(left, left);
          } else {
            robotDrive.tankDrive(right, right);
          }
        }
        break;
      }
      case ArcadeDrive: {
        double speed = -driveHID.getLeftY();
        double rotation = -driveHID.getLeftX();

        robotDrive.arcadeDrive(speed, rotation);
        break;
      }
      case CurvatureDrive: {
        double speed = -driveHID.getLeftY();
        double curvature = -driveHID.getRightX();

        robotDrive.curvatureDrive(speed, curvature, true);
        break;
      }
    }
  }
}
