package exercise3;

import java.util.ArrayList;
import java.util.List;

public class PossibleSolution1 {

    private List<String> data = new ArrayList<String>();

    public PossibleSolution1(List<String> startingData) {
        this.data = startingData;
    }
    public List<String> transform(List<StringsTransformer.StringFunction> functions) throws
            InterruptedException {

        for (final StringsTransformer.StringFunction f : functions) {
            forEach(f);
        }
        return data;
    }

    private void forEach(StringsTransformer.StringFunction function) throws InterruptedException {
        List<Thread> threads = new ArrayList<Thread>();
        for (int i=0; i < data.size(); i++) {
            Thread t = new Thread(new MyRunnable(i, function));
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
    }
    public class MyRunnable implements Runnable {
        Integer index;
        StringsTransformer.StringFunction function;
        public MyRunnable(Integer index, StringsTransformer.StringFunction function) {
            this.index = index;
            this.function = function;
        }

        public void run() {
            data.set(index , function.transform(data.get(index)));
        }
    }
    public static interface StringFunction {
        public String transform(String str);
    }
}
