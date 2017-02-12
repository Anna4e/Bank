package annaBank;

import org.hibernate.annotations.Entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Created by Anna on 11.02.2017.
 */
@Entity
@Table
@NamedQuery(name = "Counts.byId", query = "SELECT c FROM Counts c WHERE id = :id")

public class Counts {
    @Column(nullable = false)
    private int id;

    @Id
    @Column(nullable = false)
    private int count;

    @Column
    private double sum;

    @Column
    private String val;

    public Counts() {
    }

    public Counts(int id, int count, double sum, String val) {
        this.id = id;
        this.count = count;
        this.sum = sum;
        this.val = val;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Override
    public String toString() {
        return "" + getId() +", " + getCount() + ", " + getSum() + ", " + getVal() ;
    }
}
