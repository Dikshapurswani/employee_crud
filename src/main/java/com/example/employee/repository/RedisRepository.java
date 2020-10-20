package com.example.employee.repository;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.example.employee.entity.EmployeeEntity;

@Repository
public class RedisRepository {
	
    final private HashOperations hashOperations;
    final private RedisTemplate redisTemplate;

    public RedisRepository(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = this.redisTemplate.opsForHash();
    }

    public void save(EmployeeEntity employee) {
        hashOperations.put("EMPLOYEE", employee.getId(), employee);
        redisTemplate.expire("EMPLOYEE", 1, TimeUnit.MINUTES);
    }

    public List<EmployeeEntity> findAll() {
        return (List<EmployeeEntity>) hashOperations.entries("EMPLOYEE");
    }

    public EmployeeEntity findById(String id) {
        return  (EmployeeEntity) hashOperations.get("EMPLOYEE", id);
    }

//    public void update(EmployeeEntity employee) {
//        save(employee);
//    }

    public void delete(String id) {
        hashOperations.delete("EMPLOYEE", id);
    }

    public Long getSize() {
        return hashOperations.size("EMPLOYEE");
    }

    public void saveAll(List<EmployeeEntity> employeeList) {
    	for(int i=0; i<employeeList.size();i++) {
    		hashOperations.put("EMPLOYEE", employeeList.get(i).getId(), employeeList.get(i));
    	}
//        for (EmployeeEntity e : employeeList) {
//            hashOperations.put("EMPLOYEE", e.getId(), e);
//        }
        redisTemplate.expire("EMPLOYEE", 1, TimeUnit.MINUTES);
    }
   
}
