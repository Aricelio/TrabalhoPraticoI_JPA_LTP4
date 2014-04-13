
package br.edu.ifnmg.as.DominModel;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        EntityManagerFactory factory =Persistence.createEntityManagerFactory("UnidadePersistencia");
        EntityManager manager = factory.createEntityManager();
        
        Disciplina as = new Disciplina();
        as.setNome("Informatica e Sociedade");
        as.setCargaHoraria(78);
        
        Cliente cli = new Cliente();
        cli.setNome("Ari");
        cli.setRg("17.177.177");
        
        
        EntityTransaction t = manager.getTransaction();
        
        /*t.begin();
        
        try{
            manager.persist(cli);
            t.commit();
        }
        catch(Exception ex){
            t.rollback();
            ex.printStackTrace();
        }
        
        Query consulta = manager.createQuery("select o from Cliente o ",Cliente.class);
        
        List<Cliente> lista = consulta.getResultList();
        
        for(Cliente d : lista)
            System.out.println(d.getNome());*/
    }
    
}