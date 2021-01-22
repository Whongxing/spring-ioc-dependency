import annotation.Super;
import domain.UserTest;
import org.junit.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 依赖查找  单一类型
 *      通过BeanFactory
 *          一、根据 BEAN名称 查找
 *                 * getBean(String)
 *                 * getBean(String,Object)
 *          二、根据 BEAN类型 查找
 *               -实时查找
 *                 * getBean(Class)
 *                 * getBean(Class,Object)
 *               -延迟查找 spring5.1以上的采用，不然就是如下的ObjectFactory
 *                 * getBeanProvider(Class)
 *                 * getBeanProvider(ResolvableType)
 *          三、根据 名称+类型 查找
 *
 *
 *  依赖查找  集合类型
 *      通过ListableBeanFactory
 *         一、根据Bean类型查找
 *              获取同类型Bean名称列表
 *                 * getBeanNamesForType(Class);
 *              获取同类型Bean实例列表
 *                 * getBeansOfType(Class);
 *         二、通过注解查找
 *              获取同类型Bean名称列表
 *                 * getBeanNamesForAnnotation(Class)
 *              获取同类型Bean实例列表
 *                 * getBeanWithAnnotation(Class)
 *              获取名称加指定类型
 *                 * findAnnotationOnBean(str,Class)
 */
public class DependencyLookUpTest {

    //一、通过名称查找Bean
    /**
     *  实时查找
     *  通过xml方式去依赖查找一个bean
     */
    @Test
    public void dependencyLookUpOne(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("MATA-INF/dependency-lookup.xml");
        System.out.println(beanFactory.getBean("userTest"));
    }


    /**
     * 延时查找
     * 通过ObjectFactory的targetName属性关联Bean，然后根据getObject去获取需要延时加载的Bean
     */
    @Test
    public void dependencyLookUpOneLazy(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("MATA-INF/dependency-lookup.xml");
        ObjectFactory<UserTest> objectFactory = (ObjectFactory<UserTest>) beanFactory.getBean("objectFactory");
        System.out.println(objectFactory.getObject());

    }

    @Test
    public void dependencyLookUpOneLazyBeanProvider(){
        AnnotationConfigApplicationContext annotationBeanFactory = new AnnotationConfigApplicationContext();
        annotationBeanFactory.register(DependencyLookUpTest.class);
        annotationBeanFactory.refresh();
        ObjectProvider<String> s  =  annotationBeanFactory.getBeanProvider(String.class);
        System.out.println(s.getObject());
        annotationBeanFactory.close();
    }


    //二、通过类型查找, 类型查找分为查找单个对象+查找集合对象两种
    /**
     * 单一接口查找，和名称一样，只是传类型就好了
     * 方法重载
     */
    @Test
    public void dependencyLookUpByType(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("MATA-INF/dependency-lookup.xml");
        beanFactory.getBean(UserTest.class);
    }

    /**
     *  集合类型查找，ListableBeanFactory
     */
    @Test
    public void dependencyLookUpByTypeCollection(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("MATA-INF/dependency-lookup.xml");
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            System.out.println(listableBeanFactory.getBeansOfType(UserTest.class));
        }
    }


    //三、依赖查找通过注解查询Bean
    /**
     * 通过注解查找，ListAbleBeanFactory的getBeansWithAnnotation()方法
     */
    @Test
    public void dependencyLookUpByAnnotation(){
        BeanFactory beanFactory = new ClassPathXmlApplicationContext("MATA-INF/dependency-lookup.xml");
        if(beanFactory instanceof ListableBeanFactory){
            ListableBeanFactory listableBeanFactory = (ListableBeanFactory)beanFactory;
            System.out.println(listableBeanFactory.getBeansWithAnnotation(Super.class));
        }
    }

    @Bean
    public String hello(){
        return "hello wang!";
    }
}
