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

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Version {
	private int version;
	private static int SERIAL_SIZE = 4;

	private static Map<String, Map<Integer, Object>> classVersionMap = new HashMap<>();

	public synchronized static <T extends VersionInterface> void addClassVersions(final String className, List<T> keyVersions)
			throws StrongDocKeyException {
		if (Version.classVersionMap.containsKey(className)) {
			throw new StrongDocKeyException("Duplicate version class name: " + className);
		}

		Map<Integer, Object> map = new HashMap<>();
		for (T kv: keyVersions) {
			map.put(kv.getVersion().getVersion(), kv);
		}

		Version.classVersionMap.put(className, map);
	}

	public static Object getClassVersion(final String className, final Version version) {
		Map<Integer, Object> verMap = classVersionMap.get(className);
		if (verMap == null || version == null) {
			return null;
		}

		return verMap.get(version.getVersion());
	}

	public static boolean hasClassVersion(final String className, final Version version) {
		return (getClassVersion(className, version) != null);
	}


	@SuppressWarnings("unused")
	private Version() {}
	public Version(final int version) {
		this.setVersion(version);
	}

	public int getVersion() {
		return version;
	}

	private void setVersion(final int version) {
		this.version = version;
	}

	public static int getSerialSize() {
		return SERIAL_SIZE;
	}

	public static Version deserialize(final byte[] data) {
		return new Version(ByteBuffer.wrap(data).getInt());
	}

	public final byte[] serialize() {
		ByteBuffer buf = ByteBuffer.allocate(SERIAL_SIZE);
		buf.putInt(getVersion());
		return buf.array();
	}
}
