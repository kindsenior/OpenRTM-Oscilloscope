package RTMExamples.AutoTest;


/**
* RTMExamples/AutoTest/EchoListHolder.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: src/RTMExamples/AutoTestService.idl
* 2010年5月10日 16時54分07秒 JST
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
    value = RTMExamples.AutoTest.EchoListHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    RTMExamples.AutoTest.EchoListHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return RTMExamples.AutoTest.EchoListHelper.type ();
  }

}
