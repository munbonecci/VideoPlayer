package com.munbonecci.videoplayer.commons

data class Error(
    val errorCode: Int = 0,
    val errorMessage: String = ""
) {
    override fun toString(): String = errorMessage
}