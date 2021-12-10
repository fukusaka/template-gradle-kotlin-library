package org.example

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class HelloTest {

    @Test
    fun getMessage() {
        assertEquals(Hello.message, "Hello World!")
    }

    @Test
    fun fetchMessage() {
        assertEquals(Hello.fetchMessage(), "Hello World!")
    }
}
