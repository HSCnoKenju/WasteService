%====================================================================================
% waste_service description   
%====================================================================================
context(ctxpathexec, "127.0.0.1",  "TCP", "8049").
context(ctxwaste, "localhost",  "TCP", "8033").
 qactor( pathexec, ctxpathexec, "external").
  qactor( mocksystem, ctxwaste, "it.unibo.mocksystem.Mocksystem").
