package cn.st.app.base.enumeration;
/***
 * 枚举
 * 构造函数必须是private
 * 
 * 抽象方法都要实现
 * @author coolearth
 *
 */
public enum Weekday {
	MON("Monday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期一";
		}
	},TUE("Tuesday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期二";
		}
		
	},WED("Wednesday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期三";
		}
		
	},THUR("Thursday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期四";
		}
		
	},FRI("Friday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期五";
		}
		
	},SAT("Saturday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期六";
		}
		
	},SUN("Sunday"){

		@Override
		public String cn() {
			// TODO Auto-generated method stub
			return "星期日";
		}
		
	};
	public abstract String cn();
	
	private String en;
	private Weekday(String en){
		this.en=en;
	}
	
	public String en(){
		return en;
	}
}
