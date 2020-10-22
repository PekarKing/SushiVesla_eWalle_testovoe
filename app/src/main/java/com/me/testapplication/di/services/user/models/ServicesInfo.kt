package com.me.testapplication.di.services.user.models

import com.me.testapplication.di.services.api.models.ServiceResponse

data class ServicesInfo(
    val title: String,
    val iconLink: Int
) {

    constructor(serviceResponse: ServiceResponse) : this(
        serviceResponse.title,
        serviceResponse.iconLink
    )
}