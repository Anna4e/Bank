package annaBank;

import org.hibernate.annotations.Entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Anna on 12.02.2017.
 */

@Entity
@Table
public class Users {
    @OneToMany(fetch = FetchType.LAZY)
    @Column(name = "id")
    private List<Counts> counts = new ArrayList<>();


    public List<Counts> getCounts() {
        return Collections.unmodifiableList(counts);
    }

    @Id
    @Column(name = "id", nullable = false)
   private int id;
 @Column(name = "name", nullable = false)
 private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Counts count;


    private Users() {
    }

    public Users(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Counts getCount() {
        return count;
    }

    public void setCount(Counts count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "" + getId() +", " + getName();
    }

}
