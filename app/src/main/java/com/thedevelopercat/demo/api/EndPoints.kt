package com.thedevelopercat.demo.api

import com.thedevelopercat.demo.api.Environment.*

enum class Environment{
    QA{
        override val url = "http://www.mocky.io"
    },
    STAGING{
        override val url = ""
    },
    LIVE{
        override val url = ""
    };
    abstract val url: String
}

object EndPoints {

    val environment: Environment = QA
    const val version = "v2"
    val BASE_URL = environment.url

    const val USER_DETAIL: String = "/$version/5d90d43e3000006b00cacfe5"
}