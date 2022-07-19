package it.unibo.pathexecutor;

import it.unibo.kactor.IApplMessage;
import unibo.actor22.QakActor22FsmAnnot;
import unibo.actor22.annotations.*;
import unibo.actor22comm.utils.CommUtils;
import unibo.robot.*;
import alice.tuprolog.Term;
import alice.tuprolog.Struct;
import utils.pathut;

import java.nio.file.Path;
import java.text.Format;


public class PathExecutor extends QakActor22FsmAnnot {

    private String CurMoveToDo = "";
    private String StepTime = "300";



    private IApplMessage doPathRequestMsg;


    public PathExecutor(String name) {
        super(name);

        // registrazione eventi


    }


    @State(name ="s0", initial=true)
    @Transition( state = "doThePath",msgId = PathExecutorMessages.ID_dopath)
    protected void s0(IApplMessage msg) {
        CurMoveToDo = "";
        StepTime = robotSupport.INSTANCE.readStepTime();
        System.out.println("pathexec ready. StepTime="+StepTime);
    }

    // dopath( PATH )
    @State(name ="doThePath")
    @Transition(state = "nextMove")
    protected void doThePath(IApplMessage msg) {
        Struct payloadAsStruct = (Struct) Term.createTerm(msg.msgContent());
        pathut.INSTANCE.setPath(payloadAsStruct.getArg(0).toString());

        doPathRequestMsg = msg;
        System.out.println("pathexec pathTodo = "+pathut.INSTANCE.getPathTodo());
    }

    @State(name="nextMove")
    @Transition(state = "endWorkOk", guard = "emptyPathMove")
    @Transition(state = "doMove", guard = "notEmptyPathMove" )
    protected void nextMove(IApplMessage msg) {
        CurMoveToDo = pathut.INSTANCE.nextMove();
    }


    @State(name="doMove")
    @Transition(state="doMoveW", guard = "aheadMove")
    @Transition(state = "doMoveTurn", guard = "notAheadMove")
    protected void doMove(IApplMessage msg) {
        CommUtils.delay(300);
    }


    @State(name="doMoveTurn")
    @Transition(state = "nextMove")
    protected void doMoveTurn(IApplMessage msg) {
        String payload = String.format("cmd(%s)",CurMoveToDo);
        forward(PathExecutorMessages.cmd(payload,PathExecutorMessages.basicRobotName));
        CommUtils.delay(300);
    }
    @State(name="doMoveW")
    @Transition(state="endWorkKo", msgId = PathExecutorMessages.ID_alarm)
    @Transition(state="endWorkKo", msgId = PathExecutorMessages.ID_stepfail)
    @Transition(state="nextMove", msgId = PathExecutorMessages.ID_stepdone)
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
    protected void endWorkKo(IApplMessage msg) {

        System.out.println(msg);

        String PathStillTodo = pathut.INSTANCE.getPathTodo();

        System.out.println("PATH FAILURE - SORRY. PathStillTodo="+PathStillTodo);

        String payload = String.format("dopathfail(%s)",PathStillTodo);

        IApplMessage replyDoPath = CommUtils.buildReply(PathExecutorMessages.actorName,PathExecutorMessages.ID_dopathfail,payload,doPathRequestMsg.msgSender());

        sendReply(doPathRequestMsg,replyDoPath);

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
