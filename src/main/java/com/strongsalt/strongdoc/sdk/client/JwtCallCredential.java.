/*
 * Copyright 2016 Google, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Created by rayt on 10/6/16.
 */


package com.strongsalt.strongdoc.sdk.client;

import io.grpc.CallCredentials;
import io.grpc.Metadata;
import io.grpc.Status;

import java.util.concurrent.Executor;

/*
 * JSON Web Token client credentials generator and injector.
 * This will apply metadata in the client to outgoing calls.
 */
public class JwtCallCredential extends CallCredentials {
    private final String jwt;

    public JwtCallCredential(final String jwt) {
        this.jwt = jwt;
    }

    @Override
    public void applyRequestMetadata(final RequestInfo requestInfo, final Executor executor,
                                     final MetadataApplier metadataApplier) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    final Metadata headers = new Metadata();
                    final Metadata.Key<String> jwtKey = Metadata.Key.of("authorization", Metadata.ASCII_STRING_MARSHALLER);
                    headers.put(jwtKey, jwt);
                    metadataApplier.apply(headers);
                } catch (final Throwable e) {
                    metadataApplier.fail(Status.UNAUTHENTICATED.withCause(e));
                }
            }
        });
    }

    @Override
    public void thisUsesUnstableApi() {
    }

    public static JwtCallCredential getCallCredential(final String token) {
        return new JwtCallCredential("Bearer " + token);
    }
}
