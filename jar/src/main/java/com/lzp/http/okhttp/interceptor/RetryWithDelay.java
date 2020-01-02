package com.lzp.http.okhttp.interceptor;

import java.util.concurrent.TimeUnit;

/**
 * 如果你是使用OkHttp+Retrofit+RxJava，
 * 你也可以使用retryWhen操作符：
 *  retryWhen(new RetryWithDelay())来实现重试机制
 *
 * @author liuzhipeng
 * @description
 * @create 2019-12-10 13:31
 */
// public class RetryWithDelay implements Func1<Observable<? extends Throwable>, Observable<?>> {
//     private final int maxRetries;
//     private final int retryDelayMillis;
//     private int retryCount;
//
//     public RetryWithDelay(int maxRetries, int retryDelayMillis) {
//         this.maxRetries = maxRetries;
//         this.retryDelayMillis = retryDelayMillis;
//     }
//
//     @Override
//     public Observable<?> call(Observable<? extends Throwable> attempts) {
//         return attempts.flatMap(new Func1<Throwable, Observable<?>>() {
//             @Override
//             public Observable<?> call(Throwable throwable) {
//                 if (++retryCount <= maxRetries) {
//                     // When this Observable calls onNext, the original Observable will be retried (i.e.
//                     // re-subscribed).
//                     // LogUtil.print("get error, it will try after " + retryDelayMillis + " millisecond, retry " +
//                     //         "count " + retryCount);
//                     return Observable.timer(retryDelayMillis,
//                             TimeUnit.MILLISECONDS);
//                 }
//                 // Max retries hit. Just pass the error along.
//                 return Observable.error(throwable);
//             }
//         });
//     }
// }
