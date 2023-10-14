package com.example.android_programming.model

import android.os.Parcel
import android.os.Parcelable
import com.example.android_programming.R


data class SneakerItem(
    val imageId: Int,
    val name: String?,
    val price: Double
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString(),
        parcel.readDouble()
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(imageId)
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<SneakerItem> {
        override fun createFromParcel(parcel: Parcel): SneakerItem {
            return SneakerItem(parcel)
        }

        override fun newArray(size: Int): Array<SneakerItem?> {
            return arrayOfNulls(size)
        }
    }
}

fun getSneakers(): List<SneakerItem> {
    return listOf(
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
        SneakerItem(R.drawable.sneaker, "Jordan", 159.99),
    )
}
