package it.unibo.pathexecutor;


import org.checkerframework.checker.units.qual.A;
import unibo.actor22.Qak22Context;
import unibo.actor22.Qak22Util;
import unibo.actor22.annotations.Actor22;
import unibo.actor22.annotations.Context22;
import unibo.actor22comm.events.EventMsgHandler;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;


@Context22(name = "ctxbasicrobot", host = "127.0.0.1", port = "8020")
@Context22(name = "ctxpathexec",host = "localhost", port = "8049")
@Context22(name = "ctxwaste", host = "127.0.0.1", port = "8033")
@Actor22(name = PathExecutorMessages.actorName, contextName = "ctxpathexec", implement = PathExecutor.class)
@Actor22(name=PathExecutorMessages.basicRobotName, contextName = "ctxbasicrobot")
@Actor22(name= "eventdummy", contextName = "ctxwaste" )
public class MainPathExecutorInterrupt{




  protected void configure() throws Exception {
    Qak22Context.configureTheSystem(this);
//		Qak22Context.registerAsEventObserver(ApplData.robotName,SystemData.fireEventId);
//		Qak22Context.registerAsEventObserver(ApplData.robotName,SystemData.endAlarmId);

    Qak22Context.registerAsEventObserver(PathExecutorMessages.actorName,PathExecutorMessages.ID_stop);
    Qak22Context.registerAsEventObserver(PathExecutorMessages.actorName,PathExecutorMessages.ID_resume);
    Qak22Context.registerAsEventObserver(PathExecutorMessages.actorName,PathExecutorMessages.ID_alarm);
    //EventHandler è un attore: protrebbe avere riterdi nella registrazione
    Qak22Context.showActorNames();

    CommSystemConfig.tracing = true;
  }

  public static void main(String[] args) throws Exception {

    CommUtils.aboutThreads("Before start - ");
    new MainPathExecutorInterrupt().configure();

  }
}