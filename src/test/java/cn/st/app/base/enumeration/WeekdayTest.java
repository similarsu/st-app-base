package cn.st.app.base.enumeration;

import org.junit.Test;

import cn.st.app.base.enumeration.Weekday;

public class WeekdayTest {
	@Test
	public void test(){
		for(Weekday week : Weekday.values()){
			System.out.println(week.en()+":"+week.cn());
		}
	}

}
