package com.example.tvmazemvvm.data.network

import com.example.tvmazemvvm.presentation.utils.Resource
import retrofit2.Response

abstract class BaseDataSource {
    protected suspend fun <T> getResult(call : suspend () -> Response<T>) : Resource<T & Any>{
        try {
            val response = call()
            if(response.isSuccessful){
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error("${response.code()} ${response.message()}")
        }catch (e : java.lang.Exception){
            return error(e.message ?: e.toString())
        }
    }

    private fun <T>error(message : String) : Resource<T>{
        return Resource.error("call failed with the following error : $message")
    }
}