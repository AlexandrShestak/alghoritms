import java.util.PriorityQueue;

/*
public class Generics<T> {

    T field;

    public Generics() {

        this.field = new T();
    }
}
*/
class Test {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(1);
        queue.add(2);

        for (Integer integer : queue) {
            System.out.println(integer);
        }

    }
}