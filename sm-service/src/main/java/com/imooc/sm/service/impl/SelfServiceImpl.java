package com.imooc.sm.service.impl;

import com.imooc.sm.dao.SelfDao;
import com.imooc.sm.dao.StaffDao;
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
    @Resource(name = "staffDao")
    private StaffDao staffDao;

    public Staff login(String account, String password) {
        //传入username将数据库中的对象拿出来，再和传入的pwd做对比，防止sql注入
        Staff staff = selfDao.selectByAccount(account);

        if(staff.getPassword().equals(password)&&staff!=null){
            return staff;
        }
        return null;
    }

    public void changePassword(Integer id, String password) {
        Staff staff = staffDao.selectById(id);
        staff.setPassword(password);
        staffDao.update(staff);
    }
}
