//package com.velvetflow.cms.auth

package com.velvetflow.cms.velvetflow.auth

import io.ktor.server.application.*

fun Application.configureAuthentication() {
    authentication {
        jwt {
            val config = CognitoConfig()
            realm = "VelvetFlow Server"
            verifier(
                JWT.require(Algorithm.RS256)
                    .withIssuer("https://cognito-idp.${config.region}.amazonaws.com/${config.userPoolId}")
                    .build()
            )
            validate { credential ->
                if (credential.payload.audience.contains(config.clientId)) {
                    JWTPrincipal(credential.payload)
                } else null
            }
        }
    }
}