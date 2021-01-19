package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.Input;
import io.beever.annotation.Output;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;
import io.vertx.reactivex.core.Vertx;

@Input({"$obligatoryStringValue", "?$optionalStringValue", "?#optionalNumberValue", "?*optionalAnyValue", "?&optionalBool"})
@Output({"$result:ok", "$someValue"})
@Output({"$result:fail", "#code:9000"})
public class InternalSomeHandler implements BeeverHandler {

    private Vertx vertx;

    @Override
    public Single<JsonObject> handle(JsonObject input) {

//        send("nazwaMessage", params, output -> { jakis kod wywoływany gdyt przyjdzie odpowiedź})

        return Single.just(input)
            .flatMap(json -> vertx.eventBus().rxRequest("InternalDbAccess", json).map(m -> (JsonObject) m.body()))
            .map(response -> new JsonObject().put("rezultat", response.getString("data")))
            ;
    }
}
