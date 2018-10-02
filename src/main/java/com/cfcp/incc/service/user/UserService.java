package com.cfcp.incc.service.user;

import com.cfcp.incc.Constants;
import com.cfcp.incc.dao.UserDao;
import com.cfcp.incc.entity.*;
import com.cfcp.incc.security.UserAdapter;
import com.cfcp.incc.security.UserContext;
import com.cfcp.incc.service.BaseService;
import com.cfcp.incc.utils.PropertiesUtils;
import com.cfcp.incc.utils.generator.UUIDGenerator;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.tigerfacejs.commons.view.DataEvent;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhyj on 2016/11/24.
 */
@Service
public class UserService extends BaseService implements UserDetailsService {

    static final String defaultPwd="123456";

    @Autowired
    UserDao userDao;


    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return new UserAdapter(this, userName);
    }

    public User findUserByUserName(String name) {
        return userDao.getByUserName(name);
    }

    public Set<Role> listRolesByUser(User user) {
        return userDao.fetchUserRole(user.getId());
    }

    public String encodePassword(String pwd) {
        return new Md5PasswordEncoder().encodePassword(pwd, Constants.MD5_SALT);
    }

    public boolean isUserExists(String name, String id) {
        int count = userDao.countByUserName(name.trim(), id);
        if(0==count){
            return false;
        }
        return true;
    }
//
//    public int update(User user) {
//        return userDao.update(user);
//    }
//
//    public User get(String id) {
//        return userDao.get(id);
//    }
//
    public int updatePwd(String password, String userId) {
        return userDao.updatePwd(encodePassword(password), userId);
    }

    public void refreshUser() {
        UserAdapter adapter = (UserAdapter) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        adapter.refresh(this);
        UserContext.refreshAuths(adapter);
    }


    /**
     * 修改密码
     *
     * @param username
     * @param password
     * @return
     */
    public int changePwd(String username, String password) {
        String pwd = new Md5PasswordEncoder().encodePassword(password, Constants.MD5_SALT);
        System.out.println(pwd);
        return userDao.updatePwd(username, pwd);
    }

    /**
     * 创建特定权限的用户
     * 创建用户的同时创建用户信息和用户账户,并授权
     *
     * @param user
     * @return
     */
    public int insert(User user) {
        user.setId(UUIDGenerator.getUuid());
        user.setPassword(encodePassword(user.DEFAULT_PASSWORD));
        user.setStatus(Constants.USER_STATUS_ENABLE);
        user.setCreateTime(new Date());
        if(getCurrentUser()==null){
            user.setCreator("自助注册");
        }else{
            user.setCreator(getCurrentUser().getId());
        }
        int count = userDao.insert(user);
        if (count > 0) {
            grantRoles(user);
        }
        return count;
    }

    public PageInfo<User> query(Map<String, String> conditions) {
        Page page = this.initPage(conditions);
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<User> list =  userDao.query(conditions);
        PageInfo<User> pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public int saveOrUpdate(User user) {
        if (!StringUtils.hasLength(user.getId())){
            int r =  insert(user);
            return r;
        } else {
            grantRoles(user);
            return userDao.update(user);
        }
    }

    /**
     * 用户授权
     *
     * @param user
     */
    public void grantRoles(User user) {
        userDao.cleanRoles(user.getId());
        for (Role role : user.getRoles()) {
            userDao.insertRole(user.getId(), role.getId());
        }
    }

    public User get(String id) {
        return userDao.get(id);
    }

    public int delete(String id) {
        return userDao.delete(id);
    }
//
//    public List<User> query(Map<String, String> conditions) {
//        return userDao.query(conditions);
//    }
//
//    public int changeStatus(Integer status, String id) {
//        return userDao.changeStatus(status, id);
//    }
//    public int disableUser(String id){
//        if(id!=null && id.length()>0){
//            return changeStatus(User.STATUS_DESABLE,id);
//        }else{
//            return 0;
//        }
//
//    }0
}
