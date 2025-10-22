package lesson11;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

class ThreadPool {
    private final BlockingQueue<Runnable> taskQueue;
    private final int threadCount;
    private final List<Worker> workers;
    private volatile boolean isRunning = true;

    public ThreadPool(int threadCount) {
        this.threadCount = threadCount;
        this.taskQueue = new LinkedBlockingQueue<>();
        this.workers = new ArrayList<>();

        for (int i = 0; i < threadCount; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable task) {
        if (!isRunning)
            throw new IllegalStateException("Thread pool is shut down");
        taskQueue.add(task);
    }

    private class Worker extends Thread {
        public void run() {
            while (true) {
                Runnable task;
                try {
                    task = taskQueue.take();
                } catch (InterruptedException e) {
                    if (!isRunning) return;
                    continue;
                }

                try {
                    task.run();
                } catch (RuntimeException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void shutdown() {
        isRunning = false;
        for (Worker worker : workers) {
            worker.interrupt();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ThreadPool pool = new ThreadPool(3); // исправлено имя класса

        for (int i = 1; i <= 10; i++) {
            int taskId = i;
            pool.submit(() -> {
                System.out.println("Task " + taskId + " выполняется потоком " + Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            });
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        pool.shutdown();
        System.out.println("Пул потоков завершён.");
    }
}
