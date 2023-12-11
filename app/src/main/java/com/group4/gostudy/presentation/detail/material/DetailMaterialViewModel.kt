package com.group4.gostudy.presentation.detail.material

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.group4.gostudy.data.repository.ChapterRepository
import com.group4.gostudy.model.Chapter
import com.group4.gostudy.utils.ResultWrapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetailMaterialViewModel(private val chapterRepo: ChapterRepository) : ViewModel() {
    private val _chapters = MutableLiveData<ResultWrapper<List<Chapter>>>()
    val chapters: LiveData<ResultWrapper<List<Chapter>>>
        get() = _chapters
    fun getChapter() {
        viewModelScope.launch(Dispatchers.IO) {
            chapterRepo.getChapters().collect {
                _chapters.postValue(it)
            }
        }
    }
}
