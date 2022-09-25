%====================================================================================
% pathplanner description   
%====================================================================================
context(ctxbasicrobot, "127.0.0.1",  "TCP", "8020").
context(ctxpathexec, "127.0.0.1",  "TCP", "8049").
context(ctxpathplanner, "localhost",  "TCP", "8078").
 qactor( pathexec, ctxpathexec, "external").
  qactor( basicrobot, ctxbasicrobot, "external").
  qactor( pathplanner, ctxpathplanner, "it.unibo.pathplanner.Pathplanner").
  qactor( boundarymapbuilder, ctxpathplanner, "it.unibo.boundarymapbuilder.Boundarymapbuilder").
