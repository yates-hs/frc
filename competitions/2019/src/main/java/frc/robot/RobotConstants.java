/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;
import frc.robot.Axis; // Constants are defined in the Axis.java file
import frc.robot.Button; // Constants are defined in the Button.java file

public class RobotConstants {

/*
See Axis.java and Button.java for choices of which Logitech Joystick sticks, triggers and buttons
you want to control your different engines. 
*/


    public static class WheelConstants{
        public static int direction = 1; //Should be 1 or -1.
        //  rbt.tankDrive(-0.75 * logitechCtlr.getRawAxis(1), -0.75 * logitechCtlr.getRawAxis(5));
        public static double speed = 0.75;  //should be between 0.0 and 1.0
        public static int controller = 1; //Should be 1 or 2.
        public static int leftControllerAxis = Axis.leftStickFrontBack;
                // This tells the Robot program to use the front/back movement of the Left stick.     
                // Axis.leftStickFrontBack is defined in Axis.java file.
        public static int rightControllerAxis = Axis.rightStickFrontBack;
                // This tells the Robot program to use the front/back movement of the Right stick.     
                // Axis.rightStickFrontBack is defined in Axis.java file.
        }

    public static class BallCollectorConstants{
        public static int direction = 1; // Should be 1 or -1.
        public static double speed = 0.92;  // Should be between 0.0 and 1.0
        public static int controller = 2; // Should be 1 or 2.
        public static int leftControllerAxis = Axis.bottomLeftTrigger; // Constant is defined in Axis.java file
        public static int rightControllerAxis = Axis.bottomRightTrigger; // Constant is defined in Axis.java file
        }

    public static class HatchCollectorConstants{
        public static int controller = 2; // Should be 1 or 2.
        public static int upControllerButton = Button.X;   //XConstant is defined in Button.java file
        public static int downControllerButton = Button.A; //AConstant is defined in Button.java file
        }

}