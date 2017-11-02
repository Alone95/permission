package com.mmall.service;

import com.google.common.base.Preconditions;
import com.mmall.beans.PageQuery;
import com.mmall.beans.PageResult;
import com.mmall.dao.SysUserMapper;
import com.mmall.exception.ParamException;
import com.mmall.model.SysUser;
import com.mmall.param.UserParam;
import com.mmall.util.BeanValidator;
import com.mmall.util.MD5Util;
import com.mmall.util.PasswordUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author alone95
 * @date 2017/10/30
 */
@Service
public class SysUserService {

    @Resource
    private SysUserMapper sysUserMapper;

    public void save(UserParam param){
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已被占用");
        }

        String password = PasswordUtil.randomPassword();//TODO;
        password ="12345678";
        //不允许明文，暂时使用MD5加密
        String encryptedPassword = MD5Util.encrypt(password);

        SysUser user =SysUser.builder().username(param.getUsername())
                .telephone(param.getTelephone())
                .mail(param.getMail())
                .password(encryptedPassword)
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark()).build();
        user.setOperator("system");//TODO;
        user.setOperateIp("127.0.0.1");//TODO;
        user.setOperateTime(new Date());//TODO;


        //TODO: 发送邮件

        sysUserMapper.insertSelective(user);

    }

    public void update(UserParam param){
        BeanValidator.check(param);
        if (checkTelephoneExist(param.getTelephone(),param.getId())){
            throw new ParamException("电话已被占用");
        }
        if (checkEmailExist(param.getMail(),param.getId())){
            throw new ParamException("邮箱已被占用");
        }
        SysUser before = sysUserMapper.selectByPrimaryKey(param.getId());
        Preconditions.checkNotNull(before,"待更新的用户不存在");
        SysUser after =SysUser.builder().username(param.getUsername())
                .id(param.getId())
                .telephone(param.getTelephone())
                .mail(param.getMail())
                .deptId(param.getDeptId())
                .status(param.getStatus())
                .remark(param.getRemark()).build();
        sysUserMapper.updateByPrimaryKeySelective(after);

    }

    //检查Email是否存在
    public boolean checkEmailExist(String mail,Integer userId){

        return sysUserMapper.countByMail(mail,userId) > 0;
    }
    //检查电话是否存在
    public boolean checkTelephoneExist(String telephone,Integer userId){

        return sysUserMapper.countByTelephone(telephone,userId) > 0;
    }

    public SysUser findByKeyword(String keyword) {
        return sysUserMapper.findByKeyword(keyword);

    }

    public PageResult<SysUser> getPageByDeptId(int deptId, PageQuery page){
        BeanValidator.check(page);
        int count =sysUserMapper.countByDeptId(deptId);
        if(count > 0){
            List<SysUser> list = sysUserMapper.getPageByDeptId(deptId, page);
            return PageResult.<SysUser>builder().total(count).data(list).build();
        }
        return PageResult.<SysUser>builder().build();
    }
}
