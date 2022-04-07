package com.ispan.springbootdemo.model;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer>{

	@Query("from Customer where name=:name")
	public List<Customer> findCustomerByName(@Param(value="name") String name);
	
	@Query(value="select * from Customer where name=:name",nativeQuery = true)
	public List<Customer> findCustomerByName2(@Param(value="name") String name);
	
	@Transactional //交易主要在service層進行不須另外標註, 若在interface執行要加註
	@Modifying
	@Query(value="delete from Customer where id=?1",nativeQuery = true)
	public void deleteCustomerById(Integer id);
	
	public List<Customer> findByLevelOrderByLevel(Integer level);
	
	public List<Customer> findByLevelOrderByName(Integer level);
}
