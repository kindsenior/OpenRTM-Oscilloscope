set CLASSPATH=.;%RTM_JAVA_ROOT%\jar\OpenRTM-aist-1.0.0.jar;%RTM_JAVA_ROOT%\jar\commons-cli-1.1.jar
java RTMExamples.SinCosOut.SinCosOutComp -f RTMExamples\SinCosOut\rtc.conf %*
pause;
