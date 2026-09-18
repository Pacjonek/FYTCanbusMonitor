package com.aoe.canbusmonitor

import com.aoe.fytcanbusmonitor.CanbusUpdateCodes
import com.aoe.fytcanbusmonitor.MainUpdateCodes
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_CANBUS
import com.aoe.fytcanbusmonitor.ModuleCodes.MODULE_CODE_MAIN
import java.lang.reflect.Modifier
import java.util.concurrent.ConcurrentHashMap

internal data class ModuleUpdateKey(
    val moduleCode: Long,
    val updatedCode: Int
)

internal object UpdateCodeNameResolver {

    private val mainUpdateCodeNames = buildCodeNameMap(MainUpdateCodes::class.java)
    private val canbusUpdateCodeNames = buildCodeNameMap(CanbusUpdateCodes::class.java)
    private val resolvedLabels = ConcurrentHashMap<ModuleUpdateKey, String>()

    fun resolve(moduleCode: Long, updateCode: Int): String? = when (moduleCode) {
        MODULE_CODE_MAIN.toLong() -> mainUpdateCodeNames[updateCode]+"($updateCode)"
        MODULE_CODE_CANBUS.toLong() -> canbusUpdateCodeNames[updateCode]+"($updateCode)"
        else -> null
    }

    fun resolveOrFallback(moduleCode: Long, updatedCode: Int): String =
        resolvedLabels.computeIfAbsent(cacheKey(moduleCode, updatedCode)) {
            resolve(moduleCode, updatedCode) ?: updatedCode.toString()
        }

    fun resolve(moduleCode: Int, updatedCode: Int): String? =
        resolve(moduleCode.toLong(), updatedCode)

    fun resolveOrFallback(moduleCode: Int, updatedCode: Int): String =
        resolveOrFallback(moduleCode.toLong(), updatedCode)

    private fun buildCodeNameMap(codeContainer: Class<*>): Map<Int, String> {
        val namesByCode = mutableMapOf<Int, String>()
        codeContainer.declaredFields
            .filter { field ->
                field.type == Int::class.javaPrimitiveType &&
                    Modifier.isPublic(field.modifiers) &&
                    Modifier.isStatic(field.modifiers) &&
                    field.name.startsWith("U_")
            }
            .sortedBy { it.name }
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
        isBoundaryAlias(name) -> 0
        else -> 1
    }

    private fun isBoundaryAlias(name: String): Boolean =
        name.endsWith("_BEGIN") ||
            name.endsWith("_END") ||
            name.endsWith("_MAX") ||
            name.endsWith("_MIN") ||
            name.endsWith("_START") ||
            name.endsWith("_COUNT") ||
            name.endsWith("_CNT")

    private fun cacheKey(moduleCode: Long, updatedCode: Int): ModuleUpdateKey =
        ModuleUpdateKey(moduleCode, updatedCode)
}
