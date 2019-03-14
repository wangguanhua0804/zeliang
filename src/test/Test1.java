import java.util.HashMap;

public class Test1 {
    /*
    *   数组  Map List类型的字段  即使加上了final,数据也可以进行改变
    *   final只是让栈中储存的地址不能改变，而常量的值是和储存地址有关的，如果修改了常量值，地址也会变化，所以无法修改
    *   List、Map在栈中储存的是地址，地址指向的值是可以修改的
    * **/
    protected static final HashMap map=new HashMap();
    protected static final String str="str";
    static {
        map.put("a","a");
        map.put("b","b");
        map.put("c","c");
        map.put("d","d");
    }

}
