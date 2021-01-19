package io.beever.sample

import io.beever.postgres.PostgresTest
import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class PublicV2TestHandlerTest {

    @Test
    fun `when name XXX then return FAIL`() = BeeverTest.create()
        .send(PublicV2TestHandler::class.java, "{name:XXX}", "{result:fail}")

    @Test
    fun `when name is not XXX then return OK`() = BeeverTest.create()
        .send(PublicV2TestHandler::class.java, "{name:ABC}", "{result:ok}")

    @Test
    fun `when name is not XXX and number present then return OK`() = BeeverTest.create()
        .send(PublicV2TestHandler::class.java, "{name:ABC, number:123}", "{result:ok}")
}
