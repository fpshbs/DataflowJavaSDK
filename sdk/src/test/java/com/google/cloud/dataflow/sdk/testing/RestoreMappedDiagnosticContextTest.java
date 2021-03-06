/*
 * Copyright (C) 2014 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.google.cloud.dataflow.sdk.testing;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.slf4j.MDC;

/** Tests for {@link RestoreMappedDiagnosticContext}. */
@RunWith(JUnit4.class)
public class RestoreMappedDiagnosticContextTest {
  @Rule public TestRule restoreMappedDiagnosticContext = new RestoreMappedDiagnosticContext();

  /*
   * Since these tests can run out of order, both test A and B verify that they
   * could insert their property and that the other does not exist.
   */
  @Test
  public void testThatMDCIsClearedA() {
    MDC.put("TestA", "TestA");
    assertNotNull(MDC.get("TestA"));
    assertNull(MDC.get("TestB"));
  }

  @Test
  public void testThatMDCIsClearedB() {
    MDC.put("TestB", "TestB");
    assertNotNull(MDC.get("TestB"));
    assertNull(MDC.get("TestA"));
  }
}
