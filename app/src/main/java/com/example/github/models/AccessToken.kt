package com.example.github.models

import com.google.gson.annotations.SerializedName

class AccessToken {

    @SerializedName("access_token")
    private var accessToken: String? = null

    @SerializedName("token_type")
    private var tokenType: String? = null

    fun getAccessToken() : String? {
        return accessToken
    }

    fun getTokenType(): String? { // OAuth requires uppercase Authorization HTTP header value for token type
        if (!Character.isUpperCase(tokenType!![0])) {
            tokenType = Character
                .toString(tokenType!![0])
                .toUpperCase() + tokenType!!.substring(1)
        }
        return tokenType
    }
}