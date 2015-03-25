package RTMExamples.AutoTest;


/**
* RTMExamples/AutoTest/MyServiceOperations.java .
* IDL-to-Java コンパイラ (ポータブル), バージョン "3.1" で生成
* 生成元: src/RTMExamples/AutoTestService.idl
* 2010年5月10日 16時54分07秒 JST
*/

public interface MyServiceOperations 
{
  String echo (String msg);
  String[] get_echo_history ();
  void set_value (float value);
  float get_value ();
  float[] get_value_history ();
} // interface MyServiceOperations
