package cn.st.app.base.reflect;

import java.util.List;

public class Person {
	public Person(){
		System.out.println("()");
	}
	public Person(String name){
		System.out.println(name);
	}
	
	public Person(String name,int age){
		System.out.println(name+":"+age);
	}
	
	private Person(List list){
		System.out.println(list);
	}
	
	public void eat(){
		System.out.println("()");
	}
	
	public void eat(String food){
		System.out.println(food);
	}
	
	private void eat(List food){
		System.out.println(food);
	}
	
	private static String root(String name){
		return name;
	}
	
	public static void main(String[] args) {
		System.out.println("main");
	}
	
	public int age=0;
	private String name="sutong";
	public static boolean eating=true;
}
