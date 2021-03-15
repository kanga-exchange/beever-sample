package io.beever.sample;

import io.beever.BeeverHandler;
import io.beever.annotation.Input;
import io.beever.annotation.Output;
import io.beever.annotation.POST;
import io.beever.common.JsonHelper;
import io.reactivex.Single;
import io.vertx.core.json.JsonObject;

@POST
@Input({"$username", "$password"})
@Output({"$result:ok", "$@userId", "$@role"})
@Output({"$result:fail"})
public class PublicUserLoginHandler implements BeeverHandler {
    @Override
    public Single<JsonObject> handle(JsonObject input) {

        return Single.just(input)
            .filter(i -> checkCredentials(i.getString("username"), i.getString("password")))
            .map(i -> getUserId(i.getString("username"), i.getString("password")))
            .map(uid -> JsonHelper.toJson("result", "ok", "@userId", uid, "@role", "user"))
            .defaultIfEmpty(JsonHelper.toJson("result", "fail"))
            .toSingle();
    }

    private boolean checkCredentials(String userName, String password) {
        return "john".equals(userName) && "1qaz".equals(password);
    }

    private String getUserId(String userName, String password) {
        return "SOMEUSERID";
    }
}
