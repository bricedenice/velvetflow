package com.velvetflow.cms.auth

data class CognitoConfig(
    val region: String = System.getenv("AWS_REGION") ?: "us-east-1",
    val userPoolId: String = System.getenv("AWS_USER_POOL_ID") ?: throw IllegalStateException("AWS_USER_POOL_ID not set"),
    val clientId: String = System.getenv("AWS_CLIENT_ID") ?: throw IllegalStateException("AWS_CLIENT_ID not set")
)