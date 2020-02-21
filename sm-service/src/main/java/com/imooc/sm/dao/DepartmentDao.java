package com.imooc.sm.dao;

import com.imooc.sm.entity.Department;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository("departmentDao")
public interface DepartmentDao {
    public void insert(Department department);

    public void delete(Integer id);

    public void update(Department department);

    public Department selectById(Integer id);

    public List<Department> selectAll();
}
