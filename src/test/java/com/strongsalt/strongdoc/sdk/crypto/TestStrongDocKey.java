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

import java.lang.reflect.InvocationTargetException;

import org.junit.jupiter.api.Test;

import com.strongsalt.strongdoc.sdk.crypto.StrongDocKey.KeyType;


public class TestStrongDocKey extends TestBaseKey {
	@Test
	public void testBasicKeyOperation() throws StrongDocKeyException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		for (KeyType type: KeyType.values()) {
			StrongDocKey key1 = StrongDocKey.GenerateKey(type);
			StrongDocKey key2 = StrongDocKey.GenerateKey(type);
			testBaseBasicKeyOperation(StrongDocKey.class, key1, key2);
		}
	}
}
