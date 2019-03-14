import java.util.List;

public class MyThread implements Runnable {
    private Integer threadSum;
    private Integer number;
    private List list;
    private Integer begin;
    private Integer end;

    public Integer getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        this.begin = begin;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public MyThread(Integer threadSum, Integer number, List list, Integer begin, Integer end) {

        this.threadSum = threadSum;
        this.number = number;
        this.list = list;
        this.begin = begin;
        this.end = end;
    }

    public Integer getThreadSum() {
        return threadSum;
    }

    public void setThreadSum(Integer threadSum) {
        this.threadSum = threadSum;
    }

    public MyThread(Integer threadSum, Integer number, List list) {

        this.threadSum = threadSum;
        this.number = number;
        this.list = list;
    }

    public MyThread(Integer number, List list) {
        this.number = number;
        this.list = list;
    }

    public List getList() {
        return list;
    }

    public void setList(List list) {
        this.list = list;
    }

    public MyThread(Integer number) {
        this.number = number;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void init() {
        Integer size = list.size();
        Integer last = size % (threadSum - 1);
        Integer part = (size - last) / threadSum - 1;
        begin = part * (number - 1);
        end = part * number;
        if (number == threadSum) {
            end = size;
        }
    }

    @Override
    public void run() {
        init();
        for (int i = begin; i < end; i++) {
            list.set(i,"");
        }
        //System.out.println(begin+">>>"+end);
    }
}
