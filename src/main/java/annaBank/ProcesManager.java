package annaBank;

import javax.persistence.PersistenceException;
import javax.persistence.Query;
import javax.transaction.Transaction;
import java.util.List;
import java.util.Scanner;

import static annaBank.Main.em;
import static jdk.nashorn.internal.runtime.PropertyMap.getCount;

/**
 * Created by Anna on 11.02.2017.
 */
public class ProcesManager {
    static Scanner scanner = new Scanner(System.in);
    static int id;
    static int count1;
    static String val = "UAH";
    public void addFunds() {
        getUserInfo();
        System.out.println("Enter the amount of deposit:");
        double sum = Double.parseDouble(scanner.next());
        List<Counts> counts;
        try {
            Query query = em.createQuery("Counts.byId", Counts.class);
            query.setParameter("id", id);
            counts = (List<Counts>) query.getResultList();

            if (counts.isEmpty()) {
                System.out.println("counts not found!");
                return;
            } else if (counts.size() > 1) {
                for (Counts str : counts) {
                    System.out.println(str.getCount());
                }
                System.out.println("Enter count:");
                count1 = Integer.parseInt(scanner.next());
                query = em.createQuery("SELECT c.count FROM Counts c WHERE c.id = :id and c.count = :count1", Counts.class);
                query.setParameter("id", id);
                query.setParameter("count1", count1);
                counts = query.getResultList();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }

        em.clear();
        em.getTransaction().begin();
        try { Counts count = getCount(count1);
              credit(count, sum);
            // operation
            Transactions t = new Transactions(count, sum);
            em.persist(t);
            em.getTransaction().commit();

        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }
    }



    public static void moneyTransaction() {
        System.out.println("Enter your count:");
        int from = Integer.parseInt(scanner.next());
        System.out.println("Enter count of payee:");
        int to = Integer.parseInt(scanner.next());
        System.out.println("Enter the transfer amount:");
        double amount = Double.parseDouble(scanner.next());
        em.clear();
        em.getTransaction().begin();
        try {

            if ( getCount(from).getVal() == getCount(to).getVal()) {

                debit(getCount(from), amount);
                credit(getCount(to), amount);
            }else {
               System.out.println("Different account currencies!");
               return;
            }
            Transactions t = new Transactions(from, to, amount);
            em.persist(t);
            em.getTransaction().commit();
        } catch (PersistenceException ex) {
            em.getTransaction().rollback();
            throw ex;
        }

    }

    public static void conversion() {
        System.out.println("Enter your count:");
        count1 = Integer.parseInt(scanner.next());
        Counts count = getCount(count1);
        val = count.getVal();
        double sum = count.getSum();
        Query query = em.createNamedQuery("Krval.getValues", Krval.class);
        Krval krval = (Krval)query.getSingleResult();
        if (val.equals("UAH")){
            System.out.println("Count in UAH = " + sum);
            System.out.println("Count in USD = " + sum / krval.getUSD());
            System.out.println("Count in EUR = " + sum / krval.getEUR());
        }else if(val.equals("EUR")){
            System.out.println("Count in UAH = " + sum * krval.getEUR());
            System.out.println("Count in USD = " + sum * krval.getEUR() / krval.getUSD());
            System.out.println("Count in EUR = " + sum);
        }else if(val.equals("USD")){
        System.out.println("Count in UAH = " + sum * krval.getUSD());
        System.out.println("Count in USD = " + sum);
        System.out.println("Count in EUR = " + sum * krval.getUSD() / krval.getEUR());
    }

        }

    public static Counts getCount(int id) throws PersistenceException {
        return em.find(Counts.class, id);
    }
    public static void summation(int id, String val) {
        getUserInfo();
        double Summa = 0;
        em.clear();
        Query query = em.createNamedQuery("Counts.byId", Counts.class);
        query.setParameter("id", id);
        List<Counts> counts = query.getResultList();
         for (Counts count1 : counts) {
            double summ = count1.getSum();
            if (summ > 0) {
                if (count1.getVal().equals(val)) {
                   Summa += summ;
                } else {
                        query = em.createNamedQuery("Krval.getValues", Krval.class);
                        Krval krval = (Krval)query.getSingleResult();

                        if (count1.getVal().equals("USD")){
                            Summa += summ  / krval.getUSD();
                        }else if(count1.getVal().equals("EUR")){
                            Summa += summ / krval.getEUR();
                        }


                }
            }
        }
       System.out.println("Your summary balance is:" + Summa);
    }
    private static void getUserInfo() {
        System.out.println("Enter your name:");
        String name = scanner.next();
        Users c = null;

        try {
            Query query = em.createQuery("SELECT c FROM Users c WHERE c.name = :name ", Users.class);
            query.setParameter("name", name);
            c = (Users) query.getSingleResult();
            id = c.getId();
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }

    private static void debit(Counts count, double sum) throws PersistenceException {
        if (count.getSum() < sum)
            throw new PersistenceException("Insufficient balance");
        count.setSum(count.getSum() - sum);
    }

    private static void credit(Counts count, double sum) {
        count.setSum(count.getSum() + sum);
    }

}
