package annaBank;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Anna on 11.02.2017.
 */
@Entity
@Table
@NamedQuery(name = "Krval.getValues", query = "SELECT USD,EUR FROM Krval c WHERE date = current_date")
public class Krval {
    @Id
    @Column(nullable = false)
    private Date date;

    @Column(name = "USD", nullable = false)
    private double USD;

    @Column(name = "EUR", nullable = false)
    private double EUR;

    @Column(name = "UAH", nullable = false)
    private double UAH;


    public Krval() {
    }

    public Krval(Date date, double USD, double EUR, double UAH) {
        this.date = date;
        this.USD = USD;
        this.EUR = EUR;
        this.UAH = UAH;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public double getUSD() {
        return USD;
    }

    public void setUSD(double USD) {
        this.USD = USD;
    }

    public double getEUR() {
        return EUR;
    }

    public void setEUR(double EUR) {
        this.EUR = EUR;
    }

    public double getUAH() {
        return UAH;
    }

    public void setUAH(double UAH) {
        this.UAH = UAH;
    }
    @Override
    public String toString () {
        return  "" + getDate()+
                "" + getEUR()+
                "" + getUSD();
    }
}
