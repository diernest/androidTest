package com.example.ejercicio.core.data.remote.extensions

import retrofit2.HttpException
import java.util.concurrent.CancellationException

inline fun <T, R> T.resultOf(block: T.() -> R): Result<R> {
    return try {
        Result.success(block())
    } catch (e: CancellationException){
        throw  e
    } catch (e: HttpException){
        Result.failure(e)
        /*val errorMessage = e.parseError()
        if(errorMessage == null) {
            Result.failure(e)
        } else {
            Result.failure(NetworkErrorException(errorMessage))
        }*/
    } catch (e: Exception){
        Result.failure(e)
    }
}