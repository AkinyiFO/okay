package com.ibrajix.directcurrencyconverter.viewmodel

import android.content.ClipData
import androidx.lifecycle.*
import com.ibrajix.directcurrencyconverter.data.SingleLogDao
import com.ibrajix.directcurrencyconverter.data.SingleLogEntity
import kotlinx.coroutines.launch

class LogViewModel(private val singleLogDao: SingleLogDao) : ViewModel() {

    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<SingleLogEntity>> = singleLogDao.getItems().asLiveData()
    /**
     * Inserts the new Item into database.
     */
    fun addNewItem(amount: String, from: String, to: String) {
        val newItem = getNewItemEntry(amount, from, to)
        insertItem(newItem)
    }

    /**
     * Launching a new coroutine to insert an item in a non-blocking way
     */
    private fun insertItem(singleLogEntity: SingleLogEntity) {
        viewModelScope.launch {
            singleLogDao.insert(singleLogEntity)
        }
    }

    /**
     * Returns true if the EditTexts are not empty
     */
    fun isEntryValid(amount: String, from: String, to: String): Boolean {
        if (amount.isBlank() || from.isBlank() || to.isBlank()) {
            return false
        }
        return true
    }

    /**
     * Returns an instance of the [Item] entity class with the item info entered by the user.
     * This will be used to add a new entry to the Inventory database.
     */
    private fun getNewItemEntry(amount: String, from: String, to: String): SingleLogEntity {
        return SingleLogEntity(
            amount = amount.toDouble(),
            from = from,
            to = to
        )
    }
}

/**
 * Factory class to instantiate the [ViewModel] instance.
 */
class LogViewModelFactory(private val singleLogDao: SingleLogDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LogViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return LogViewModel(singleLogDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
