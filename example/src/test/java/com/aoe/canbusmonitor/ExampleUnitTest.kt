package com.aoe.canbusmonitor

import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_BT
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun resolvesMainUpdateCodeName() {
        assertEquals("U_ACC_ON", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_MAIN, 50))
    }

    @Test
    fun resolvesCanbusCodeNameOverRangeMarker() {
        assertEquals("U_DOOR_ENGINE", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_CANBUS, 0))
    }

    @Test
    fun fallsBackToNumericCodeWhenNameIsMissing() {
        assertEquals("31", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_BT, 31))
    }

    @Test
    fun keepsResolvedNamesScopedPerModule() {
        assertEquals("U_APP_ID", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_MAIN, 0))
        assertEquals("U_DOOR_ENGINE", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_CANBUS, 0))
        assertEquals("0", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_BT, 0))
        assertEquals("U_APP_ID", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_MAIN, 0))
        assertEquals("U_DOOR_ENGINE", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_CANBUS, 0))
        assertEquals("0", UpdateCodeNameResolver.resolveOrFallback(MODULE_CODE_BT, 0))
    }
}