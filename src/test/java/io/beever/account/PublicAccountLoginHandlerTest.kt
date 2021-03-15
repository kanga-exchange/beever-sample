package io.beever.account

import io.beever.postgres.PostgresTest
import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class PublicAccountLoginHandlerTest {
    @Test
    fun `when proper login and password then result ok`() = BeeverTest.create()
        .exec(PostgresTest::reset)
        .exec(
            PostgresTest.insert(
                "accounts",
                "{id:1, username:U, password:P}"
            )
        )
        .send(
            PublicAccountLoginHandler::class.java,
            "{username:U, password:P}",
            "{result:ok, @userId:1, @role:user}"
        )

    @Test
    fun `when not proper login then result ok`() = BeeverTest.create()
        .exec(PostgresTest::reset)
        .exec(
            PostgresTest.insert(
                "accounts",
                "{id:1, username:U, password:P}"
            )
        )
        .send(
            PublicAccountLoginHandler::class.java,
            "{username:UU, password:P}",
            "{result:fail, code:1000}"
        )


}
