package io.beever.sample;


import io.beever.BeeverHandler;
import io.beever.annotation.Input;
import io.beever.annotation.Output;
import io.beever.common.JsonHelper;
import io.beever.mongo.MongoFactory;
import io.beever.mongo.MongoService;
import io.beever.postgres.PostgresFactory;
import io.beever.postgres.PostgresService;
import io.beever.redis.RedisFactory;
import io.beever.redis.RedisService;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;

@Input({"$obligatoryStringValue", "?$optionalStringValue", "?#optionalNumberValue", "?*optionalAnyValue", "?&optionalBool"})
@Output({"$result:ok", "$someValue"})
@Output({"$result:fail", "#code:9000"})
public class InternalDbAccessHandler implements BeeverHandler {

    private PostgresService postgres;
    private MongoService mongo;
    private RedisService redis;

    public InternalDbAccessHandler(Vertx vertx) {
        this.postgres = PostgresFactory.get(vertx);
        this.mongo = MongoFactory.get(vertx);
        this.redis = RedisFactory.get(vertx);
    }

    @Override
    public Single<JsonObject> handle(JsonObject jsonObject) {
        return Single.just(JsonHelper.toJson(
            "result", "ok",
            "someValue", "value"
        ));
    }
}
