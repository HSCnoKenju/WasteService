package qak;

import static org.junit.Assert.*;

import it.unibo.ctxwaste.MainCtxwasteKt;
import it.unibo.kactor.*;
import org.eclipse.californium.core.CoapClient;
import org.eclipse.californium.core.CoapObserveRelation;
import org.junit.*;
import unibo.comm22.utils.ColorsOut;
import unibo.comm22.utils.CommUtils;


public class TestCoreRequisiti {

	private CoapObserveRelation relation = null;

	private static String ipaddr      = "localhost:8033" ;		//5683 default
	private static String context     = "ctxwaste" ;
	private static String destactor   = "waste_service" ;
	private static CoapClient client = null;

	@BeforeClass
	public  static void up() {
		new Thread(MainCtxwasteKt::main).start();
		waitForApplStarted();
		client = new CoapClient("coap://"+ipaddr+"/"+context+"/"+destactor);
		String initContainersStr = CommUtils.buildDispatch("test","init_capacity","values("+MAXGB+","+MAXPB+")","waste_service").toString();
		String initPositionsStr = CommUtils.buildDispatch("test","all_position", "coordinates(0,0,1,4,6,3,5,0)","transporttrolley").toString();

		try{
			connTcp = new ConnTcp("localhost", 8033);
			connTcp.forward(initContainersStr);
			connTcp.forward(initPositionsStr);

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
	public void testLoadAccept() {
		ColorsOut.outappl("testLoadok STARTS" , ColorsOut.BLUE);
		String truckRequestStr = CommUtils.buildRequest("test","waste","details(Glass,6)","waste_service").toString();

		try {

			String reply = connTcp.request(truckRequestStr);
			assertTrue(reply.contains("loadAccept"));

			String data = client.get().getResponseText().trim();
			ColorsOut.outappl("data" +data, ColorsOut.ANSI_PURPLE );
			assertTrue(data.equals("Glass=14 Plastic=10"));
		}
		catch (Exception e){
			ColorsOut.outerr("testLoadAccept ERROR:" + e.getMessage());
		}

	}

	@Test
	public  void testLoadReject(){
		ColorsOut.outappl("testLoadReject STARTS" , ColorsOut.BLUE);
		String truckRequestStr = CommUtils.buildRequest("test","waste","details(Plastic,25)","waste_service").toString();

		try {

			String reply = connTcp.request(truckRequestStr);

			assertTrue(reply.contains("loadRejected"));

			String data = client.get().getResponseText().trim();

			ColorsOut.outappl("data" +data, ColorsOut.ANSI_PURPLE );

			assertTrue(data.equals("Glass=20 Plastic=10")); // se è stato eseguito come primo test
		}
		catch (Exception e){
			ColorsOut.outerr("testLoadReject ERROR:" + e.getMessage());
		}
	}

}
