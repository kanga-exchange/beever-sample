package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.GET;
import io.beever.annotation.Input;
import io.beever.annotation.Output;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;

@GET
@Output({"$result:ok", "$ts","$version"})
public class PublicPingHandler implements BeeverHandler {
    @Override
    public Single<JsonObject> handle(JsonObject input) {
        return Single.just(JsonHelper.toJson(
            "result", "ok",
            "ts", LocalDateTime.now().toString(),
            "version", "1.0"
        ));
    }
}
