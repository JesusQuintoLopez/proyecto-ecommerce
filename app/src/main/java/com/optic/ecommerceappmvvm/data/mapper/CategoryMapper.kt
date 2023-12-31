package com.optic.ecommerceappmvvm.data.mapper

import com.optic.ecommerceappmvvm.data.dataSource.local.entity.CategoryEntity
import com.optic.ecommerceappmvvm.domain.model.Category

fun CategoryEntity.toCategory(): Category {
    return Category(
        id = id,
        name = name,
        description = description,
        image = image
    )
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(
        id = id ?: "",
        name = name,
        description = description,
        image = image ?: ""
    )
}