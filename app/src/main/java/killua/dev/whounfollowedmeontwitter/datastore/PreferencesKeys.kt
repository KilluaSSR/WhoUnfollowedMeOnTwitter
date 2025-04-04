package killua.dev.whounfollowedmeontwitter.datastore

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import killua.dev.whounfollowedmeontwitter.ui.theme.ThemeMode

val APPLICATION_USER_USERCT0_KEY =
    stringPreferencesKey("app_user_userct0")
val APPLICATION_USER_USERAUTH_KEY =
    stringPreferencesKey("app_user_userauth")
val APPLICATION_USER_ID =
    stringPreferencesKey("app_user_id")
val PROTECT_ME = booleanPreferencesKey("protect_me")
val THEME_MODE = stringPreferencesKey("theme_mode")
//Read
fun Context.readApplicationUserCt0() = readStoreString(key = APPLICATION_USER_USERCT0_KEY, defValue = "")
fun Context.readApplicationUserAuth() = readStoreString(key = APPLICATION_USER_USERAUTH_KEY, defValue = "")
fun Context.readApplicationUserID() = readStoreString(key = APPLICATION_USER_ID, defValue = "")
fun Context.readProtectMe() = readStoreBoolean(key = PROTECT_ME, defValue = false)
fun Context.readTheme() = readStoreString(THEME_MODE, defValue = ThemeMode.SYSTEM.name)

//Write

suspend fun Context.writeApplicationUserCt0(ct0: String) = saveStoreString(key = APPLICATION_USER_USERCT0_KEY, value = ct0)
suspend fun Context.writeApplicationUserAuth(auth: String) = saveStoreString(key = APPLICATION_USER_USERAUTH_KEY, value = auth)
suspend fun Context.writeApplicationUserID(id: String) = saveStoreString(APPLICATION_USER_ID, id)
suspend fun Context.writeProtectMe(set: Boolean) = saveStoreBoolean(PROTECT_ME, set)
suspend fun Context.writeTheme(theme: String) = saveStoreString(THEME_MODE, theme)

