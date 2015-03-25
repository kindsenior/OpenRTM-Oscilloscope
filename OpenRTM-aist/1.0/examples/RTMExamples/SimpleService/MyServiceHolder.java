package RTMExamples.SimpleService;

/**
* RTMExamples/SimpleService/MyServiceHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/MyService.idl
* 2010�N5��10�� 16��54��06�b JST
*/

public final class MyServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public RTMExamples.SimpleService.MyService value = null;

  public MyServiceHolder ()
  {
  }

  public MyServiceHolder (RTMExamples.SimpleService.MyService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTMExamples.SimpleService.MyServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTMExamples.SimpleService.MyServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTMExamples.SimpleService.MyServiceHelper.type ();
  }

}
