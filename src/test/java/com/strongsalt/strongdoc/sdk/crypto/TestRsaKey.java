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
import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import com.strongsalt.strongdoc.sdk.crypto.RsaKey.RsaKeySize;

public class TestRsaKey extends TestBaseKey {
	@Test
	public void testBasicKeyOperation() throws StrongDocKeyException, NoSuchMethodException, SecurityException,
			InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Rsa4kKey key1 = Rsa4kKey.GenerateKey();
		Rsa4kKey key2 = Rsa4kKey.GenerateKey();
		Assertions.assertEquals(key1.getPrivateKey().getKeySize(), RsaKeySize.RSA4K);
		Assertions.assertEquals(key1.getPublicKey().getKeySize(), RsaKeySize.RSA4K);
		Assertions.assertEquals(key2.getPrivateKey().getKeySize(), RsaKeySize.RSA4K);
		Assertions.assertEquals(key2.getPublicKey().getKeySize(), RsaKeySize.RSA4K);
		testBaseBasicKeyOperation(Rsa4kKey.class, key1, key2);

		Rsa2kKey key3 = Rsa2kKey.GenerateKey();
		Rsa2kKey key4 = Rsa2kKey.GenerateKey();
		Assertions.assertEquals(key3.getPrivateKey().getKeySize(), RsaKeySize.RSA2K);
		Assertions.assertEquals(key3.getPublicKey().getKeySize(), RsaKeySize.RSA2K);
		Assertions.assertEquals(key4.getPrivateKey().getKeySize(), RsaKeySize.RSA2K);
		Assertions.assertEquals(key4.getPublicKey().getKeySize(), RsaKeySize.RSA2K);
		testBaseBasicKeyOperation(Rsa2kKey.class, key3, key4);
	}

	@Test
	@Disabled
	public void testMaximumDataSize() throws StrongDocKeyException {
		Random rand = new Random();
		byte[] plaintext1 = new byte[RsaKeySize.RSA4K.getDataSize()];
		rand.nextBytes(plaintext1);
		byte[] plaintext2 = new byte[RsaKeySize.RSA4K.getDataSize()+60];
		rand.nextBytes(plaintext2);

		Rsa4kKey key1 = Rsa4kKey.GenerateKey();
		byte[] ciphertext1 = key1.encrypt(plaintext1);
		byte[] ciphertext2 = key1.encrypt(plaintext2);


		byte[] plaintext3 = new byte[RsaKeySize.RSA2K.getDataSize()];
		rand.nextBytes(plaintext3);
		byte[] plaintext4 = new byte[RsaKeySize.RSA2K.getDataSize()];
		rand.nextBytes(plaintext3);

		Rsa4kKey key2 = Rsa4kKey.GenerateKey();
		byte[] ciphertext3 = key2.encrypt(plaintext3);
		byte[] ciphertext4 = key2.encrypt(plaintext4);

	}
}
