package io.beever.sample

import io.beever.annotation.Output
import io.beever.annotation.Outputs
import io.beever.annotation.Periodic
import io.beever.common.JsonHelper
import io.beever.core.KotlinHandlerAbstract
import io.vertx.core.json.JsonObject
import io.vertx.core.logging.LoggerFactory
import io.vertx.reactivex.core.Vertx


@Periodic(60)
@Outputs(
    Output("\$result:ok"),
    Output("\$result:fail")
)
class PeriodicSampleHandler(vertx: Vertx) : KotlinHandlerAbstract(vertx) {

    val log = LoggerFactory.getLogger(javaClass)

    override suspend fun apply(input: JsonObject): JsonObject {
        val sendResponse = sendAwait(
            "InternalHttpRequest", JsonHelper.toJson(
                "url", "https://api.kanga.exchange/api/ping"
            )
        )
        log.debug("Response from call:\n ${sendResponse.encodePrettily()}")
        return Response.OK
    }
}
