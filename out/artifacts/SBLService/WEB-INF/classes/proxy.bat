set AXIS_HOME=C:\Tejarat\SBLService\WebContent\WEB-INF
set AXIS_LIB=%AXIS_HOME%\lib
set CLASSPATH=%AXIS_LIB%\axis.jar;%AXIS_LIB%\commons-logging.jar;%AXIS_LIB%\commons-discovery-0.2.jar;%AXIS_LIB%\commons-logging-1.0.4.jar;%AXIS_LIB%\jaxrpc.jar;%AXIS_LIB%\saaj.jar;%AXIS_LIB%\log4j-1.2.8.jar;%AXIS_LIB%\wsdl4j-1.5.1.jar 
java  -classpath %CLASSPATH% org.apache.axis.wsdl.WSDL2Java -T 1.2 -o F:\SBLAxis http://localhost:8081/SBLService/axis/SpringWS?wsdl