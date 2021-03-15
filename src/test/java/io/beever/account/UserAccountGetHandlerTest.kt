package io.beever.account

import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class UserAccountGetHandlerTest {
    @Test
    fun `when proper userId then return profile`() = BeeverTest.create()
        .expect(
            InternalAccountProfileGetHandler::class.java,
            "{userId:123}",
            "{result:ok, profile:{name:N, email:E, avatar:A}}"
        )
        .send(
            UserAccountGetHandler::class.java,
            "{@userId:123}",
            "{result:ok, profile:{name:N, email:E, avatar:A}}"
        )


    @Test
    fun `whenno profile  then return fail`() = BeeverTest.create()
        .expect(
            InternalAccountProfileGetHandler::class.java,
            "{userId:123}",
            "{result:fail}"
        )
        .send(
            UserAccountGetHandler::class.java,
            "{@userId:123}",
            "{result:fail, code:1000}"
        )
}
