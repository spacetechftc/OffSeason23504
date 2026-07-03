package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
import dev.nextftc.core.commands.Command;
import dev.nextftc.core.commands.delays.Delay;
import dev.nextftc.core.commands.groups.SequentialGroup;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.FollowPath;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;
@Autonomous (name = "18 ARTIFACTS AUTONOMOUS RED", group = "Auto RED", preselectTeleOp = "TeleOpNextFTC")
public class EighteenBallsAuto extends NextFTCOpMode {
    public EighteenBallsAuto(){
        addComponents(
                new SubsystemComponent(Intake.INSTANCE),
                BulkReadComponent.INSTANCE,
                new PedroComponent(Constants::createFollower)
                );
    }



    // ------------------------------------- Coordenadas ------------------------------------------

    private final Pose startPose = new Pose(111.7, 133.7, Math.toRadians(90));
    private final Pose scorePose = new Pose(82, 82, Math.toRadians(45));
    public static Pose finalPoseAuto;

    // Mid Artifacts

    private final Pose prepareMidBalls = new Pose(105, 53.5);
    private final Pose midBalls = new Pose(125, 59, Math.toRadians(15));

    // Up Artifacts

    private final Pose upBalls = new Pose(121, 82, 0);

    // Gate

    private final Pose takeGate = new Pose(131, 58, Math.toRadians(40));
    private final Pose openGate = new Pose(126, 61, 0);


    // -------------------------------------------------------------------------------------------


    private PathChain startToScore, scoreMidBalls, scoreUpBalls, scoreTakeGate, scoreTakeGateAndOpenGate;


    public void buildPaths(Follower follower){
        startToScore = PedroComponent.follower().pathBuilder()
                .addPath(new BezierLine(startPose, scorePose))
                .addParametricCallback(0.8, Intake.INSTANCE.eject)
                .addParametricCallback(0.98, Intake.INSTANCE.stopMotors)
                .setLinearHeadingInterpolation(startPose.getHeading(), scorePose.getHeading())
                .build();
        scoreMidBalls = PedroComponent.follower().pathBuilder()
                .addPath(new BezierCurve(scorePose, prepareMidBalls, midBalls))
                .setTangentHeadingInterpolation()
                .addParametricCallback(0.7, Intake.INSTANCE.colect)
                .addParametricCallback(0.98, Intake.INSTANCE.stopMotors)
                .addPath(new BezierLine(midBalls, scorePose))
                .setLinearHeadingInterpolation(midBalls.getHeading(), scorePose.getHeading())
                .addParametricCallback(0.8, Intake.INSTANCE.eject)
                .addParametricCallback(1, Intake.INSTANCE.stopMotors)
                .build();
        scoreUpBalls = PedroComponent.follower().pathBuilder()
                .addPath(new BezierLine(scorePose, upBalls))
                .setLinearHeadingInterpolation(scorePose.getHeading(), upBalls.getHeading())
                .addParametricCallback(0.6, Intake.INSTANCE.colect)
                .addParametricCallback(0.98, Intake.INSTANCE.stopMotors)
                .addPath(new BezierLine(upBalls, scorePose))
                .setLinearHeadingInterpolation(upBalls.getHeading(), scorePose.getHeading())
                .addParametricCallback(0.8, Intake.INSTANCE.eject)
                .addParametricCallback(0.98, Intake.INSTANCE.stopMotors)
                .build();

        scoreTakeGate = PedroComponent.follower().pathBuilder()
                .addPath(new BezierLine(scorePose, takeGate))
                .setLinearHeadingInterpolation(scorePose.getHeading(), takeGate.getHeading())
                .addParametricCallback(0.8, Intake.INSTANCE.colect)
                .addPath(new BezierLine(takeGate, scorePose))
                .setLinearHeadingInterpolation(takeGate.getHeading(), scorePose.getHeading())
                .addParametricCallback(0.2, Intake.INSTANCE.stopMotors)
                .addParametricCallback(0.8, Intake.INSTANCE.eject)
                .addParametricCallback(0.98, Intake.INSTANCE.stopMotors)
                .build();

        scoreTakeGateAndOpenGate = PedroComponent.follower().pathBuilder()
                .addPath(new BezierLine(scorePose, takeGate))
                .setLinearHeadingInterpolation(scorePose.getHeading(), takeGate.getHeading())
                .addParametricCallback(0.8, Intake.INSTANCE.colect)
                .addPath(new BezierLine(takeGate, openGate))
                .setLinearHeadingInterpolation(takeGate.getHeading(), openGate.getHeading())
                .addParametricCallback(0.8, Intake.INSTANCE.stopMotors)
                .addPath(new BezierLine(openGate, scorePose))
                .setLinearHeadingInterpolation(openGate.getHeading(), scorePose.getHeading())
                .addParametricCallback(1, Intake.INSTANCE.eject)
                .build();
    }

    public Command routineAutonomous(){
        return new SequentialGroup(
                new FollowPath(startToScore, true),
                new Delay(0.2),
                new FollowPath(scoreMidBalls, true),
                new Delay(0.2),
                new FollowPath(scoreUpBalls, true),
                new Delay(0.2),
                new FollowPath(scoreTakeGateAndOpenGate, true),
                new Delay(0.2),
                new FollowPath(scoreTakeGate, true),
                new Delay(0.2),
                new FollowPath(scoreTakeGateAndOpenGate, true)
        );
    }

    @Override
    public void onInit() {
        PedroComponent.follower().setStartingPose(startPose);
        buildPaths(PedroComponent.follower());
    }

    @Override
    public void onStartButtonPressed() {
        routineAutonomous().invoke();
    }


    @Override
    public void onStop() {
        finalPoseAuto = PedroComponent.follower().getPose();
    }
}
