package RTMExamples.SimpleService;


/**
* RTMExamples/SimpleService/EchoListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/MyService.idl
* 2010�N5��10�� 16��54��06�b JST
*/

public final class EchoListHolder implements org.omg.CORBA.portable.Streamable
{
  public String value[] = null;

  public EchoListHolder ()
  {
  }

  public EchoListHolder (String[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTMExamples.SimpleService.EchoListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTMExamples.SimpleService.EchoListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTMExamples.SimpleService.EchoListHelper.type ();
  }

}
