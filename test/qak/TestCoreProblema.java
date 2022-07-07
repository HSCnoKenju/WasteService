package qak;

import it.unibo.ctxwaste.MainCtxwasteKt;
import it.unibo.kactor.ActorBasic;
import it.unibo.kactor.QakContext;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapObserveRelation;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import unibo.actor22comm.utils.ColorsOut;
import unibo.actor22comm.utils.CommUtils;

import static org.junit.Assert.*;


public class TestCoreProblema {

	private final CoapObserveRelation service_relation = null;

	private static CoapClient 	service_client = null;

	private final CoapObserveRelation trolley_relation = null;
	private static CoapClient 	trolley_client = null;

	@BeforeClass
	public  static void up() {
		new Thread(MainCtxwasteKt::main).start();
		waitForApplStarted();
		//5683 default
		String service_ipaddr = "localhost:8033";
		String service_context = "ctxwaste";
		String service_destactor = "waste_service";
		service_client = new CoapClient("coap://"+ service_ipaddr +"/"+ service_context +"/"+ service_destactor);
		//5683 default
		String trolley_ipaddr = "localhost:8033";
		String trolley_context = "ctxwaste";
		String trolley_destactor = "transporttrolley";
		trolley_client = new CoapClient("coap://"+ trolley_ipaddr +"/"+ trolley_context +"/"+ trolley_destactor);

		String initContainersStr = CommUtils.buildDispatch("test","init_capacity","values("+MAXGB+","+MAXPB+")","waste_service").toString();
		try{
			connTcp = new ConnTcp("localhost", 8033);
			connTcp.forward(initContainersStr);

		}catch(Exception e){
			ColorsOut.outerr("initial ERROR:" + e.getMessage());

		}

	}

	static ConnTcp connTcp;
	static int MAXPB = 10;
	static int MAXGB = 20;


	protected  static void waitForApplStarted(){
		ActorBasic wasteservice = QakContext.Companion.getActor("waste_service");
		while( wasteservice == null ){
			ColorsOut.outappl("TestCoreRequisiti waits for appl ... " , ColorsOut.GREEN);
			CommUtils.delay(200);
			wasteservice = QakContext.Companion.getActor("waste_service");
		}
	}

	@AfterClass
	public static void down() {

		try {
			connTcp.close();
		}catch(Exception e){
			ColorsOut.outerr("close ERROR:" + e.getMessage());
		}
		ColorsOut.outappl("TestCoreRequisiti ENDS" , ColorsOut.BLUE);
	}

	@After
	public void resetWeight(){

		String resetDispatch = CommUtils.buildDispatch("test","reset", "info(reset)", "waste_service").toString();
		connTcp.forward(resetDispatch);
	}

	@Test
	public void testSingleLoadAccepted() {
		ColorsOut.outappl("testLoadok STARTS" , ColorsOut.BLUE);
		String truckRequestStr = CommUtils.buildRequest("test","waste","details(Glass,6)","waste_service").toString();



			try {
				String reply = connTcp.request(truckRequestStr);
				assertTrue(reply.contains("loadAccept"));

			String pathStr = trolley_client.get().getResponseText();

			while (pathStr.split(" ").length < 4){ 	// controllo che ci siano almeno 4 elementi, amplio
															// per percorsi futuri
				pathStr = trolley_client.get().getResponseText();
			}

			ColorsOut.outappl("position " +pathStr, ColorsOut.ANSI_PURPLE );
			assertTrue(pathStr.contains("ACCEPTED"));
			pathStr = pathStr.substring(pathStr.indexOf("ACCEPTED"));

			assertTrue(pathStr.contains("INDOOR"));
			pathStr = pathStr.substring(pathStr.indexOf("INDOOR"));

			assertTrue(pathStr.contains("Glass"));
			pathStr = pathStr.substring(pathStr.indexOf("Glass"));

			assertTrue(pathStr.contains("HOME"));


		}
		catch (Exception e){
			ColorsOut.outerr("testLoadAccept ERROR:" + e.getMessage());
		}

	}

	@Test
	public void testConsecutiveLoadAccepted() {
		ColorsOut.outappl("testLoadok_double STARTS" , ColorsOut.BLUE);
		String truckRequestStr_1 = CommUtils.buildRequest("test","waste","details(Glass,6)","waste_service").toString();
		String truckRequestStr_2 = CommUtils.buildRequest("test","waste","details(Glass,4)","waste_service").toString();

		try {

		new Thread( (() ->{

			String reply_1 = null;
			String reply_2 = null;
			try {
				reply_1 = connTcp.request(truckRequestStr_1);
				reply_2 = connTcp.request(truckRequestStr_2);
			} catch (Exception e) {
				throw new RuntimeException(e);
			}

			assertTrue(reply_1.contains("loadAccept"));
			assertTrue(reply_2.contains("loadAccept"));
		} )).start();


			String pathStr = trolley_client.get().getResponseText();

			while (pathStr.split(" ").length < 6 /*&& (pathStr.contains("Glass") || pathStr.contains(("Plastic")))*/){ 	// controllo che ci siano almeno 4 elementi, amplio
				// per percorsi futuri
				pathStr = trolley_client.get().getResponseText();
			}

			ColorsOut.outappl("position " +pathStr, ColorsOut.ANSI_PURPLE );
			assertTrue(pathStr.contains("ACCEPTED"));
			pathStr = pathStr.substring(pathStr.indexOf("ACCEPTED"));

			assertTrue(pathStr.contains("INDOOR"));
			pathStr = pathStr.substring(pathStr.indexOf("INDOOR"));

			assertTrue(pathStr.contains("Glass"));
			pathStr = pathStr.substring(pathStr.indexOf("Glass"));

			assertFalse(pathStr.contains("HOME"));

			assertTrue(pathStr.contains("ACCEPTED"));
			pathStr = pathStr.substring(pathStr.indexOf("ACCEPTED"));

			assertTrue(pathStr.contains("INDOOR"));
			pathStr = pathStr.substring(pathStr.indexOf("INDOOR"));

			assertTrue(pathStr.contains("Glass"));
			pathStr = pathStr.substring(pathStr.indexOf("Glass"));


		}
		catch (Exception e){
			ColorsOut.outerr("testLoadAccept ERROR:" + e.getMessage());
		}

	}



}
