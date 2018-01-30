package com.scv.vadmin.dao;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.scv.vadmin.entity.User;


/**
  * @ClassName: UserDao 
  * @Description: TODO
  * @author chenjx
  * @date 2017年12月20日 上午9:51:23
*/

@Transactional
public interface UserDao extends CrudRepository<User, Integer>, JpaSpecificationExecutor<User> {
	public List<User> findByName(String name);
	public List<User> findBySex(char sex);
	public List<User> findByBirthday(Date birthday);
	public List<User> findBySendtime(Date sendtime);
	public List<User> findByPrice(BigDecimal price);
	public List<User> findByFloatprice(float floatprice);
	public List<User> findByDoubleprice(double doubleprice);
//	
//	@Transactional
//	@Modifying(clearAutomatically = true)
//	@Query("update user as c set c.name = ?1 where c.id=?2")
//	public int updateNameById(String name, int id);
}
