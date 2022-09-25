package it.unibo.pathexecutor;

import it.unibo.kactor.IApplMessage;
import unibo.actor22.Qak22Context;
import unibo.actor22.QakActor22FsmAnnot;
import unibo.actor22.annotations.*;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;
import unibo.robot.*;
import alice.tuprolog.Term;
import alice.tuprolog.Struct;
import utils.pathut;
import utils.timestamputil;



import java.sql.Timestamp;
import java.time.Instant;


public class PathExecutor extends QakActor22FsmAnnot {

    private String CurMoveToDo = "";
    private String StepTime = "300";



    private IApplMessage doPathRequestMsg;


    public PathExecutor(String name) {
        super(name);

        // registrazione eventi
       // Qak22Context.registerAsEventObserver(name,PathExecutorMessages.ID_stop);
        // Qak22Context.registerAsEventObserver(name,PathExecutorMessages.ID_resume);


    }


    @State(name ="s0", initial=true)
    @Transition( state = "doThePath",msgId = PathExecutorMessages.ID_dopath)
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void s0(IApplMessage msg) {
        CurMoveToDo = "";
        StepTime = robotSupport.INSTANCE.readStepTime();
        System.out.println("pathexec ready. StepTime="+StepTime);
    }

    // dopath( PATH )
    @State(name ="doThePath")
    @Transition(state = "nextMove")
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void doThePath(IApplMessage msg) {

       // if( checkMsgContent( Term.createTerm("step(TIME)"), Term.createTerm("step(T)"),
       //         currentMsg.msgContent()) ) { //set msgArgList


        //System.out.println("Content" + msg.msgContent());
        Struct payloadAsStruct = (Struct) Term.createTerm(msg.msgContent());
        String path = payloadAsStruct.getArg(0).toString();
        System.out.println("doThePath | "+ path);
        if (null != path  && path!="") {
            pathut.INSTANCE.setPath(path);
            doPathRequestMsg = msg;
        }
      //  System.out.println("pathexec pathTodo = "+pathut.INSTANCE.getPathTodo());
    }

    @State(name="nextMove")
    @Transition(state = "endWorkOk", guard = "emptyPathMove")
    @Transition(state = "doMove", guard = "notEmptyPathMove" )
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void nextMove(IApplMessage msg) {
        CurMoveToDo = pathut.INSTANCE.nextMove();
        System.out.println("CurMoveToDo "+ CurMoveToDo + " ; PathTodo " + pathut.INSTANCE.getPathTodo());
    }


    @State(name="doMove")
    @Transition(state="doMoveW", guard = "aheadMove")
    @Transition(state = "doMoveTurn", guard = "notAheadMove")
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void doMove(IApplMessage msg) {

        // Event robotState : info(ATHOME,MOVING,STOPPED,TIMESTAMP)

        String payload = String.format("info(false,true,false,%s)", timestamputil.INSTANCE.TimestampNow());
        emit(PathExecutorMessages.robotstate(payload));

        CommUtils.delay(Integer.parseInt(StepTime));
    }


    @State(name="doMoveTurn")
    @Transition(state = "nextMove")
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void doMoveTurn(IApplMessage msg) {
        String payload = String.format("cmd(%s)",CurMoveToDo);
        forward(PathExecutorMessages.cmd(payload,PathExecutorMessages.basicRobotName));
       
    }
    @State(name="doMoveW")
    @Transition(state="endWorkKo", msgId = PathExecutorMessages.ID_alarm)
    @Transition(state="endWorkKo", msgId = PathExecutorMessages.ID_stepfail)
    @Transition(state="nextMove", msgId = PathExecutorMessages.ID_stepdone)
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void doMoveW(IApplMessage msg) {
        String payload = String.format("step(%s)",StepTime);
        request(PathExecutorMessages.step(payload,PathExecutorMessages.basicRobotName));
    }

    @State(name="endWorkOk")
    @Transition(state="s0")
    protected void endWorkOk(IApplMessage msg) {
        System.out.println("endWorkOk: PATH DONE - BYE");
        String payload = "dopathdone(ok)";
        IApplMessage replyDoPath = CommUtils.buildReply(PathExecutorMessages.actorName,PathExecutorMessages.ID_dopathdone,payload,doPathRequestMsg.msgSender());
        sendReply(doPathRequestMsg,replyDoPath);

    }

    @State(name="endWorkKo")
    @Transition(state="s0")
    @Transition( state = "stopped",  msgId= PathExecutorMessages.ID_stop, interrupt = true  )
    protected void endWorkKo(IApplMessage msg) {
        System.out.println(msg);
        String PathStillTodo = pathut.INSTANCE.getPathTodo();
        System.out.println("PATH FAILURE - SORRY. PathStillTodo="+PathStillTodo);
        String payload = String.format("dopathfail(%s)",PathStillTodo);
        IApplMessage replyDoPath = CommUtils.buildReply(PathExecutorMessages.actorName,PathExecutorMessages.ID_dopathfail,payload,doPathRequestMsg.msgSender());
        sendReply(doPathRequestMsg,replyDoPath);
    }

    @State( name = "stopped" )
    @Transition( state = "resume",  msgId= PathExecutorMessages.ID_resume  )
    protected void stopped( IApplMessage msg ) {
        ColorsOut.outappl(this.getName() + "/" + "STOPPED" + " | " + msg, ColorsOut.ANSI_PURPLE);
        // Event robotState : info(ATHOME,MOVING,STOPPED,TIMESTAMP)
        String payload = String.format("info(false,false,true,%s)", timestamputil.INSTANCE.TimestampNow());
        emit(PathExecutorMessages.robotstate(payload));
    }

    @State( name = "resume" )
    protected void resume( IApplMessage msg ) {
        ColorsOut.outappl(this.getName() + "/" + "RESUME" + " | " + msg, ColorsOut.ANSI_PURPLE);
        resume();
    }

    //protected void s1(IApplMessage msg) {
//
  //  }


    @TransitionGuard
    protected  boolean emptyPathMove() {
        return CurMoveToDo.length() == 0;
    }

    @TransitionGuard
    protected  boolean notEmptyPathMove() {
        return CurMoveToDo.length() != 0;
    }

    @TransitionGuard
    protected boolean aheadMove() {
        return CurMoveToDo.equalsIgnoreCase("w");
    }

    @TransitionGuard
    protected  boolean notAheadMove(){
        return !(CurMoveToDo.equalsIgnoreCase("w"));
    }
}
