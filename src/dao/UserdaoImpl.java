package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.management.Query;

import org.junit.Test;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import domain.PageBean;
import domain.User;

public class UserdaoImpl implements Userdao{
	//获取JDBCtemplate
	private JdbcTemplate tem=new JdbcTemplate(JDBCUtils.getDataSource());

	@Override
	public List<User> findAll() {
		// TODO Auto-generated method stub
		String sqlString="select *from User";
		List<User > users =tem.query(sqlString, new BeanPropertyRowMapper(User.class));
		
		return users;
	}
	@Test
	public void ttt() {
		User user=new User();
		user.setUsername("zhansan");
		user.setPassword("123");
		new UserdaoImpl().loginUser(user);
	}
	
	@Override
	public User loginUser(User user) {
		// TODO Auto-generated method stub
		User user2=null;
		
		try {
			String sqlString="select *from User where username=? and password=?";
			//tem.queryForList(sqlString,user.getUsername(),user.getPassword() );
			 user2=tem.queryForObject(sqlString,  new BeanPropertyRowMapper<User>(User.class),user.getUsername(),user.getPassword());
			System.out.println(user2);
		} catch (Exception e) {
			return null;
			// TODO: handle exception
		}
		return user2;
		
	}
	/*
	 * 添加用户信息
	 * params User user
	 */
	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		String sql="insert into User values(null,?,?,?,?,?,?,null,null)";
		System.out.println(user.getName()+user.getGender()+user.getAge()+user.getAddress()+user.getQq()+user.getEmail()+user.getUsername()+user.getPassword()+"---------");
		tem.update(sql, user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
		
		
	}
	@Override
	public void delUser(int id) {
		// TODO Auto-generated method stub
		String sql="delete from user where id=?";
		tem.update(sql, id);
		
	}
	@Override
	public User findUserById(int id) {
		// TODO Auto-generated method stub
		String sql="select *from User where id=?";
		User user=tem.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), id);
		return user;
	}
	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		String sql="update User set name=?,gender=?,age=?,address=?,qq=?,email=? where id=?";
		tem.update(sql,user.getName(),user.getGender(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getId());
	}
	/*
	 * 分页查询
	 */
	@Override
	public List<User> findUserByPage(int start,int rows, Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		String sql="select * from User where 1=1  ";
		
		StringBuilder sb=new StringBuilder(sql);			//动态构建sql语句
		Set<String> set=condition.keySet();	
		List <Object> valueList=new ArrayList<Object>();	//保存参数的值
		
		for (String key:set) {								
			String value=condition.get(key)[0];
			if(key.equals("currentPage")||key.equals("rows")) continue;
			if(value!=null&&!"".equals(value)) {//不为空则将键值加入
				sb.append(" and " +key+" like ? ");
				valueList.add("%"+value+"%");
			}
		}
		sb.append(" limit ?,? ");
		valueList.add(start);
		valueList.add(rows);
		sql=sb.toString();
		
		List<User> list=tem.query(sql, new BeanPropertyRowMapper<User>(User.class), valueList.toArray());
		System.out.println(list+"-----------------list");
		return list;
	}
	@Override
	public int findTotalCount(Map<String, String[]> condition) {
		// TODO Auto-generated method stub
		String sql="select count(*) from User where 1=1";
		StringBuilder sb=new StringBuilder(sql);			//动态构建sql语句
		Set<String> set=condition.keySet();	
		List <Object> valueList=new ArrayList<Object>();	//保存参数的值
		
		for (String key:set) {								
			String value=condition.get(key)[0];
			if(key.equals("currentPage")||key.equals("rows")) continue;
			if(value!=null&&!"".equals(value)) {//不为空则将键值加入
				sb.append(" and " +key+" like ? ");
				valueList.add("%"+value+"%");
			}
		}
		sql=sb.toString();
		
		int count=tem.queryForObject(sql, Integer.class,valueList.toArray());
		return count;
	}

}
