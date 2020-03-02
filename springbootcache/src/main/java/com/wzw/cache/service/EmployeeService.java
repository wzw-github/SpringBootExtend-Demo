package com.wzw.cache.service;

import com.wzw.cache.bean.Employee;
import com.wzw.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig(cacheNames = "emp")    //抽取缓存的公共配置
@Service
public class EmployeeService {

    @Autowired
    EmployeeMapper employeeMapper;

    /**
     * 将方法的运行结果进行缓存，以后再有相同的数据就直接从缓存中取，不再调用方法
     *  CacheManager管理多个Cache组件，对缓存的CRUD操作在Cache组件中，每一个缓存组件有自己唯一的名字
     *  @Cacheable的几个属性
     *      value/cacheNames:指定缓存组件的名称，将方法的返回结果放在哪个缓存中，是数组的方式，可以指定多个缓存
     *      key:缓存数据使用的Key，可以用它来指定，key默认是使用方法参数的值，value它的值就是方法的返回值
     *          编写SpEL:#id：参数id的值     #root.args[0]：第0个参数的值
     *          key = "#root.methodName+'['+#id+']'"————key的值就是getEmp[2]
     *      keyGenerator:key的生成器，可以自己指定key的生成器的组件Id
     *              keyGenerator/key:二选一使用
     *      cacheManager：指定缓存管理器，或者cacheResolver指定缓存解析器
     *              cacheManagercacheResolver/二选一
     *      condition:指定符合条件的情况下才缓存  condition = "#id>0":#id大于0才缓存
     *      unless:否定缓存；当unless指定条件为true，方法的返回值就不会被缓存；可以获取到结果进行判断
     *              unless="#result==null"
     *      sync：是否使用异步模式
     * @param id
     * @return
     */
    @Cacheable(cacheNames = "emp"/*,keyGenerator = "myKeyGenerator",condition = "#a0>1",unless = "#a0==2"*/)
    public Employee getEmp(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * @CachePut ：既调用方法又更新缓存，修改了数据库某个数据，同时更新缓存
     *      1、先调用目标方法，
     *      2、将目标方法的结果缓存起来
     *  测试：
     *      1、查询1号员工，结果会存在缓存中
     *      2、更新1号员工
     *      3、结果没更新
     *          因为之前1号员工的key保存的是1，而更新1号员工时，默认的key是employee对象
     *      4、指定更新的员工的key
     *          key = "#employee.id"，可以从传入的参数的属性取值
     *          key = "#result.id",这个方法返回了值，也可以从返回值的属性取值
     * @param employee
     * @return
     */
    @CachePut(value = "emp",key = "#employee.id")
    public Employee updateEmp(Employee employee){
        System.out.println("调update方法："+employee);
        employeeMapper.updateEmployee(employee);
        return employee;
    }

    /**
     * @CacheEvict 缓存清除
     *      allEntries = true:清除emp这个缓存中所有缓存
     *      beforeInvocation = false：缓存的清除是否在方法之前执行，默认是在方法执行之后执行，如果出现异常，缓存就不会清除
     *      beforeInvocation = true：代表清除缓存在方法之前运行，无论是否出现异常，缓存都清除
     * @param id
     */
    @CacheEvict(value = "emp",allEntries = true)
    public void deleteEmp(Integer id){
        System.out.println("删除员工："+id);
        employeeMapper.deleteEmpById(id);
    }

    /**
     * @Caching 定义复杂的缓存规则
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.id"),
                    @CachePut(value = "emp",key = "#result.email")
            }
    )
    public Employee getEmpByLastName(String lastName){
        Employee employee=employeeMapper.getEmpByLastName(lastName);
        return employee;
    }
}
