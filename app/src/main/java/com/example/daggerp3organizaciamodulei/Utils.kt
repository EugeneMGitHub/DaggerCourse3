package com.example.daggerp3organizaciamodulei

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlin.properties.ReadOnlyProperty


fun stringArgs(key: String): ReadOnlyProperty<Fragment, String> {
    return ReadOnlyProperty { thisRef, _ ->
        val args = thisRef.requireArguments()
        require(args.containsKey(key)) { "Arguments don't contain key '$key'" }
        requireNotNull(args.getString(key))
    }
}

fun FragmentManager.isFragmentContainerEmpty(@IdRes id: Int): Boolean {
    return findFragmentById(id) == null
}


const val messageTag = "MessageTag"
const val messagePart = "This is message from "
const val someDependencyFromOutside = "Some dependency from outside"

