package io.beever.sample

import io.beever.postgres.PostgresTest
import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class PublicPingHandlerTest {

    @Test
    fun `when GET then result ok`() = BeeverTest.create()
        .send(PublicPingHandler::class.java, "{}", "{result:ok, ts:*, version:'1.0'}")
}
