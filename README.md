# spring-ioc-dependency
spirngIOC-依赖查找的各种测试Demo,以及一些常见异常等等

#  依赖查找  单一类型
  
##       通过BeanFactory
       
###           一、根据 BEAN名称 查找
           
                  * getBean(String)
                  
                  * getBean(String,Object)
                  
###           二、根据 BEAN类型 查找
           
                -实时查找
                
                  * getBean(Class)
                  * getBean(Class,Object)
                  
                -延迟查找 spring5.1以上的采用，不然就是如下的ObjectFactory
                  * getBeanProvider(Class)
                  * getBeanProvider(ResolvableType)
                  
           三、根据 名称+类型 查找
 
 
#   依赖查找  集合类型
   
##       通过ListableBeanFactory
       
###          一、根据Bean类型查找
          
               获取同类型Bean名称列表
               
                  * getBeanNamesForType(Class);
                  
               获取同类型Bean实例列表
               
                  * getBeansOfType(Class);
###          二、通过注解查找
          
               获取同类型Bean名称列表
               
                  * getBeanNamesForAnnotation(Class)
                  
               获取同类型Bean实例列表
               
                  * getBeanWithAnnotation(Class)
                  
               获取名称加指定类型

                  * findAnnotationOnBean(str,Class)

