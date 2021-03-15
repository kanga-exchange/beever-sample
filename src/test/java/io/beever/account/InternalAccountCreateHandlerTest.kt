package io.beever.account

import io.beever.postgres.PostgresTest
import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class InternalAccountCreateHandlerTest {


    @Test
    fun `when new user name then insert account`(): BeeverTest {
        return BeeverTest.create()
            .exec(PostgresTest::reset)
            .send(InternalAccountCreateHandler::class.java, "{username:USER, password:ABC}", "{result:ok}")
            .exec(PostgresTest.check("SELECT username, password FROM accounts", "{username:USER, password:ABC}"))
    }

    @Test
    fun `when username already exists then fail 9000`() = BeeverTest.create()
        .exec(PostgresTest::reset)
        .exec(PostgresTest.insert("accounts", "{username:USER, password:P}"))
        .send(InternalAccountCreateHandler::class.java, "{username:USER, password:PPP}", "{result:fail, code:9000}")
        .exec(PostgresTest.check("SELECT username, password FROM accounts", "{username:USER, password:P}"))


}
