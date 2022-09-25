%====================================================================================
% alarmrpi description   
%====================================================================================
context(ctxwaste, "127.0.0.1",  "TCP", "8033").
context(ctx_rpi, "localhost",  "TCP", "8099").
 qactor( sonarlatestart, ctx_rpi, "it.unibo.sonarlatestart.Sonarlatestart").
  qactor( sonardata, ctx_rpi, "it.unibo.sonardata.Sonardata").
  qactor( led, ctx_rpi, "it.unibo.led.Led").
