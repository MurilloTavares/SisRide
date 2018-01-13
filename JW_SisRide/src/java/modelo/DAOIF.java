package modelo;

import java.sql.SQLException;

public interface DAOIF<E> {
    
    public void add(E e) throws SQLException;
    public void update(E e, E novo) throws SQLException;
    public void delete(E e) throws SQLException;
    
}
