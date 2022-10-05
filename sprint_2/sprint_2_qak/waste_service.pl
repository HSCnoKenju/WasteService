%====================================================================================
% waste_service description   
%====================================================================================
context(ctxbasicrobot, "localhost",  "TCP", "8020").
context(ctxpathexec, "localhost",  "TCP", "8043").
context(ctxpathplanner, "localhost",  "TCP", "8078").
context(ctxconfig, "localhost",  "TCP", "8034").
context(ctxtest, "localhost",  "TCP", "8035").
context(ctxwaste, "localhost",  "TCP", "8033").
context(ctxledqak22, "localhost",  "TCP", "8065").
context(ctxsonarqak22, "localhost",  "TCP", "8061").
 qactor( trolleyanalyzer, ctxwaste, "it.unibo.trolleyanalyzer.Trolleyanalyzer").
  qactor( thresholdchecker, ctxwaste, "it.unibo.thresholdchecker.Thresholdchecker").
  qactor( sonar, ctxsonarqak22, "it.unibo.sonar.Sonar").
  qactor( led, ctxledqak22, "it.unibo.led.Led").
  qactor( pathexec, ctxpathexec, "it.unibo.pathexec.Pathexec").
  qactor( basicrobot, ctxbasicrobot, "it.unibo.basicrobot.Basicrobot").
  qactor( pathplanner, ctxpathplanner, "it.unibo.pathplanner.Pathplanner").
  qactor( boundarymapbuilder, ctxpathplanner, "it.unibo.boundarymapbuilder.Boundarymapbuilder").
  qactor( configurer, ctxconfig, "it.unibo.configurer.Configurer").
  qactor( tester, ctxtest, "it.unibo.tester.Tester").
  qactor( transporttrolley, ctxwaste, "it.unibo.transporttrolley.Transporttrolley").
  qactor( waste_truck_mock, ctxwaste, "it.unibo.waste_truck_mock.Waste_truck_mock").
  qactor( waste_service, ctxwaste, "it.unibo.waste_service.Waste_service").
tracing.
