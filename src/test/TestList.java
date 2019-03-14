import java.util.Collection;
import java.util.List;

public class TestList<T,R> {
    private List<T> listT;
    private List<R> listR;

    public static <T,R>TestList<T,R> create(List<T> listT, List<R> listR) {
        return new TestList<T,R>(listT,listR);
    }
    public List<T> getListT() {
        return listT;
    }

    public void setListT(List<T> listT) {
        this.listT = listT;
    }

    public List<R> getListR() {
        return listR;
    }

    public void setListR(List<R> listR) {
        this.listR = listR;
    }

    public TestList(List<T> listT, List<R> listR) {

        this.listT = listT;
        this.listR = listR;
    }
}
