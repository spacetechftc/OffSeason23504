package org.firstinspires.ftc.teamcode.OpModes.Autonomous.Testes;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.telemetry.TelemetryManager;
import com.bylazar.telemetry.PanelsTelemetry;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.follower.Follower;
import com.pedropathing.paths.PathChain;
import com.pedropathing.geometry.Pose;

@Configurable // Panels
public class Autonomous extends OpMode {
    private TelemetryManager panelsTelemetry; // Panels Telemetry instance
    public Follower follower; // Pedro Pathing follower instance
    private int pathState; // Current autonomous path state (state machine)
    private Paths paths; // Paths defined in the Paths class

    @Override
    public void init() {
        panelsTelemetry = PanelsTelemetry.INSTANCE.getTelemetry();

        follower = Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(72, 8, Math.toRadians(90)));

        paths = new Paths(follower); // Build paths

        panelsTelemetry.debug("Status", "Initialized");
        panelsTelemetry.update(telemetry);
    }

    @Override
    public void loop() {
        follower.update(); // Update Pedro Pathing
        pathState = autonomousPathUpdate(); // Update autonomous state machine

        // Log values to Panels and Driver Station
        panelsTelemetry.debug("Path State", pathState);
        panelsTelemetry.debug("X", follower.getPose().getX());
        panelsTelemetry.debug("Y", follower.getPose().getY());
        panelsTelemetry.debug("Heading", follower.getPose().getHeading());
        panelsTelemetry.update(telemetry);
    }

    public static class Paths {
        public PathChain MainChain;

        public Paths(Follower follower) {
            MainChain = follower.pathBuilder()
                    .addPath(
                            new BezierLine(
                                    new Pose(111.613, 133.211),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(45))
                    .addPath(
                            new BezierCurve(
                                    new Pose(82.490, 81.905),
                                    new Pose(80.450, 51.301),
                                    new Pose(123.265, 58.287)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(0))
                    .addPath(
                            new BezierLine(
                                    new Pose(123.265, 58.287),
                                    new Pose(126.404, 63.812)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                    .addPath(
                            new BezierLine(
                                    new Pose(126.404, 63.812),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))
                    .addPath(
                            new BezierLine(
                                    new Pose(82.490, 81.905),
                                    new Pose(124.377, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(0))
                    .addPath(
                            new BezierLine(
                                    new Pose(124.377, 81.905),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))
                    .addPath(
                            new BezierCurve(
                                    new Pose(82.490, 81.905),
                                    new Pose(102.908, 54.872),
                                    new Pose(131.173, 59.531)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(40))
                    .addPath(
                            new BezierLine(
                                    new Pose(131.173, 59.531),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(45))
                    .addPath(
                            new BezierCurve(
                                    new Pose(82.490, 81.905),
                                    new Pose(102.908, 54.872),
                                    new Pose(131.173, 59.531)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(40))
                    .addPath(
                            new BezierLine(
                                    new Pose(131.173, 59.531),
                                    new Pose(126.314, 63.246)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(0))
                    .addPath(
                            new BezierLine(
                                    new Pose(126.314, 63.246),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(0), Math.toRadians(45))
                    .addPath(
                            new BezierCurve(
                                    new Pose(82.490, 81.905),
                                    new Pose(102.908, 54.872),
                                    new Pose(131.173, 59.531)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(45), Math.toRadians(40))
                    .addPath(
                            new BezierLine(
                                    new Pose(131.173, 59.531),
                                    new Pose(82.490, 81.905)
                            )
                    )
                    .setLinearHeadingInterpolation(Math.toRadians(40), Math.toRadians(45))
                    .build();
        }
    }

    public int autonomousPathUpdate() {
        // Add your state machine Here
        // Access paths with paths.pathName
        // Refer to the Pedro Pathing Docs (Auto Example) for an example state machine
        return 0;
    }
}