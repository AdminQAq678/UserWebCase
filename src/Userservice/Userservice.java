package Userservice;

import java.util.List;
import java.util.Map;

import dao.UserdaoImpl;
import domain.PageBean;
import domain.User;

public interface Userservice {
 List<User> findAll() ;
 User  login(User user);
 void addUser(User user);
 void delUser(int id);
 User findUserById(int  id);
 void updateUser(User user);
 void delSelectedUser(String []uid);
 PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
