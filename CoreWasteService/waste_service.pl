%====================================================================================
% waste_service description   
%====================================================================================
context(ctxpathexec, "127.0.0.1",  "TCP", "8049").
context(ctxpathplanner, "127.0.0.1",  "TCP", "8078").
context(ctxledqak22, "192.168.126.33",  "TCP", "8065").
context(ctxsonarqak22, "192.168.126.33",  "TCP", "8061").
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( pathplanner, ctxpathplanner, "external").
  qactor( sonarqak22, ctxsonarqak22, "external").
  qactor( trolleyanalyzer, ctxwaste, "it.unibo.trolleyanalyzer.Trolleyanalyzer").
  qactor( thresholdchecker, ctxwaste, "it.unibo.thresholdchecker.Thresholdchecker").
  qactor( transporttrolley, ctxwaste, "it.unibo.transporttrolley.Transporttrolley").
  qactor( waste_service, ctxwaste, "it.unibo.waste_service.Waste_service").
