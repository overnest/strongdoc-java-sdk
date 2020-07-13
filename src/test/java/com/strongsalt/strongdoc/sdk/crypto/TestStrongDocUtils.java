/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.strongsalt.strongdoc.sdk.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TestStrongDocUtils {
	public static String str1 = "string1";
	public static String str2 = "string2";
	public static String str3 = "string3";

	@Test
	public void TestArrayConcat() throws StrongDocException {
		byte[] concat = StrongDocUtils.ArrayConcat(str1.getBytes(), str2.getBytes(), str3.getBytes());
		Assertions.assertEquals(str1+str2+str3, new String(concat));

		byte[] array1 = new byte[str1.getBytes().length];
		byte[] array2 = new byte[str2.getBytes().length];
		byte[] array3 = new byte[str3.getBytes().length];
		byte[] array4 = new byte[10];

		Assertions.assertTrue(StrongDocUtils.ArraySplit(concat, array1, array2, array3));
		Assertions.assertFalse(StrongDocUtils.ArraySplit(concat, array1, array2));
		Assertions.assertThrows(StrongDocException.class, () -> {
			StrongDocUtils.ArraySplit(concat, array1, array2, array3, array4);
		});
	}
}
