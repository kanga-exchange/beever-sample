package io.beever.core

import io.beever.BeeverHandler
import io.reactivex.Single
import io.vertx.core.json.JsonArray
import io.vertx.core.json.JsonObject
import io.vertx.ext.asyncsql.AsyncSQLClient
import io.vertx.ext.asyncsql.PostgreSQLClient
import io.vertx.ext.mongo.MongoClient
import io.vertx.kotlin.core.eventbus.requestAwait
import io.vertx.kotlin.coroutines.dispatcher
import io.vertx.reactivex.core.Vertx
import io.vertx.redis.RedisClient
import io.vertx.redis.RedisOptions
import kotlinx.coroutines.rx2.rxSingle

abstract class KotlinHandlerAbstract(vertx: Vertx) : BeeverHandler {

    val vertx = vertx.delegate!!

    companion object {
        val emptyJson = JsonObject(mapOf())
        val emptyArray = JsonArray(listOf<Any>())
    }

    protected fun mongo(id: String = "mongo"): MongoClient {
        val config = JsonObject(javaClass.getResource("/$id.json").readText())
        return MongoClient.createShared(vertx, config, id)
    }

    protected fun postgres(id: String = "postgres"): AsyncSQLClient {
        val config = JsonObject(javaClass.getResource("/$id.json").readText())
        return PostgreSQLClient.createShared(vertx, config, id)
    }

    protected fun postgresNonShared(id: String = "postgres"): AsyncSQLClient {
        val config = JsonObject(javaClass.getResource("/$id.json").readText())
        return PostgreSQLClient.createNonShared(vertx, config)
    }

    protected fun redis(id: String = "redis"): RedisClient {
        val config = JsonObject(javaClass.getResource("/$id.json").readText())
        return RedisClient.create(vertx, RedisOptions(config))
    }

    override fun handle(input: JsonObject): Single<JsonObject> {
        return rxSingle(vertx.dispatcher()) {
            apply(input)
        }
    }

    internal abstract suspend fun apply(input: JsonObject): JsonObject

    suspend fun sendAwait(address: String, body: JsonObject = emptyJson): JsonObject {
        return vertx.eventBus().requestAwait<JsonObject>(address, body).body()
    }

    fun send(address: String, body: JsonObject = emptyJson) {
        vertx.eventBus().send(address, body)
    }

    fun publish(address: String, body: JsonObject = emptyJson) {
        vertx.eventBus().publish(address, body)
    }
}
