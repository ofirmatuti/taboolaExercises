package exercise3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/*
    we can use a multi-threaded approach
    we will run in different thread for each index in data
    the thread will apply all the transformations for the given index
    this kind of solution is great for scenarios where the transform method is lightweight.
 */
public class PossibleSolution2 {
    private List<String> data = new ArrayList<String>();

    public PossibleSolution2(List<String> startingData) {
        this.data = startingData;
    }

    public List<String> transform(List<StringsTransformer.StringFunction> functions){
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newScheduledThreadPool(10);
        boolean terminated = false;
        for (int i=0; i < data.size(); i++) {
            executor.execute(new MyRunnable(i, functions));
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
        return data;
    }

    public class MyRunnable implements Runnable {
        Integer index;
        List<StringsTransformer.StringFunction> functions;
        public MyRunnable(Integer index, List<StringsTransformer.StringFunction> functions) {
            this.index = index;
            this.functions = functions;
        }

        public void run() {
            for (final StringsTransformer.StringFunction f : functions) {
                data.set(index , f.transform(data.get(index)));
            }
        }
    }
    public static interface StringFunction {
        public String transform(String str);
    }
}
