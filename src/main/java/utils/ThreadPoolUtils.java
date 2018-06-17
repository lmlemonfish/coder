package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPoolUtils {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();
    
    public static void execute(Runnable r) {
    	threadPool.execute(r);
    }
    
    public static void close() {
    	threadPool.shutdown();
    }
}
