package io.beever.sample;

import io.reactivex.Single;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.vertx.codegen.annotations.Nullable;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import java.util.Map;

import static io.beever.common.JsonHelper.toJson;

@SuppressWarnings("unused")
public final class Response {
    private static final Logger log = LoggerFactory.getLogger(Response.class);
    public static final JsonObject OK = new JsonObject(Map.of("result", "ok"));
    public static final JsonObject FAIL = new JsonObject(Map.of("result", "fail"));
    public static final Single<JsonObject> JUST_OK = Single.just(OK);
    public static final Single<JsonObject> JUST_FAIL = Single.just(FAIL);

    public static JsonObject ok(Object input) {
        return OK;
    }

    public static JsonObject appendOk(@Nullable JsonObject res) {
        return res != null ? res.mergeIn(OK) : Response.OK;
    }

    public static JsonObject ok(final String field1, final Object value1, final Object... other) {
        return toJson(field1, value1, other).mergeIn(OK);
    }

    public static Function<Object, JsonObject> returnFail(Object code, String message) {
        return __ -> toJson("result", "fail", "code", code, "message", message);
    }

    public static JsonObject returnFailJson(Object code, String message) {
        return toJson("result", "fail", "code", code, "message", message);
    }

    public static @NonNull
    JsonObject failCode(int code) {
        return toJson("result", "fail", "code", code);
    }

    public static JsonObject fail(Object error) {
        if (error instanceof ResponseException) {
            return new JsonObject(Map.of(
                "result", "fail",
                "code", ((ResponseException) error).getCode()));
        }
        return fail(error, FAIL);
    }

    public static @NonNull
    JsonObject failWithCode(Object error) {
        return (error instanceof ResponseException) ?
            new JsonObject(Map.of(
                "result", "fail",
                "code", ((ResponseException) error).getCode())) :
            new JsonObject(Map.of(
                "result", "fail",
                "code", (error instanceof Integer) ? error : error.toString()));
    }

    public static JsonObject isOkOrFail(JsonObject json) {
        return isOk(json) ?
            new JsonObject(Map.of("result", "ok"))
            :
            new JsonObject(Map.of(
                "result", "fail",
                "code", json.getInteger("code")));
    }

    public static JsonObject fail(Object error, JsonObject output) {
        if (log.isWarnEnabled()) {
            if (error instanceof Throwable) {
                final StackTraceElement[] stackTrace = ((Throwable) error).getStackTrace();
                if (stackTrace.length > 0 && stackTrace[0].getClassName().startsWith("exchange.kanga.")) {
                    final StackTraceElement s = stackTrace[0];
                    if (s.getFileName() == null) {
                        log.warn("[response::fail] {}: {} ({})", error.getClass().getName(), ((Throwable) error).getMessage(), s.getClassName());
                    } else {
                        log.warn("[response::fail] {}: {} ({}:{})", error.getClass().getName(), ((Throwable) error).getMessage(), s.getFileName(), s.getLineNumber());
                    }
                } else {
                    log.warn("[response::fail] {}: {}", error.getClass().getName(), ((Throwable) error).getMessage());
                }
            } else {
                log.warn("[response::fail] {}", error);
            }
        }
        return output;
    }

    public static boolean isOk(JsonObject response) {
        return response != null && "ok".equals(response.getString("result"));
    }

    public static boolean isFailed(JsonObject response) {
        return response != null && "fail".equals(response.getString("result"));
    }

    public static boolean isNotFailed(JsonObject response) {
        return response != null &&
            (!response.containsKey("result") || !"fail".equals(response.getString("result")));
    }

    public static boolean isNotOk(JsonObject response) {
        return response == null || !"ok".equals(response.getString("result"));
    }

    public static final class ResponseException extends RuntimeException {

        private final Object code;

        Object getCode() {
            return code;
        }

        public ResponseException(Object code) {
            super(null, null, false, false);
            this.code = code;
        }

        ResponseException(Object code, String message) {
            super(message, null, false, false);
            this.code = code;
        }

        @Override
        public String toString() {
            if (getMessage() != null) {
                return "ResponseException(code=" + code + ", message=\"" + getMessage() + "\")";
            }
            return "ResponseException(code=" + code + ")";
        }
    }

}
