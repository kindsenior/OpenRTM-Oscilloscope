package RTMExamples.AutoTest;


/**
* RTMExamples/AutoTest/MyServiceOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/AutoTestService.idl
* 2010�N5��10�� 16��54��07�b JST
*/

public interface MyServiceOperations 
{
  String echo (String msg);
  String[] get_echo_history ();
  void set_value (float value);
  float get_value ();
  float[] get_value_history ();
} // interface MyServiceOperations
