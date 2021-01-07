package com.example.madlevel3task2

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Portal(

    var portalName: String,
    var portalUrl: String
) : Parcelable {
    companion object {
        val NAMES = arrayOf(
            "hva",
            "at5",
            "nos"
        )
        val URLS = arrayOf(
            "https://www.pathe.nl/bioscoopagenda",
            "https://www.pathe.nl/bioscoopagenda",
            "https://www.pathe.nl/bioscoopagenda"
        )
    }
}