package com.example.daggerp3organizaciamodulei.CreatingVM.easyWay

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggerp3organizaciamodulei.SomeRepository
import com.example.daggerp3organizaciamodulei.messagePart
import com.example.daggerp3organizaciamodulei.messageTag
import javax.inject.Inject

class SomeViewModel(
    // меняющийся параметр, который задаем извне
    private val newsId: String,
    //Некий репозиторий доставляемый из графа зависимостей
    private val someRepository: SomeRepository
) : ViewModel(){

    fun getMessage(){
        Log.d(messageTag, messagePart + "SomeViewModel: " + "c news = " + newsId
                + "и с репозиторием = " + someRepository.someRepository
        )
    }

    class Factory(
        private val newsId: String,
        private val someRepository: SomeRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return SomeViewModel(newsId = newsId, someRepository = someRepository) as T
        }


        //Благодаря @Inject мы можем внести зависимость из граффа зависимостей,
        // а переменный параметр newsId мы внесем в Activity при создании VM.
        class FactoryForFactory @Inject constructor(val someRepository: SomeRepository){
            fun create(newsId: String) : SomeViewModel.Factory{
                return SomeViewModel.Factory(newsId, someRepository = someRepository)
            }
        }
    }
}
