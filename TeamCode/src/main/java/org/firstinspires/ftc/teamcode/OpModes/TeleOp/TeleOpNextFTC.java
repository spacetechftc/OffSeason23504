package org.firstinspires.ftc.teamcode.OpModes.TeleOp;

import static org.firstinspires.ftc.teamcode.OpModes.Autonomous.EighteenBallsAuto.finalPoseAuto;

import com.pedropathing.geometry.Pose;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.skeletonarmy.marrow.zones.Point;
import com.skeletonarmy.marrow.zones.PolygonZone;

import org.firstinspires.ftc.teamcode.Mecanismos.Pinpoint;
import org.firstinspires.ftc.teamcode.Subsystems.Intake;
import org.firstinspires.ftc.teamcode.Subsystems.MecanumDrive;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

import dev.nextftc.core.components.BindingsComponent;
import dev.nextftc.core.components.SubsystemComponent;
import dev.nextftc.extensions.pedro.PedroComponent;
import dev.nextftc.ftc.Gamepads;
import dev.nextftc.ftc.NextFTCOpMode;
import dev.nextftc.ftc.components.BulkReadComponent;

@TeleOp
public class TeleOpNextFTC extends NextFTCOpMode {
    private MecanumDrive mecanumDrive;
    private final Pinpoint pinpoint = new Pinpoint();
    public Pose startingPose = finalPoseAuto;

    private final PolygonZone blueBase = new PolygonZone(new Point(105.5, 33.5), 20, 20);
    private final PolygonZone redBase = new PolygonZone(new Point(38.5, 33.5), 20, 20);
    private final PolygonZone robotZone = new PolygonZone(18, 18);



    public TeleOpNextFTC(){
        addComponents(new SubsystemComponent(Intake.INSTANCE), BulkReadComponent.INSTANCE, BindingsComponent.INSTANCE, new PedroComponent(Constants::createFollower
        ));
    }



    @Override
    public void onInit() {
        pinpoint.init(hardwareMap);
        mecanumDrive = new MecanumDrive(pinpoint);
        PedroComponent.follower().setStartingPose(startingPose == null ? new Pose() : startingPose);
        PedroComponent.follower().updatePose();
    }

    @Override
    public void onStartButtonPressed() {

        mecanumDrive.start();
        Gamepads.gamepad1().rightTrigger().greaterThan(0.2)
                .whenBecomesTrue(Intake.INSTANCE.colect)
                .whenBecomesFalse(Intake.INSTANCE.stopMotors);

        Gamepads.gamepad1().leftTrigger().greaterThan(0.2)
                .whenBecomesTrue(Intake.INSTANCE.eject)
                .whenBecomesFalse(Intake.INSTANCE.stopMotors);


    }

    @Override
    public void onUpdate() {
        pinpoint.updatePinpoint();

        robotZone.setPosition(PedroComponent.follower().getPose().getX(), PedroComponent.follower().getPose().getY());
        robotZone.setRotation(PedroComponent.follower().getHeading());

        if(robotZone.isInside(redBase)){
            telemetry.addLine("Robot INSIDE Red Base!");
        }else {
            telemetry.addLine("Robot OUTSIDE Red Base");
        }

        telemetry.addData("Distance to Blue Base", robotZone.distanceTo(redBase));
    }
}
