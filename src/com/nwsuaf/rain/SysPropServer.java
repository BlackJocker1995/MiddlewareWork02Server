package com.nwsuaf.rain;
import org.omg.CosNaming.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

class SysPropImpl extends SysPropPOA {
    private ORB orb;
    public void setOrb(ORB orb_val)
    {
        this.orb = orb_val;
    }
    @Override
    public void CreateTable(String table_name) {
        DBmanager.CreateTable(table_name);
    }

    @Override
    public void Insert(String name, int score) {
        DBmanager.Insert(name,score);
    }

    @Override
    public int GetScore(String name) {
        return DBmanager.Search(name);
    }
}
public class SysPropServer // 起动服务的程序
{
    public static void main(String args[]) {
        try{
            System.out.println("创建和初始化 ORB ");

            ORB orb = ORB.init(args, null);

            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));

            rootpoa.the_POAManager().activate();

            System.out.println("创建服务对象并将其向 ORB 注册 ");
            SysPropImpl sysProImpl = new SysPropImpl();

            sysProImpl.setOrb(orb);

            //System.out.println(orb.object_to_string(sysProImpl));

            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
            NameComponent[] path = {new NameComponent("SysProp", "")};

            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(sysProImpl);
            SysProp href = SysPropHelper.narrow(ref);
            System.out.println(orb.object_to_string(href));

            System.out.println(ncRef.getClass().toString());
            ncRef.rebind(path, href);

            System.out.println("SysPropServer ready and waiting ...");

            orb.run();

        } catch (Exception e) {
            System.err.println("Error: " + e);
            e.printStackTrace(System.out);
        }

    }
}
