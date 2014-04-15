

package br.edu.ifnmg.as.DataAccess;

import br.edu.ifnmg.as.DominModel.Cliente;
import br.edu.ifnmg.as.DominModel.IClienteRepositorio;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author celio
 */
public class ClienteDAO extends DAOGenerico<Cliente> implements IClienteRepositorio {

    private static IClienteRepositorio instancia;
    public static IClienteRepositorio getInstancia(){
        if(instancia == null)
            instancia = new ClienteDAO();
        return instancia;
    }
    
    private ClienteDAO(){
        super(Cliente.class);
    }
    
    @Override
    public List<Cliente> Buscar(Cliente obj){
        String sql = "select o from cliente o";
        String where = "";
        HashMap<String, Object> parametros = new HashMap<>();
        if(obj != null){
            if(obj.getId() != null){
                where += "o.id = :id";
                parametros.put("id", obj.getId());
            }
        }
        
        //Se houver filtros,coloca o "where" na consulta
        if(where.length() > 0)
            sql = sql + " where " + where;
        
        //Cria a consulta no JPA
        Query query = manager.createQuery(sql);
        
        //Aplica os parametros
        for(String par : parametros.keySet()){
            query.setParameter(par, parametros.get(par));
        }
        
        //Executa a consulta
        return query.getResultList();
    }
}