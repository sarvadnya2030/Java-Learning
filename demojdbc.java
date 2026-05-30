import java.sql.*;
public class demojdbc {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {

        /*
        import pakg
        load and register
        create connection
        create statement
        execure statement
        process results
        close
         */

        String url="jdbc:postgresql://localhost:5432/demo";
        String uname="postgres";
        String pass="MyNewPassword";
        String sql1="select sname from public.\"Student\" where sid = 1";
        String sql2="select * from public.\"Student\" ";
        String sql3="insert into  public.\"Student\" values(6,'Ram', 100)";
        String sql4="update public.\"Student\" set sname='Laxman' where sid= 2";
        String sql5="delete from public.\"Student\" where sid =5";
        Class.forName("org.postgresql.Driver");
        Connection con=DriverManager.getConnection( url,uname,pass);
        System.out.println("Connection Established");
        Statement st = con.createStatement();
        boolean status1=st.execute(sql3);
        System.out.println(status1);

        boolean status2=st.execute(sql4);
        System.out.println(status2);

        boolean status3=st.execute(sql5);
        System.out.println(status3);

        ResultSet rs1 =st.executeQuery(sql1);
        ResultSet rs2 =st.executeQuery(sql2);

       // System.out.println(rs.next());
       // System.out.println(rs.getString("sname"));

        //while(rs2.next()){

            //System.out.print(rs2.getInt(1)+" - ");
            //System.out.print(rs2.getString(2)+" - ");
           // System.out.println(rs2.getInt(3));


        //}

        con.close();
        System.out.println("connection closed");


    }
}
