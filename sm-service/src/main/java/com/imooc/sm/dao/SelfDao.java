package com.imooc.sm.dao;

import com.imooc.sm.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("selfDao")
public interface SelfDao {
    Staff selectByAccount(String account);
    //传入username将数据库中的对象拿出来，再和传入的pwd做对比，防止sql注入

    void changePassword(@Param("id") Integer id, @Param("password") String password);
}
