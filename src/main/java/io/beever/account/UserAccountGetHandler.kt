package io.beever.account

import io.beever.annotation.Input
import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.annotation.POST
import io.beever.common.JsonHelper
import io.beever.core.KotlinHandlerAbstract
import io.vertx.core.json.JsonObject
import io.vertx.reactivex.core.Vertx


@POST
@Input("#@userId")
@Outputs(
    Output("\$result:ok", "\$profile.name", "\$profile.email", "\$profile.avatar"),
    Output("\$result:fail", "#code:1000", description = "No profile")
)
class UserAccountGetHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    val postgres = postgres()

    override suspend fun apply(input: JsonObject): JsonObject {
        val userId = input.getLong("@userId")
        val profile = getProfile(userId) ?: return JsonHelper.toJson("result", "fail", "code", 1000)

        return JsonHelper.toJson(
            "result", "ok",
            "profile", profile
        )
    }

    private suspend fun getProfile(userId: Long) = sendAwait(
        "InternalAccountProfileGet", JsonHelper.toJson("userId", userId)
    ).getJsonObject("profile") ?: null
}
