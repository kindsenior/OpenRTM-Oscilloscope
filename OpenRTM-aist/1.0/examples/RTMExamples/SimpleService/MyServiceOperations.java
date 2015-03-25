package RTMExamples.SimpleService;


/**
* RTMExamples/SimpleService/MyServiceOperations.java .
* IDL-to-Java �R���p�C�� (�|�[�^�u��), �o�[�W���� "3.1" �Ő���
* ������: src/RTMExamples/MyService.idl
* 2010�N5��10�� 16��54��06�b JST
*/

public interface MyServiceOperations 
{
  String echo (String msg);
  String[] get_echo_history ();
  void set_value (float value);
  float get_value ();
  float[] get_value_history ();
} // interface MyServiceOperations
