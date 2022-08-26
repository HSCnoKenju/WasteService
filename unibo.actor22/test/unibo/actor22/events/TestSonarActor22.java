package unibo.actor22.events;

import it.unibo.radarSystem22.domain.utils.DomainSystemConfig;
import unibo.actor22.Qak22Context;
import unibo.actor22.Qak22Util;
import unibo.actor22.annotations.Actor;
import unibo.actor22.annotations.Actor22;
import unibo.actor22.annotations.ActorLocal;
import unibo.actor22.annotations.Context22;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.actor22comm.utils.CommSystemConfig;
import unibo.actor22comm.utils.CommUtils;
import unibo.actor22.common.ApplData;
import unibo.actor22.common.EventObserver;
import unibo.actor22.common.ControllerForSonarActor;
import unibo.actor22.common.RadarSystemConfig;


@ActorLocal(name =     { ApplData.sonarName, ApplData.controllerName }, 
			implement = {SonarActor22.class, ControllerForSonarActor.class }
)
@Context22(name = "ctxbasicrobot", host = "127.0.0.1", port = "8020")
@Context22(name = "ctxpathexec",host = "127.0.1.1", port = "8049")
@Context22(name = "ctxwaste", host = "127.0.0.1", port = "8033")
@Actor22(name = "aa", contextName = "ctxpathexec")
@Actor22(name = "bb", contextName = "ctxwaste")

public class TestSonarActor22 {
	
	public TestSonarActor22() {
		configure();
	}
	
	protected void configure() {
		DomainSystemConfig.simulation   	= true;			
		DomainSystemConfig.tracing      	= false;
		DomainSystemConfig.sonarDelay   	= 200;
		CommSystemConfig.tracing        	= true;
		
		//con false, il ControllerForSonarActor chiede la distanza, 
		//con true,  il ControllerForSonarActor agisce come observer
		RadarSystemConfig.sonarObservable 	= true; 
		
		//ALTRO Observer oltr il Controller
// 		new EventObserver(ApplData.observerName);
// 		Qak22Context.registerAsEventObserver(ApplData.observerName, ApplData.evDistance);

		Qak22Context.configureTheSystem(this);
		Qak22Context.handleLocalActorDecl(this);
		if( RadarSystemConfig.sonarObservable  ) {
 			Qak22Context.registerAsEventObserver(ApplData.controllerName, ApplData.evDistance);
		}
 	}

	
	
	public void doJob() {
		Qak22Util.sendAMsg( ApplData.activateCrtl );
//		CommUtils.delay(3000);
//		Qak22Util.sendAMsg( ApplData.deactivateSonar );
		
	}

	public static void main( String[] args) {
		CommUtils.aboutThreads("Before start - ");
		new TestSonarActor22().doJob();
		CommUtils.aboutThreads("Before end - ");
	}
}
