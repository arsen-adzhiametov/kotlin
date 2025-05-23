/*
 * Copyright 2010-2018 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen.inline

import org.jetbrains.kotlin.codegen.StackValue
import org.jetbrains.org.objectweb.asm.Type

class ParametersBuilder private constructor() {

    private val params = arrayListOf<ParameterInfo>()

    var nextParameterOffset = 0
        private set

    private var nextValueParameterIndex = 0

    fun addThis(type: Type, skipped: Boolean): ParameterInfo {
        return addParameter(ParameterInfo(type, skipped, nextParameterOffset, -1, nextValueParameterIndex), false)
    }

    fun addNextParameter(type: Type, skipped: Boolean, typeOnStack: Type = type): ParameterInfo {
        return addParameter(ParameterInfo(type, skipped, nextParameterOffset, null, nextValueParameterIndex, typeOnStack), false)
    }

    fun addNextValueParameter(type: Type, skipped: Boolean, remapValue: StackValue?, parameterIndex: Int): ParameterInfo {
        return addParameter(
            ParameterInfo(type, skipped, nextParameterOffset, remapValue, parameterIndex),
            true
        )
    }

    fun addCapturedParam(original: CapturedParamInfo, newFieldName: String): CapturedParamInfo {
        val info = CapturedParamInfo(
            original.desc, newFieldName, original.isSkipped, nextParameterOffset, original.index, original.isSkipInConstructor
        )
        info.functionalArgument = original.functionalArgument
        return addParameter(info, false)
    }

    fun addCapturedParam(desc: CapturedParamDesc, newFieldName: String, skipInConstructor: Boolean): CapturedParamInfo {
        return addParameter(CapturedParamInfo(desc, newFieldName, false, nextParameterOffset, -1, skipInConstructor), false)
    }

    fun addCapturedParamCopy(copyFrom: CapturedParamInfo): CapturedParamInfo {
        return addParameter(copyFrom.cloneWithNewDeclarationIndex(-1), false)
    }

    fun addCapturedParam(
        containingLambdaType: Type,
        fieldName: String,
        newFieldName: String,
        type: Type,
        skipped: Boolean,
        original: ParameterInfo?
    ): CapturedParamInfo {
        val info = CapturedParamInfo(
            CapturedParamDesc(containingLambdaType, fieldName, type), newFieldName, skipped, nextParameterOffset, original?.index ?: -1, false
        )
        if (original != null) {
            info.functionalArgument = original.functionalArgument
        }
        return addParameter(info, false)
    }

    private fun <T : ParameterInfo> addParameter(info: T, isValueParameter: Boolean): T {
        params.add(info)
        nextParameterOffset += info.type.size
        if (info !is CapturedParamInfo) {
            nextValueParameterIndex++
        }
        return info
    }

    fun listCaptured(): List<CapturedParamInfo> {
        return params.filterIsInstance<CapturedParamInfo>()
    }

    /*TODO use Parameters instead*/
    fun listAllParams(): List<ParameterInfo> {
        return params
    }

    fun buildParameters(): Parameters {
        var nextDeclarationIndex = (params.maxOfOrNull { it.declarationIndex } ?: -1) + 1

        return Parameters(params.map { param ->
            if (param is CapturedParamInfo) {
                param.cloneWithNewDeclarationIndex(nextDeclarationIndex++)
            } else {
                param
            }
        })
    }

    companion object {
        @JvmStatic
        fun newBuilder(): ParametersBuilder {
            return ParametersBuilder()
        }
    }
}
