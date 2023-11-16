package edu.ap.mobiledevelopmentproject.ui.fields

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FieldsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is fields Fragment"
    }
    val text: LiveData<String> = _text
}