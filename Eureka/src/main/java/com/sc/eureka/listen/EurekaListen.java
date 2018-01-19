package com.sc.eureka.listen;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.shared.Application;
import com.netflix.discovery.shared.Applications;
import com.netflix.eureka.EurekaServerContextHolder;
//import com.netflix.eureka.registry.InstanceRegistry;
import com.netflix.eureka.registry.PeerAwareInstanceRegistry;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.eureka.server.event.*;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.List;

@Configuration
@EnableScheduling
@Slf4j
public class EurekaListen implements ApplicationListener {

    private Logger logger = LoggerFactory.getLogger(EurekaListen.class);

    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        // 服务下线事件
        if(event instanceof EurekaInstanceCanceledEvent){
            EurekaInstanceCanceledEvent eurekaInstanceCanceledEvent = (EurekaInstanceCanceledEvent) event;
            PeerAwareInstanceRegistry registry = EurekaServerContextHolder.getInstance().getServerContext().getRegistry();
            Applications applications = registry.getApplications();
            List<Application> registeredApplications1 = applications.getRegisteredApplications();
            for(Application application : registeredApplications1){
                List<InstanceInfo> instances = application.getInstances();
                for(InstanceInfo instanceInfo1 : instances){
                    if(instanceInfo1.getInstanceId().equals(eurekaInstanceCanceledEvent.getServerId())){
                        logger.info("服务【"+instanceInfo1.getAppName()+"】下线");
                        log.info("注解配置日志");
                    }
                }
            }
//            applications.getRegisteredApplications().forEach((registeredApplications)->{
//                registeredApplications.getInstances().forEach((instance)->{
//                    if(instance.getInstanceId().equals(eurekaInstanceCanceledEvent.getServerId())){
//                      logger.info(instance.getAppName());
//                      log.info("注解配置日志");
//                    }
//                });
//            });
        }

        //服务注册事件
        if(event instanceof EurekaInstanceRegisteredEvent){
            EurekaInstanceRegisteredEvent eurekaInstanceRegisteredEvent = (EurekaInstanceRegisteredEvent) event;
            log.info("服务【"+eurekaInstanceRegisteredEvent.getInstanceInfo().getAppName()+"】注册成功");
            log.info("注册IP【"+eurekaInstanceRegisteredEvent.getInstanceInfo().getIPAddr()+"】");
        }

        if(event instanceof EurekaInstanceRenewedEvent){
            EurekaInstanceRenewedEvent eurekaInstanceRenewedEvent = (EurekaInstanceRenewedEvent) event;
            log.info("心跳检测服务"+eurekaInstanceRenewedEvent.getInstanceInfo().getAppName()+"--");
        }

        if(event instanceof EurekaRegistryAvailableEvent){
            log.info("服务->EurekaRegistryAvailableEvent");
        }

        if(event instanceof EurekaServerStartedEvent){
            EurekaServerStartedEvent eurekaServerStartedEvent = (EurekaServerStartedEvent) event;
            log.info("服务【"+eurekaServerStartedEvent.getSource()+"】启动");
        }
    }
}

