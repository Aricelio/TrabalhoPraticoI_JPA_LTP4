
package br.edu.ifnmg.as.DominModel;

import java.util.List;

/**
 *
 * @author celio
 */
public interface IRepositorio<T> {
    boolean Salvar(T obj);
    T Abrir(long id);
    boolean Excluir(T obj);
    List<T> Buscar(T filtro);
    
}