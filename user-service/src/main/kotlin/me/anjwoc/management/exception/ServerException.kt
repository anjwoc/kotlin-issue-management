package me.anjwoc.management.exception

sealed class ServerException(
    val code: Int,
    override val message: String
) : RuntimeException(message)
