package com.keyvani.movieapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.keyvani.movieapplication.db.MovieEntity
import com.keyvani.movieapplication.repository.DetailRepository
import com.keyvani.movieapplication.response.datail.ResponseDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private  val repository: DetailRepository):ViewModel() {

    //Api
    val detailMovie = MutableLiveData<ResponseDetail>()
    val loading = MutableLiveData<Boolean>()

    fun loadDetailMovie(id :Int)= viewModelScope.launch {
        loading.postValue(true)
        val response = repository.detailMovie(id)
        if (response.isSuccessful){
            detailMovie.postValue(response.body())
        }
        loading.postValue(false)
    }

    //Database
    val isFavorite = MutableLiveData<Boolean>()
    suspend fun existMovie(id:Int) = withContext(viewModelScope.coroutineContext){
        repository.existMovie(id)
    }
     fun favoriteMovie(id:Int ,entity: MovieEntity) = viewModelScope.launch{
        val exists = repository.existMovie(id)
        if(exists){
            isFavorite.postValue(false)
            repository.deleteMovie(entity)
        }else{
            isFavorite.postValue(true)
            repository.insertMovie(entity)
        }
    }

}