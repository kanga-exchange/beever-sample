package io.beever.account

import io.beever.annotation.Input
import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.common.JsonHelper
import io.beever.core.KotlinHandlerAbstract
import io.beever.sample.Response
import io.vertx.core.json.JsonObject
import io.vertx.kotlin.ext.sql.queryWithParamsAwait
import io.vertx.reactivex.core.Vertx


@Input("\$username", "\$password")
@Outputs(
    Output("\$result:ok"),
    Output("\$result:fail", "#code:9000", description = "Insert failed")
)
class InternalAccountCreateHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    val postgres = postgres()

    override suspend fun apply(input: JsonObject): JsonObject {
        val username = input.getString("username")
        val password = input.getString("password")

        val insertResult = insertAccount(username, password) ?: return Response.failCode(9000)

        return Response.OK
    }

    private suspend fun insertAccount(username: String, password: String) = postgres
        .queryWithParamsAwait(
            "INSERT INTO accounts (username, password) VALUES (?,?) ON CONFLICT (username) DO NOTHING RETURNING id",
            JsonHelper.toArray(username, password)
        )
        .rows
        .firstOrNull()
        ?.getLong("id")

}
