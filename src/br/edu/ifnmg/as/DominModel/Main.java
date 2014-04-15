
package br.edu.ifnmg.as.DominModel;

import br.edu.ifnmg.as.DataAccess.ProdutoDAO;
import java.math.BigDecimal;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        IProdutoRepositorio prodDAO = ProdutoDAO.getInstancia();
        
        Cliente cli = new Cliente();
        cli.setNome("Ari");
        cli.setRg("17.177.177");
        
        Produto prod = new Produto();
        prod.setNome("Notebook");
        prod.setPreco(new BigDecimal("160.00"));
        
        Produto prod2 = new Produto();
        prod2.setNome("Desktop");
        prod2.setPreco(new BigDecimal("120.00"));
        
        prodDAO.Salvar(prod2);
        
    }
    
}