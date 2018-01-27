package com.scv.vinterface.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scv.vinterface.feign.VAdminTest;
import com.scv.vinterface.globle.bo.EnvValue;
import com.xx.kit.util.RedisBo;
import com.xx.kit.util.RedisUtil;


@RestController
public class TestController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
    private VAdminTest vAdminTest;
	
	/**
	 * 接口测试
	 * @return
	 */
    @RequestMapping(value = "/hello"/*,method = RequestMethod.GET*/)
    public String sayHello() {
        return "vinterface say hello";
    }
    
    /**
     * 应答feign调用
     * @param name
     * @return
     */
    @RequestMapping(value = "/feignRespone"/*,method = RequestMethod.GET*/)
    @ResponseBody
    public String feignRespone(@RequestParam(value = "name") String name) {
        return "Hi " + name + ", this is vinterface service";
    }
    

    /**
     * feign调用
     * @param name
     * @return
     */
    @RequestMapping(value = "/feign"/*,method = RequestMethod.GET*/)
    public String feign(@RequestParam(value = "name") String name) {
        return vAdminTest.feignRespone(name);
    }
    
    @Value("${eureka.client.serviceUrl.defaultZone}")
    private String url;
    @Value("${server.port}")
    private String port;

    @RequestMapping("/getProperties")
    public String getProperties() {
        return url + " : " + port;
    }
    
    
    @RequestMapping("/testRedis")
    public String testRedis() {
    	RedisUtil.set("name", "xiaoxian");
    	String name = RedisUtil.get("name");
    	return name;
    }
    
    @RequestMapping("/pushMsg")
    public String pushMsg(@RequestParam(value = "name") String name,
    		@RequestParam(value = "age") int age) {
    	
    	Map<String, Object> params = new HashMap<String, Object>();
    	params.put("name", name);
    	params.put("age", age);
    	if (!RedisUtil.sendMessage(RedisBo.createRedis(EnvValue.getInstance().getTopicInterface(), 1001, params))) {
    		logger.error("push fail. {}", params);
    		return "push fail";
    	}
    	return "push succ";
    }
    
    
    
    
}
