package com.mybookgal.mybookgallery.configuration

import org.springframework.boot.web.client.ClientHttpRequestFactories
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.BufferingClientHttpRequestFactory
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfig {

    @Bean("restTemplateCustom")
    fun restTemplate(builder: RestTemplateBuilder): RestTemplate {
        return builder.requestFactory { settings ->
                           BufferingClientHttpRequestFactory(
                               ClientHttpRequestFactories.get(HttpComponentsClientHttpRequestFactory::class.java, settings)
                           )
                       }
                      .setConnectTimeout(Duration.ofSeconds(300))
                      .setReadTimeout(Duration.ofSeconds(300))
                      .build()
    }
}