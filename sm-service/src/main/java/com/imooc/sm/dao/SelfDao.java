package com.imooc.sm.dao;

import com.imooc.sm.entity.Staff;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("selfDao")
public interface SelfDao {
    Staff selectByAccount(String account);

    void changePassword(@Param("id") Integer id, @Param("password") String password);
}
