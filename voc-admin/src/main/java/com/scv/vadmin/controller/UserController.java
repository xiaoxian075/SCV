package com.scv.vadmin.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.scv.vadmin.entity.User;
import com.scv.vadmin.service.UserService;
import com.xx.kit.util.GsonUtil;


/**
  * @ClassName: UserController 
  * @Description: 
  * @author chenjx 
  * @date 2017年12月20日 上午9:53:13 
*/

@Controller
public class UserController {

	@Resource
	private UserService userService;
	

	/**
	 * 简单条件查询
	 * @param name
	 * @return
	 */
	@RequestMapping("/getName")
	@ResponseBody
	public String getByName(String name) {
		List<User> userList = userService.getByName(name);
		if (userList != null && userList.size()!=0) {
			for(User user : userList) {
				System.out.println(user);
			}
			return "The user length is: " + userList.size();
		}
		return "user " + name + " is not exist.";
	}

	/**
	 * 保存
	 * @return
	 */
	@RequestMapping("/save")
	@ResponseBody
	public String save() {
		User user = userService.save();
		if (user != null) {
			return user.toString();
		}
		return "save user error.";
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
	public String update(int id, String name, String price) {
		
		User user = userService.update(id, name, price);
		if (user != null) {
			return user.toString();
		} else {
			return "update user fail.";
		}
	}
	
	/**
	 * 根据条件查询
	 * @return
	 */
	@RequestMapping("/getByCondition")
	@ResponseBody
	public String getByCondition() {
		
		List<User> userList = userService.getByCondition();
		if (userList != null) {
			return "succ";
		} else {
			return "fail";
		}
	}
	
	
	/**
	 * 根据条件查询（分页）
	 * @return
	 */
	@RequestMapping("/getPageByCondition")
	@ResponseBody
	public String getPageByCondition() {
		Page<User> userPage = userService.getPageByCondition();
		if (userPage != null) {
			List<User> listUser = userPage.getContent();
			return GsonUtil.toString(listUser);
		} else {
			return "fail";
		}
	}
}	
//	/**
//	 * 分页
//	 * @param pageNumber
//	 * @param pageSize
//	 * @return
//	 */
//	@RequestMapping("/getAllPage")
//	@ResponseBody
//	public String getAllPage(int pageNumber, int pageSize) {
//		Order order1 = new Order(Direction.ASC, "birthday");
//		Order order2 = new Order(Direction.DESC, "height");
//		PageRequest request = new PageRequest(pageNumber - 1, pageSize, new Sort(order1, order2));
//        Page<User> pageUser= userPageDao.findAll(request);
//        System.out.println(pageUser);
//        return "get all page is succ";
//	}

//@RequestMapping("/getSex")
//@ResponseBody
//public String getBySex(char sex) {
//	List<User> userList = userDao.findBySex(sex);
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + sex + " is not exist.";
//}
//
//
//@RequestMapping("/getBirthday")
//@ResponseBody
//public String findByBirthday(String birthday) {
//	System.out.println("birthday:"+birthday);
//	SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd");
//	List<User> userList = null;
//	try {
//		userList = userDao.findByBirthday(formate.parse(birthday));
//	} catch (ParseException e) {
//		 e.printStackTrace();
//	}
//
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + birthday + " is not exist.";
//}
//
//
//@RequestMapping("/getSendtime")
//@ResponseBody
//public String findBySendtime(String sendtime) {
//	System.out.println("sendtime:"+sendtime);
//	SimpleDateFormat formate=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//	List<User> userList = null;
//	try {
//		userList = userDao.findBySendtime(formate.parse(sendtime));
//	} catch (ParseException e) {
//		e.printStackTrace();
//	}
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + sendtime + " is not exist.";
//}
//
//
//@RequestMapping("/getPrice")
//@ResponseBody
//public String findByPrice(BigDecimal price) {
//	List<User> userList = null;
//	userList = userDao.findByPrice(price);
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + price + " is not exist.";
//}
//
//
//@RequestMapping("/getFloatprice")
//@ResponseBody
//public String findFloatprice(float floatprice) {
//	List<User> userList = null;
//	userList = userDao.findByFloatprice(floatprice);
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + floatprice + " is not exist.";
//}
//
//
//@RequestMapping("/getDoubleprice")
//@ResponseBody
//public String findByPrice(double doubleprice) {
//	List<User> userList = null;
//	userList = userDao.findByDoubleprice(doubleprice);
//	if (userList != null && userList.size()!=0) {
//		return "The user length is: " + userList.size();
//	}
//	return "user " + doubleprice + " is not exist.";
//}

