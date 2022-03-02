package exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
we can use a multi-threaded approach on the foreach method instead of transform method.
transform method will iterate over functions synchronously.
foreach method will work with a different thread for different elements in data.
since different elements are stored in different locations in memory, there is no danger for data corruption.
this kind of solution is great for scenarios where the transform method is heavy.
 */
public class PossibleSolution1 {

    private List<String> data = new ArrayList<String>();

    public PossibleSolution1(List<String> startingData) {
        this.data = startingData;
    }

    public List<String> transform(List<StringsTransformer.StringFunction> functions){
        for (final StringsTransformer.StringFunction f : functions) {
            forEach(f);
        }
        return data;
    }

    private void forEach(StringsTransformer.StringFunction function) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        boolean terminated = false;
        for (int i=0; i < data.size(); i++) {
            executor.execute(new MyRunnable(i, function));
        }
        executor.shutdown();
        try {
            terminated = executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            System.out.println("interrupted while waiting for all subtask to run");
        }
        if(!terminated){
            //TODO create timeout exception
            System.out.println("timeout - did not finish executing the functions");
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
