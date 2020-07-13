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

import com.goterl.lazycode.lazysodium.LazySodiumJava;
import com.goterl.lazycode.lazysodium.SodiumJava;
import com.goterl.lazycode.lazysodium.interfaces.Box;
import com.goterl.lazycode.lazysodium.utils.Key;

public class EcX25519KeyPub implements KeyPublic {

	private static LazySodiumJava ls = new LazySodiumJava(new SodiumJava());
	private static Box.Native nbox = ls;
	private Key key;

	protected EcX25519KeyPub() {}
	protected EcX25519KeyPub(final byte[] data) {
		this.setKey(Key.fromBytes(data));
	}

	public static EcX25519KeyPub Deserialize(final byte[] data) throws StrongDocKeyException {
		EcX25519KeyPub key = new EcX25519KeyPub();
		return key.deserialize(data);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends KeySerialization> T deserialize(byte[] data) throws StrongDocKeyException {
		int len = data == null ? 0 : data.length;
		if (len != Box.PUBLICKEYBYTES) {
			throw new StrongDocKeyException("Key length " + len + " is invalid");
		}
		setKey(Key.fromBytes(data));
		return (T) this;
	}

	@Override
	public byte[] encrypt(final byte[] plaintext) throws StrongDocKeyException {
		final byte[] ciphertext = new byte[Box.SEALBYTES + plaintext.length];

		if (!nbox.cryptoBoxSeal(ciphertext, plaintext, plaintext.length, getKey().getAsBytes())) {
			throw new StrongDocKeyException("Could not encrypt plaintext");
		}

		return ciphertext;
	}

	@Override
	public byte[] serialize() {
		return (getKey() == null || getKey().getAsBytes() == null ? new byte[0] :
			getKey().getAsBytes());
	}

	public Key getKey() {
		return key;
	}

	private void setKey(Key key) {
		this.key = key;
	}
}
