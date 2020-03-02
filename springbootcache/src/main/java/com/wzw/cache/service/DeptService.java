package com.wzw.cache.service;

import com.wzw.cache.bean.Department;
import com.wzw.cache.mapper.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DeptService {

    @Autowired
    DepartmentMapper departmentMapper;

    @Autowired
    CacheManager cacheManager;

//    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        Department department = departmentMapper.getDeptById(id);
        //获取某个缓存
        Cache dept=cacheManager.getCache("dept");
        //第一个参数为名称，第二个参数为要存储的对象
        dept.put("dept",department);
        System.out.println("查询部门:"+id);
        return department;
    }

}
