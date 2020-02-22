package com.imooc.sm.service.impl;

import com.imooc.sm.dao.SelfDao;
import com.imooc.sm.entity.Staff;
import com.imooc.sm.service.SelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("selfService")
public class SelfServiceImpl implements SelfService {
    @Resource(name = "selfDao")
    private SelfDao selfDao;

    public Staff login(String account, String password) {
        Staff staff = selfDao.selectByAccount(account);
        if(staff.getPassword().equals(password)){
            return staff;
        }
        return null;
    }

    public void changePassword(Integer id, String password) {
        selfDao.changePassword(id,password);
    }
}
