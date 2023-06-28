package com.cc.springboot2demo;

import com.cc.springboot2demo.bean.Pet;
import com.cc.springboot2demo.bean.User;
import com.cc.springboot2demo.config.MyConfig;
import com.cc.springboot2demo.testController.TestController;
import com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @ComponentScan 默认包扫描的位置，程序入口所在目录下 “com.cc.springboot2demo”
 */
@SpringBootApplication
//@ComponentScan("com.cc.springboot2demo")
public class SprigBoot2DemoApplication {

    public static void main(String[] args) {
        //返回IOC容器
        ConfigurableApplicationContext run = SpringApplication.run(SprigBoot2DemoApplication.class, args);

        //遍历IOC容器中的组件
        String[] names = run.getBeanDefinitionNames();

        //输出容器中组件的名字
        //输出结果中有user01， myConfig，testController说明：@Configuration，@Bean，@RestController都会被当成组件
        for (String str : names) {
            System.out.println(str);
        }

        //从容器中获取组件,第二个参数制定组件的类型
        User user01 = run.getBean("user01", User.class);
        User user02 = run.getBean("user01", User.class);

        MyConfig myConfig01 = run.getBean("myConfig", MyConfig.class);
        MyConfig myConfig02 = run.getBean("myConfig", MyConfig.class);

        TestController testController01 = run.getBean("testController", TestController.class);
        TestController testController02 = run.getBean("testController", TestController.class);

        User user03 = myConfig01.user01();
        User user04 = myConfig01.user01();


        //输出五个都是true,说明组件都是单实例的，本质是获取容器中被代理的组件，都是同一个组件
//        System.out.println(user01 == user02);
//        System.out.println(myConfig01 == myConfig02);
//        System.out.println(testController01 == testController02);
//        System.out.println(user03 == user04);
//        System.out.println(user02 == user03);

        //@Configuration(proxyBeanMethods = false) 情况下
        //如果@Configuration(proxyBeanMethods = true)代理对象调用方法。SpringBoot总会检查这个组件是否在容器中有。

        MyConfig bean = run.getBean(MyConfig.class);
        //@Configuration(proxyBeanMethods = true) 情况下:是经过在cglib增强的代理对象
        //com.cc.sprigboot2demo.config.MyConfig$$EnhancerBySpringCGLIB$$87dd1b25@3af7d855
        //@Configuration(proxyBeanMethods = false) 情况下:
        // com.cc.sprigboot2demo.config.MyConfig@7601bc96
//        System.out.println(bean);
        //保持组件单实例
        User user = bean.user01();
        User user1 = bean.user01();
        //@Configuration(proxyBeanMethods = true)输出为true
        //@Configuration(proxyBeanMethods = false)输出为false
//        System.out.println(user == user1);
//        Pet tom = run.getBean("tomcatPet", Pet.class);
//        System.out.println("用户的宠物：" + (user01.getPet() == tom));

        //获取@Import引入的组件
        String[] beanNamesForType = run.getBeanNamesForType(User.class);
        for (String s : beanNamesForType) {
            System.out.println(s);
        }

        LongStreamSerializer longStreamSerializer = run.getBean(LongStreamSerializer.class);
        System.out.println(longStreamSerializer);
        //输出效果如下
//        com.cc.springboot2demo.bean.User
//                user01
//        com.fasterxml.jackson.datatype.jdk8.LongStreamSerializer@178f268a

    }

}
