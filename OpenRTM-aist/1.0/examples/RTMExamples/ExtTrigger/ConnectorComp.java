package RTMExamples.ExtTrigger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import jp.go.aist.rtm.RTC.CorbaNaming;
import jp.go.aist.rtm.RTC.port.CorbaConsumer;
import jp.go.aist.rtm.RTC.util.CORBA_SeqUtil;
import jp.go.aist.rtm.RTC.util.NVUtil;
import jp.go.aist.rtm.RTC.util.ORBUtil;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.NamingContextPackage.CannotProceed;
import org.omg.CosNaming.NamingContextPackage.InvalidName;
import org.omg.CosNaming.NamingContextPackage.NotFound;

import _SDOPackage.NVListHolder;
import _SDOPackage.NameValue;

import RTC.ConnectorProfile;
import RTC.ConnectorProfileHolder;
import OpenRTM.DataFlowComponent;
import RTC.ExecutionContext;
import RTC.ExecutionContextListHolder;
import OpenRTM.ExtTrigExecutionContextService;
import RTC.PortService;
import RTC.PortServiceListHolder;
import RTC.RTObject;
import RTC.ReturnCode_t;


public class ConnectorComp {

    public static void main(String[] args) {
        
        String subs_type = "";
        String period = "";
        for( int intIdx=1;intIdx<args.length;++intIdx ) {
            String arg = new String(args[intIdx]);
            if( arg.equals("--flush") ) {
                subs_type = "Flush";
            } else if( arg.equals("--new") ) {
                subs_type = "New";
            } else if (arg.equals("--periodic") ) {
                subs_type = "Periodic";
                if( ++intIdx<args.length) {
                    period = args[intIdx];
                } else {
                    period = "1.0";
                }
            } else if( arg.equals("--help") ) {
                usage();
            } else {
                subs_type = "Flush";
            }
        }

        System.out.println( "Subscription Type: "  + subs_type );
        if( !period.equals("") ) {
            System.out.println( "Period: "  + period + " [Hz]" );
        }
        
        ORB orb = ORBUtil.getOrb(args);
        CorbaNaming naming = null;
        try {
            naming = new CorbaNaming(orb, "localhost:2809");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        CorbaConsumer<DataFlowComponent> conin = new CorbaConsumer<DataFlowComponent>(DataFlowComponent.class);
        CorbaConsumer<DataFlowComponent> conout = new CorbaConsumer<DataFlowComponent>(DataFlowComponent.class);
        CorbaConsumer<ExtTrigExecutionContextService> ec0 = new CorbaConsumer<ExtTrigExecutionContextService>(ExtTrigExecutionContextService.class);
        CorbaConsumer<ExtTrigExecutionContextService> ec1 = new CorbaConsumer<ExtTrigExecutionContextService>(ExtTrigExecutionContextService.class);
        PortServiceListHolder pin = new PortServiceListHolder();
        pin.value = new PortService[0];
        PortServiceListHolder pout = new PortServiceListHolder();
        pout.value = new PortService[0];

        // find ConsoleIn0 component
        try {
            conin.setObject(naming.resolve("ConsoleIn0.rtc"));
        } catch (NotFound e) {
            e.printStackTrace();
        } catch (CannotProceed e) {
            e.printStackTrace();
        } catch (InvalidName e) {
            e.printStackTrace();
        }

        // get ports
        pin.value = conin._ptr().get_ports();
        pin.value[0].disconnect_all();
        assert(pin.value.length > 0);
        // activate ConsoleIn0
        ExecutionContextListHolder eclisti = new ExecutionContextListHolder();
        eclisti.value = new ExecutionContext[0];
        RTObject coninRef = conin._ptr();
        eclisti.value =  coninRef.get_owned_contexts();
        eclisti.value[0].activate_component(coninRef);
        ec0.setObject(eclisti.value[0]);
        ExtTrigExecutionContextService ec0Ref = ec0._ptr();

        // find ConsoleOut0 component
        try {
            conout.setObject(naming.resolve("ConsoleOut0.rtc"));
        } catch (NotFound e) {
            e.printStackTrace();
        } catch (CannotProceed e) {
            e.printStackTrace();
        } catch (InvalidName e) {
            e.printStackTrace();
        }
        // get ports
        pout.value = conout._ptr().get_ports();
        pout.value[0].disconnect_all();
        assert(pout.value.length > 0);
        // activate ConsoleOut0
        ExecutionContextListHolder eclisto = new ExecutionContextListHolder();
        eclisto.value = new ExecutionContext[0];
        RTObject conoutRef = conout._ptr();
        eclisto.value =  conoutRef.get_owned_contexts();
        eclisto.value[0].activate_component(conoutRef);
        ec1.setObject(eclisto.value[0]);
        ExtTrigExecutionContextService ec1Ref = ec1._ptr();
        
        // connect ports
        ConnectorProfile prof = new ConnectorProfile();
        prof.connector_id = "";
        prof.name = "connector0";
        prof.ports = new PortService[2];
        prof.ports[0] = pin.value[0];
        prof.ports[1] = pout.value[0];
        NVListHolder nvholder = new NVListHolder();
        nvholder.value = prof.properties;
        if( nvholder.value==null ) nvholder.value = new NameValue[0];
        CORBA_SeqUtil.push_back(nvholder, NVUtil.newNV("dataport.interface_type","CORBA_Any", String.class));
        CORBA_SeqUtil.push_back(nvholder, NVUtil.newNV("dataport.dataflow_type", "Push", String.class));
        CORBA_SeqUtil.push_back(nvholder, NVUtil.newNV("dataport.subscription_type", subs_type, String.class));
        
        if( !period.equals("") )
            CORBA_SeqUtil.push_back(nvholder, NVUtil.newNV("dataport.push_interval", period, String.class));
        prof.properties = nvholder.value;
        
        ConnectorProfileHolder proflist = new ConnectorProfileHolder();
        proflist.value = prof; 

        ReturnCode_t ret = pin.value[0].connect(proflist);
        assert(ret == ReturnCode_t.RTC_OK);

        System.out.println( "Connector ID: " + prof.connector_id );
        NVUtil.dump(new NVListHolder(proflist.value.properties));

        String cmd = "";
        while( true ) {
            try {
                System.out.println("");
                System.out.println("");
                System.out.println("0: tick ConsoleIn component");
                System.out.println("1: tick ConsoleOut component");
                System.out.println("2: tick both components" );
                System.out.println("cmd? >");
                BufferedReader buff = new BufferedReader(new InputStreamReader( System.in ));
                try {
                    cmd = buff.readLine();
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if( cmd.equals("0") ) {
                    ec0Ref.tick();
                } else if( cmd.equals("1") ) {
                    ec1Ref.tick();
                } else if( cmd.equals("2") ) { 
                    ec0Ref.tick();
                    ec1Ref.tick();
                }
            } catch(Exception ex) {
            }
        }
//        orb.destroy();
    }

    private static void usage() {
        System.out.println("");
        System.out.println( "usage: ConnectorComp [options]" );
        System.out.println("");
        System.out.println( "  --flush         " );
        System.out.println( ": Set subscription type Flush" );
        System.out.println( "  --new           " );
        System.out.println( ": Set subscription type New" );
        System.out.println( "  --periodic [Hz] " );
        System.out.println( ": Set subscription type Periodic" ); 
        System.out.println("");
    }

}
