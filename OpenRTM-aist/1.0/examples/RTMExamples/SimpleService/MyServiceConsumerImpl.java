package RTMExamples.SimpleService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.CorbaConsumer;
import jp.go.aist.rtm.RTC.port.CorbaPort;
import RTC.ReturnCode_t;

public class MyServiceConsumerImpl  extends DataFlowComponentBase {

    public MyServiceConsumerImpl(Manager manager) {
        super(manager);
        // <rtc-template block="initializer">
        m_MyServicePort = new CorbaPort("MyService");
    }

    // The initialize action (on CREATED->ALIVE transition)
    // formaer rtc_init_entry() 
    @Override
    protected RTC.ReturnCode_t onInitialize() {
        m_MyServicePort.registerConsumer("myservice0", 
                                            "MyService", 
                                            m_myservice0Base);
        // Set CORBA Service Ports
        addPort(m_MyServicePort);
        return super.onInitialize();
    }
    // The finalize action (on ALIVE->END transition)
    // formaer rtc_exiting_entry()
//    @Override
//    protected ReturnCode_t onFinalize() {
//        return super.onFinalize();
//    }
    //
    // The startup action when ExecutionContext startup
    // former rtc_starting_entry()
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }
    //
    // The shutdown action when ExecutionContext stop
    // former rtc_stopping_entry()
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }
    //
    // The activated action (Active state entry action)
    // former rtc_active_entry()
//    @Override
//    protected ReturnCode_t onActivated(int ec_id) {
//        return super.onActivated(ec_id);
//    }
    //
    // The deactivated action (Active state exit action)
    // former rtc_active_exit()
//    @Override
//    protected ReturnCode_t onDeactivated(int ec_id) {
//        return super.onDeactivated(ec_id);
//    }
    //
    // The execution action that is invoked periodically
    // former rtc_active_do()
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
        try {
            System.out.println("");
            System.out.println("Command list: ");
            System.out.println(" echo [msg]       : echo message.");
            System.out.println(" set_value [value]: set value." );
            System.out.println(" get_value        : get current value.");
            System.out.println(" get_echo_history : get input messsage history." );
            System.out.println(" get_value_history: get input value history." );
            System.out.print("> ");
          
            String args = null;
            int pos;
            String argv[] = null;
//          std::getline(std::cin, args);
            BufferedReader buff = new BufferedReader(new InputStreamReader( System.in ));
            try {
                args = buff.readLine();
            } catch (NumberFormatException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
          
            pos = args.indexOf(" ");
            if( pos>0 ) {
                argv = new String[2];
                argv[0] = args.substring(0,pos);
                argv[1] = args.substring(pos+1);
            } else {
                argv = new String[1];
                argv[0] = args;
                
            }
          
            /*
             std::cout << "command: " << argv[0] << std::endl;
             if (argv.size() > 1)
             std::cout << "arg    : " << argv[1] << std::endl;
            */
          
          m_myservice0 = m_myservice0Base._ptr();
          if( argv[0].equals("echo") && argv.length>1 ) {
              String retmsg = m_myservice0.echo(argv[1]);
              System.out.println( "echo return: " + retmsg );
              return super.onExecute(ec_id);
          }
          
          if( argv[0].equals("set_value") && argv.length>1 ) {
              Float val = Float.valueOf(argv[1]);
              m_myservice0.set_value(val.floatValue());
              System.out.println( "Set remote value: " + val );
              return super.onExecute(ec_id);
          }
          
          if( argv[0].equals("get_value") ) {
              System.out.println( "Current remote value: " + m_myservice0.get_value() );
              return super.onExecute(ec_id);
          }
          
          if( argv[0].equals("get_echo_history") ) {
              String[] echo_history = m_myservice0.get_echo_history();
              for( int intIdx=0;intIdx<echo_history.length;intIdx++ ) {
                  System.out.println( intIdx+": "+echo_history[intIdx]);
              }
              return super.onExecute(ec_id);
          }
          
          if( argv[0].equals("get_value_history") ) {
              float[] value_history = m_myservice0.get_value_history();
              for( int intIdx=0;intIdx<value_history.length;intIdx++ ) {
                  System.out.println( intIdx+": "+value_history[intIdx]);
              }
              return super.onExecute(ec_id);
          }
          System.out.println( "Invalid command or argument(s)." );
        } catch (Exception ex) {
            System.out.println( "No service connected." );
        }
        return super.onExecute(ec_id);
    }
    //
    // The aborting action when main logic error occurred.
    // former rtc_aborting_entry()
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }
    //
    // The error action in ERROR state
    // former rtc_error_do()
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }
    //
    // The reset action that is invoked resetting
    // This is same but different the former rtc_init_entry()
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }
//  
    // The state update action that is invoked after onExecute() action
    // no corresponding operation exists in OpenRTm-aist-0.2.0
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }
    //
    // The action that is invoked when execution context's rate is changed
    // no corresponding operation exists in OpenRTm-aist-0.2.0
//    @Override
//    protected ReturnCode_t onRateChanged(int ec_id) {
//        return super.onRateChanged(ec_id);
//    }
//
    // DataInPort declaration
    // <rtc-template block="inport_declare">
    
    // </rtc-template>

    // DataOutPort declaration
    // <rtc-template block="outport_declare">
    
    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    protected CorbaPort m_MyServicePort;
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    protected CorbaConsumer<MyService> m_myservice0Base =
        new CorbaConsumer<MyService>(MyService.class);
    
    protected MyService m_myservice0;
    
    // </rtc-template>
}
