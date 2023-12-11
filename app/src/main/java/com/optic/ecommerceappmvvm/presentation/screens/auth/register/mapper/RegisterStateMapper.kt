package com.optic.ecommerceappmvvm.presentation.screens.auth.register.mapper

import com.optic.ecommerceappmvvm.domain.model.User
import com.optic.ecommerceappmvvm.presentation.screens.auth.register.RegisterState

fun RegisterState.toUser(): User {
    return User(
        name = name,
        lastname = lastname,
        email = email,
        phone = phone,
        password = password
    )
}