package com.scv.vadmin.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.scv.vadmin.entity.User;


/**
  * @ClassName: UserPageDao 
  * @Description: TODO
  * @author chenjx
  * @date 2017年12月20日 上午11:58:19
*/
public interface UserPageDao extends PagingAndSortingRepository<User, Integer>, JpaSpecificationExecutor<User> {

}
