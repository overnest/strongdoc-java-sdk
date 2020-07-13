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

import java.util.Arrays;


public interface VersionInterface {
	public static <T extends VersionInterface> void setClassVersions(final String className, final T[] keyVersions) {
		try {
			Version.addClassVersions(className, Arrays.asList(keyVersions));
		} catch (StrongDocKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static <T extends VersionInterface> boolean hasClassVersion(final String className, final T keyVersion) {
		return Version.hasClassVersion(className, keyVersion.getVersion());
	}

	public static int GetSerializedSize() {
		return Version.getSerialSize();
	}

	default int getSeriaizedSize() {
		return VersionInterface.GetSerializedSize();
	}

	default byte[] serialize() {
		return getVersion().serialize();
	}

	@SuppressWarnings("unchecked")
	public static <T extends VersionInterface> T Deserialize(Class<T> classObj, final byte[] data) throws StrongDocKeyException {
		Version version = Version.deserialize(data);
		Object keyVersion = Version.getClassVersion(classObj.getName(), version);
		if (keyVersion == null) {
			throw new StrongDocKeyException("Version " + version.getVersion() + " is not supported in " + classObj.getName());
		}
		if (!classObj.isInstance(keyVersion)) {
			throw new StrongDocKeyException("Version is not of type " + classObj.getName());
		}
		return (T) keyVersion;
	}

	public Version getVersion();
}
