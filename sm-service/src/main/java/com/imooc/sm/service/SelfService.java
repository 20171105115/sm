package com.imooc.sm.service;

import com.imooc.sm.entity.Staff;

public interface SelfService {

    public Staff login(String account, String password);

    public void changePassword(Integer id,String password);
}
