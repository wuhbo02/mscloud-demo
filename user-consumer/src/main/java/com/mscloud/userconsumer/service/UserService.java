package com.mscloud.userconsumer.service;

import com.mscloud.userconsumer.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;// Eureka客户端，可以获取到服务实例信息

    public List<User> queryUserByIds(List<Long> ids) {
        String serviceId = "cloud-zuul";//serviceId="user-api";
        List<User> users = new ArrayList<User>();
        // String baseUrl = "http://localhost:8081/user/";
        // 根据服务名称，获取服务实例
        List<ServiceInstance> instances = discoveryClient.getInstances(serviceId);
        // 因为只有一个UserService,因此我们直接get(0)获取
        ServiceInstance instance = instances.get(0);
        // 获取ip和端口信息
        String baseUrl = "http://" + instance.getHost() + ":" + instance.getPort() + "/api/userapi/user/";
        ids.forEach(id -> {
            // 我们测试多次查询，
            users.add(this.restTemplate.getForObject(baseUrl + id, User.class));

            // 每次间隔500毫秒
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        return users;
    }

    @PostConstruct
    public void test(){
        List<Long> param = new ArrayList<Long>();
        param.add(1l);
        param.add(2l);
        List<User> list = queryUserByIds(param);
        for(User user:list){
            System.out.println("--user--name:"+user.getName());
        }
    }
}
