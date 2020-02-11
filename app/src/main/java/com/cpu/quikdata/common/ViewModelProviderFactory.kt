package com.cpu.quikdata.common

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

@Suppress("UNCHECKED_CAST")
class ViewModelProviderFactory
    @Inject constructor(creators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>)
    : ViewModelProvider.Factory {

    private val mCreators: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>> = creators

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        var creator = mCreators.get(modelClass)
        if (creator == null) {

            // If the viewmodel has not been created,
            // loop through allowable keys (or allowed classes with the @ViewModelKey2)
            mCreators.entries.forEach {

                // If allowed, set the Provider<ViewModel>
                if (modelClass.isAssignableFrom(it.key)) {
                    creator = it.value
                    return@forEach
                }
            }
        }

        // If this is not one of the allowed keys, throw an exception
        requireNotNull(creator) { "Unknown ViewModel class $modelClass" }

        try {
            return creator!!.get() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }
    }


}