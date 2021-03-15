package io.beever.account

import io.beever.annotation.Input
import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.core.KotlinHandlerAbstract
import io.beever.sample.Response
import io.vertx.core.json.JsonObject
import io.vertx.reactivex.core.Vertx

@Input("#userId")
@Outputs(
    Output("\$result:ok", "\$profile.name", "\$profile.email", "\$profile.avatar"),
    Output("\$result:fail", description = "No profile")
)
class InternalAccountProfileGetHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    val postgres = postgres()
    val db = mongo()
    val redis = redis()
    val dbTransactions = postgres("postgres.transactions.json")

    override suspend fun apply(input: JsonObject): JsonObject {

        return Response.FAIL
    }

}
