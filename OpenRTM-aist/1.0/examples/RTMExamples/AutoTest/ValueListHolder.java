package RTMExamples.AutoTest;


/**
* RTMExamples/AutoTest/ValueListHolder.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/AutoTestService.idl
* 2010�N5��10�� 16��54��07�b JST
*/

public final class ValueListHolder implements org.omg.CORBA.portable.Streamable
{
  public float value[] = null;

  public ValueListHolder ()
  {
  }

  public ValueListHolder (float[] initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = RTMExamples.AutoTest.ValueListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTMExamples.AutoTest.ValueListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTMExamples.AutoTest.ValueListHelper.type ();
  }

}
