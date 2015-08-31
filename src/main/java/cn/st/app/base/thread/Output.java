package cn.st.app.base.thread;

/**
 * <p>
 * desciption:
 * </p>
 * 
 * @author coolearth
 * @date 2015年8月31日
 */
public class Output {
    public void print(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            System.out.print(s.charAt(i));
        }
        System.out.println();
    }
}
