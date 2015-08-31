package cn.st.app.base.introspector;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import cn.st.app.base.introspector.Person;

public class PersonTest {
	/***
	 * 使用内省Introspector 获取bean 属性
	 * @throws IntrospectionException
	 */
	@Test
	public void test1() throws IntrospectionException{
		BeanInfo info=Introspector.getBeanInfo(Person.class);
		PropertyDescriptor[] descriptors=info.getPropertyDescriptors();
		for(PropertyDescriptor descriptor:descriptors){
			System.out.println(descriptor.getName());
		}
	}
	/***
	 * 去除继承Object的class属性
	 * @throws IntrospectionException
	 */
	@Test
	public void test2() throws IntrospectionException{
		BeanInfo info=Introspector.getBeanInfo(Person.class,Object.class);
		PropertyDescriptor[] descriptors=info.getPropertyDescriptors();
		for(PropertyDescriptor descriptor:descriptors){
			System.out.println(descriptor.getName());
		}
	}
	
	/***
	 * 获取age属性
	 * @throws IntrospectionException
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException 
	 */
	@Test
	public void test3() throws IntrospectionException, IllegalArgumentException, IllegalAccessException, InvocationTargetException{
		Person person=new Person();
		PropertyDescriptor descriptor=new PropertyDescriptor("age",Person.class);
		System.out.println(descriptor.getPropertyType());
		//setAge(int age)
		Method method=descriptor.getWriteMethod();
		method.invoke(person, 12);
		//getAge()
		method=descriptor.getReadMethod();
		System.out.println(method.invoke(person));
		//System.out.println(person.getAge());
	}
}
