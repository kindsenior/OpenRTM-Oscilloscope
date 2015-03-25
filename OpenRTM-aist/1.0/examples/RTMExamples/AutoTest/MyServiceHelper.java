package RTMExamples.AutoTest;


/**
* RTMExamples/AutoTest/MyServiceHelper.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/AutoTestService.idl
* 2010�N5��10�� 16��54��07�b JST
*/

abstract public class MyServiceHelper
{
  private static String  _id = "IDL:AutoTest/MyService:1.0";

  public static void insert (org.omg.CORBA.Any a, RTMExamples.AutoTest.MyService that)
  {
    org.omg.CORBA.portable.OutputStream out = a.create_output_stream ();
    a.type (type ());
    write (out, that);
    a.read_value (out.create_input_stream (), type ());
  }

  public static RTMExamples.AutoTest.MyService extract (org.omg.CORBA.Any a)
  {
    return read (a.create_input_stream ());
  }

  private static org.omg.CORBA.TypeCode __typeCode = null;
  synchronized public static org.omg.CORBA.TypeCode type ()
  {
    if (__typeCode == null)
    {
      __typeCode = org.omg.CORBA.ORB.init ().create_interface_tc (RTMExamples.AutoTest.MyServiceHelper.id (), "MyService");
    }
    return __typeCode;
  }

  public static String id ()
  {
    return _id;
  }

  public static RTMExamples.AutoTest.MyService read (org.omg.CORBA.portable.InputStream istream)
  {
    return narrow (istream.read_Object (_MyServiceStub.class));
  }

  public static void write (org.omg.CORBA.portable.OutputStream ostream, RTMExamples.AutoTest.MyService value)
  {
    ostream.write_Object ((org.omg.CORBA.Object) value);
  }

  public static RTMExamples.AutoTest.MyService narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTMExamples.AutoTest.MyService)
      return (RTMExamples.AutoTest.MyService)obj;
    else if (!obj._is_a (id ()))
      throw new org.omg.CORBA.BAD_PARAM ();
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTMExamples.AutoTest._MyServiceStub stub = new RTMExamples.AutoTest._MyServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

  public static RTMExamples.AutoTest.MyService unchecked_narrow (org.omg.CORBA.Object obj)
  {
    if (obj == null)
      return null;
    else if (obj instanceof RTMExamples.AutoTest.MyService)
      return (RTMExamples.AutoTest.MyService)obj;
    else
    {
      org.omg.CORBA.portable.Delegate delegate = ((org.omg.CORBA.portable.ObjectImpl)obj)._get_delegate ();
      RTMExamples.AutoTest._MyServiceStub stub = new RTMExamples.AutoTest._MyServiceStub ();
      stub._set_delegate(delegate);
      return stub;
    }
  }

}
