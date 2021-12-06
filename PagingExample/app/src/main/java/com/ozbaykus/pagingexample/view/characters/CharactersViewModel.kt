package com.ozbaykus.pagingexample.view.characters

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.ozbaykus.pagingexample.data.model.Character
import com.ozbaykus.pagingexample.data.repository.CharactersRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(
    private val repository: CharactersRepository
) : ViewModel() {

    private var charactersResultLiveData: LiveData<PagingData<Character>>? = null

    fun charactersLiveData(): LiveData<PagingData<Character>> {
        val newResultLiveData: LiveData<PagingData<Character>> =
            repository.getCharacters().cachedIn(viewModelScope)
        charactersResultLiveData = newResultLiveData
        return newResultLiveData
    }
}