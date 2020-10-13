package testdb;

import java.util.ArrayList;

public interface InvoiceDao {
    ArrayList<Invoice> showAllInvoices();
    void deleteInvoice(int id);
    void insertInvoice(Invoice i2);
}
