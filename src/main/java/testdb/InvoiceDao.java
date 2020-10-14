package testdb;

import java.sql.*;
import java.util.ArrayList;

public interface InvoiceDao {
    ArrayList<Invoice> showAllInvoices();
    void deleteInvoice(int id);
    void insertInvoice(Invoice i2);
    void updateInvoice(int id, Invoice i);
    void showInvoicesSQL();
    void insertInvoiceSQL(Date date, String description, double value, Byte paid);
    void updateInvoiceSQL(int id, Date date, String description, double value, Boolean paid);
    void deleteInvoiceSQL(int id);

}
