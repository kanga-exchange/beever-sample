package io.beever.account

import io.beever.annotation.Input
import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.annotation.POST
import io.beever.common.JsonHelper
import io.beever.core.KotlinHandlerAbstract
import io.beever.sample.Response
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.ext.sql.queryWithParamsAwait
import io.vertx.reactivex.core.Vertx


@POST
@Input("\$username", "\$password")
@Outputs(
    Output("\$result:ok", "#@userId", "$@role"),
    Output("\$result:fail", "#code:1000", description = "Wrong login/password")
)
class PublicAccountLoginHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    val postgres = postgres()

    override suspend fun apply(input: JsonObject): JsonObject {
        val username = input.getString("username")
        val password = input.getString("password")

        val selectResult = postgres.queryWithParamsAwait(
            "SELECT id FROM accounts WHERE username=? and password=?",
            JsonHelper.toArray(username, password)
        ).rows.firstOrNull() ?: return Response.failCode(1000)

        return Response.ok(
            "@userId", selectResult.getLong("id"),
            "@role", "user"
        )
    }

}
