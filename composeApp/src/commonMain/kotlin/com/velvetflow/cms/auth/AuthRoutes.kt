//package com.velvetflow.cms.auth

package com.velvetflow.cms.auth

import com.velvetflow.cms.getEnvVariable
import aws.sdk.kotlin.services.cognitoidentityprovider.CognitoIdentityProviderClient
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.post
import io.ktor.server.routing.route

fun Route.authRoutes(cognitoClient: CognitoIdentityProviderClient) {
    route("/auth") {
        post("/signup") {
            val request = try {
                call.receive<SignUpRequest>()
            } catch (e: Exception) {
                call.respond(HttpStatusCode.BadRequest, "Invalid request body")
                return@post
            }

            if (request.email.isBlank() || !request.email.contains("@") || request.password.length < 8) {
                call.respond(HttpStatusCode.BadRequest, "Invalid email or password")
                return@post
            }

            try {
                val response = cognitoClient.signUp {
                    clientId = getEnvVariable("AWS_CLIENT_ID") ?: error("Client ID not found")
                    username = request.email
                    password = request.password
                }
                call.respond(HttpStatusCode.Created, response)
            } catch (e: Exception) {
                call.respond(HttpStatusCode.InternalServerError, "Failed to sign up: ${e.message}")
            }
        }
    }
}