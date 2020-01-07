package com.sx.mvp.starter.http.function

import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.util.concurrent.TimeUnit
import java.util.concurrent.TimeoutException

/**
 * @author sunxin
 * @date 2020-01-07 18:28
 * @desc 请求重连
 */
class RetryWhenDelay : Function<Observable<out Throwable>, Observable<*>> {

    /**
     * 重试次数
     */
    private var maxRetryCount = 3

    /**
     * 重试等待时间
     */
    private var retryDelayMillis: Long = 3000

    @Throws(Exception::class)
    override fun apply(observable: Observable<out Throwable>): Observable<*> {
        return observable
            .zipWith(Observable.range(1, maxRetryCount + 1),
                BiFunction<Throwable, Int, Wrapper> { t1, t2 -> Wrapper(t2, t1) })
            .flatMap { wrapper ->
                val t = wrapper.throwable
                if ((t is ConnectException
                            || t is SocketTimeoutException
                            || t is TimeoutException
                            || t is HttpException)
                    && wrapper.index < maxRetryCount + 1
                ) {
                    Observable.timer(retryDelayMillis * wrapper.index, TimeUnit.MILLISECONDS)
                } else Observable.error<Any>(wrapper.throwable)
            }
    }


    private inner class Wrapper(val index: Int, val throwable: Throwable)

}