package io.beever.sample

import io.beever.annotation.Input
import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.annotation.POST
import io.beever.core.KotlinHandlerAbstract
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
