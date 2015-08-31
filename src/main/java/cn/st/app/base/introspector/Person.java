package cn.st.app.base.introspector;
/**
 * javabean 属性 
 * set 或  get 方法
 * @author coolearth
 *	如下 含有5个属性
 *	name,password,age,ab,class(Person默认继承Object类，而Object含有getClass()方法)
 */
public class Person {
	private String name;
	private String password;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getAb(){
		return "ab";
	}
}
