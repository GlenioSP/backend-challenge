package com.invillia.acme.exception

open class BaseException : RuntimeException {

    constructor()

    constructor(msg: String) : super(msg)

    constructor(msg: String, throwable: Throwable) : super(msg, throwable)

    constructor(throwable: Throwable) : super(throwable)

    constructor(msg: String, throwable: Throwable, enableSuppression: Boolean, writableStackTrace: Boolean) : super(msg, throwable, enableSuppression, writableStackTrace)
}