import domain.UserTest;
import org.junit.Test;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * Spring依赖查找中的异常
 *      --  NoSuchBeanDefinitionException
 *              Bean不存在于IOC容器中时候触发    如： BeanFactory的getBean方法、ObjectFactory中的getObject
 *      --  NoUniqueBeanDefinitionException
 *              类型依赖查找时，Ioc容器存在多个Bean实例
 *      --  BeanInstantiationException
 *              Bean对应的类型非具体类，比如一个接口，抽象类就会出现这种情况
 *      --  BeanCreationException
 *              Bean初始化过程中，bean初始化方法执行异常时  pojo类中没有任何属性等
 *      --  BeanDefinitionStoreException
 *              当BeanDefinition配置元信息非法时，   加载的xml资源无法打开
 */
public class DependencyLookUpExceptionTest {
    /**
     * 几个简单的异常
     */
    @Test
    public void  NoSuchBeanEx(){
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        beanFactory.register(DependencyLookUpExceptionTest.class);
        beanFactory.refresh();
        //会出现NoSuchBean异常
        beanFactory.getBean(UserTest.class);
        //出现NoUniqueBean异常
        beanFactory.getBean(String.class);
        beanFactory.close();
    }

    @Bean
    public String hell(){
        return "111";
    }

    @Bean
    public String hello(){
        return "222";
    }

    /**
     * 快速失败的一种设计模式
     */
    @Test
    public void creationException(){
        AnnotationConfigApplicationContext beanFactory = new AnnotationConfigApplicationContext();
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(PoJo.class);
        beanFactory.registerBeanDefinition("pojo",beanDefinitionBuilder.getBeanDefinition());
        beanFactory.refresh();
        beanFactory.close();
    }


    static class PoJo implements InitializingBean{
        public void afterPropertiesSet() throws Exception {
            throw new Exception();
        }
    }
}
