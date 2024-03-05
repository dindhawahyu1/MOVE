package com.example.move.sign.sign_in

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class User (
    var Email: String ?="",
    var Name: String ?="",
    var Password: String ?="",
    var Url: String ?="",
    var Username: String ?="",
    var Saldo: String ?=""
): Parcelable