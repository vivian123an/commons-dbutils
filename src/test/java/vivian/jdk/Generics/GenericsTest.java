package vivian.jdk.Generics;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GenericsTest<T extends Collection<String>> {
	private T t;
    public GenericsTest(){}
    public GenericsTest(T t){
        this.t = t;
    }
    public T getT(){
        return t;
    }
    public void setT(T t){
        this.t = t;
    }
    public static void main(String[] args) {
        //这样实例化没问题
    	GenericsTest<List<String>> class1 = new GenericsTest<List<String>>();
        //这样实例化就会报错
        //因为class1已经明确他能接受的类型是List
        //class1 = new GenericsTest<ArrayList<String>>();
    }
}
