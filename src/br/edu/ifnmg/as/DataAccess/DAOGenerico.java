

package br.edu.ifnmg.as.DataAccess;

import br.edu.ifnmg.as.DominModel.IRepositorio;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;


public abstract class DAOGenerico<T> implements IRepositorio<T> {
    //Gerenciador de entidades
    protected EntityManager manager;
    private EntityManagerFactory factory;
    private Class tipo;
    
    public DAOGenerico(Class t){
        factory = Persistence.createEntityManagerFactory("UnidadePersistencia");
        manager = factory.createEntityManager();
        tipo = t;
    }
    
    //...................Método Salvar........................................//
    @Override
    public boolean Salvar(T obj){
        //Obtem a transaçao
        EntityTransaction transacao = manager.getTransaction();
        try{
            //Iniciar a transaçao
            transacao.begin();
            
            //Persiste o objeto
            manager.persist(obj);
            
            //Se tudo der certo, confirma a transaçao
            transacao.commit();
            
            return true;
        }
        catch(Exception ex){
            //Aconteceu algo de errado
            transacao.rollback();
            
            return false;
        }
    }
    
    //................Método Abrir............................................//
    @Override
    public T Abrir(long id){
        try{
            //persiste o objeto
            T obj = (T)manager.find(tipo, id);
            
            return obj;
        }
        catch(Exception ex){
            return null;
        }
    }
    
    //.................Método Excluir.........................................//
    @Override
    public boolean Excluir(T obj){
        //Obtem a transaçao
        EntityTransaction transacao = manager.getTransaction();
        
        try{
            transacao.begin();
            
            //Remove o objeto
            manager.remove(obj);
            
            //Se tudo ocorrer certo, confirma a transaçao
            transacao.commit();
            
            return true;
        }
        catch(Exception ex){
            //Se ocorrer algum problema
            transacao.rollback();
            return false;
        }
    }
    
}