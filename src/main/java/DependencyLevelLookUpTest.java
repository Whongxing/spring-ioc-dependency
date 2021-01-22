import org.junit.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次性依赖查找  HierarchicalBeanFactory
 * 结构
 *           ConfigurableBeanFactory  extends  HierarchicalBeanFactory
 *           组合关系
 *           ConfigListableBeanFactory extends  ListableBeanFactory、AutowireCapableBeanFactory、ConfigurableBeanFactory
 *
 */
public class DependencyLevelLookUpTest {
    @Test
    public void dependencyLookUpOneLazyBeanProvider(){
        AnnotationConfigApplicationContext annotationBeanFactory = new AnnotationConfigApplicationContext();
        annotationBeanFactory.register(DependencyLookUpTest.class);
        annotationBeanFactory.refresh();

        //1、 获取 HierarchicalBeanFactory -->  ConfigurableBeanFactory -->  ConfigListableBeanFactory
        // 双亲委派模型  获取子接口就行
        ConfigurableListableBeanFactory beanFactory = annotationBeanFactory.getBeanFactory();
        System.out.println("当前beanFactory的parent BeanFactory: "+ beanFactory.getParentBeanFactory());  //输出null

        //2、 设置parent BeanFactory
        //beanFactory.setParentBeanFactory();
        annotationBeanFactory.close();
    }
}
