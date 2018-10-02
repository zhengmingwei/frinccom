package com.cfcp.incc.dao;

import com.cfcp.incc.entity.Role;
import com.cfcp.incc.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p/>
 *
 * @author
 * @date 2016/11/24
 * @since 0.1
 */
public interface UserDao extends GenericDao {
	int delete(String id);

	public int insert(User user);

//	int insertSelective(User user);

	public User get(String id);
//
////	int updateSelective(User user);
//
////	int update(User user);
//
//	@Override
//	public double count(Map conditions);
////
////	public Long getCount();
//
//	public List<User> queryAll();
//
//	/**
//	 * 取用户
//	 * @param userName
//	 * @return
//     */
	public User getByUserName(String userName);


	/**
	 * 用户查重
	 * @param userName
	 * @param id
	 * @return
     */
	public int countByUserName(@Param("userName") String userName, @Param("id") String id);

//	/**
//	 * 取用户的角色
//	 * @return
//     */
////	List<Map<Integer,String>> getAllUserRole();
//
	/**
	 * 取用户角色
	 * @param id
	 * @return
     */
	public Set<Role> fetchUserRole(String id);
//
	public int insertRole(String userId, String roleId);

	public int cleanRoles(String userId);
//
	/**
	 * 修改密码
	 * @param username
	 * @param password
     * @return
     */
	int updatePwd(@Param("name") String username, @Param("password") String password);


//
//
//	int changeStatus(@Param("status")Integer status, @Param("id")String id);

	List<User> query(Map conditions);


//    int insertRole(@Param("userId") String userId, @Param("userId") String roleId);
}

