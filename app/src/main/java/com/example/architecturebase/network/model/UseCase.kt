package com.example.architecturebase.network.model

class UseCase {
    //Правильно понял, когда сказал, что нужно вынести логику в отдельный UseCase?

    fun createUseCase(posts: List<Post>):  List<Post>{
        return posts.filter {
            !it.title.startsWith("H")
        }.map {
            it.copy(title = it.title + "appendix")
        }.sortedBy {
            it.title
        }.subList(0, posts.size - 3)
    }
}