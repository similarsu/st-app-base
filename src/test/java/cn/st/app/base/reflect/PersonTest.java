package cn.st.app.base.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import cn.st.app.base.reflect.Person;

public class PersonTest {
	/**
	 * 解剖构造函数   public Person()
	 * @throws Exception 
	 */
	@Test
	public void test1() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Constructor c=clazz.getConstructor();
		c.newInstance();
	}
	
	/**
	 * 解剖构造函数   public Person(String name)
	 * @throws Exception 
	 */
	@Test
	public void test2() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Constructor c=clazz.getConstructor(String.class);
		c.newInstance("苏同");
	}
	
	/**
	 * 解剖构造函数   public Person(String name,int age)
	 * @throws Exception 
	 */
	@Test
	public void test3() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Constructor c=clazz.getConstructor(String.class,int.class);
		c.newInstance("苏同",25);
	}
	
	/**
	 * 解剖构造函数   private Person(List list)
	 * @throws Exception 
	 */
	@Test
	public void test4() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		//private 要调用getDeclaredConstructor
		Constructor c=clazz.getDeclaredConstructor(List.class);
		//暴力访问
		c.setAccessible(true);
		List list=new ArrayList();
		list.add(1);
		list.add(2);
		c.newInstance(list);
	}
	
	/**
	 * 反射方法 public void eat()
	 * @throws Exception
	 */
	@Test
	public void test5() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Method method=clazz.getMethod("eat");
		method.invoke(new Person());
	}

	/**
	 * 反射方法 public void eat(String food)
	 * @throws Exception
	 */
	@Test
	public void test6() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Method method=clazz.getMethod("eat",String.class);
		method.invoke(new Person(),"苏同");
	}
	
	/**
	 * 反射方法 private void eat(List food)
	 * @throws Exception
	 */
	@Test
	public void test7() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		//private getDeclaredMethod
		Method method=clazz.getDeclaredMethod("eat",List.class);
		//暴力访问
		method.setAccessible(true);
		List list=new ArrayList();
		list.add(1);
		list.add(2);
		method.invoke(new Person(),list);
	}
	
	/**
	 * 反射方法 private static String root(String name)
	 * @throws Exception
	 */
	@Test
	public void test8() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		//private getDeclaredMethod
		Method method=clazz.getDeclaredMethod("root",String.class);
		//暴力访问
		method.setAccessible(true);
		//static 可以 null
		String value=(String) method.invoke(null,"收到回复");
		System.out.println(value);
	}
	
	/**
	 * 反射方法 public static void main(String[] args)
	 * @throws Exception
	 */
	@Test
	public void test9() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Method method=clazz.getMethod("main",String[].class);
		String[] strs=new String[]{"ab","bc"};
		//由于兼容1.4的版本问题，main方法执行比较特别，可用以下两种方法
		//method.invoke(null,(Object)strs);
		method.invoke(null,new Object[]{strs});
	}
	
	/**
	 * 反射字段  public int age=0
	 * @throws Exception
	 */
	@Test
	public void test10() throws Exception{
		Person p=new Person();
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Field field=clazz.getField("age");
		System.out.println((Integer)field.get(p));
		field.set(p, 10);
		System.out.println(p.age);
	}
	
	/**
	 * 反射字段  private String name="sutong";
	 * @throws Exception
	 */
	@Test
	public void test11() throws Exception{
		Person p=new Person();
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		//private getDeclaredField
		Field field=clazz.getDeclaredField("name");
		//暴力访问
		field.setAccessible(true);
		System.out.println((String)field.get(p));
	}
	
	/**
	 * 反射字段  public static boolean eating=true
	 * @throws Exception
	 */
	@Test
	public void test12() throws Exception{
		Class clazz=Class.forName("com.st.app.base.reflect.Person");
		Field field=clazz.getField("eating");
		//static 可以 为 null
		System.out.println((Boolean)field.get(null));
	}
	
}
