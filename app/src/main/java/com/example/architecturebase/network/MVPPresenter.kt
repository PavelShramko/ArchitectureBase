package com.example.architecturebase.network

import android.widget.Toast
import com.example.architecturebase.network.model.Post
import com.example.architecturebase.network.model.UseCase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MVPPresenter(private val mvpView: MVPView) : MVPContract.IPresenter {

    private val retrofit: com.example.architecturebase.adapter.Retrofit = com.example.architecturebase.adapter.Retrofit()
    private val useCase = UseCase()

    override fun getPost() {
        retrofit.postApi.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    response.body()?.let { posts ->
                        mvpView.mainAdapter.items = useCase.createUseCase(posts)
                        mvpView.binding?.listSRL?.isRefreshing = false
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(mvpView.context, t.message, Toast.LENGTH_SHORT).show()
                t.printStackTrace()
                mvpView.binding?.listSRL?.isRefreshing = false
            }
        })
    }
}



