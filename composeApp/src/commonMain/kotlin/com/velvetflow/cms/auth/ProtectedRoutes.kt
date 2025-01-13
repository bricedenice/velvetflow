//package com.velvetflow.cms.auth

package com.velvetflow.cms.velvetflow.auth

fun Route.protectedRoutes() {
    authenticate("jwt") {
        get("/user/profile") {
            val principal = call.principal<JWTPrincipal>()
            val email = principal?.payload?.getClaim("email")?.asString()
            call.respondText("Authenticated user: $email")
        }
    }
}