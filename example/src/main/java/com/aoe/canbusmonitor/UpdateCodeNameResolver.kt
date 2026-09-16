package com.aoe.canbusmonitor

import com.aoe.fytcanbusmonitor.CanbusUpdateCodes
import com.aoe.fytcanbusmonitor.MainUpdateCodes
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

internal object UpdateCodeNameResolver {

    private val mainUpdateCodeNames = buildCodeNameMap(MainUpdateCodes::class.java)
    private val canbusUpdateCodeNames = buildCodeNameMap(CanbusUpdateCodes::class.java)
    private val resolvedLabels = ConcurrentHashMap<Long, String>()

    fun resolve(moduleCode: Int, updatedCode: Int): String? = when (moduleCode) {
        MODULE_CODE_MAIN -> mainUpdateCodeNames[updatedCode]
        MODULE_CODE_CANBUS -> canbusUpdateCodeNames[updatedCode]
        else -> null
    }

    fun resolveOrFallback(moduleCode: Int, updatedCode: Int): String =
        resolvedLabels.computeIfAbsent(cacheKey(moduleCode, updatedCode)) {
            resolve(moduleCode, updatedCode) ?: updatedCode.toString()
        }

    private fun buildCodeNameMap(codeContainer: Class<*>): Map<Int, String> {
        val namesByCode = mutableMapOf<Int, String>()
        codeContainer.fields
            .filter { field ->
                field.type == Int::class.javaPrimitiveType &&
                    Modifier.isStatic(field.modifiers) &&
                    field.name.startsWith("U_")
            }
            .forEach { field ->
                val code = field.getInt(null)
                val candidateName = field.name
                val previousName = namesByCode[code]
                if (previousName == null || isPreferredName(candidateName, previousName)) {
                    namesByCode[code] = candidateName
                }
            }
        return namesByCode
    }

    private fun isPreferredName(candidateName: String, currentName: String): Boolean {
        val candidateScore = scoreName(candidateName)
        val currentScore = scoreName(currentName)
        return when {
            candidateScore != currentScore -> candidateScore > currentScore
            else -> candidateName < currentName
        }
    }

    private fun scoreName(name: String): Int = when {
        name.endsWith("_BEGIN") || name.endsWith("_END") || name.endsWith("_MAX") -> 0
        else -> 1
    }

    private fun cacheKey(moduleCode: Int, updatedCode: Int): Long =
        (moduleCode.toLong() shl 32) or (updatedCode.toLong() and 0xffffffffL)
}
