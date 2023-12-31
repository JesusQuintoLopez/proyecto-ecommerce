package com.optic.ecommerceappmvvm.domain.useCase.users

import com.optic.ecommerceappmvvm.domain.model.User
import com.optic.ecommerceappmvvm.domain.repository.UsersRepository
import java.io.File

class UpdateUserWithImageUseCase(private val repository: UsersRepository) {

    suspend operator fun invoke(id: String, user: User, file: File) = repository.updateWithImage(id, user, file)

}