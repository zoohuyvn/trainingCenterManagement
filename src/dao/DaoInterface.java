package dao;
import java.util.ArrayList;

/**
 * @author zoohuy
 *
 * @param <O> 28 thg 12, 2023
 */

public interface DaoInterface<O> {
	
	public O read(O x);
	
	public ArrayList<O> readAll();
	
	public int create(O x);
	
	public int update(O x);
	
	public int delete(O x);
	
}