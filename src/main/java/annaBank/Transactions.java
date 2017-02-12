package annaBank;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anna on 11.02.2017.
 */
@Entity
@Table
public class Transactions {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;
    @Column(nullable = false)
    private int transaction;
    @Column(nullable = false)
    private int count;
    @Column
    private double sum;
    @Column
    private String val;
     @Column
    private Date date;



    public Transactions(int id, int transaction, int count, double sum, String val, Date date) {
        this.transaction = transaction;
        this.count = count;
        this.sum = sum;
        this.val = val;
        this.date = date;
        this.id = id;
    }

       public Transactions(Counts count, double sum) {
    }


    public Transactions(int from, int to, double sum) {
    }


    public int getTransaction() {
        return transaction;
    }

    public void setTransaction(int transaction) {
        this.transaction = transaction;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Transaction{" +
                "" + getTransaction()+
                "" + getCount()+
                "" + getSum() +
                ", " + getVal() +
                ", " + getDate() +
                '}';
    }
}
