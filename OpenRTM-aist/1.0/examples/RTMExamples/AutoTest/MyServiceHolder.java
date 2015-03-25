package RTMExamples.AutoTest;

/**
* RTMExamples/AutoTest/MyServiceHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/AutoTestService.idl
* 2010�N5��10�� 16��54��07�b JST
*/

public final class MyServiceHolder implements org.omg.CORBA.portable.Streamable
{
  public RTMExamples.AutoTest.MyService value = null;

  public MyServiceHolder ()
  {
  }

  public MyServiceHolder (RTMExamples.AutoTest.MyService initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTMExamples.AutoTest.MyServiceHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTMExamples.AutoTest.MyServiceHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTMExamples.AutoTest.MyServiceHelper.type ();
  }

}
