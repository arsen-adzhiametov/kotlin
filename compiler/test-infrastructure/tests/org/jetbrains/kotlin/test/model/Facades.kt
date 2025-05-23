/*
 * Copyright 2010-2020 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.test.model

import org.jetbrains.kotlin.test.directives.model.DirectivesContainer
import org.jetbrains.kotlin.test.services.ServiceRegistrationData
import org.jetbrains.kotlin.test.services.TestServices
import org.jetbrains.kotlin.test.services.defaultsProvider

interface ServicesAndDirectivesContainer {
    val additionalServices: List<ServiceRegistrationData>
        get() = emptyList()

    val directiveContainers: List<DirectivesContainer>
        get() = emptyList()
}

abstract class AbstractTestFacade<InputArtifact, OutputArtifact> : ServicesAndDirectivesContainer
        where InputArtifact : ResultingArtifact<InputArtifact>,
              OutputArtifact : ResultingArtifact<OutputArtifact> {
    abstract val inputKind: TestArtifactKind<InputArtifact>
    abstract val outputKind: TestArtifactKind<OutputArtifact>

    abstract fun transform(module: TestModule, inputArtifact: InputArtifact): OutputArtifact?
    abstract fun shouldTransform(module: TestModule): Boolean
}

abstract class FrontendFacade<FrontendOutputArtifact>(
    val testServices: TestServices,
    final override val outputKind: FrontendKind<FrontendOutputArtifact>
) : AbstractTestFacade<ResultingArtifact.Source, FrontendOutputArtifact>()
        where FrontendOutputArtifact : ResultingArtifact.FrontendOutput<FrontendOutputArtifact> {
    final override val inputKind: TestArtifactKind<ResultingArtifact.Source>
        get() = SourcesKind

    override fun shouldTransform(module: TestModule): Boolean {
        return testServices.defaultsProvider.frontendKind == outputKind
    }

    abstract fun analyze(module: TestModule): FrontendOutputArtifact?

    final override fun transform(module: TestModule, inputArtifact: ResultingArtifact.Source): FrontendOutputArtifact? {
        // TODO: pass sources
        return analyze(module)
    }
}

abstract class Frontend2BackendConverter<FrontendOutputArtifact, BackendInputArtifact>(
    val testServices: TestServices,
    final override val inputKind: FrontendKind<FrontendOutputArtifact>,
    final override val outputKind: BackendKind<BackendInputArtifact>,
) : AbstractTestFacade<FrontendOutputArtifact, BackendInputArtifact>()
        where FrontendOutputArtifact : ResultingArtifact.FrontendOutput<FrontendOutputArtifact>,
              BackendInputArtifact : ResultingArtifact.BackendInput<BackendInputArtifact> {
    override fun shouldTransform(module: TestModule): Boolean {
        return testServices.defaultsProvider.backendKind == outputKind
    }
}

abstract class IrPreSerializationLoweringFacade<BackendInputArtifact>(
    val testServices: TestServices,
    final override val inputKind: BackendKind<BackendInputArtifact>,
    final override val outputKind: BackendKind<BackendInputArtifact>,
) : AbstractTestFacade<BackendInputArtifact, BackendInputArtifact>()
        where BackendInputArtifact : ResultingArtifact.BackendInput<BackendInputArtifact>

abstract class BackendFacade<BackendInputArtifact, BinaryOutputArtifact>(
    val testServices: TestServices,
    final override val inputKind: BackendKind<BackendInputArtifact>,
    final override val outputKind: ArtifactKind<BinaryOutputArtifact>,
) : AbstractTestFacade<BackendInputArtifact, BinaryOutputArtifact>()
        where BackendInputArtifact : ResultingArtifact.BackendInput<BackendInputArtifact>,
              BinaryOutputArtifact : ResultingArtifact.Binary<BinaryOutputArtifact> {
    override fun shouldTransform(module: TestModule): Boolean {
        return with(testServices.defaultsProvider) {
            backendKind == inputKind && artifactKind == outputKind
        }
    }
}

abstract class DeserializerFacade<BinaryArtifact, BackendInputArtifact>(
    val testServices: TestServices,
    final override val inputKind: ArtifactKind<BinaryArtifact>,
    final override val outputKind: BackendKind<BackendInputArtifact>,
) : AbstractTestFacade<BinaryArtifact, BackendInputArtifact>()
        where BinaryArtifact : ResultingArtifact.Binary<BinaryArtifact>,
              BackendInputArtifact : ResultingArtifact.BackendInput<BackendInputArtifact> {

    override fun shouldTransform(module: TestModule): Boolean {
        return testServices.defaultsProvider.backendKind == outputKind
    }
}
