package io.beever.sample

import io.beever.core.InternalHttpRequestHandler
import io.beever.test.BeeverTest
import io.beever.test.BeeverTestRunner
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(BeeverTestRunner::class)
class PeriodicSampleHandlerTest {

    @Test
    fun `when nothing to do then OK`() = BeeverTest.create()
        .register(InternalHttpRequestHandler::class.java)
        .send(PeriodicSampleHandler::class.java, "{}", "{result:ok}")

}
