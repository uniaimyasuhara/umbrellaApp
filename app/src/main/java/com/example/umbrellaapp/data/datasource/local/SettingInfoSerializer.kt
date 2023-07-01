package com.example.umbrellaapp.data.datasource.local

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.example.umbrellaapp.SettingInfo
import com.google.protobuf.InvalidProtocolBufferException
import java.io.InputStream
import java.io.OutputStream

object SettingInfoSerializer : Serializer<SettingInfo> {
    const val DATA_STORE_FILE_NAME = "setting_info.pb"
    override val defaultValue: SettingInfo = SettingInfo.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): SettingInfo {
        try {
            return SettingInfo.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SettingInfo, output: OutputStream) = t.writeTo(output)

}

val Context.SettingInfoDataStore: DataStore<SettingInfo> by dataStore(
    fileName = SettingInfoSerializer.DATA_STORE_FILE_NAME,
    serializer = SettingInfoSerializer
)

