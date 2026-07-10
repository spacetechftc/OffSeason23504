package org.firstinspires.ftc.teamcode.pedroPathing;

import com.pedropathing.control.PIDFCoefficients;
import com.pedropathing.follower.Follower;
import com.pedropathing.follower.FollowerConstants;
import com.pedropathing.ftc.FollowerBuilder;
import com.pedropathing.ftc.drivetrains.MecanumConstants;
import com.pedropathing.ftc.localization.constants.PinpointConstants;
import com.pedropathing.paths.PathConstraints;
import com.qualcomm.hardware.gobilda.GoBildaPinpointDriver;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Constants {
    public static FollowerConstants followerConstants = new FollowerConstants()
            .mass(5.0)
            .forwardZeroPowerAcceleration(-49.79)
            .lateralZeroPowerAcceleration(-39.953233028422474 )
            .translationalPIDFCoefficients(new PIDFCoefficients(0.05, 0.0008, 0, 0.008))
            .headingPIDFCoefficients(new PIDFCoefficients(0, 0, 0.0005, 0.03));
            ;



    public static PathConstraints pathConstraints = new PathConstraints(0.99, 100, 1, 1);




    public static MecanumConstants driveConstants = new MecanumConstants()
            .maxPower(1)
            .rightFrontMotorName("fr")
            .rightRearMotorName("rr")
            .leftRearMotorName("rl")
            .leftFrontMotorName("fl")
            .leftFrontMotorDirection(DcMotor.Direction.FORWARD)
            .leftRearMotorDirection(DcMotor.Direction.FORWARD)
            .rightFrontMotorDirection(DcMotor.Direction.REVERSE)
            .rightRearMotorDirection(DcMotor.Direction.FORWARD)
            .xVelocity(64.8044)
            .yVelocity(55.4875);





    public static Follower createFollower(HardwareMap hardwareMap) {
        return new FollowerBuilder(followerConstants, hardwareMap)
                .pathConstraints(pathConstraints)
                .mecanumDrivetrain(driveConstants)
                .pinpointLocalizer(localizerConstants)
                .build();


    }

    public static PinpointConstants localizerConstants = new PinpointConstants()
            .forwardPodY(3.4104537363127463)
            .strafePodX(-0.7898727777436036)
            .distanceUnit(DistanceUnit.INCH)
            .hardwareMapName("pinpoint")
            // .customEncoderResolution(19.8943)
            .encoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_4_BAR_POD)
            .forwardEncoderDirection(GoBildaPinpointDriver.EncoderDirection.FORWARD)
            .strafeEncoderDirection(GoBildaPinpointDriver.EncoderDirection.REVERSED);

}