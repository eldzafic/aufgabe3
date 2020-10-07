package testdb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test","root","");
            Statement stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery("select id, name, nachname from benutzer");
            while(rs.next())
                System.out.println(rs.getInt("id")+"  "+rs.getString("name") +" "+rs.getString("nachname"));
            con.close();
        }catch(Exception e){ System.out.println(e);}
    }
}