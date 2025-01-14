//package com.velvetflow.cms.auth

package com.velvetflow.cms.auth

import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm

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