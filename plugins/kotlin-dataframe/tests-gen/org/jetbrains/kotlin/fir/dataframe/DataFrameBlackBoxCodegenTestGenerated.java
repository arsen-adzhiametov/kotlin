

package org.jetbrains.kotlin.fir.dataframe;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.fir.dataframe.GenerateTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("testData/box")
@TestDataPath("$PROJECT_ROOT")
public class DataFrameBlackBoxCodegenTestGenerated extends AbstractDataFrameBlackBoxCodegenTest {
  @Test
  public void testAllFilesPresentInBox() {
    KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("testData/box"), Pattern.compile("^(.+)\\.kt$"), null, TargetBackend.JVM_IR, true);
  }

  @Test
  @TestMetadata("conflictingJvmDeclarations.kt")
  public void testConflictingJvmDeclarations() {
    runTest("testData/box/conflictingJvmDeclarations.kt");
  }

  @Test
  @TestMetadata("dataRowSchemaApi.kt")
  public void testDataRowSchemaApi() {
    runTest("testData/box/dataRowSchemaApi.kt");
  }

  @Test
  @TestMetadata("dataSchemaCodegen.kt")
  public void testDataSchemaCodegen() {
    runTest("testData/box/dataSchemaCodegen.kt");
  }

  @Test
  @TestMetadata("dfIde.kt")
  public void testDfIde() {
    runTest("testData/box/dfIde.kt");
  }

  @Test
  @TestMetadata("diff.kt")
  public void testDiff() {
    runTest("testData/box/diff.kt");
  }

  @Test
  @TestMetadata("duplicatedSignature.kt")
  public void testDuplicatedSignature() {
    runTest("testData/box/duplicatedSignature.kt");
  }

  @Test
  @TestMetadata("explode.kt")
  public void testExplode() {
    runTest("testData/box/explode.kt");
  }

  @Test
  @TestMetadata("explodeDataFrame.kt")
  public void testExplodeDataFrame() {
    runTest("testData/box/explodeDataFrame.kt");
  }

  @Test
  @TestMetadata("extractPluginSchemaWithUnfold.kt")
  public void testExtractPluginSchemaWithUnfold() {
    runTest("testData/box/extractPluginSchemaWithUnfold.kt");
  }

  @Test
  @TestMetadata("flexibleReturnType.kt")
  public void testFlexibleReturnType() {
    runTest("testData/box/flexibleReturnType.kt");
  }

  @Test
  @TestMetadata("injectAccessors.kt")
  public void testInjectAccessors() {
    runTest("testData/box/injectAccessors.kt");
  }

  @Test
  @TestMetadata("injectAccessorsDsl.kt")
  public void testInjectAccessorsDsl() {
    runTest("testData/box/injectAccessorsDsl.kt");
  }

  @Test
  @TestMetadata("insert.kt")
  public void testInsert() {
    runTest("testData/box/insert.kt");
  }

  @Test
  @TestMetadata("join.kt")
  public void testJoin() {
    runTest("testData/box/join.kt");
  }

  @Test
  @TestMetadata("lowerGeneratedImplicitReceiver.kt")
  public void testLowerGeneratedImplicitReceiver() {
    runTest("testData/box/lowerGeneratedImplicitReceiver.kt");
  }

  @Test
  @TestMetadata("main.kt")
  public void testMain() {
    runTest("testData/box/main.kt");
  }

  @Test
  @TestMetadata("nestedDataSchemaCodegen.kt")
  public void testNestedDataSchemaCodegen() {
    runTest("testData/box/nestedDataSchemaCodegen.kt");
  }

  @Test
  @TestMetadata("OuterClass.kt")
  public void testOuterClass() {
    runTest("testData/box/OuterClass.kt");
  }

  @Test
  @TestMetadata("parametrizedDataFrame.kt")
  public void testParametrizedDataFrame() {
    runTest("testData/box/parametrizedDataFrame.kt");
  }

  @Test
  @TestMetadata("platformType.kt")
  public void testPlatformType() {
    runTest("testData/box/platformType.kt");
  }

  @Test
  @TestMetadata("read.kt")
  public void testRead() {
    runTest("testData/box/read.kt");
  }

  @Test
  @TestMetadata("readCSV.kt")
  public void testReadCSV() {
    runTest("testData/box/readCSV.kt");
  }

  @Test
  @TestMetadata("readJson.kt")
  public void testReadJson() {
    runTest("testData/box/readJson.kt");
  }

  @Test
  @TestMetadata("Schema.kt")
  public void testSchema() {
    runTest("testData/box/Schema.kt");
  }

  @Test
  @TestMetadata("transformReplaceFunctionCall.kt")
  public void testTransformReplaceFunctionCall() {
    runTest("testData/box/transformReplaceFunctionCall.kt");
  }
}
