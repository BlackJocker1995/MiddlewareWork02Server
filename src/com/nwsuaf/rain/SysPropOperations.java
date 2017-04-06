package com.nwsuaf.rain;

/**
* com.nwsuaf.rain.SysPropOperations.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��DataService.idl
* 2017��3��31�� ������ ����05ʱ22��53�� CST
*/

public interface SysPropOperations 
{
  void CreateTable(String table_name);
  void Insert(String name, int score);
  int GetScore(String name);
} // interface com.nwsuaf.rain.SysPropOperations
