package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.Input;
import io.beever.annotation.Output;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

@Input({"$obligatoryStringValue", "?$optionalStringValue", "?#optionalNumberValue", "?*optionalAnyValue", "?&optionalBool"})
@Output({"$result:ok", "$someValue"})
@Output({"$result:fail", "#code:9000"})
public class InternalSomeHandler implements BeeverHandler {
    @Override
    public Single<JsonObject> handle(JsonObject jsonObject) {
        return Single.just(JsonHelper.toJson(
            "result", "ok",
            "someValue", "value"
        ));
    }
}
