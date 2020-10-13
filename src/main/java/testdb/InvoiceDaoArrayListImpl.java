package testdb;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class InvoiceDaoArrayListImpl implements InvoiceDao{

    ArrayList <Invoice> invoices;

    public InvoiceDaoArrayListImpl() {
        this.invoices = new ArrayList<>();
        java.sql.Date date2 = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        this.invoices.add(new Invoice(1, date2, "Beschreibung 1", 1.0, true));
        this.invoices.add(new Invoice(2, date2, "Beschreibung 2", 2.0, false));
        this.invoices.add(new Invoice(3, date2, "Beschreibung 3", 3.0, true));
        this.invoices.add(new Invoice(4, date2, "Beschreibung 4", 4.0, false));
    }

    public ArrayList<Invoice> showAllInvoices(){
        return this.invoices;
    }

    //public void updateStudent(int id, Date date, String description, double value, boolean paid){
    //    invoices.set(1, "djd");
    //}

    public void deleteInvoice(int id){
        for (Invoice i2 : this.invoices)
        {
            if(i2.getId()==id)
            {
                this.invoices.remove(i2);
                return;
            }
        }
    }

    public void insertInvoice(Invoice i2){
        if(i2!=null){
            this.invoices.add(i2);
        }
    }
}
