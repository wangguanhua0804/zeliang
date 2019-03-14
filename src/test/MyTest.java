import org.junit.Test;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

public class MyTest {
    @Test
    public void run1(){
        List<String> strList = new ArrayList<>();
        for(int i=0;i<1000000;i++){
            strList.add(i+"");
        }
        MyThread myThread1 = new MyThread(5, 1, strList);
        MyThread myThread2 = new MyThread(5,2,strList);
        MyThread myThread3 = new MyThread(5,3,strList);
        MyThread myThread4 = new MyThread(5,4,strList);
        MyThread myThread5 = new MyThread(5,5,strList);
        long time1 = System.currentTimeMillis();
        myThread1.run();
        myThread2.run();
        myThread3.run();
        myThread4.run();
        myThread5.run();
        long time2 = System.currentTimeMillis();
        for (int i = 0; i <strList.size() ; i++) {
            strList.set(i,"");
        }
        long time3 = System.currentTimeMillis();

        System.out.println(time2-time1);
        System.out.println(time3-time2);

    }


    @Test
    public void run2(){
        List<String> list=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        TestList<String,Integer> stringTestList = TestList.create(list, list2);
        Class<? extends TestList> clazz = stringTestList.getClass();
        String name = clazz.getName();
        System.out.println(name);

    }
    @Test
    public void run3(){
        List<String> list1=new ArrayList<>();
        List<Integer> list2=new ArrayList<>();
        String param1Name = list1.getClass().getName();
        String param2Name = list2.getClass().getName();
        System.out.println("param1："+param1Name+"   param2："+param2Name);
        System.out.println(param1Name.equals(param2Name));
    }
}
