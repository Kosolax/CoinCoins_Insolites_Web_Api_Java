package api.ibusiness;

import java.util.List;
import java.util.Map;

public interface IBaseBusiness<T> {
	public Map<Boolean, T> create(T t);
    
    public Boolean delete(T t);
    
	public Map<Boolean, List<T>> findAll();

    public Map<Boolean, T> findById(Integer id);

    public Map<Boolean, T> update(T t);
}
