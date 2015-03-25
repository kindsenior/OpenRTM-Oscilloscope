// -*- Java -*-
/*!
 * @file  OscillatorImpl.java
 * @brief Oscillator Component
 * @date  $Date$
 *
 * $Id$
 */

import jp.go.aist.rtm.RTC.DataFlowComponentBase;
import jp.go.aist.rtm.RTC.Manager;
import jp.go.aist.rtm.RTC.port.InPort;
import jp.go.aist.rtm.RTC.port.OutPort;
import jp.go.aist.rtm.RTC.util.DataRef;
import RTC.ReturnCode_t;
import RTC.Time;
import RTC.TimedDouble;
import RTC.TimedFloat;
import RTC.TimedLong;
import RTC.TimedShort;

/*!
 * @class OscillatorImpl
 * @brief Oscillator Component
 *
 */
public class OscillatorImpl extends DataFlowComponentBase {
	
	BasicFrame frame;
	long time;

  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
	public OscillatorImpl(Manager manager) {  
        super(manager);
        // <rtc-template block="initializer">
        
        for(int i = 0; i < OscillatorComp.dataNumber; i++){
        	m_longOut_val[i] = new TimedLong(new RTC.Time(0, 0), 0);
        	m_shortOut_val[i] = new TimedShort(new RTC.Time(0, 0),  (short)0);
        	m_doubleOut_val[i] = new TimedDouble(new RTC.Time(0, 0), (double)0.0);
        	m_floatOut_val[i] = new TimedFloat();
        	
        	m_longOut[i] = new DataRef<TimedLong>(m_longOut_val[i]);
        	m_shortOut[i] = new DataRef<TimedShort>(m_shortOut_val[i]);
        	m_doubleOut[i] = new DataRef<TimedDouble>(m_doubleOut_val[i]);
        	m_floatOut[i] = new DataRef<TimedFloat>(m_floatOut_val[i]);
        	
            String str = "in" + i;
            m_longOutOut[i] = new OutPort<TimedLong>(str, m_longOut[i]);
            m_shortOutOut[i] = new OutPort<TimedShort>(str, m_shortOut[i]);
            m_doubleOutOut[i] = new OutPort<TimedDouble>(str, m_doubleOut[i]);
            m_floatOutOut[i] = new OutPort<TimedFloat>(str, m_floatOut[i]);
        } 

        // </rtc-template>

    }

    /**
     *
     * The initialize action (on CREATED->ALIVE transition)
     * formaer rtc_init_entry() 
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onInitialize() {
        // Registration: InPort/OutPort/Service
        // <rtc-template block="registration">
        
        // Set OutPort buffer
    	
    	String str;
    	
    	for(int i = 0; i < OscillatorComp.dataNumber; i++){
    		System.out.println(OscillatorComp.dataName[i]);
    		
    		switch(OscillatorComp.dataType[i]){
    		case 0:
    			str = "m_longIn" + i;
        		addOutPort(str, m_longOutOut[i]);
        		break;
    		case 1:
    			str = "m_shortIn" + i;
    			addOutPort(str, m_shortOutOut[i]);
    			break;
    		case 2:
    			str = "m_doubleIn" + i;
    			addOutPort(str, m_doubleOutOut[i]);
    			break;
    		case 3:
    			str = "m_floatIn" + i;
    			addOutPort(str, m_floatOutOut[i]);
    			break;
    		default:
    			System.out.println("選択されたデータ型はまだ実装されていません");
    		}		
    	}
        // </rtc-template>
        frame = new BasicFrame();
        frame.setVisible(true);
        return super.onInitialize();
    }

    /***
     *
     * The finalize action (on ALIVE->END transition)
     * formaer rtc_exiting_entry()
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onFinalize() {
        return super.onFinalize();
    }

    /***
     *
     * The startup action when ExecutionContext startup
     * former rtc_starting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStartup(int ec_id) {
//        return super.onStartup(ec_id);
//    }

    /***
     *
     * The shutdown action when ExecutionContext stop
     * former rtc_stopping_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onShutdown(int ec_id) {
//        return super.onShutdown(ec_id);
//    }

    /***
     *
     * The activated action (Active state entry action)
     * former rtc_active_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onActivated(int ec_id) {
    	time = 0;
        return super.onActivated(ec_id);
    }

    /***
     *
     * The deactivated action (Active state exit action)
     * former rtc_active_exit()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onDeactivated(int ec_id) {
        return super.onDeactivated(ec_id);
    }

    /***
     *
     * The execution action that is invoked periodically
     * former rtc_active_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
    @Override
    protected ReturnCode_t onExecute(int ec_id) {
    	
    	for (int i = 0; i < OscillatorComp.dataNumber; i++) {
			switch (OscillatorComp.dataType[i]) {
			case 0:
				m_longOut[i].v.data = (int)((Integer)BasicFrame.panel[i].jSpinner1.getValue() * Math.sin(Math.PI * (Double)BasicFrame.panel[i].jSpinner2.getValue() * time / get_context(ec_id).get_rate()));
				m_longOutOut[i].write();
				break;
			case 1:
				m_longOut[i].v.data = (short)((Short)BasicFrame.panel[i].jSpinner1.getValue() * Math.sin(Math.PI * (Double)BasicFrame.panel[i].jSpinner2.getValue() * time / get_context(ec_id).get_rate()));
				m_shortOutOut[i].write();
				break;
			case 2:
				m_doubleOut[i].v.data = (double)((Double)BasicFrame.panel[i].jSpinner1.getValue() * Math.sin(Math.PI * (Double)BasicFrame.panel[i].jSpinner2.getValue() * time / get_context(ec_id).get_rate()));
				m_doubleOutOut[i].write();
				break;
			case 3:
				m_floatOut[i].v.data = (float)((Float)BasicFrame.panel[i].jSpinner1.getValue() * Math.sin(Math.PI * (Double)BasicFrame.panel[i].jSpinner2.getValue() * time / get_context(ec_id).get_rate()));
				m_floatOutOut[i].write();
				break;
			default:
			}
		}
    	time++;
    	return super.onExecute(ec_id);
    }

    /***
     *
     * The aborting action when main logic error occurred.
     * former rtc_aborting_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//  @Override
//  public ReturnCode_t onAborting(int ec_id) {
//      return super.onAborting(ec_id);
//  }

    /***
     *
     * The error action in ERROR state
     * former rtc_error_do()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    public ReturnCode_t onError(int ec_id) {
//        return super.onError(ec_id);
//    }

    /***
     *
     * The reset action that is invoked resetting
     * This is same but different the former rtc_init_entry()
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onReset(int ec_id) {
//        return super.onReset(ec_id);
//    }

    /***
     *
     * The state update action that is invoked after onExecute() action
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
//    @Override
//    protected ReturnCode_t onStateUpdate(int ec_id) {
//        return super.onStateUpdate(ec_id);
//    }

    /***
     *
     * The action that is invoked when execution context's rate is changed
     * no corresponding operation exists in OpenRTm-aist-0.2.0
     *
     * @param ec_id target ExecutionContext Id
     *
     * @return RTC::ReturnCode_t
     * 
     * 
     */
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
    protected TimedLong[] m_longOut_val = new TimedLong[OscillatorComp.dataNumber];
    protected TimedShort[] m_shortOut_val = new TimedShort[OscillatorComp.dataNumber];
    protected TimedDouble[] m_doubleOut_val = new TimedDouble[OscillatorComp.dataNumber];
    protected TimedFloat[] m_floatOut_val = new TimedFloat[OscillatorComp.dataNumber];
    
    protected DataRef<TimedLong>[] m_longOut = new DataRef[OscillatorComp.dataNumber];
    protected DataRef<TimedShort>[] m_shortOut = new DataRef[OscillatorComp.dataNumber];
    protected DataRef<TimedDouble>[] m_doubleOut = new DataRef[OscillatorComp.dataNumber];
    protected DataRef<TimedFloat>[] m_floatOut = new DataRef[OscillatorComp.dataNumber];
    /*!
     */
    protected OutPort<TimedLong>[] m_longOutOut = new OutPort[OscillatorComp.dataNumber];
    protected OutPort<TimedShort>[] m_shortOutOut = new OutPort[OscillatorComp.dataNumber];
    protected OutPort<TimedDouble>[] m_doubleOutOut = new OutPort[OscillatorComp.dataNumber];
    protected OutPort<TimedFloat>[] m_floatOutOut = new OutPort[OscillatorComp.dataNumber];

    // </rtc-template>

    // CORBA Port declaration
    // <rtc-template block="corbaport_declare">
    
    // </rtc-template>

    // Service declaration
    // <rtc-template block="service_declare">
    
    // </rtc-template>

    // Consumer declaration
    // <rtc-template block="consumer_declare">
    
    // </rtc-template>


}
