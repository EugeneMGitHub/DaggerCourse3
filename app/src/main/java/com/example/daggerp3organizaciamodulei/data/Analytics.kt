package com.example.daggerp3organizaciamodulei.data

import dagger.Provides
import javax.inject.Inject

class Analytics @Inject constructor() {

    fun trackScreenShow() {
    }

    fun trackNewsRequest(newsId: String) {
        // Do nothing
    }
}
