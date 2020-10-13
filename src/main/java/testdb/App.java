package testdb;

import java.sql.*;
import java.util.Calendar;

/**
 * Hello world!
 */
public class App {


    private static Connection con;

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
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        //insertInvoice(verbindungAufbauen(), date, "beschreibung!", 12345.1, true);

        verbindungAufbauen();
        //showInvoices();
        insertInvoice(date, "Beschreibung", 10.0, (byte) 1);

    }

    public static void verbindungAufbauen() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
        } catch (Exception e) {
            System.out.println("Verbindung konnte nicht hergestellt werden!");
        }
    }

    public static void verbindungClose(){
        if(con != null) {
            try {
                con.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        else{
            System.out.println("Keine Verbindung!");
        }
    }

    public static void showInvoices()
    {
        try{
        Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, idate, idescription, ivalue, ipaid from invoice");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " " +rs.getInt(4) + " " +rs.getString(5));
            verbindungClose();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void insertInvoice(Date date, String description, double value, Byte paid)
    {
        try {
            Statement mystmt = con.createStatement();
            String sql = "insert into invoice " + " (idate, idescription, ivalue, ipaid) " + " values ('"+date+"', '"+description+"', '"+value+"', '"+paid+"')";
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

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}