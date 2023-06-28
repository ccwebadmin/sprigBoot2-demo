package com.cc.springboot2demo.config;

import com.cc.springboot2demo.bean.Pet;
import com.cc.springboot2demo.bean.User;
import com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import javax.annotation.Resource;

/**
 * 配置类
 *
 * @author cc
 * @date 2023/6/25-22:41
 */

/**
 * @Configuration :告诉SpringBoot这是一个配置类 == 配置文件
 * 1、配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
 * 2、配置类本身也是组件
 * 3、proxyBeanMethods：代理bean的方法
 *      Full(proxyBeanMethods = true)、【保证每个@Bean方法被调用多少次返回的组件都是单实例的】
 *      Lite(proxyBeanMethods = false)【每个@Bean方法被调用多少次返回的组件都是新创建的】
 *      组件依赖必须使用Full模式默认。其他默认是否Lite模式
 *      轻量级模式减少模块间的依赖，加载启动速度更快
 *
 * 4、@Import({User.class, DBHelper.class})
 *      给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
 *
 *
 * 5、@ImportResource("classpath:beans.xml")导入Spring的配置文件，
 *
 */
@Configuration(proxyBeanMethods = true)
@Import({User.class, LongStreamSerializer.class})
public class MyConfig {

    //默认Bean的名字方法名 也就是user01
    //@Bean：给容器中添加组件。以方法名作为组件的id。返回类型就是组件类型。返回的值，就是组件在容器中的实例
    @Resource
    LongStreamSerializer longStreamSerializer;
    @Bean
    public User user01() {
        User zhangsan = new User("zhangsan", 18);
        //user组件依赖了Pet组件 默认full模式
        zhangsan.setPet(tomcatPet());
        return zhangsan;
    }

    @Bean()
    public Pet tomcatPet(){
        return new Pet("tomcat");
    }
}
