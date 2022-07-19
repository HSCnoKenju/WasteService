package it.unibo.pathexecutor;

import it.unibo.kactor.IApplMessage;
import unibo.actor22comm.utils.CommUtils;

public class PathExecutorMessages {

    public final static String actorName = "pathexec";

    public final static String basicRobotName = "basicrobot";

        public final static String ID_cmd = "cmd";
        public final static String ID_end = "end";
        public final static String ID_step = "step";
        public final static String ID_stepdone = "stepdone";
        public final static String ID_stepfail = "stepfail";
        public final static String ID_obstacle = "obstacle";
        public final static String ID_info = "info";
        public final static String ID_sonar = "sonar";
        public final static String ID_alarm = "alarm";
        public final static String ID_dopath = "dopath";
        public final static String ID_dopathdone = "dopathdone";
        public final static String ID_dopathfail = "dopathfail";

        public final static String ID_stop = "stop";

        public final static String ID_resume = "resume";

        public final static String ID_robotstate = "robotState";




    public static IApplMessage cmd(String payload, String dest){

        return CommUtils.buildDispatch(actorName,ID_cmd,payload,dest);
    }

    public static IApplMessage step(String payload, String dest){

        return  CommUtils.buildRequest(actorName,ID_step,payload,dest);
    }

    public static  IApplMessage robotstate(String payload){

        return  CommUtils.buildEvent(actorName,ID_robotstate,payload);
    }
}
