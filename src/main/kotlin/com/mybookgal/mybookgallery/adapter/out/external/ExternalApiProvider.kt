package com.mybookgal.mybookgallery.adapter.out.external

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.bind.ConstructorBinding

@ConfigurationProperties(prefix = "external-api")
data class ExternalApiProvider @ConstructorBinding constructor(
    val naver: Naver
) {
    data class Naver(
        val baseUrl: String,
        val header: Header,
        val credential: Cridential
    )

    data class Header(
        val keyClientId: String,
        val keyClientSecret: String
    )

    data class Cridential(
        val clientId: String,
        val restApiKey: String
    )
}