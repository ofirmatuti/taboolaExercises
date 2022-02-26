package exercise3;

import java.util.*;
/*
Jimmy was tasked with writing a class that takes a base list of strings and a series of
transformations and applies them, returning the end result.
To better utilize all available resources, the solution was done in a multi-threaded fashion.
Explain the problems with this solution, and offer 2 alternatives. Discuss the advantages of each
approach.
 */

public class StringsTransformer {

    private List<String> data = new ArrayList<String>();

    public StringsTransformer(List<String> startingData) {
        this.data = startingData;
    }
    public List<String> transform(List<StringFunction> functions) throws
            InterruptedException {
        List<Thread> threads = new ArrayList<Thread>();
        for (final StringFunction f : functions) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    forEach(f);
                }
            });
            t.start();
            threads.add(t);
        }
        for (Thread t : threads) {
            t.join();
        }
        return data;
    }

    private void forEach(StringFunction function) {
        List<String> newData = new ArrayList<String>();
        for (String str : data) {
            newData.add(function.transform(str));
        }
        data = newData;
    }

    public static interface StringFunction {
        public String transform(String str);
    }
}
