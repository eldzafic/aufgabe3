package testdb;

import java.sql.*;
import java.util.ArrayList;

public class SQLInvoiceDaoIImpl implements InvoiceDao{

    private static Connection con;

    public void verbindungAufbauen() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/test", "root", "");
        } catch (Exception e) {
            System.out.println("Verbindung konnte nicht hergestellt werden!");
        }
    }

    public void verbindungClose(){
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

    public void showInvoicesSQL()
    {
        verbindungAufbauen();
        try{
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select id, idate, idescription, ivalue, ipaid from invoice");
            while (rs.next())
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + " " + rs.getString(3) + " " +rs.getInt(4) + " " +rs.getString(5));
            verbindungClose();
        } catch (Exception e) {
            System.out.println(e);
        }
        verbindungClose();
    }

    public void insertInvoiceSQL(Date date, String description, double value, Byte paid)
    {
        verbindungAufbauen();
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
        verbindungClose();
    }

    public void updateInvoiceSQL(int id, Date date, String description, double value, Boolean paid)
    {
        verbindungAufbauen();
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
        verbindungClose();
    }

    public void deleteInvoiceSQL(int id)
    {
        verbindungAufbauen();
        try {
            PreparedStatement myStmt = con.prepareStatement("delete from invoice where id = ?");
            myStmt.setInt(1, id);
            myStmt.executeUpdate();
            System.out.println("Eintrag mit der ID " + id + " wurde geloescht!");
        }catch (Exception e){
            System.out.println(e);
        }
        verbindungClose();
    }

    @Override
    public ArrayList<Invoice> showAllInvoices() {
        return null;
    }

    @Override
    public void deleteInvoice(int id) {

    }

    @Override
    public void insertInvoice(Invoice i2) {

    }

    @Override
    public void updateInvoice(int id, Invoice i) {

    }
}
