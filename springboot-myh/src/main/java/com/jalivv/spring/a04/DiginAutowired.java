package com.jalivv.spring.a04;

import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.beans.factory.annotation.InjectionMetadata;
import org.springframework.beans.factory.config.DependencyDescriptor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.annotation.ContextAnnotationAutowireCandidateResolver;
import org.springframework.core.MethodParameter;
import org.springframework.core.env.StandardEnvironment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class DiginAutowired {
    public static void main(String[] args) throws Throwable {
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        beanFactory.registerSingleton("bean2", new Bean2());
        beanFactory.registerSingleton("bean3", new Bean3());

        // @Value
        beanFactory.setAutowireCandidateResolver(new ContextAnnotationAutowireCandidateResolver());
        // ${} 的解析器
        beanFactory.addEmbeddedValueResolver(new StandardEnvironment()::resolvePlaceholders);


        AutowiredAnnotationBeanPostProcessor processor = new AutowiredAnnotationBeanPostProcessor();
        // AutowiredAnnotationBeanPostProcessor 需要解析 Autowired 注解，依赖注入，找容器中的bean 进行注入，肯定需要 beanFactory
        processor.setBeanFactory(beanFactory);

        Bean1 bean1 = new Bean1();
        System.out.println(bean1);

        /**
         * 此方法重写 InstantiationAwareBeanPostProcessor 接口中定义的方法
         * PropertyValues ，一个键值对 ，指定式的
         */
        //processor.postProcessProperties(null, bean1, "bean1");
        Method method = AutowiredAnnotationBeanPostProcessor.class
                .getDeclaredMethod("findAutowiringMetadata", String.class, Class.class, PropertyValues.class);
        // private InjectionMetadata findAutowiringMetadata(String beanName, Class<?> clazz, @Nullable PropertyValues pvs)

        method.setAccessible(true);
        InjectionMetadata metadata = (InjectionMetadata) method.invoke(processor, "bean1", Bean1.class, null);

        System.out.println(metadata);
        metadata.inject(bean1, "bean1", null);
        System.out.println(bean1);


        System.out.println(">>>>>>>>>>>>>>>>>>>>>>");


        // 3.如何按照类型查找值 metadata.inject 内部做的事情
        Field bean3 = Bean1.class.getDeclaredField("bean3");
        // 将 属性 封装成一个 DependencyDescriptor 对象，第二个参数（必须的） true：找不到，就报错；false：找不到就是null，不会报错
        DependencyDescriptor dp = new DependencyDescriptor(bean3, false);
        // 让 BeanFactory 去找这个 dp
        Object o = beanFactory.doResolveDependency(dp, null, null, null);
        System.out.println(o);


        Method setBean2 = Bean1.class.getDeclaredMethod("setBean2", Bean2.class);
        DependencyDescriptor dp1 = new DependencyDescriptor(new MethodParameter(setBean2, 0), false);
        Object o1 = beanFactory.doResolveDependency(dp1, null, null, null);
        System.out.println(o1);

        Method setHome = Bean1.class.getDeclaredMethod("setHome", String.class);
        DependencyDescriptor dp2 = new DependencyDescriptor(new MethodParameter(setHome, 0), false);
        Object o2 = beanFactory.doResolveDependency(dp2, null, null, null);
        System.out.println(o2);
    }
}
