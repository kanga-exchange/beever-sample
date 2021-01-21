package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.GET;
import io.beever.annotation.Output;
import io.beever.annotation.POST;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

import java.time.LocalDateTime;

@POST
@Output({"$result:ok", "$ts","$version"})
public class PublicMovieSearchHandler implements BeeverHandler {
    @Override
    public Single<JsonObject> handle(JsonObject input) {
        return Single.just(JsonHelper.toJson(
            "result", "ok",
            "ts", LocalDateTime.now().toString(),
            "version", "1.0"
        ));
    }
}
