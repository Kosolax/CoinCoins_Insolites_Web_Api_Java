package api.idataaccess;

import java.util.List;

public interface IBaseDataAccess<T> {
    public T create(T t);
    
    public Boolean delete(int id);
    
	public List<T> findAll();

    public T findById(Integer id);

    public T update(T t);
}
