package com.e_store

import android.app.Application
import android.content.Context

public lateinit var currentPage: String

class Application: Application() {
    fun setCurrentPage(page: String) {
        currentPage = page
    }

    fun getCurrentPage(): String? {
        return currentPage
    }

    fun getContext(): Context {
        return this.getContext()
    }

    fun validateEmail(email: String): Boolean  {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return email.matches(emailPattern.toRegex())
    }
}