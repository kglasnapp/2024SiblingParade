package frc.robot.commands;

import static frc.robot.Util.logf;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.PoseSubsystem;

public class ResetOdometryWithCameraCommand extends Command {

    PoseSubsystem poseSubsystem;

    public ResetOdometryWithCameraCommand(PoseSubsystem poseSubsystem) {
        this.poseSubsystem = poseSubsystem;
    }

    @Override
    public void initialize() {
        logf("*************************Starting the reset odometry\n");
        poseSubsystem.assumeNextVisionPose();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
