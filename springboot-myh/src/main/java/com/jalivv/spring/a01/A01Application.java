package com.jalivv.spring.a01;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

public class A01Application {


    private static final Logger logger = LoggerFactory.getLogger(A01Application.class);


    public static void main(String[] args) {
        // 获取一个 BeanFactory 对象
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        // 建造者模式 生成一个 BD 对象
        AbstractBeanDefinition configBD = BeanDefinitionBuilder.genericBeanDefinition(Config.class).setScope("singleton").getBeanDefinition();

        // 将 bd 注册到 beanFactory 中
        beanFactory.registerBeanDefinition("config", configBD);

        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 给 beanFactory 添加一些常用的后置处理器
        AnnotationConfigUtils.registerAnnotationConfigProcessors(beanFactory);


        // 找到所有的 BeanFacotoryPostProcessor ，来执行这些处理器，增强 beanFactory
        beanFactory.getBeansOfType(BeanFactoryPostProcessor.class).values().forEach(beanFactoryPostProcessor ->
                beanFactoryPostProcessor.postProcessBeanFactory(beanFactory)
        );


        // Bean 后置处理器，针对 bean 的生命周期的各个阶段提供的扩展，例如 @Autowired @Resource ....
        //beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanFactory::addBeanPostProcessor);

        //beanFactory.getBeansOfType(BeanPostProcessor.class).values().forEach(beanPostProcessor ->{
        //    logger.info("{}", beanPostProcessor);
        //    beanFactory.addBeanPostProcessor(beanPostProcessor);}
        //);

        // autowired 跟 resource 可以制定顺序(同时加autowired 跟 resource 注解)
        beanFactory.getBeansOfType(BeanPostProcessor.class).values().stream().sorted(
                beanFactory.getDependencyComparator()
        ).forEach(beanPostProcessor -> {
                    logger.info("{}", beanPostProcessor);
                    beanFactory.addBeanPostProcessor(beanPostProcessor);
                }
        );

        // 可以看到 bean1 ，bean2 已经加入容器中了
        for (String name : beanFactory.getBeanDefinitionNames()) {
            System.out.println(name);
        }

        // 但是此时 bean1 中依赖的 bean2 还是一个 null 对象，说明 autowired 并没有起作用
        // 再加入 BeanPostProsessor 之后，对 Bean 进行后置处理， bean2 就不为 null 了
        System.out.println(beanFactory.getBean(Bean1.class).bean2());

        /**
         * 同时在 field 加上 autowired 跟 resource 注解，autowired 会生效，resource 不会生效
         * 因为 AutowiredAnnotationBeanPostProcessor 在 CommonAnnotationBeanPostProcessor 之前生效，后续就不起作用
         *
         */
        System.out.println(beanFactory.getBean(Bean1.class).bean3());

    }


    @Configuration
    static class Config {

        public Config() {
        }

        @Bean
        Bean1 bean1() {
            return new Bean1();
        }

        @Bean
        Bean2 bean2() {
            return new Bean2();
        }

        @Bean
        Bean3 bean3() {
            return new Bean3();
        }

        @Bean
        Bean4 bean4() {
            return new Bean4();
        }
    }

    static class Bean1 {

        public Bean1() {
            logger.debug("{}", "bean1正在创建");
        }

        @Autowired
        Bean2 bean2;

        public Bean2 bean2() {
            return bean2;
        }

        @Autowired
        @Resource(name = "bean3")
        inter bean4;

        public inter bean3() {
            return bean4;
        }
    }

    static class Bean2 {
        public Bean2() {
            logger.debug("{}", "bean2正在创建");
        }
    }


    interface inter {
    }

    static class Bean3 implements inter {
    }

    static class Bean4 implements inter {
    }


}
