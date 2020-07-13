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
import java.lang.reflect.Method;

import org.junit.jupiter.api.Assertions;

import com.strongsalt.strongdoc.sdk.crypto.StrongDocKey.KeyType;


public class TestBaseKey {
	public static String testString = "This is a test of a base key. Could be either symmetric or asymmetric.";

	public static KeyBase callStaticDeserialize(Class<? extends KeyBase> cls, final byte[] data) {
		try {
			Method m = cls.getMethod(KeyType.DeserializeFunction, byte[].class);
			Object k = m.invoke(null, data);
			return (KeyBase) k;
		} catch (NoSuchMethodException | SecurityException e) {
			throw new RuntimeException("Class " + cls.getName() +
					" is missing the " + KeyType.DeserializeFunction + "() function", e);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			throw new RuntimeException("Function call " + cls.getName() +
					"." + KeyType.DeserializeFunction + "() failed", e);
		}
	}


	public void testBaseBasicKeyOperation(Class<? extends KeyBase> cls, final KeyBase key1, final KeyBase key2)
			throws StrongDocKeyException, NoSuchMethodException, SecurityException, InstantiationException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// Encrypt/Decrypt
		final byte[] k1ciphertext = key1.encrypt(testString.getBytes());
		final byte[] plaintext = key1.decrypt(k1ciphertext);
		Assertions.assertEquals(testString, new String(plaintext), cls.getName() + " failed");

		// Serialize/Deserialize
		final byte[] serialKey1 = key1.serialize();
		KeyBase deserialKey1 = callStaticDeserialize(cls, serialKey1);
		Assertions.assertEquals(testString, new String(deserialKey1.decrypt(k1ciphertext)), cls.getName() + " failed");

		// Test decrypt with wrong key. This could have two results:
		// 1. For keys with authentication tag, an exception will be thrown
		// 2. For streaming keys without authentication tag, the result will
		//    simply be incorrect, but no exception will be thrown.
		boolean testOk = false;
		try {
			final byte[] wrongPlaintext = key2.decrypt(k1ciphertext);
			Assertions.assertNotEquals(testString, new String(wrongPlaintext), cls.getName() + " failed");
			testOk = true;
		} catch (StrongDocKeyException e) {
			testOk = true;
		}

		Assertions.assertTrue(testOk, cls.getName() + ": Decrypt with wrong key test failed");
	}
}
