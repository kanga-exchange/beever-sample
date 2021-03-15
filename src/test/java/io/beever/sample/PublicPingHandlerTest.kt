package io.beever.sample

import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class PublicPingHandlerTest {
    @Test
    fun test(): BeeverTest {
        return BeeverTest.create()
            .send(PublicPingHandler::class.java, "{}", "{result:ok,ts:*,version:'1.0'}")
    }
}
