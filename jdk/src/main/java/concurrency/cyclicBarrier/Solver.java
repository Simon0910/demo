package concurrency.cyclicBarrier;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * JDK6中的示例用法：下面是一个在并行分解设计中使用 barrier 的例子。给出示意代码结构，不可运行。
 */
class Solver {
    final int N;
    final float[][] data;
    final CyclicBarrier barrier;

    class Worker implements Runnable {
        int myRow;

        public Worker(int row) {
            myRow = row;
        }

        @Override
        public void run() {
            while (!done()) {
                processRow(myRow);
                try {
                    barrier.await();
                } catch (InterruptedException ex) {
                    return;
                } catch (BrokenBarrierException ex) {
                    return;
                }
            }
        }

        private void processRow(int myRow) {
        }

        private boolean done() {
            return true;
        }

    }

    public Solver(float[][] matrix) {
        data = matrix;
        N = matrix.length;

        barrier = new CyclicBarrier(N, new Runnable() {
            @Override
            public void run() {
                mergeRows();
            }

            private void mergeRows() {
            }
        });

        for (int i = 0; i < N; ++i) {
            new Thread(new Worker(i)).start();
        }
        waitUntilDone();
    }

    private void waitUntilDone() {
    }
}