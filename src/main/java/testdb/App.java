package testdb;



import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Hello world!
 */
public class App {


    private static Connection con;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());

        //************************************************************Aufgabe 3******************************************************************
        //verbindungAufbauen();
        //showInvoices();
        //insertInvoice(date, "Beschreibung1234", 5100.0, (byte) 0);
        //deleteInvoice(15);
        //updateInvoice(16, date, "abcd", 9999.0, true);

        //************************************************************Aufgabe 4******************************************************************
        /*
        InvoiceDao dao = new InvoiceDaoArrayListImpl();

        ArrayList<Invoice> liste = dao.showAllInvoices();
        for(Invoice i : liste)
        {
            System.out.println(i);
        }

        dao.updateInvoice(1, new Invoice(2, date, "Beschreinung neu", 500.0, true));

        dao.deleteInvoice(3);

        dao.insertInvoice(new Invoice(6, date, "Beschreibung neu", 9999.0, true));


        for(Invoice i : liste)
        {
            System.out.println(i);
        }
         */

        //************************************************************Aufgabe 5******************************************************************
        /*
        InvoiceDao dao = new SQLInvoiceDaoIImpl();
        dao.showAllInvoices();

        dao.insertInvoiceSQL(date, "Beschreinung SQL", 433.0, (byte) 1);
        dao.insertInvoiceSQL(date, "Beschreinung SQL", 533.0, (byte) 1);

        dao.updateInvoiceSQL(17, date, "Beschreibung update", 500.0, true);

        dao.deleteInvoiceSQL(18);

        dao.insertInvoiceSQL(date, "Beschreinung SQL", 333.0, (byte) 1);
         */
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
            PreparedStatement myStmt = con.prepareStatement("insert into invoice (idate, idescription, ivalue, ipaid) values (?,?,?,?)");
            myStmt.setDate(1, date);
            myStmt.setString(2, description);
            myStmt.setDouble(3, value);
            myStmt.setByte(4, paid);
            myStmt.executeUpdate();
            System.out.println("Insert erfolgreich!");
        }catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void updateInvoice(int id, Date date, String description, double value, Boolean paid)
    {
       try{

           PreparedStatement myStmt = con.prepareStatement("update invoice set idate = ?, idescription = ?, ivalue = ?, ipaid = ? where id = ?");
           myStmt.setDate(1, date);
           myStmt.setString(2, description);
           myStmt.setDouble(3, value);
           myStmt.setBoolean(4, paid);
           myStmt.setInt(5, id);
           myStmt.executeUpdate();
           System.out.println("Eintrag für ID "+id +" wurde geaendert!" );
       } catch (Exception e){
            System.out.println("Update fehlgeschlagen!");
       }
    }

    public static void deleteInvoice(int id)
    {
        try {
            PreparedStatement myStmt = con.prepareStatement("delete from invoice where id = ?");
            myStmt.setInt(1, id);
            myStmt.executeUpdate();
            System.out.println("Eintrag mit der ID " + id + " wurde geloescht!");
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }

}