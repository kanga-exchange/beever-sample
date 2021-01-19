package io.beever.sample

import io.beever.annotation.*
import io.beever.common.JsonHelper.toJson
import io.beever.openapi.annotation.OpenAPI
import io.vertx.core.json.JsonObject
import io.vertx.reactivex.core.Vertx


@POST
@Input("\$name", "?#number")
@Outputs(
    Output("\$result:ok"),
    Output("\$result:fail")
)
class PublicV2TestHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    private val mongo = mongo()

    override suspend fun apply(input: JsonObject): JsonObject {
        val name = input.getString("name")
        name == "XXX" && return Response.FAIL

        return Response.OK
    }
}
