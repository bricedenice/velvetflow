package com.velvetflow.cms.auth


// Import the getEnvVariable function from your Platform.kt
import com.velvetflow.cms.getEnvVariable

class CognitoConfig {
    val userPoolId = getEnvVariable("AWS_USER_POOL_ID") ?: error("AWS_USER_POOL_ID not found")
    val clientId = getEnvVariable("AWS_CLIENT_ID") ?: error("AWS_CLIENT_ID not found")
    val region = getEnvVariable("AWS_REGION") ?: error("AWS_REGION not found")

    // Helper properties for JWT configuration
    val issuer = "https://cognito-idp.$region.amazonaws.com/$userPoolId"
    val jwksUrl = "$issuer/.well-known/jwks.json"

    companion object {
        // Default values for development (should match your Cognito setup)
        const val DEFAULT_REGION = "eu-south-2"
        const val DEFAULT_USER_POOL_ID = "eu-south-2_qua76Gtgp"
        const val DEFAULT_CLIENT_ID = "6vfjgegvof055t3krpo2u0farj"
    }
}