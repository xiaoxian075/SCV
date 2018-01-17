package com.scv.vinterface.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.scv.vinterface.feign.VAdminTest;


@RestController
public class TestController {

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
    
    @Value("${ip}")
    private String ip;
    @Value("${port}")
    private String port;
    @Value("${name}")
    private String name;

    @RequestMapping("/getProperties")
    public String getProperties(){
        return name + " " + ip + " : " + port;
    }
}
