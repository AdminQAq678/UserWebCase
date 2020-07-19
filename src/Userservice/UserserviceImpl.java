package Userservice;

import java.util.List;
import java.util.Map;

import org.junit.Test;

import dao.Userdao;
import dao.UserdaoImpl;
import domain.PageBean;
import domain.User;

public class UserserviceImpl implements Userservice{
	
	
	@Test
	@Override
	public List<User> findAll(){
		return new UserdaoImpl().findAll();
	}
//	public  static void main (String args[]){
//		new UserserviceImpl().findAll();
//	}
	@Override
	public User login(User user) {
		// TODO Auto-generated method stub
		return new UserdaoImpl().loginUser(user);
	}
	@Override
	public void addUser(User user) {
		 new UserdaoImpl().addUser(user);
	}
	@Override
	public void delUser(int id) {
		// TODO Auto-generated method stub
		 new UserdaoImpl().delUser(id);
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		
		return  new UserdaoImpl().findUserById(id);
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		new UserdaoImpl().updateUser(user);
	}
	@Override
	public void delSelectedUser(String[] uid) {
		// TODO Auto-generated method stub
		for(String id:uid) {
			new UserdaoImpl().delUser(Integer.parseInt(id));
		}
	}
	
	@Override
	public PageBean<User> findUserByPage(String _currentPage, String _rows,Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		PageBean<User> pageBean=new PageBean<User>();
		int currentPage=Integer.parseInt(_currentPage);
		int rows=Integer.parseInt(_rows);
		pageBean.setCurrentPage(currentPage);
		pageBean.setRows(rows);
		
		Userdao dao =new UserdaoImpl();
		int totalCount=dao.findTotalCount(condition);
		pageBean.setTotalCount(totalCount);
		int start=(currentPage-1)*rows;
		List<User> list=dao.findUserByPage(start,rows,condition);
		pageBean.setList(list);
		
		//¼ÆËã×ÜÒ³Âë 
		int totalPage=totalCount%rows==0?totalCount/rows:totalCount/rows+1;
		
		pageBean.setTotalPage(totalPage);
		if(totalPage==0) pageBean.setCurrentPage(0);
		return pageBean;
	}
}
