package com.nwsuaf.rain;

/**
* com.nwsuaf.rain.SysPropPOA.java .
* ��IDL-to-Java ������ (����ֲ), �汾 "3.2"����
* ��DataService.idl
* 2017��3��31�� ������ ����05ʱ22��53�� CST
*/

public abstract class SysPropPOA extends org.omg.PortableServer.Servant
 implements SysPropOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("CreateTable", new Integer (0));
    _methods.put ("Insert", new Integer (1));
    _methods.put ("GetScore", new Integer (2));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    Integer __method = (Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // com.nwsuaf.rain.SysProp/CreateTable
       {
         String table_name = in.read_string ();
         this.CreateTable (table_name);
         out = $rh.createReply();
         break;
       }

       case 1:  // com.nwsuaf.rain.SysProp/Insert
       {
         String name = in.read_string ();
         int score = in.read_long ();
         this.Insert (name, score);
         out = $rh.createReply();
         break;
       }

       case 2:  // com.nwsuaf.rain.SysProp/GetScore
       {
         String name = in.read_string ();
         int $result = (int)0;
         $result = this.GetScore (name);
         out = $rh.createReply();
         out.write_long ($result);
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:com.nwsuaf.rain.SysProp:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public SysProp _this() 
  {
    return SysPropHelper.narrow(
    super._this_object());
  }

  public SysProp _this(org.omg.CORBA.ORB orb) 
  {
    return SysPropHelper.narrow(
    super._this_object(orb));
  }


} // class com.nwsuaf.rain.SysPropPOA
