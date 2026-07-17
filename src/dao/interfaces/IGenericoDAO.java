package dao.interfaces;

import java.util.List;

public interface IGenericoDAO<T> {
    boolean crear(T t);
    boolean actualizar(T t);
    boolean eliminar(int id);
    T obtenerPorId(int id);
    List<T> listar();
}
