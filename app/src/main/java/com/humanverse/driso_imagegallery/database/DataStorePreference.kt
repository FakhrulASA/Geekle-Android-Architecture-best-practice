package com.humanverse.driso_imagegallery.database

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

object DataStorePreference {
    val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "current_app_flavor")
    val APP_FLAVOR = intPreferencesKey("app_flavor")
    fun setCurrentAppFlavor(context: Context, flavor: Int) {
        CoroutineScope(Dispatchers.IO).launch {
            context.dataStore.edit { current_app_flavor ->
                current_app_flavor[APP_FLAVOR] = flavor
            }
        }
    }

    fun getCurrentAppFlavor(context: Context): Flow<Int> {
        return context.dataStore.data
            .map { preferences ->
                // No type safety.
                preferences[APP_FLAVOR] ?: 0
            }
    }

}