package com.optic.ecommerceappmvvm.domain.useCase.products

import com.optic.ecommerceappmvvm.domain.model.Product
import com.optic.ecommerceappmvvm.domain.repository.ProductsRepository
import java.io.File

class UpdateProductWithImageUseCase(private val repository: ProductsRepository) {
    suspend operator fun invoke(id: String, product: Product, files: List<File>) = repository.updateWithImage(id, product, files)
}