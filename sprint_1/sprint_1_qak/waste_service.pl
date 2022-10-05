%====================================================================================
% waste_service description   
%====================================================================================
context(ctxbasicrobot, "localhost",  "TCP", "8020").
context(ctxpathplanner, "localhost",  "TCP", "8078").
context(ctxconfig, "localhost",  "TCP", "8034").
context(ctxtest, "localhost",  "TCP", "8035").
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( pathexec, ctxbasicrobot, "it.unibo.pathexec.Pathexec").
  qactor( basicrobot, ctxbasicrobot, "it.unibo.basicrobot.Basicrobot").
  qactor( pathplanner, ctxpathplanner, "it.unibo.pathplanner.Pathplanner").
  qactor( boundarymapbuilder, ctxpathplanner, "it.unibo.boundarymapbuilder.Boundarymapbuilder").
  qactor( configurer, ctxconfig, "it.unibo.configurer.Configurer").
  qactor( tester, ctxtest, "it.unibo.tester.Tester").
  qactor( transporttrolley, ctxwaste, "it.unibo.transporttrolley.Transporttrolley").
  qactor( waste_truck_mock, ctxwaste, "it.unibo.waste_truck_mock.Waste_truck_mock").
  qactor( waste_service, ctxwaste, "it.unibo.waste_service.Waste_service").
tracing.
