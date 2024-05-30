package com.example.nopshop.screen.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nopshop.model.category.CategoryWiseProductsItem
import com.example.nopshop.repository.HomeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(private val repository: HomeRepository): ViewModel(){
    private val _category: MutableLiveData<CategoryWiseProductsItem> by lazy {
        MutableLiveData<CategoryWiseProductsItem>()
    }

    val category:LiveData<CategoryWiseProductsItem>
        get() = _category

    fun getCategories() = viewModelScope.launch{
        try {
            val response = repository.getCategoryWiseProducts()
            _category.value = response.body()
        }catch (e:Exception){

        }
    }
}