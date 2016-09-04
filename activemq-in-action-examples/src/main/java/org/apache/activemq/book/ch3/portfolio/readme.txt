1. Start ActiveMQ: ${ACTIVEMQ_HOME}/bin/activemq xbean:src/main/resources/org/apache/activemq/book/ch2/activemq.xml
2. Run Publisher class with desired stock as parameters (e.g. IONA JAVA)
3. Run Consumer class with desired stock as parameters (e.g. IONA JAVA)


__________
Guido 20160425

When running both the Consumer and the Publisher in this package with mvn exec:java

 mvn exec:java -Dexec.mainClass=org.apache.activemq.book.ch3.portfolio.Consumer -Dexec.args="CSCO ORCL"
 mvn exec:java -Dexec.mainClass=org.apache.activemq.book.ch3.portfolio.Publisher -Dexec.args="CSCO ORCL"

as suggested in the 'in action' book, the second one to be started will always fail with this error:

 ERROR: transport error 202: bind failed: Address already in use
 ERROR: JDWP Transport dt_socket failed to initialize, TRANSPORT_INIT(510)
 JDWP exit error AGENT_ERROR_TRANSPORT_INIT(197): No transports initialized [debugInit.c:750]
 FATAL ERROR in native method: JDWP No transports initialized, jvmtiError=AGENT_ERROR_TRANSPORT_INIT(197)
 Abort trap: 6

Starting it another way (e.g. from within IntelliJ, passing "CSCO ORCL" as Program Arguments in
the run configuration) will resolve the issue.