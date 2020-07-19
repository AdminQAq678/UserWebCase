package dao;

import java.util.List;
import java.util.Map;

import domain.PageBean;
import domain.User;

public interface Userdao {
	public List<User> findAll();
	public User loginUser(User user);
	public void addUser(User user);
	public void delUser(int id) ;
	public User findUserById(int id);
	public void updateUser(User user);
	public List<User> findUserByPage(int start,int rows, Map<String, String[]> condition);
	public int findTotalCount(Map<String, String[]> condition);
}
