package com.wzw.cache;

import com.wzw.cache.bean.Employee;
import com.wzw.cache.mapper.EmployeeMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class Springboot01CacheApplicationTests {

	@Autowired
	EmployeeMapper employeeMapper;

	@Autowired
    RedisTemplate redisTemplate;    //操作k-v都是对象的

	@Autowired
    StringRedisTemplate stringRedisTemplate;  //因为操作字符串的比较多，所以单独列出来一个StringRedisTemplate操作k-v都是字符串的

    @Autowired
    RedisTemplate<Object, Employee> empRedisTemplate;

	@Test
	void testInsert() {
		Employee employee=new Employee(null,"张三1","123@1231.com",2,2);
		employeeMapper.insertUser(employee);
		System.out.println(employee.getId());
	}
	@Test
	void testSelectId(){

		System.out.println(employeeMapper.getEmpById(1));
	}

	@Test
	void testSelectName(){
		System.out.println(employeeMapper.getEmpByLastName("张三"));
	}

    /**
     * redis常见的五大数据类型
     *  String(字符串)
     *      stringRedisTemplate.opsForValue()
     *  List(列表)
     *      stringRedisTemplate.opsForList()
     *  Set(集合)
     *      stringRedisTemplate.opsForSet()
     *  Hash(散列)
     *      stringRedisTemplate.opsForHash()
     *  ZSet(有序集合)
     *      stringRedisTemplate.opsForZSet()
     */
	@Test
	void testRedis(){
//	    stringRedisTemplate.opsForValue().append("key1","value1");
//        System.out.println(stringRedisTemplate.opsForValue().get("key1"));
        stringRedisTemplate.opsForList().leftPushAll("mylist","1","2","3","4");
//        System.out.println(stringRedisTemplate.opsForList().range("mylist",0,-1));
	}

	//测试保存对象
	@Test
    void tetRedisObject(){
	    //默认如果保存对象，使用Jdk序列化机制，序列化后的数据保存到redis中
//	    redisTemplate.opsForValue().set("emp1",employeeMapper.getEmpById(1));
        /**
         * 1.将数据以json方式保存，
         *      （1）自己将对象转成json
         *      （2）redisTemplate默认的序列化规则,改变默认的序列化规则
         */
        empRedisTemplate.opsForValue().set("emp1",employeeMapper.getEmpById(1));
        System.out.println(empRedisTemplate.opsForValue().get("emp1"));


    }

}
