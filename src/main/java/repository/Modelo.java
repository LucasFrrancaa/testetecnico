package repository;

import java.util.List;

public interface Modelo <T>{
    public void incluir(T obj);
    public void alterar(T obj);
    public void exclui(T obj);
    public List<T> listar();
}
