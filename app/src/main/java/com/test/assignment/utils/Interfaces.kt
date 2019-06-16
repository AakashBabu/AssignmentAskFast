package com.test.assignment.utils

interface Pager{
    fun pageEnded()
}

interface ResponceHandler {
    public fun handleSuccess(obj:Any)
    public fun handleError(t:Throwable,retry:Boolean)
}