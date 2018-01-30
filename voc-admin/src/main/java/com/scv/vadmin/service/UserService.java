package com.scv.vadmin.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scv.vadmin.dao.UserDao;
import com.scv.vadmin.dao.UserPageDao;
import com.scv.vadmin.entity.User;


/**
  * @ClassName: UserService 
  * @Description: TODO
  * @author chenjx
  * @date 2017年12月22日 上午10:44:22
*/

@Service
public class UserService {
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserPageDao userPageDao;
	

	/**
	 * 简单条件查询
	 * @param name
	 * @return
	 */
	public List<User> getByName(String name) {
		return userDao.findByName(name);
	}

	/**
	 * 保存
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public User save() {
		Date date = new Date();
		User user = new User("xiaofeng", 100, 'M', date, date, new BigDecimal("12.34"), 13.43f, 15.98);
		User newUser = userDao.save(user);
		if (newUser != null) {
			return newUser;
		}
		return null;
	}
	
	/**
	 * 更新
	 * @param id
	 * @param name
	 * @param price
	 * @return
	 */
	@RequestMapping("/update")
	@ResponseBody
	public User update(int id, String name, String price) {
		
		User user = userDao.findOne(id);
		if (user == null) {
			return null;
		}
		
		user.setName(name);
		user.setPrice(new BigDecimal(price));
		User newUser = userDao.save(user);
		if (newUser != null) {
			return newUser;
		}

		return null;
	}
	
	/**
	 * 根据条件查询
	 * @return
	 */
	@RequestMapping("/getByCondition")
	@ResponseBody
	public List<User> getByCondition() {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Date date = new Date();
				
				List<Predicate> predicates = new ArrayList<Predicate>();

				//predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%xian%"));
				predicates.add(criteriaBuilder.equal(root.get("sex").as(String.class), "M"));
				predicates.add(criteriaBuilder.lessThan(root.get("birthday"), date));
	            
	            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		return userDao.findAll(spec);
	}
	
	
	/**
	 * 根据条件查询（分页）
	 * @return
	 */
	@RequestMapping("/getPageByCondition")
	@ResponseBody
	public Page<User> getPageByCondition() {
		Specification<User> spec = new Specification<User>() {
			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
				Date date = new Date();
				
				List<Predicate> predicates = new ArrayList<Predicate>();

				//predicates.add(criteriaBuilder.like(root.get("name").as(String.class), "%xian%"));
				predicates.add(criteriaBuilder.equal(root.get("sex").as(String.class), "M"));
				predicates.add(criteriaBuilder.lessThan(root.get("birthday"), date));
	            
	            
	            return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		
		Order order1 = new Order(Direction.ASC, "birthday");
		Order order2 = new Order(Direction.DESC, "height");
		Pageable pageable = new PageRequest(0, 10, new Sort(order1, order2));
		
		return userPageDao.findAll(spec, pageable);
	}
}
