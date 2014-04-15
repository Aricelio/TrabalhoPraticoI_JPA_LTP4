

package br.edu.ifnmg.as.DataAccess;

import br.edu.ifnmg.as.DominModel.IProdutoRepositorio;
import br.edu.ifnmg.as.DominModel.Produto;
import java.util.HashMap;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author celio
 */
public class ProdutoDAO extends DAOGenerico<Produto> implements IProdutoRepositorio {

    private static IProdutoRepositorio instancia;
    public static IProdutoRepositorio getInstancia(){
        if(instancia == null)
            instancia = new ProdutoDAO();
        return instancia;
    }
    
    private ProdutoDAO(){
        super(Produto.class);
    }
    
    @Override
    public List<Produto> Buscar(Produto obj){
        String sql = "select o from produto o";
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
