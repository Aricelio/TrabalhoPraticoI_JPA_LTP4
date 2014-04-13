
package br.edu.ifnmg.as.DataAccess;

import br.edu.ifnmg.as.DominModel.IVendaRepositorio;
import br.edu.ifnmg.as.DominModel.Venda;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author celio
 */
public class VendaDAO extends DAOGenerico<Venda> implements IVendaRepositorio{
    
    
    private static IVendaRepositorio instancia;
    public static IVendaRepositorio getInstancia(){
        if(instancia == null)
            instancia = new VendaDAO();
        return instancia;
    }
    
    private VendaDAO(){
        super(Venda.class);
    }
    
    @Override
    public List<Venda> Buscar(Venda obj){
        String sql = "select o from venda o";
        String where = "";
        HashMap<String, Object> parametros = new HashMap<>();
        if(obj != null){
            if(obj.getId() != null){
                where += "o.id = :id";
                parametros.put("id", obj.getId());
            }
            if(obj.getCliente() != null){
                if(where.length() > 0)
                    where += " and ";
                
                where += "o.cliente = :cliente";
                parametros.put("cliente", obj.getCliente());
            }
            if(obj.getDataVenda() != null){
                if(where.length() > 0)
                    where += " and ";
                
                where += "o.data = :data";
                parametros.put("data", obj.getDataVenda());
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