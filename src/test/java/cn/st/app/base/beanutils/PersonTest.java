package cn.st.app.base.beanutils;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConversionException;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;
import org.junit.Test;

import cn.st.app.base.beanutils.Person;

public class PersonTest {
	@Test
	public void test1() throws IllegalAccessException, InvocationTargetException{
		String name="st";
		String password="123";
		String age="21";
		Person p=new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
	}
	
	/***
	 * 以下代码有误，BeanUtil 支持8中基本类型的转化
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Test
	public void test2() throws IllegalAccessException, InvocationTargetException{
		String name="st";
		String password="123";
		String age="21";
		String birthday="1989-01-01";
		Person p=new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);
		BeanUtils.setProperty(p, "birthday", birthday);
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}
	/**
	 * 为了支持String-->Date类型转化，需要注册转换器
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	@Test
	public void test3() throws IllegalAccessException, InvocationTargetException{
		String name="st";
		String password="123";
		String age="21";
		String birthday="1989-01-01";
		ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if(value==null){
					return null;
				}
				if(!(value instanceof String)){
					throw new ConversionException("支持string类型");
				}
				String str=(String)value;
				if(str.trim().equals("")){
					return null;
				}
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				try {
					return format.parse(str);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		Person p=new Person();
		BeanUtils.setProperty(p, "name", name);
		BeanUtils.setProperty(p, "password", password);
		BeanUtils.setProperty(p, "age", age);
		BeanUtils.setProperty(p, "birthday", birthday);
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}
	
	
	@Test
	public void test4() throws IllegalAccessException, InvocationTargetException{
		Map<String,String> map=new HashMap<String,String>();
		String name="st";
		String password="123";
		String age="21";
		String birthday="1989-01-01";
		map.put("name",name);
		map.put("password",password);
		map.put("age",age);
		map.put("birthday",birthday);
		ConvertUtils.register(new Converter() {
			
			public Object convert(Class type, Object value) {
				if(value==null){
					return null;
				}
				if(!(value instanceof String)){
					throw new ConversionException("支持string类型");
				}
				String str=(String)value;
				if(str.trim().equals("")){
					return null;
				}
				SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
				try {
					return format.parse(str);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					throw new RuntimeException(e);
				}
			}
		}, Date.class);
		Person p=new Person();
		BeanUtils.populate(p, map);
		System.out.println(p.getName());
		System.out.println(p.getPassword());
		System.out.println(p.getAge());
		System.out.println(p.getBirthday());
	}
}
