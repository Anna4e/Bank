package annaBank;

import org.hibernate.cfg.beanvalidation.GroupsPerOperation;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

/**
 * Created by Anna on 11.02.2017.
 */
public class Main {
    static EntityManagerFactory emf;
    static EntityManager em;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        try {
            emf = Persistence.createEntityManagerFactory("Bank");
            em = emf.createEntityManager();
            try{
                System.out.println("Choose the operation:");

                System.out.println("1 - add funds");
                System.out.println("2 - money transaction");
                System.out.println("3 - conversion");
                System.out.println("4 - summation means");

               String string = scan.next();
               switch (string){
                   case "1":
                       ProcesManager.addFunds();
                       break;
                   case "2":
                       ProcesManager.moneyTransaction();
                       break;
                   case "3":
                       ProcesManager.conversion();
                       break;
                   case "4":
                       ProcesManager.summation();
                       break;

               }

            }catch (Exception ex){
                ex.printStackTrace();
                return;
            }

        } finally {
            emf.close();
            em.close();
            scan.close();
        }
    }
}