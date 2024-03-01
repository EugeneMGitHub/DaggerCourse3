package com.example.daggerp3organizaciamodulei.CreatingVM.withAssisted

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.daggerp3organizaciamodulei.SomeRepository
import com.example.daggerp3organizaciamodulei.messagePart
import com.example.daggerp3organizaciamodulei.messageTag
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class ViewModelwithAssisted(
    // меняющийся параметр, который задаем извне руками в активити
    private val newsId: String,
    //Некий репозиторий доставляемый из графа зависимостей
    private val someRepository: SomeRepository
) : ViewModel(){

    fun getMessage(){
        Log.d(
            messageTag, messagePart + "ViewModelwithAssisted: " + "c news = " + newsId
                    + " и с репозиторием = " + someRepository.someRepository
        )
    }

    class Factory @AssistedInject constructor(
        // Помечаем какой параметр будет доставляться вручную
        @Assisted(value = "newsId") private val newsId: String,
        // Обычный параметр доставляется из графа зависимостей
        private val someRepository: SomeRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return ViewModelwithAssisted(newsId = newsId, someRepository = someRepository) as T
        }

        @AssistedFactory
        interface FactoryForFactory {
            fun create(@Assisted(value = "newsId") newsId: String) : ViewModelwithAssisted.Factory
        }
    }
}