package com.taotao.mapper;


import com.taotao.pojo.TbUser;

public interface TbUserMapper {
    /**
     * 根据用户名查询数据库
     *
     * @param userName 用户名
     * @return TbUser如果对象不为null则表示用户已经存在
     */
    TbUser getUserByUserName(String userName);

    /**
     * 根据手机号码查询数据库
     * @param phone 手机号
     * @return TbUser如果对象不为null则表示手机号码已经存在
     */
    TbUser getUserByPhone(String phone);

    /**
     * 根据emai查询数据库
     * @param email email
     * @return TbUser如果对象不为null则表示email已经存在
     */
    TbUser getUserByEmail(String email);

    /**
     * 添加一个用户信息到数据库
     * @param tbUser 需要添加的用户信息
     */
    void insertUser(TbUser tbUser);

    /**
     * 根据账号密码查询用户是否存在 注意密码是md5加密的
     * @param userName 用户账号
     * @param passWord 用户面膜
     * @return 如果不为null则表示用户存在
     */
    TbUser getUserByUserAndPass(String userName,String passWord);
}