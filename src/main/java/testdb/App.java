package testdb;

import java.sql.*;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
/*        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, name, nachname from benutzer");
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("name") + " " + rs.getString("nachname"));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }

 */
        Date date = new Date();
        insertInvoice(verbindungAufbauen(), date, "beschreibung!", 12345.1, true);

        verbindungClose(verbindungAufbauen());

    }

    public static Connection verbindungAufbauen() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
        return con;
    }

    public static void verbindungClose(Connection con) throws SQLException {
        con.close();
    }

    public static void showInvoices(Connection con)
    {
        try{
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, name, nachname from benutzer");
            while (rs.next())
                System.out.println(rs.getInt("id") + "  " + rs.getString("name") + " " + rs.getString("nachname"));
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertInvoice(Connection con, Date date, String description, double value, Boolean paid)
    {
        try {


            Statement mystmt = con.createStatement();
            String sql = "insert into invoice "
                    + " (idate, idescription, ivalue, ipaid) "
                    + " values (date, description, value, paid)";
            mystmt.executeUpdate(sql);
            System.out.println("Insert erfolgreich!");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateInvoice(int id, Date date, String description, double value, Boolean paid)
    {

    }

    public static void deleteInvoice(int id)
    {

    }

}