package com.example.vediosystem.service.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.vediosystem.controller.Code;
import com.example.vediosystem.dao.FavoritesDao;
import com.example.vediosystem.dao.UserDao;
import com.example.vediosystem.dao.UserDetailDao;
import com.example.vediosystem.domain.Favorites;
import com.example.vediosystem.domain.User;
import com.example.vediosystem.domain.UserDetail;
import com.example.vediosystem.exception.BusinessException;
import com.example.vediosystem.service.UserService;
import com.example.vediosystem.utils.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserDetailDao userDetailDao;

    @Autowired
    private FavoritesDao favoritesDao;

    @Autowired
    private StringUtil util;

    @Override
    public boolean register(User user) {

        String name = user.getName();
        String password = user.getPassword();

        //校验必要信息
        if (util.isEmpty(name) || util.isEmpty(password)){
            Integer code = Code.SAVE_ERR;
            throw new BusinessException(code,"关键信息不能为空不能为空");
        }

        //查询注册用户是否存在
        User tu = this.selectByName(name);
        if (tu!=null){
            Integer code = Code.SAVE_ERR;
            throw new BusinessException(code,"用户名已经被注册");
        }

        //加密密码
        Md5Hash md5Hash = new Md5Hash(user.getPassword(),Code.SALT,3);

        //创建要插入用户表的实体类
        User insertData = new User();
        insertData.setName(name);
        insertData.setPassword(md5Hash.toHex());

        //插入用户表
        int rows = userDao.insert(insertData);
        if (rows != 1) throw new BusinessException(Code.SAVE_ERR,"添加数据失败");

        //获得插入成功后的userId
        User user1 = this.selectByName(name);
        UserDetail userDetail = new UserDetail();
        userDetail.setId(user1.getId());
        //在UserDetail建立对应项
        int rows2 = userDetailDao.insert(userDetail);
        if (rows2 != 1) throw new BusinessException(Code.SAVE_ERR,"添加数据失败");

        //为用户创建收藏夹
        Favorites favorites = new Favorites();
        favorites.setUserId(user1.getId());
        favorites.setName("默认收藏夹");
        int rows3 = favoritesDao.insert(favorites);
        if (rows3 != 1) throw new BusinessException(Code.SAVE_ERR,"添加数据失败");

        return true;
    }

    @Override
    public User login(String loginName, String password) {
        //1.获取subject对象
        Subject subject = SecurityUtils.getSubject();
        //2.封装请求数据到token
        System.out.println(loginName);
        AuthenticationToken token = new UsernamePasswordToken(loginName,password);
        //3.调用login方法进行登录认证
        try {
            subject.login(token);
            return this.selectByName(loginName);
        }catch (AuthenticationException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public User selectByName(String name) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name",name);
        return userDao.selectOne(queryWrapper);
    }

    @Override
    public void updateUserDetail(UserDetail userDetail) {
        int i = userDetailDao.updateById(userDetail);
        if (i<1) throw new BusinessException(Code.UPDATE_ERR,"更新失败");
    }

    @Override
    public UserDetail getUserDetail(int id) {
        return userDetailDao.selectById(id);
    }
}
