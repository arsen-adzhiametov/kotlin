/*
 * Copyright 2010-2024 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.analysis.checkers.type

import org.jetbrains.kotlin.diagnostics.DiagnosticReporter
import org.jetbrains.kotlin.diagnostics.reportOn
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors
import org.jetbrains.kotlin.fir.types.ConeDynamicType
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef

object FirDynamicUnsupportedChecker : FirResolvedTypeRefChecker(MppCheckerKind.Common) {
    const val MESSAGE: String = "Dynamic type is only supported in Kotlin JS."

    context(context: CheckerContext, reporter: DiagnosticReporter)
    override fun check(typeRef: FirResolvedTypeRef) {
        // It's assumed this checker is only called
        // by a platform that disallows dynamics
        if (typeRef.coneType is ConeDynamicType) {
            reporter.reportOn(typeRef.source, FirErrors.UNSUPPORTED, MESSAGE)
        }
    }
}
