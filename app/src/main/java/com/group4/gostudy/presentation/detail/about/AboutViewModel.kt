package com.group4.gostudy.presentation.detail.about

/*
class AboutViewModel(
    private val detailRepository: DetailRepository
) : ViewModel() {

    private val _module = MutableLiveData<ResultWrapper<List<Module>>>()

    val modules: LiveData<ResultWrapper<List<Module>>>
        get() = _module

    fun getModule() {
        viewModelScope.launch(Dispatchers.IO) {
            detailRepository.getModule().collect {
                _module.postValue(it)
            }
        }
    }
}
*/
