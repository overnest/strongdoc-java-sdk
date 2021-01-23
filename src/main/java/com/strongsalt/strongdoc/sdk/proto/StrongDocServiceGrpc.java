package com.strongsalt.strongdoc.sdk.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.26.0)",
    comments = "Source: strongdoc.proto")
public final class StrongDocServiceGrpc {

  private StrongDocServiceGrpc() {}

  public static final String SERVICE_NAME = "proto.StrongDocService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getRegisterOrganizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterOrganization",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getRegisterOrganizationMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getRegisterOrganizationMethod;
    if ((getRegisterOrganizationMethod = StrongDocServiceGrpc.getRegisterOrganizationMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRegisterOrganizationMethod = StrongDocServiceGrpc.getRegisterOrganizationMethod) == null) {
          StrongDocServiceGrpc.getRegisterOrganizationMethod = getRegisterOrganizationMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterOrganization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RegisterOrganization"))
              .build();
        }
      }
    }
    return getRegisterOrganizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getReactivateOrganizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ReactivateOrganization",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getReactivateOrganizationMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> getReactivateOrganizationMethod;
    if ((getReactivateOrganizationMethod = StrongDocServiceGrpc.getReactivateOrganizationMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getReactivateOrganizationMethod = StrongDocServiceGrpc.getReactivateOrganizationMethod) == null) {
          StrongDocServiceGrpc.getReactivateOrganizationMethod = getReactivateOrganizationMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ReactivateOrganization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ReactivateOrganization"))
              .build();
        }
      }
    }
    return getReactivateOrganizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> getRemoveOrganizationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveOrganization",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> getRemoveOrganizationMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> getRemoveOrganizationMethod;
    if ((getRemoveOrganizationMethod = StrongDocServiceGrpc.getRemoveOrganizationMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRemoveOrganizationMethod = StrongDocServiceGrpc.getRemoveOrganizationMethod) == null) {
          StrongDocServiceGrpc.getRemoveOrganizationMethod = getRemoveOrganizationMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveOrganization"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RemoveOrganization"))
              .build();
        }
      }
    }
    return getRemoveOrganizationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> getInviteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "InviteUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> getInviteUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> getInviteUserMethod;
    if ((getInviteUserMethod = StrongDocServiceGrpc.getInviteUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getInviteUserMethod = StrongDocServiceGrpc.getInviteUserMethod) == null) {
          StrongDocServiceGrpc.getInviteUserMethod = getInviteUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "InviteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("InviteUser"))
              .build();
        }
      }
    }
    return getInviteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> getListInvitationsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListInvitations",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> getListInvitationsMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq, com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> getListInvitationsMethod;
    if ((getListInvitationsMethod = StrongDocServiceGrpc.getListInvitationsMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListInvitationsMethod = StrongDocServiceGrpc.getListInvitationsMethod) == null) {
          StrongDocServiceGrpc.getListInvitationsMethod = getListInvitationsMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq, com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListInvitations"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListInvitations"))
              .build();
        }
      }
    }
    return getListInvitationsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> getRevokeInvitationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RevokeInvitation",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> getRevokeInvitationMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq, com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> getRevokeInvitationMethod;
    if ((getRevokeInvitationMethod = StrongDocServiceGrpc.getRevokeInvitationMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRevokeInvitationMethod = StrongDocServiceGrpc.getRevokeInvitationMethod) == null) {
          StrongDocServiceGrpc.getRevokeInvitationMethod = getRevokeInvitationMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq, com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RevokeInvitation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RevokeInvitation"))
              .build();
        }
      }
    }
    return getRevokeInvitationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> getRegisterUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RegisterUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> getRegisterUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> getRegisterUserMethod;
    if ((getRegisterUserMethod = StrongDocServiceGrpc.getRegisterUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRegisterUserMethod = StrongDocServiceGrpc.getRegisterUserMethod) == null) {
          StrongDocServiceGrpc.getRegisterUserMethod = getRegisterUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq, com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RegisterUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RegisterUser"))
              .build();
        }
      }
    }
    return getRegisterUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq,
      com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> getGetUserPrivateKeysMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserPrivateKeys",
      requestType = com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq,
      com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> getGetUserPrivateKeysMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq, com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> getGetUserPrivateKeysMethod;
    if ((getGetUserPrivateKeysMethod = StrongDocServiceGrpc.getGetUserPrivateKeysMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetUserPrivateKeysMethod = StrongDocServiceGrpc.getGetUserPrivateKeysMethod) == null) {
          StrongDocServiceGrpc.getGetUserPrivateKeysMethod = getGetUserPrivateKeysMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq, com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserPrivateKeys"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetUserPrivateKeys"))
              .build();
        }
      }
    }
    return getGetUserPrivateKeysMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq,
      com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> getSetUserKdfMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserKdfMetadata",
      requestType = com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq,
      com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> getSetUserKdfMetadataMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq, com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> getSetUserKdfMetadataMethod;
    if ((getSetUserKdfMetadataMethod = StrongDocServiceGrpc.getSetUserKdfMetadataMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetUserKdfMetadataMethod = StrongDocServiceGrpc.getSetUserKdfMetadataMethod) == null) {
          StrongDocServiceGrpc.getSetUserKdfMetadataMethod = getSetUserKdfMetadataMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq, com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserKdfMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetUserKdfMetadata"))
              .build();
        }
      }
    }
    return getSetUserKdfMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> getSetUserAuthMetadataMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserAuthMetadata",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> getSetUserAuthMetadataMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq, com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> getSetUserAuthMetadataMethod;
    if ((getSetUserAuthMetadataMethod = StrongDocServiceGrpc.getSetUserAuthMetadataMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetUserAuthMetadataMethod = StrongDocServiceGrpc.getSetUserAuthMetadataMethod) == null) {
          StrongDocServiceGrpc.getSetUserAuthMetadataMethod = getSetUserAuthMetadataMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq, com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserAuthMetadata"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetUserAuthMetadata"))
              .build();
        }
      }
    }
    return getSetUserAuthMetadataMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> getListUsersMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListUsers",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> getListUsersMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq, com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> getListUsersMethod;
    if ((getListUsersMethod = StrongDocServiceGrpc.getListUsersMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListUsersMethod = StrongDocServiceGrpc.getListUsersMethod) == null) {
          StrongDocServiceGrpc.getListUsersMethod = getListUsersMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq, com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListUsers"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListUsers"))
              .build();
        }
      }
    }
    return getListUsersMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> getRemoveUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> getRemoveUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> getRemoveUserMethod;
    if ((getRemoveUserMethod = StrongDocServiceGrpc.getRemoveUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRemoveUserMethod = StrongDocServiceGrpc.getRemoveUserMethod) == null) {
          StrongDocServiceGrpc.getRemoveUserMethod = getRemoveUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RemoveUser"))
              .build();
        }
      }
    }
    return getRemoveUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> getPreparePromoteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PreparePromoteUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> getPreparePromoteUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> getPreparePromoteUserMethod;
    if ((getPreparePromoteUserMethod = StrongDocServiceGrpc.getPreparePromoteUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getPreparePromoteUserMethod = StrongDocServiceGrpc.getPreparePromoteUserMethod) == null) {
          StrongDocServiceGrpc.getPreparePromoteUserMethod = getPreparePromoteUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PreparePromoteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("PreparePromoteUser"))
              .build();
        }
      }
    }
    return getPreparePromoteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> getPromoteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PromoteUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> getPromoteUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> getPromoteUserMethod;
    if ((getPromoteUserMethod = StrongDocServiceGrpc.getPromoteUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getPromoteUserMethod = StrongDocServiceGrpc.getPromoteUserMethod) == null) {
          StrongDocServiceGrpc.getPromoteUserMethod = getPromoteUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PromoteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("PromoteUser"))
              .build();
        }
      }
    }
    return getPromoteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> getDemoteUserMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DemoteUser",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq,
      com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> getDemoteUserMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> getDemoteUserMethod;
    if ((getDemoteUserMethod = StrongDocServiceGrpc.getDemoteUserMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getDemoteUserMethod = StrongDocServiceGrpc.getDemoteUserMethod) == null) {
          StrongDocServiceGrpc.getDemoteUserMethod = getDemoteUserMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq, com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DemoteUser"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("DemoteUser"))
              .build();
        }
      }
    }
    return getDemoteUserMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> getListDocumentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDocuments",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> getListDocumentsMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq, com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> getListDocumentsMethod;
    if ((getListDocumentsMethod = StrongDocServiceGrpc.getListDocumentsMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListDocumentsMethod = StrongDocServiceGrpc.getListDocumentsMethod) == null) {
          StrongDocServiceGrpc.getListDocumentsMethod = getListDocumentsMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq, com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDocuments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListDocuments"))
              .build();
        }
      }
    }
    return getListDocumentsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> getRemoveDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> getRemoveDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> getRemoveDocumentMethod;
    if ((getRemoveDocumentMethod = StrongDocServiceGrpc.getRemoveDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRemoveDocumentMethod = StrongDocServiceGrpc.getRemoveDocumentMethod) == null) {
          StrongDocServiceGrpc.getRemoveDocumentMethod = getRemoveDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RemoveDocument"))
              .build();
        }
      }
    }
    return getRemoveDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp> getUploadDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp> getUploadDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp> getUploadDocumentStreamMethod;
    if ((getUploadDocumentStreamMethod = StrongDocServiceGrpc.getUploadDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getUploadDocumentStreamMethod = StrongDocServiceGrpc.getUploadDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getUploadDocumentStreamMethod = getUploadDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("UploadDocumentStream"))
              .build();
        }
      }
    }
    return getUploadDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp> getE2EEUploadDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "E2EEUploadDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp> getE2EEUploadDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp> getE2EEUploadDocumentStreamMethod;
    if ((getE2EEUploadDocumentStreamMethod = StrongDocServiceGrpc.getE2EEUploadDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getE2EEUploadDocumentStreamMethod = StrongDocServiceGrpc.getE2EEUploadDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getE2EEUploadDocumentStreamMethod = getE2EEUploadDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "E2EEUploadDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("E2EEUploadDocumentStream"))
              .build();
        }
      }
    }
    return getE2EEUploadDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> getE2EEPrepareDownloadDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "E2EEPrepareDownloadDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> getE2EEPrepareDownloadDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> getE2EEPrepareDownloadDocumentMethod;
    if ((getE2EEPrepareDownloadDocumentMethod = StrongDocServiceGrpc.getE2EEPrepareDownloadDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getE2EEPrepareDownloadDocumentMethod = StrongDocServiceGrpc.getE2EEPrepareDownloadDocumentMethod) == null) {
          StrongDocServiceGrpc.getE2EEPrepareDownloadDocumentMethod = getE2EEPrepareDownloadDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "E2EEPrepareDownloadDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("E2EEPrepareDownloadDocument"))
              .build();
        }
      }
    }
    return getE2EEPrepareDownloadDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> getE2EEDownloadDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "E2EEDownloadDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> getE2EEDownloadDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> getE2EEDownloadDocumentStreamMethod;
    if ((getE2EEDownloadDocumentStreamMethod = StrongDocServiceGrpc.getE2EEDownloadDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getE2EEDownloadDocumentStreamMethod = StrongDocServiceGrpc.getE2EEDownloadDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getE2EEDownloadDocumentStreamMethod = getE2EEDownloadDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "E2EEDownloadDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("E2EEDownloadDocumentStream"))
              .build();
        }
      }
    }
    return getE2EEDownloadDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> getUploadDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UploadDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> getUploadDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> getUploadDocumentMethod;
    if ((getUploadDocumentMethod = StrongDocServiceGrpc.getUploadDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getUploadDocumentMethod = StrongDocServiceGrpc.getUploadDocumentMethod) == null) {
          StrongDocServiceGrpc.getUploadDocumentMethod = getUploadDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UploadDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("UploadDocument"))
              .build();
        }
      }
    }
    return getUploadDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> getDownloadDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> getDownloadDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> getDownloadDocumentStreamMethod;
    if ((getDownloadDocumentStreamMethod = StrongDocServiceGrpc.getDownloadDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getDownloadDocumentStreamMethod = StrongDocServiceGrpc.getDownloadDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getDownloadDocumentStreamMethod = getDownloadDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq, com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DownloadDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("DownloadDocumentStream"))
              .build();
        }
      }
    }
    return getDownloadDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> getDownloadDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DownloadDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> getDownloadDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> getDownloadDocumentMethod;
    if ((getDownloadDocumentMethod = StrongDocServiceGrpc.getDownloadDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getDownloadDocumentMethod = StrongDocServiceGrpc.getDownloadDocumentMethod) == null) {
          StrongDocServiceGrpc.getDownloadDocumentMethod = getDownloadDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq, com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DownloadDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("DownloadDocument"))
              .build();
        }
      }
    }
    return getDownloadDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp> getEncryptDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EncryptDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp> getEncryptDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp> getEncryptDocumentStreamMethod;
    if ((getEncryptDocumentStreamMethod = StrongDocServiceGrpc.getEncryptDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getEncryptDocumentStreamMethod = StrongDocServiceGrpc.getEncryptDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getEncryptDocumentStreamMethod = getEncryptDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EncryptDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("EncryptDocumentStream"))
              .build();
        }
      }
    }
    return getEncryptDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> getEncryptDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "EncryptDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> getEncryptDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> getEncryptDocumentMethod;
    if ((getEncryptDocumentMethod = StrongDocServiceGrpc.getEncryptDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getEncryptDocumentMethod = StrongDocServiceGrpc.getEncryptDocumentMethod) == null) {
          StrongDocServiceGrpc.getEncryptDocumentMethod = getEncryptDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "EncryptDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("EncryptDocument"))
              .build();
        }
      }
    }
    return getEncryptDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp> getDecryptDocumentStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecryptDocumentStream",
      requestType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp> getDecryptDocumentStreamMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp> getDecryptDocumentStreamMethod;
    if ((getDecryptDocumentStreamMethod = StrongDocServiceGrpc.getDecryptDocumentStreamMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getDecryptDocumentStreamMethod = StrongDocServiceGrpc.getDecryptDocumentStreamMethod) == null) {
          StrongDocServiceGrpc.getDecryptDocumentStreamMethod = getDecryptDocumentStreamMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DecryptDocumentStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("DecryptDocumentStream"))
              .build();
        }
      }
    }
    return getDecryptDocumentStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> getDecryptDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DecryptDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq,
      com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> getDecryptDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> getDecryptDocumentMethod;
    if ((getDecryptDocumentMethod = StrongDocServiceGrpc.getDecryptDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getDecryptDocumentMethod = StrongDocServiceGrpc.getDecryptDocumentMethod) == null) {
          StrongDocServiceGrpc.getDecryptDocumentMethod = getDecryptDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq, com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DecryptDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("DecryptDocument"))
              .build();
        }
      }
    }
    return getDecryptDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> getPrepareShareDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrepareShareDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> getPrepareShareDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> getPrepareShareDocumentMethod;
    if ((getPrepareShareDocumentMethod = StrongDocServiceGrpc.getPrepareShareDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getPrepareShareDocumentMethod = StrongDocServiceGrpc.getPrepareShareDocumentMethod) == null) {
          StrongDocServiceGrpc.getPrepareShareDocumentMethod = getPrepareShareDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrepareShareDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("PrepareShareDocument"))
              .build();
        }
      }
    }
    return getPrepareShareDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> getShareDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ShareDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> getShareDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> getShareDocumentMethod;
    if ((getShareDocumentMethod = StrongDocServiceGrpc.getShareDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getShareDocumentMethod = StrongDocServiceGrpc.getShareDocumentMethod) == null) {
          StrongDocServiceGrpc.getShareDocumentMethod = getShareDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ShareDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ShareDocument"))
              .build();
        }
      }
    }
    return getShareDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> getUnshareDocumentMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UnshareDocument",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> getUnshareDocumentMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> getUnshareDocumentMethod;
    if ((getUnshareDocumentMethod = StrongDocServiceGrpc.getUnshareDocumentMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getUnshareDocumentMethod = StrongDocServiceGrpc.getUnshareDocumentMethod) == null) {
          StrongDocServiceGrpc.getUnshareDocumentMethod = getUnshareDocumentMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq, com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UnshareDocument"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("UnshareDocument"))
              .build();
        }
      }
    }
    return getUnshareDocumentMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> getListDocActionHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListDocActionHistory",
      requestType = com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq,
      com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> getListDocActionHistoryMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq, com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> getListDocActionHistoryMethod;
    if ((getListDocActionHistoryMethod = StrongDocServiceGrpc.getListDocActionHistoryMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListDocActionHistoryMethod = StrongDocServiceGrpc.getListDocActionHistoryMethod) == null) {
          StrongDocServiceGrpc.getListDocActionHistoryMethod = getListDocActionHistoryMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq, com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListDocActionHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListDocActionHistory"))
              .build();
        }
      }
    }
    return getListDocActionHistoryMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LoginReq,
      com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> getLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Login",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.LoginReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.LoginResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LoginReq,
      com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> getLoginMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LoginReq, com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> getLoginMethod;
    if ((getLoginMethod = StrongDocServiceGrpc.getLoginMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getLoginMethod = StrongDocServiceGrpc.getLoginMethod) == null) {
          StrongDocServiceGrpc.getLoginMethod = getLoginMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.LoginReq, com.strongsalt.strongdoc.sdk.proto.Account.LoginResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Login"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.LoginReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.LoginResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("Login"))
              .build();
        }
      }
    }
    return getLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> getPrepareLoginMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrepareLogin",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> getPrepareLoginMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq, com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> getPrepareLoginMethod;
    if ((getPrepareLoginMethod = StrongDocServiceGrpc.getPrepareLoginMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getPrepareLoginMethod = StrongDocServiceGrpc.getPrepareLoginMethod) == null) {
          StrongDocServiceGrpc.getPrepareLoginMethod = getPrepareLoginMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq, com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrepareLogin"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("PrepareLogin"))
              .build();
        }
      }
    }
    return getPrepareLoginMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> getPrepareAuthMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "PrepareAuth",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq,
      com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> getPrepareAuthMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq, com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> getPrepareAuthMethod;
    if ((getPrepareAuthMethod = StrongDocServiceGrpc.getPrepareAuthMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getPrepareAuthMethod = StrongDocServiceGrpc.getPrepareAuthMethod) == null) {
          StrongDocServiceGrpc.getPrepareAuthMethod = getPrepareAuthMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq, com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "PrepareAuth"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("PrepareAuth"))
              .build();
        }
      }
    }
    return getPrepareAuthMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> getSrpInitMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SrpInit",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> getSrpInitMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq, com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> getSrpInitMethod;
    if ((getSrpInitMethod = StrongDocServiceGrpc.getSrpInitMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSrpInitMethod = StrongDocServiceGrpc.getSrpInitMethod) == null) {
          StrongDocServiceGrpc.getSrpInitMethod = getSrpInitMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq, com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SrpInit"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SrpInit"))
              .build();
        }
      }
    }
    return getSrpInitMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> getSrpProofMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SrpProof",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> getSrpProofMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq, com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> getSrpProofMethod;
    if ((getSrpProofMethod = StrongDocServiceGrpc.getSrpProofMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSrpProofMethod = StrongDocServiceGrpc.getSrpProofMethod) == null) {
          StrongDocServiceGrpc.getSrpProofMethod = getSrpProofMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq, com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SrpProof"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SrpProof"))
              .build();
        }
      }
    }
    return getSrpProofMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq,
      com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> getLogoutMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Logout",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq,
      com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> getLogoutMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq, com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> getLogoutMethod;
    if ((getLogoutMethod = StrongDocServiceGrpc.getLogoutMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getLogoutMethod = StrongDocServiceGrpc.getLogoutMethod) == null) {
          StrongDocServiceGrpc.getLogoutMethod = getLogoutMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq, com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Logout"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("Logout"))
              .build();
        }
      }
    }
    return getLogoutMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Search.SearchReq,
      com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> getSearchMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "Search",
      requestType = com.strongsalt.strongdoc.sdk.proto.Search.SearchReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Search.SearchResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Search.SearchReq,
      com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> getSearchMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Search.SearchReq, com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> getSearchMethod;
    if ((getSearchMethod = StrongDocServiceGrpc.getSearchMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSearchMethod = StrongDocServiceGrpc.getSearchMethod) == null) {
          StrongDocServiceGrpc.getSearchMethod = getSearchMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Search.SearchReq, com.strongsalt.strongdoc.sdk.proto.Search.SearchResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "Search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Search.SearchReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Search.SearchResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("Search"))
              .build();
        }
      }
    }
    return getSearchMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq,
      com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> getAddSharableOrgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddSharableOrg",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq,
      com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> getAddSharableOrgMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq, com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> getAddSharableOrgMethod;
    if ((getAddSharableOrgMethod = StrongDocServiceGrpc.getAddSharableOrgMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getAddSharableOrgMethod = StrongDocServiceGrpc.getAddSharableOrgMethod) == null) {
          StrongDocServiceGrpc.getAddSharableOrgMethod = getAddSharableOrgMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq, com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddSharableOrg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("AddSharableOrg"))
              .build();
        }
      }
    }
    return getAddSharableOrgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> getRemoveSharableOrgMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemoveSharableOrg",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq,
      com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> getRemoveSharableOrgMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> getRemoveSharableOrgMethod;
    if ((getRemoveSharableOrgMethod = StrongDocServiceGrpc.getRemoveSharableOrgMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRemoveSharableOrgMethod = StrongDocServiceGrpc.getRemoveSharableOrgMethod) == null) {
          StrongDocServiceGrpc.getRemoveSharableOrgMethod = getRemoveSharableOrgMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq, com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemoveSharableOrg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RemoveSharableOrg"))
              .build();
        }
      }
    }
    return getRemoveSharableOrgMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> getSetMultiLevelSharingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetMultiLevelSharing",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> getSetMultiLevelSharingMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq, com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> getSetMultiLevelSharingMethod;
    if ((getSetMultiLevelSharingMethod = StrongDocServiceGrpc.getSetMultiLevelSharingMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetMultiLevelSharingMethod = StrongDocServiceGrpc.getSetMultiLevelSharingMethod) == null) {
          StrongDocServiceGrpc.getSetMultiLevelSharingMethod = getSetMultiLevelSharingMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq, com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetMultiLevelSharing"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetMultiLevelSharing"))
              .build();
        }
      }
    }
    return getSetMultiLevelSharingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> getSetAccountInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetAccountInfo",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> getSetAccountInfoMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> getSetAccountInfoMethod;
    if ((getSetAccountInfoMethod = StrongDocServiceGrpc.getSetAccountInfoMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetAccountInfoMethod = StrongDocServiceGrpc.getSetAccountInfoMethod) == null) {
          StrongDocServiceGrpc.getSetAccountInfoMethod = getSetAccountInfoMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetAccountInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetAccountInfo"))
              .build();
        }
      }
    }
    return getSetAccountInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> getGetBillingDetailsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBillingDetails",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> getGetBillingDetailsMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> getGetBillingDetailsMethod;
    if ((getGetBillingDetailsMethod = StrongDocServiceGrpc.getGetBillingDetailsMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetBillingDetailsMethod = StrongDocServiceGrpc.getGetBillingDetailsMethod) == null) {
          StrongDocServiceGrpc.getGetBillingDetailsMethod = getGetBillingDetailsMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBillingDetails"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetBillingDetails"))
              .build();
        }
      }
    }
    return getGetBillingDetailsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> getGetBillingFrequencyListMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBillingFrequencyList",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> getGetBillingFrequencyListMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> getGetBillingFrequencyListMethod;
    if ((getGetBillingFrequencyListMethod = StrongDocServiceGrpc.getGetBillingFrequencyListMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetBillingFrequencyListMethod = StrongDocServiceGrpc.getGetBillingFrequencyListMethod) == null) {
          StrongDocServiceGrpc.getGetBillingFrequencyListMethod = getGetBillingFrequencyListMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBillingFrequencyList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetBillingFrequencyList"))
              .build();
        }
      }
    }
    return getGetBillingFrequencyListMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> getSetNextBillingFrequencyMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetNextBillingFrequency",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> getSetNextBillingFrequencyMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq, com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> getSetNextBillingFrequencyMethod;
    if ((getSetNextBillingFrequencyMethod = StrongDocServiceGrpc.getSetNextBillingFrequencyMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetNextBillingFrequencyMethod = StrongDocServiceGrpc.getSetNextBillingFrequencyMethod) == null) {
          StrongDocServiceGrpc.getSetNextBillingFrequencyMethod = getSetNextBillingFrequencyMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq, com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetNextBillingFrequency"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetNextBillingFrequency"))
              .build();
        }
      }
    }
    return getSetNextBillingFrequencyMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> getGetLargeTrafficMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetLargeTraffic",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> getGetLargeTrafficMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> getGetLargeTrafficMethod;
    if ((getGetLargeTrafficMethod = StrongDocServiceGrpc.getGetLargeTrafficMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetLargeTrafficMethod = StrongDocServiceGrpc.getGetLargeTrafficMethod) == null) {
          StrongDocServiceGrpc.getGetLargeTrafficMethod = getGetLargeTrafficMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq, com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetLargeTraffic"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetLargeTraffic"))
              .build();
        }
      }
    }
    return getGetLargeTrafficMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> getGetAccountInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetAccountInfo",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> getGetAccountInfoMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> getGetAccountInfoMethod;
    if ((getGetAccountInfoMethod = StrongDocServiceGrpc.getGetAccountInfoMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetAccountInfoMethod = StrongDocServiceGrpc.getGetAccountInfoMethod) == null) {
          StrongDocServiceGrpc.getGetAccountInfoMethod = getGetAccountInfoMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetAccountInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetAccountInfo"))
              .build();
        }
      }
    }
    return getGetAccountInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> getGetUserInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetUserInfo",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> getGetUserInfoMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> getGetUserInfoMethod;
    if ((getGetUserInfoMethod = StrongDocServiceGrpc.getGetUserInfoMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getGetUserInfoMethod = StrongDocServiceGrpc.getGetUserInfoMethod) == null) {
          StrongDocServiceGrpc.getGetUserInfoMethod = getGetUserInfoMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetUserInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("GetUserInfo"))
              .build();
        }
      }
    }
    return getGetUserInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> getChangeUserPasswordMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ChangeUserPassword",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq,
      com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> getChangeUserPasswordMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq, com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> getChangeUserPasswordMethod;
    if ((getChangeUserPasswordMethod = StrongDocServiceGrpc.getChangeUserPasswordMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getChangeUserPasswordMethod = StrongDocServiceGrpc.getChangeUserPasswordMethod) == null) {
          StrongDocServiceGrpc.getChangeUserPasswordMethod = getChangeUserPasswordMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq, com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ChangeUserPassword"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ChangeUserPassword"))
              .build();
        }
      }
    }
    return getChangeUserPasswordMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> getSetUserInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetUserInfo",
      requestType = com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq,
      com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> getSetUserInfoMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> getSetUserInfoMethod;
    if ((getSetUserInfoMethod = StrongDocServiceGrpc.getSetUserInfoMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetUserInfoMethod = StrongDocServiceGrpc.getSetUserInfoMethod) == null) {
          StrongDocServiceGrpc.getSetUserInfoMethod = getSetUserInfoMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq, com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetUserInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetUserInfo"))
              .build();
        }
      }
    }
    return getSetUserInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> getListCreditCardsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListCreditCards",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> getListCreditCardsMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq, com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> getListCreditCardsMethod;
    if ((getListCreditCardsMethod = StrongDocServiceGrpc.getListCreditCardsMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListCreditCardsMethod = StrongDocServiceGrpc.getListCreditCardsMethod) == null) {
          StrongDocServiceGrpc.getListCreditCardsMethod = getListCreditCardsMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq, com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListCreditCards"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListCreditCards"))
              .build();
        }
      }
    }
    return getListCreditCardsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> getAddPaymentMethodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "AddPaymentMethod",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> getAddPaymentMethodMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> getAddPaymentMethodMethod;
    if ((getAddPaymentMethodMethod = StrongDocServiceGrpc.getAddPaymentMethodMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getAddPaymentMethodMethod = StrongDocServiceGrpc.getAddPaymentMethodMethod) == null) {
          StrongDocServiceGrpc.getAddPaymentMethodMethod = getAddPaymentMethodMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "AddPaymentMethod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("AddPaymentMethod"))
              .build();
        }
      }
    }
    return getAddPaymentMethodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> getSetDefaultPaymentMethodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SetDefaultPaymentMethod",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> getSetDefaultPaymentMethodMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> getSetDefaultPaymentMethodMethod;
    if ((getSetDefaultPaymentMethodMethod = StrongDocServiceGrpc.getSetDefaultPaymentMethodMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getSetDefaultPaymentMethodMethod = StrongDocServiceGrpc.getSetDefaultPaymentMethodMethod) == null) {
          StrongDocServiceGrpc.getSetDefaultPaymentMethodMethod = getSetDefaultPaymentMethodMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SetDefaultPaymentMethod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("SetDefaultPaymentMethod"))
              .build();
        }
      }
    }
    return getSetDefaultPaymentMethodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> getRemovePaymentMethodMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "RemovePaymentMethod",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> getRemovePaymentMethodMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> getRemovePaymentMethodMethod;
    if ((getRemovePaymentMethodMethod = StrongDocServiceGrpc.getRemovePaymentMethodMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getRemovePaymentMethodMethod = StrongDocServiceGrpc.getRemovePaymentMethodMethod) == null) {
          StrongDocServiceGrpc.getRemovePaymentMethodMethod = getRemovePaymentMethodMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq, com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "RemovePaymentMethod"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("RemovePaymentMethod"))
              .build();
        }
      }
    }
    return getRemovePaymentMethodMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> getListPaymentsMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ListPayments",
      requestType = com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq.class,
      responseType = com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq,
      com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> getListPaymentsMethod() {
    io.grpc.MethodDescriptor<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq, com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> getListPaymentsMethod;
    if ((getListPaymentsMethod = StrongDocServiceGrpc.getListPaymentsMethod) == null) {
      synchronized (StrongDocServiceGrpc.class) {
        if ((getListPaymentsMethod = StrongDocServiceGrpc.getListPaymentsMethod) == null) {
          StrongDocServiceGrpc.getListPaymentsMethod = getListPaymentsMethod =
              io.grpc.MethodDescriptor.<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq, com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ListPayments"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp.getDefaultInstance()))
              .setSchemaDescriptor(new StrongDocServiceMethodDescriptorSupplier("ListPayments"))
              .build();
        }
      }
    }
    return getListPaymentsMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static StrongDocServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceStub>() {
        @java.lang.Override
        public StrongDocServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongDocServiceStub(channel, callOptions);
        }
      };
    return StrongDocServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static StrongDocServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceBlockingStub>() {
        @java.lang.Override
        public StrongDocServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongDocServiceBlockingStub(channel, callOptions);
        }
      };
    return StrongDocServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static StrongDocServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<StrongDocServiceFutureStub>() {
        @java.lang.Override
        public StrongDocServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new StrongDocServiceFutureStub(channel, callOptions);
        }
      };
    return StrongDocServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class StrongDocServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * Registers a new organization
     * The user who created the organization is automatically an administrator
     * Does not require Login
     * </pre>
     */
    public void registerOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterOrganizationMethod(), responseObserver);
    }

    /**
     * <pre>
     * Reactivate an organization that was unsubscribed via aws
     * The user reactivating the organization becomes the administrator
     * Does not require login
     * </pre>
     */
    public void reactivateOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> responseObserver) {
      asyncUnimplementedUnaryCall(getReactivateOrganizationMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove an organization and its search indexes
     * Requires Administrator privilege. Only an administrator can remove the whole organization
     * Requires Login
     * </pre>
     */
    public void removeOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveOrganizationMethod(), responseObserver);
    }

    /**
     * <pre>
     * InviteUser
     * Requires administrator privilege
     * </pre>
     */
    public void inviteUser(com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getInviteUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public void listInvitations(com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListInvitationsMethod(), responseObserver);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public void revokeInvitation(com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRevokeInvitationMethod(), responseObserver);
    }

    /**
     * <pre>
     * Register new user
     * Creates new user if it doesn't already exist. If the user already exist, and
     * error is thrown
     * Does not require Login
     * </pre>
     */
    public void registerUser(com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRegisterUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Get user private keys
     * Requires Login
     * </pre>
     */
    public void getUserPrivateKeys(com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserPrivateKeysMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sets the user's kdf metadata for a new passwordKey
     * Requires Login
     * </pre>
     */
    public void setUserKdfMetadata(com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetUserKdfMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * Sets the user's authentication metadata
     * Requires Login
     * </pre>
     */
    public void setUserAuthMetadata(com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetUserAuthMetadataMethod(), responseObserver);
    }

    /**
     * <pre>
     * List the users of the organization
     * Requires Login
     * </pre>
     */
    public void listUsers(com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListUsersMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove user from organization
     * Removes the user from the organization. The users documents still exists,
     * but belong to the organization, only accessible by organization admin.
     * Requires administrator privilege.
     * </pre>
     */
    public void removeUser(com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Prepare to promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public void preparePromoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPreparePromoteUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public void promoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPromoteUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * Demote administrator to regular user at the organization. Attempting to
     * demote the last administrator of an organization will fail
     * Requires administrator privilege.
     * </pre>
     */
    public void demoteUser(com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDemoteUserMethod(), responseObserver);
    }

    /**
     * <pre>
     * List the documents the user can access
     * Administrators can see all documents belonging to the organization
     * Requires Login
     * </pre>
     */
    public void listDocuments(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListDocumentsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove document the user can access
     * Admin user can remove document for the whole organization
     * Regular user only can remove document for him/herself
     * Requires Login
     * </pre>
     */
    public void removeDocument(com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq> uploadDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp> responseObserver) {
      return asyncUnimplementedStreamingCall(getUploadDocumentStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * This is not available through gRPC REST gateway,
     * since REST api does not support streaming protocol
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq> e2EEUploadDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp> responseObserver) {
      return asyncUnimplementedStreamingCall(getE2EEUploadDocumentStreamMethod(), responseObserver);
    }

    /**
     */
    public void e2EEPrepareDownloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> responseObserver) {
      asyncUnimplementedUnaryCall(getE2EEPrepareDownloadDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * This is not available through gRPC REST gateway,
     * since REST api does not support streaming protocol
     * </pre>
     */
    public void e2EEDownloadDocumentStream(com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> responseObserver) {
      asyncUnimplementedUnaryCall(getE2EEDownloadDocumentStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public void uploadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUploadDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Download document stream
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public void downloadDocumentStream(com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadDocumentStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * Download document
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public void downloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDownloadDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Encrypt document stream encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq> encryptDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp> responseObserver) {
      return asyncUnimplementedStreamingCall(getEncryptDocumentStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * Encrypt document encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public void encryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> responseObserver) {
      asyncUnimplementedUnaryCall(getEncryptDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Decrypt document stream decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq> decryptDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp> responseObserver) {
      return asyncUnimplementedStreamingCall(getDecryptDocumentStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * Decrypt document decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * </pre>
     */
    public void decryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> responseObserver) {
      asyncUnimplementedUnaryCall(getDecryptDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * PrepareShare a document to another user
     * Requires Login
     * </pre>
     */
    public void prepareShareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPrepareShareDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Share a document to another user
     * Requires Login
     * </pre>
     */
    public void shareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> responseObserver) {
      asyncUnimplementedUnaryCall(getShareDocumentMethod(), responseObserver);
    }

    /**
     * <pre>
     * Unshare a document that had previously been shared to a user
     * Requires Login
     * </pre>
     */
    public void unshareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> responseObserver) {
      asyncUnimplementedUnaryCall(getUnshareDocumentMethod(), responseObserver);
    }

    /**
     */
    public void listDocActionHistory(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListDocActionHistoryMethod(), responseObserver);
    }

    /**
     * <pre>
     * Obtain an authentication token to be used with other APIs
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public void login(com.strongsalt.strongdoc.sdk.proto.Account.LoginReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> responseObserver) {
      asyncUnimplementedUnaryCall(getLoginMethod(), responseObserver);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to login with a specified user
     * </pre>
     */
    public void prepareLogin(com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPrepareLoginMethod(), responseObserver);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to authenticate with a specified user
     * Requires Login
     * </pre>
     */
    public void prepareAuth(com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> responseObserver) {
      asyncUnimplementedUnaryCall(getPrepareAuthMethod(), responseObserver);
    }

    /**
     * <pre>
     * The first step in the SRP login proccess
     * </pre>
     */
    public void srpInit(com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSrpInitMethod(), responseObserver);
    }

    /**
     * <pre>
     * The second and final step in the SRP login proccess
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public void srpProof(com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSrpProofMethod(), responseObserver);
    }

    /**
     * <pre>
     * Logout current user
     * Requires Login
     * </pre>
     */
    public void logout(com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> responseObserver) {
      asyncUnimplementedUnaryCall(getLogoutMethod(), responseObserver);
    }

    /**
     * <pre>
     * Search within a list of user's accessible documents
     * The response will include a list document id and its score when matches are found
     * Requires Login
     * </pre>
     */
    public void search(com.strongsalt.strongdoc.sdk.proto.Search.SearchReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    /**
     * <pre>
     *Add a sharable organization to the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void addSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> responseObserver) {
      asyncUnimplementedUnaryCall(getAddSharableOrgMethod(), responseObserver);
    }

    /**
     * <pre>
     *Remove a sharable organization from the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void removeSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRemoveSharableOrgMethod(), responseObserver);
    }

    /**
     * <pre>
     *Update the organization's multi-level sharing setting
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void setMultiLevelSharing(com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetMultiLevelSharingMethod(), responseObserver);
    }

    /**
     * <pre>
     *Update the organization's account info
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void setAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetAccountInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     *List all items of the cost breakdown and also other details such as the billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getBillingDetails(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBillingDetailsMethod(), responseObserver);
    }

    /**
     * <pre>
     *Obtain the list of billing frequencies (past, current and future)
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getBillingFrequencyList(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetBillingFrequencyListMethod(), responseObserver);
    }

    /**
     * <pre>
     *Change the next billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void setNextBillingFrequency(com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetNextBillingFrequencyMethod(), responseObserver);
    }

    /**
     * <pre>
     *Obtain the list of large traffic usages
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getLargeTraffic(com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetLargeTrafficMethod(), responseObserver);
    }

    /**
     * <pre>
     *Obtain information about the account
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetAccountInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Obtain information about logged in user
     * Requires Login
     * </pre>
     */
    public void getUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Change the password of a logged in user
     * Requires Login
     * </pre>
     */
    public void changeUserPassword(com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> responseObserver) {
      asyncUnimplementedUnaryCall(getChangeUserPasswordMethod(), responseObserver);
    }

    /**
     * <pre>
     * Set information about a logged in user
     * Requires Login
     * </pre>
     */
    public void setUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetUserInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * Obtain a list of the org's credit cards
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void listCreditCards(com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListCreditCardsMethod(), responseObserver);
    }

    /**
     * <pre>
     * Add a payment method to the customer and validate the payment method
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void addPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> responseObserver) {
      asyncUnimplementedUnaryCall(getAddPaymentMethodMethod(), responseObserver);
    }

    /**
     * <pre>
     * Set the default payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void setDefaultPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> responseObserver) {
      asyncUnimplementedUnaryCall(getSetDefaultPaymentMethodMethod(), responseObserver);
    }

    /**
     * <pre>
     * Remove a payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void removePaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> responseObserver) {
      asyncUnimplementedUnaryCall(getRemovePaymentMethodMethod(), responseObserver);
    }

    /**
     * <pre>
     * List payments for the organization
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void listPayments(com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> responseObserver) {
      asyncUnimplementedUnaryCall(getListPaymentsMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegisterOrganizationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>(
                  this, METHODID_REGISTER_ORGANIZATION)))
          .addMethod(
            getReactivateOrganizationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>(
                  this, METHODID_REACTIVATE_ORGANIZATION)))
          .addMethod(
            getRemoveOrganizationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp>(
                  this, METHODID_REMOVE_ORGANIZATION)))
          .addMethod(
            getInviteUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp>(
                  this, METHODID_INVITE_USER)))
          .addMethod(
            getListInvitationsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq,
                com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp>(
                  this, METHODID_LIST_INVITATIONS)))
          .addMethod(
            getRevokeInvitationMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp>(
                  this, METHODID_REVOKE_INVITATION)))
          .addMethod(
            getRegisterUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp>(
                  this, METHODID_REGISTER_USER)))
          .addMethod(
            getGetUserPrivateKeysMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq,
                com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp>(
                  this, METHODID_GET_USER_PRIVATE_KEYS)))
          .addMethod(
            getSetUserKdfMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq,
                com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp>(
                  this, METHODID_SET_USER_KDF_METADATA)))
          .addMethod(
            getSetUserAuthMetadataMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp>(
                  this, METHODID_SET_USER_AUTH_METADATA)))
          .addMethod(
            getListUsersMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq,
                com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp>(
                  this, METHODID_LIST_USERS)))
          .addMethod(
            getRemoveUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp>(
                  this, METHODID_REMOVE_USER)))
          .addMethod(
            getPreparePromoteUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp>(
                  this, METHODID_PREPARE_PROMOTE_USER)))
          .addMethod(
            getPromoteUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp>(
                  this, METHODID_PROMOTE_USER)))
          .addMethod(
            getDemoteUserMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq,
                com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp>(
                  this, METHODID_DEMOTE_USER)))
          .addMethod(
            getListDocumentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp>(
                  this, METHODID_LIST_DOCUMENTS)))
          .addMethod(
            getRemoveDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp>(
                  this, METHODID_REMOVE_DOCUMENT)))
          .addMethod(
            getUploadDocumentStreamMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp>(
                  this, METHODID_UPLOAD_DOCUMENT_STREAM)))
          .addMethod(
            getE2EEUploadDocumentStreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp>(
                  this, METHODID_E2EEUPLOAD_DOCUMENT_STREAM)))
          .addMethod(
            getE2EEPrepareDownloadDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp>(
                  this, METHODID_E2EEPREPARE_DOWNLOAD_DOCUMENT)))
          .addMethod(
            getE2EEDownloadDocumentStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp>(
                  this, METHODID_E2EEDOWNLOAD_DOCUMENT_STREAM)))
          .addMethod(
            getUploadDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp>(
                  this, METHODID_UPLOAD_DOCUMENT)))
          .addMethod(
            getDownloadDocumentStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp>(
                  this, METHODID_DOWNLOAD_DOCUMENT_STREAM)))
          .addMethod(
            getDownloadDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp>(
                  this, METHODID_DOWNLOAD_DOCUMENT)))
          .addMethod(
            getEncryptDocumentStreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp>(
                  this, METHODID_ENCRYPT_DOCUMENT_STREAM)))
          .addMethod(
            getEncryptDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq,
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp>(
                  this, METHODID_ENCRYPT_DOCUMENT)))
          .addMethod(
            getDecryptDocumentStreamMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq,
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp>(
                  this, METHODID_DECRYPT_DOCUMENT_STREAM)))
          .addMethod(
            getDecryptDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq,
                com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp>(
                  this, METHODID_DECRYPT_DOCUMENT)))
          .addMethod(
            getPrepareShareDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp>(
                  this, METHODID_PREPARE_SHARE_DOCUMENT)))
          .addMethod(
            getShareDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp>(
                  this, METHODID_SHARE_DOCUMENT)))
          .addMethod(
            getUnshareDocumentMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp>(
                  this, METHODID_UNSHARE_DOCUMENT)))
          .addMethod(
            getListDocActionHistoryMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq,
                com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp>(
                  this, METHODID_LIST_DOC_ACTION_HISTORY)))
          .addMethod(
            getLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.LoginReq,
                com.strongsalt.strongdoc.sdk.proto.Account.LoginResp>(
                  this, METHODID_LOGIN)))
          .addMethod(
            getPrepareLoginMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq,
                com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp>(
                  this, METHODID_PREPARE_LOGIN)))
          .addMethod(
            getPrepareAuthMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq,
                com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp>(
                  this, METHODID_PREPARE_AUTH)))
          .addMethod(
            getSrpInitMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp>(
                  this, METHODID_SRP_INIT)))
          .addMethod(
            getSrpProofMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp>(
                  this, METHODID_SRP_PROOF)))
          .addMethod(
            getLogoutMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq,
                com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp>(
                  this, METHODID_LOGOUT)))
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Search.SearchReq,
                com.strongsalt.strongdoc.sdk.proto.Search.SearchResp>(
                  this, METHODID_SEARCH)))
          .addMethod(
            getAddSharableOrgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq,
                com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp>(
                  this, METHODID_ADD_SHARABLE_ORG)))
          .addMethod(
            getRemoveSharableOrgMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq,
                com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp>(
                  this, METHODID_REMOVE_SHARABLE_ORG)))
          .addMethod(
            getSetMultiLevelSharingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp>(
                  this, METHODID_SET_MULTI_LEVEL_SHARING)))
          .addMethod(
            getSetAccountInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp>(
                  this, METHODID_SET_ACCOUNT_INFO)))
          .addMethod(
            getGetBillingDetailsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp>(
                  this, METHODID_GET_BILLING_DETAILS)))
          .addMethod(
            getGetBillingFrequencyListMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp>(
                  this, METHODID_GET_BILLING_FREQUENCY_LIST)))
          .addMethod(
            getSetNextBillingFrequencyMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp>(
                  this, METHODID_SET_NEXT_BILLING_FREQUENCY)))
          .addMethod(
            getGetLargeTrafficMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp>(
                  this, METHODID_GET_LARGE_TRAFFIC)))
          .addMethod(
            getGetAccountInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq,
                com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp>(
                  this, METHODID_GET_ACCOUNT_INFO)))
          .addMethod(
            getGetUserInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq,
                com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp>(
                  this, METHODID_GET_USER_INFO)))
          .addMethod(
            getChangeUserPasswordMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq,
                com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp>(
                  this, METHODID_CHANGE_USER_PASSWORD)))
          .addMethod(
            getSetUserInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq,
                com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp>(
                  this, METHODID_SET_USER_INFO)))
          .addMethod(
            getListCreditCardsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp>(
                  this, METHODID_LIST_CREDIT_CARDS)))
          .addMethod(
            getAddPaymentMethodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp>(
                  this, METHODID_ADD_PAYMENT_METHOD)))
          .addMethod(
            getSetDefaultPaymentMethodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp>(
                  this, METHODID_SET_DEFAULT_PAYMENT_METHOD)))
          .addMethod(
            getRemovePaymentMethodMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp>(
                  this, METHODID_REMOVE_PAYMENT_METHOD)))
          .addMethod(
            getListPaymentsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq,
                com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp>(
                  this, METHODID_LIST_PAYMENTS)))
          .build();
    }
  }

  /**
   */
  public static final class StrongDocServiceStub extends io.grpc.stub.AbstractAsyncStub<StrongDocServiceStub> {
    private StrongDocServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongDocServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongDocServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * Registers a new organization
     * The user who created the organization is automatically an administrator
     * Does not require Login
     * </pre>
     */
    public void registerOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterOrganizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Reactivate an organization that was unsubscribed via aws
     * The user reactivating the organization becomes the administrator
     * Does not require login
     * </pre>
     */
    public void reactivateOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getReactivateOrganizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove an organization and its search indexes
     * Requires Administrator privilege. Only an administrator can remove the whole organization
     * Requires Login
     * </pre>
     */
    public void removeOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveOrganizationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * InviteUser
     * Requires administrator privilege
     * </pre>
     */
    public void inviteUser(com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getInviteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public void listInvitations(com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListInvitationsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public void revokeInvitation(com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRevokeInvitationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Register new user
     * Creates new user if it doesn't already exist. If the user already exist, and
     * error is thrown
     * Does not require Login
     * </pre>
     */
    public void registerUser(com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Get user private keys
     * Requires Login
     * </pre>
     */
    public void getUserPrivateKeys(com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserPrivateKeysMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sets the user's kdf metadata for a new passwordKey
     * Requires Login
     * </pre>
     */
    public void setUserKdfMetadata(com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetUserKdfMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Sets the user's authentication metadata
     * Requires Login
     * </pre>
     */
    public void setUserAuthMetadata(com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetUserAuthMetadataMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List the users of the organization
     * Requires Login
     * </pre>
     */
    public void listUsers(com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListUsersMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove user from organization
     * Removes the user from the organization. The users documents still exists,
     * but belong to the organization, only accessible by organization admin.
     * Requires administrator privilege.
     * </pre>
     */
    public void removeUser(com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Prepare to promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public void preparePromoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPreparePromoteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public void promoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPromoteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Demote administrator to regular user at the organization. Attempting to
     * demote the last administrator of an organization will fail
     * Requires administrator privilege.
     * </pre>
     */
    public void demoteUser(com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDemoteUserMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List the documents the user can access
     * Administrators can see all documents belonging to the organization
     * Requires Login
     * </pre>
     */
    public void listDocuments(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListDocumentsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove document the user can access
     * Admin user can remove document for the whole organization
     * Regular user only can remove document for him/herself
     * Requires Login
     * </pre>
     */
    public void removeDocument(com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamReq> uploadDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getUploadDocumentStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * This is not available through gRPC REST gateway,
     * since REST api does not support streaming protocol
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamReq> e2EEUploadDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getE2EEUploadDocumentStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     */
    public void e2EEPrepareDownloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getE2EEPrepareDownloadDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * This is not available through gRPC REST gateway,
     * since REST api does not support streaming protocol
     * </pre>
     */
    public void e2EEDownloadDocumentStream(com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getE2EEDownloadDocumentStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public void uploadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUploadDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Download document stream
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public void downloadDocumentStream(com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getDownloadDocumentStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Download document
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public void downloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDownloadDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Encrypt document stream encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamReq> encryptDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getEncryptDocumentStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Encrypt document encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public void encryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getEncryptDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Decrypt document stream decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * Requires Login
     * </pre>
     */
    public io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamReq> decryptDocumentStream(
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getDecryptDocumentStreamMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * Decrypt document decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * </pre>
     */
    public void decryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getDecryptDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * PrepareShare a document to another user
     * Requires Login
     * </pre>
     */
    public void prepareShareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrepareShareDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Share a document to another user
     * Requires Login
     * </pre>
     */
    public void shareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getShareDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Unshare a document that had previously been shared to a user
     * Requires Login
     * </pre>
     */
    public void unshareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnshareDocumentMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void listDocActionHistory(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListDocActionHistoryMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Obtain an authentication token to be used with other APIs
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public void login(com.strongsalt.strongdoc.sdk.proto.Account.LoginReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to login with a specified user
     * </pre>
     */
    public void prepareLogin(com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrepareLoginMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to authenticate with a specified user
     * Requires Login
     * </pre>
     */
    public void prepareAuth(com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getPrepareAuthMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * The first step in the SRP login proccess
     * </pre>
     */
    public void srpInit(com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSrpInitMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * The second and final step in the SRP login proccess
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public void srpProof(com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSrpProofMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Logout current user
     * Requires Login
     * </pre>
     */
    public void logout(com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Search within a list of user's accessible documents
     * The response will include a list document id and its score when matches are found
     * Requires Login
     * </pre>
     */
    public void search(com.strongsalt.strongdoc.sdk.proto.Search.SearchReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Add a sharable organization to the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void addSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddSharableOrgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Remove a sharable organization from the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void removeSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemoveSharableOrgMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Update the organization's multi-level sharing setting
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void setMultiLevelSharing(com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetMultiLevelSharingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Update the organization's account info
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public void setAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetAccountInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *List all items of the cost breakdown and also other details such as the billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getBillingDetails(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBillingDetailsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Obtain the list of billing frequencies (past, current and future)
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getBillingFrequencyList(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetBillingFrequencyListMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Change the next billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void setNextBillingFrequency(com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetNextBillingFrequencyMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Obtain the list of large traffic usages
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getLargeTraffic(com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetLargeTrafficMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *Obtain information about the account
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public void getAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetAccountInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Obtain information about logged in user
     * Requires Login
     * </pre>
     */
    public void getUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Change the password of a logged in user
     * Requires Login
     * </pre>
     */
    public void changeUserPassword(com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getChangeUserPasswordMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Set information about a logged in user
     * Requires Login
     * </pre>
     */
    public void setUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetUserInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Obtain a list of the org's credit cards
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void listCreditCards(com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListCreditCardsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Add a payment method to the customer and validate the payment method
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void addPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAddPaymentMethodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Set the default payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void setDefaultPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSetDefaultPaymentMethodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * Remove a payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void removePaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRemovePaymentMethodMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * List payments for the organization
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public void listPayments(com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq request,
        io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getListPaymentsMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class StrongDocServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<StrongDocServiceBlockingStub> {
    private StrongDocServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongDocServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongDocServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * Registers a new organization
     * The user who created the organization is automatically an administrator
     * Does not require Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp registerOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterOrganizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Reactivate an organization that was unsubscribed via aws
     * The user reactivating the organization becomes the administrator
     * Does not require login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp reactivateOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request) {
      return blockingUnaryCall(
          getChannel(), getReactivateOrganizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove an organization and its search indexes
     * Requires Administrator privilege. Only an administrator can remove the whole organization
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp removeOrganization(com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveOrganizationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * InviteUser
     * Requires administrator privilege
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp inviteUser(com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq request) {
      return blockingUnaryCall(
          getChannel(), getInviteUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp listInvitations(com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq request) {
      return blockingUnaryCall(
          getChannel(), getListInvitationsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp revokeInvitation(com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq request) {
      return blockingUnaryCall(
          getChannel(), getRevokeInvitationMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Register new user
     * Creates new user if it doesn't already exist. If the user already exist, and
     * error is thrown
     * Does not require Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp registerUser(com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq request) {
      return blockingUnaryCall(
          getChannel(), getRegisterUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Get user private keys
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp getUserPrivateKeys(com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq request) {
      return blockingUnaryCall(
          getChannel(), getGetUserPrivateKeysMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sets the user's kdf metadata for a new passwordKey
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp setUserKdfMetadata(com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq request) {
      return blockingUnaryCall(
          getChannel(), getSetUserKdfMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Sets the user's authentication metadata
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp setUserAuthMetadata(com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq request) {
      return blockingUnaryCall(
          getChannel(), getSetUserAuthMetadataMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List the users of the organization
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp listUsers(com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq request) {
      return blockingUnaryCall(
          getChannel(), getListUsersMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove user from organization
     * Removes the user from the organization. The users documents still exists,
     * but belong to the organization, only accessible by organization admin.
     * Requires administrator privilege.
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp removeUser(com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Prepare to promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp preparePromoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq request) {
      return blockingUnaryCall(
          getChannel(), getPreparePromoteUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp promoteUser(com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq request) {
      return blockingUnaryCall(
          getChannel(), getPromoteUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Demote administrator to regular user at the organization. Attempting to
     * demote the last administrator of an organization will fail
     * Requires administrator privilege.
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp demoteUser(com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq request) {
      return blockingUnaryCall(
          getChannel(), getDemoteUserMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List the documents the user can access
     * Administrators can see all documents belonging to the organization
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp listDocuments(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq request) {
      return blockingUnaryCall(
          getChannel(), getListDocumentsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove document the user can access
     * Admin user can remove document for the whole organization
     * Regular user only can remove document for him/herself
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp removeDocument(com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveDocumentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp e2EEPrepareDownloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq request) {
      return blockingUnaryCall(
          getChannel(), getE2EEPrepareDownloadDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * This is not available through gRPC REST gateway,
     * since REST api does not support streaming protocol
     * </pre>
     */
    public java.util.Iterator<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp> e2EEDownloadDocumentStream(
        com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq request) {
      return blockingServerStreamingCall(
          getChannel(), getE2EEDownloadDocumentStreamMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp uploadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq request) {
      return blockingUnaryCall(
          getChannel(), getUploadDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Download document stream
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public java.util.Iterator<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp> downloadDocumentStream(
        com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq request) {
      return blockingServerStreamingCall(
          getChannel(), getDownloadDocumentStreamMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Download document
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp downloadDocument(com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq request) {
      return blockingUnaryCall(
          getChannel(), getDownloadDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Encrypt document encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp encryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq request) {
      return blockingUnaryCall(
          getChannel(), getEncryptDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Decrypt document decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp decryptDocument(com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq request) {
      return blockingUnaryCall(
          getChannel(), getDecryptDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * PrepareShare a document to another user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp prepareShareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq request) {
      return blockingUnaryCall(
          getChannel(), getPrepareShareDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Share a document to another user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp shareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq request) {
      return blockingUnaryCall(
          getChannel(), getShareDocumentMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Unshare a document that had previously been shared to a user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp unshareDocument(com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq request) {
      return blockingUnaryCall(
          getChannel(), getUnshareDocumentMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp listDocActionHistory(com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq request) {
      return blockingUnaryCall(
          getChannel(), getListDocActionHistoryMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Obtain an authentication token to be used with other APIs
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.LoginResp login(com.strongsalt.strongdoc.sdk.proto.Account.LoginReq request) {
      return blockingUnaryCall(
          getChannel(), getLoginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to login with a specified user
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp prepareLogin(com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq request) {
      return blockingUnaryCall(
          getChannel(), getPrepareLoginMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to authenticate with a specified user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp prepareAuth(com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq request) {
      return blockingUnaryCall(
          getChannel(), getPrepareAuthMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * The first step in the SRP login proccess
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp srpInit(com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq request) {
      return blockingUnaryCall(
          getChannel(), getSrpInitMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * The second and final step in the SRP login proccess
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp srpProof(com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq request) {
      return blockingUnaryCall(
          getChannel(), getSrpProofMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Logout current user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp logout(com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq request) {
      return blockingUnaryCall(
          getChannel(), getLogoutMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Search within a list of user's accessible documents
     * The response will include a list document id and its score when matches are found
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Search.SearchResp search(com.strongsalt.strongdoc.sdk.proto.Search.SearchReq request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Add a sharable organization to the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp addSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq request) {
      return blockingUnaryCall(
          getChannel(), getAddSharableOrgMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Remove a sharable organization from the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp removeSharableOrg(com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq request) {
      return blockingUnaryCall(
          getChannel(), getRemoveSharableOrgMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Update the organization's multi-level sharing setting
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp setMultiLevelSharing(com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq request) {
      return blockingUnaryCall(
          getChannel(), getSetMultiLevelSharingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Update the organization's account info
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp setAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq request) {
      return blockingUnaryCall(
          getChannel(), getSetAccountInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *List all items of the cost breakdown and also other details such as the billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp getBillingDetails(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq request) {
      return blockingUnaryCall(
          getChannel(), getGetBillingDetailsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Obtain the list of billing frequencies (past, current and future)
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp getBillingFrequencyList(com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq request) {
      return blockingUnaryCall(
          getChannel(), getGetBillingFrequencyListMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Change the next billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp setNextBillingFrequency(com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq request) {
      return blockingUnaryCall(
          getChannel(), getSetNextBillingFrequencyMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Obtain the list of large traffic usages
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp getLargeTraffic(com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq request) {
      return blockingUnaryCall(
          getChannel(), getGetLargeTrafficMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *Obtain information about the account
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp getAccountInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq request) {
      return blockingUnaryCall(
          getChannel(), getGetAccountInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Obtain information about logged in user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp getUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq request) {
      return blockingUnaryCall(
          getChannel(), getGetUserInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Change the password of a logged in user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp changeUserPassword(com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq request) {
      return blockingUnaryCall(
          getChannel(), getChangeUserPasswordMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Set information about a logged in user
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp setUserInfo(com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq request) {
      return blockingUnaryCall(
          getChannel(), getSetUserInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Obtain a list of the org's credit cards
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp listCreditCards(com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq request) {
      return blockingUnaryCall(
          getChannel(), getListCreditCardsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Add a payment method to the customer and validate the payment method
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp addPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq request) {
      return blockingUnaryCall(
          getChannel(), getAddPaymentMethodMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Set the default payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp setDefaultPaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq request) {
      return blockingUnaryCall(
          getChannel(), getSetDefaultPaymentMethodMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * Remove a payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp removePaymentMethod(com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq request) {
      return blockingUnaryCall(
          getChannel(), getRemovePaymentMethodMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * List payments for the organization
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp listPayments(com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq request) {
      return blockingUnaryCall(
          getChannel(), getListPaymentsMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class StrongDocServiceFutureStub extends io.grpc.stub.AbstractFutureStub<StrongDocServiceFutureStub> {
    private StrongDocServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected StrongDocServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new StrongDocServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * Registers a new organization
     * The user who created the organization is automatically an administrator
     * Does not require Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> registerOrganization(
        com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterOrganizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Reactivate an organization that was unsubscribed via aws
     * The user reactivating the organization becomes the administrator
     * Does not require login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp> reactivateOrganization(
        com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq request) {
      return futureUnaryCall(
          getChannel().newCall(getReactivateOrganizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove an organization and its search indexes
     * Requires Administrator privilege. Only an administrator can remove the whole organization
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp> removeOrganization(
        com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveOrganizationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * InviteUser
     * Requires administrator privilege
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp> inviteUser(
        com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getInviteUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp> listInvitations(
        com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListInvitationsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * ListInvitations
     * Requires administrator privilege
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp> revokeInvitation(
        com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRevokeInvitationMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Register new user
     * Creates new user if it doesn't already exist. If the user already exist, and
     * error is thrown
     * Does not require Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp> registerUser(
        com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRegisterUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Get user private keys
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp> getUserPrivateKeys(
        com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserPrivateKeysMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sets the user's kdf metadata for a new passwordKey
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp> setUserKdfMetadata(
        com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetUserKdfMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Sets the user's authentication metadata
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp> setUserAuthMetadata(
        com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetUserAuthMetadataMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * List the users of the organization
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp> listUsers(
        com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListUsersMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove user from organization
     * Removes the user from the organization. The users documents still exists,
     * but belong to the organization, only accessible by organization admin.
     * Requires administrator privilege.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp> removeUser(
        com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Prepare to promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp> preparePromoteUser(
        com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPreparePromoteUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Promote a regular user to administrator at the organization
     * Requires administrator privilege.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp> promoteUser(
        com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPromoteUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Demote administrator to regular user at the organization. Attempting to
     * demote the last administrator of an organization will fail
     * Requires administrator privilege.
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp> demoteUser(
        com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDemoteUserMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * List the documents the user can access
     * Administrators can see all documents belonging to the organization
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp> listDocuments(
        com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListDocumentsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove document the user can access
     * Admin user can remove document for the whole organization
     * Regular user only can remove document for him/herself
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp> removeDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveDocumentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp> e2EEPrepareDownloadDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq request) {
      return futureUnaryCall(
          getChannel().newCall(getE2EEPrepareDownloadDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Upload document
     * User can upload document to the organization for storage
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp> uploadDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUploadDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Download document
     * User can download the documents
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp> downloadDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDownloadDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Encrypt document encrypts the document and returns the ciphertext
     * back to the user without storing it.
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp> encryptDocument(
        com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq request) {
      return futureUnaryCall(
          getChannel().newCall(getEncryptDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Decrypt document decrypts the ciphertext passed in and returns
     * decrypted plain text back to the user without storing it
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp> decryptDocument(
        com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq request) {
      return futureUnaryCall(
          getChannel().newCall(getDecryptDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * PrepareShare a document to another user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp> prepareShareDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPrepareShareDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Share a document to another user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp> shareDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq request) {
      return futureUnaryCall(
          getChannel().newCall(getShareDocumentMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Unshare a document that had previously been shared to a user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp> unshareDocument(
        com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq request) {
      return futureUnaryCall(
          getChannel().newCall(getUnshareDocumentMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp> listDocActionHistory(
        com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListDocActionHistoryMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Obtain an authentication token to be used with other APIs
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.LoginResp> login(
        com.strongsalt.strongdoc.sdk.proto.Account.LoginReq request) {
      return futureUnaryCall(
          getChannel().newCall(getLoginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to login with a specified user
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp> prepareLogin(
        com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPrepareLoginMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Obtain the metadata needed to attempt to authenticate with a specified user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp> prepareAuth(
        com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq request) {
      return futureUnaryCall(
          getChannel().newCall(getPrepareAuthMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * The first step in the SRP login proccess
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp> srpInit(
        com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSrpInitMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * The second and final step in the SRP login proccess
     * An authentication token will be returned after user has been validated
     * The returned token will be used as a Bearer Token and need to be set in
     * the request header
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp> srpProof(
        com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSrpProofMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Logout current user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp> logout(
        com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq request) {
      return futureUnaryCall(
          getChannel().newCall(getLogoutMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Search within a list of user's accessible documents
     * The response will include a list document id and its score when matches are found
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Search.SearchResp> search(
        com.strongsalt.strongdoc.sdk.proto.Search.SearchReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Add a sharable organization to the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp> addSharableOrg(
        com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq request) {
      return futureUnaryCall(
          getChannel().newCall(getAddSharableOrgMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Remove a sharable organization from the user's organization.
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp> removeSharableOrg(
        com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemoveSharableOrgMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Update the organization's multi-level sharing setting
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp> setMultiLevelSharing(
        com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetMultiLevelSharingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Update the organization's account info
     *Requires Administrator privilege.
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp> setAccountInfo(
        com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetAccountInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *List all items of the cost breakdown and also other details such as the billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp> getBillingDetails(
        com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBillingDetailsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Obtain the list of billing frequencies (past, current and future)
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp> getBillingFrequencyList(
        com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetBillingFrequencyListMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Change the next billing frequency
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp> setNextBillingFrequency(
        com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetNextBillingFrequencyMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Obtain the list of large traffic usages
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp> getLargeTraffic(
        com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetLargeTrafficMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *Obtain information about the account
     *Requires Administrator privilege
     *Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp> getAccountInfo(
        com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetAccountInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Obtain information about logged in user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp> getUserInfo(
        com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Change the password of a logged in user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp> changeUserPassword(
        com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq request) {
      return futureUnaryCall(
          getChannel().newCall(getChangeUserPasswordMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Set information about a logged in user
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp> setUserInfo(
        com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetUserInfoMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Obtain a list of the org's credit cards
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp> listCreditCards(
        com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListCreditCardsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Add a payment method to the customer and validate the payment method
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp> addPaymentMethod(
        com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq request) {
      return futureUnaryCall(
          getChannel().newCall(getAddPaymentMethodMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Set the default payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp> setDefaultPaymentMethod(
        com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq request) {
      return futureUnaryCall(
          getChannel().newCall(getSetDefaultPaymentMethodMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * Remove a payment method for a stripe customer
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp> removePaymentMethod(
        com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq request) {
      return futureUnaryCall(
          getChannel().newCall(getRemovePaymentMethodMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     * List payments for the organization
     * Requires Administrator privilege
     * Requires Login
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp> listPayments(
        com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq request) {
      return futureUnaryCall(
          getChannel().newCall(getListPaymentsMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REGISTER_ORGANIZATION = 0;
  private static final int METHODID_REACTIVATE_ORGANIZATION = 1;
  private static final int METHODID_REMOVE_ORGANIZATION = 2;
  private static final int METHODID_INVITE_USER = 3;
  private static final int METHODID_LIST_INVITATIONS = 4;
  private static final int METHODID_REVOKE_INVITATION = 5;
  private static final int METHODID_REGISTER_USER = 6;
  private static final int METHODID_GET_USER_PRIVATE_KEYS = 7;
  private static final int METHODID_SET_USER_KDF_METADATA = 8;
  private static final int METHODID_SET_USER_AUTH_METADATA = 9;
  private static final int METHODID_LIST_USERS = 10;
  private static final int METHODID_REMOVE_USER = 11;
  private static final int METHODID_PREPARE_PROMOTE_USER = 12;
  private static final int METHODID_PROMOTE_USER = 13;
  private static final int METHODID_DEMOTE_USER = 14;
  private static final int METHODID_LIST_DOCUMENTS = 15;
  private static final int METHODID_REMOVE_DOCUMENT = 16;
  private static final int METHODID_E2EEPREPARE_DOWNLOAD_DOCUMENT = 17;
  private static final int METHODID_E2EEDOWNLOAD_DOCUMENT_STREAM = 18;
  private static final int METHODID_UPLOAD_DOCUMENT = 19;
  private static final int METHODID_DOWNLOAD_DOCUMENT_STREAM = 20;
  private static final int METHODID_DOWNLOAD_DOCUMENT = 21;
  private static final int METHODID_ENCRYPT_DOCUMENT = 22;
  private static final int METHODID_DECRYPT_DOCUMENT = 23;
  private static final int METHODID_PREPARE_SHARE_DOCUMENT = 24;
  private static final int METHODID_SHARE_DOCUMENT = 25;
  private static final int METHODID_UNSHARE_DOCUMENT = 26;
  private static final int METHODID_LIST_DOC_ACTION_HISTORY = 27;
  private static final int METHODID_LOGIN = 28;
  private static final int METHODID_PREPARE_LOGIN = 29;
  private static final int METHODID_PREPARE_AUTH = 30;
  private static final int METHODID_SRP_INIT = 31;
  private static final int METHODID_SRP_PROOF = 32;
  private static final int METHODID_LOGOUT = 33;
  private static final int METHODID_SEARCH = 34;
  private static final int METHODID_ADD_SHARABLE_ORG = 35;
  private static final int METHODID_REMOVE_SHARABLE_ORG = 36;
  private static final int METHODID_SET_MULTI_LEVEL_SHARING = 37;
  private static final int METHODID_SET_ACCOUNT_INFO = 38;
  private static final int METHODID_GET_BILLING_DETAILS = 39;
  private static final int METHODID_GET_BILLING_FREQUENCY_LIST = 40;
  private static final int METHODID_SET_NEXT_BILLING_FREQUENCY = 41;
  private static final int METHODID_GET_LARGE_TRAFFIC = 42;
  private static final int METHODID_GET_ACCOUNT_INFO = 43;
  private static final int METHODID_GET_USER_INFO = 44;
  private static final int METHODID_CHANGE_USER_PASSWORD = 45;
  private static final int METHODID_SET_USER_INFO = 46;
  private static final int METHODID_LIST_CREDIT_CARDS = 47;
  private static final int METHODID_ADD_PAYMENT_METHOD = 48;
  private static final int METHODID_SET_DEFAULT_PAYMENT_METHOD = 49;
  private static final int METHODID_REMOVE_PAYMENT_METHOD = 50;
  private static final int METHODID_LIST_PAYMENTS = 51;
  private static final int METHODID_UPLOAD_DOCUMENT_STREAM = 52;
  private static final int METHODID_E2EEUPLOAD_DOCUMENT_STREAM = 53;
  private static final int METHODID_ENCRYPT_DOCUMENT_STREAM = 54;
  private static final int METHODID_DECRYPT_DOCUMENT_STREAM = 55;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final StrongDocServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(StrongDocServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REGISTER_ORGANIZATION:
          serviceImpl.registerOrganization((com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>) responseObserver);
          break;
        case METHODID_REACTIVATE_ORGANIZATION:
          serviceImpl.reactivateOrganization((com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterOrganizationResp>) responseObserver);
          break;
        case METHODID_REMOVE_ORGANIZATION:
          serviceImpl.removeOrganization((com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveOrganizationResp>) responseObserver);
          break;
        case METHODID_INVITE_USER:
          serviceImpl.inviteUser((com.strongsalt.strongdoc.sdk.proto.Account.InviteUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.InviteUserResp>) responseObserver);
          break;
        case METHODID_LIST_INVITATIONS:
          serviceImpl.listInvitations((com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListInvitationsResp>) responseObserver);
          break;
        case METHODID_REVOKE_INVITATION:
          serviceImpl.revokeInvitation((com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RevokeInvitationResp>) responseObserver);
          break;
        case METHODID_REGISTER_USER:
          serviceImpl.registerUser((com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RegisterUserResp>) responseObserver);
          break;
        case METHODID_GET_USER_PRIVATE_KEYS:
          serviceImpl.getUserPrivateKeys((com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.GetUserPrivateKeysResp>) responseObserver);
          break;
        case METHODID_SET_USER_KDF_METADATA:
          serviceImpl.setUserKdfMetadata((com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Encryption.SetUserKdfMetadataResp>) responseObserver);
          break;
        case METHODID_SET_USER_AUTH_METADATA:
          serviceImpl.setUserAuthMetadata((com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserAuthMetadataResp>) responseObserver);
          break;
        case METHODID_LIST_USERS:
          serviceImpl.listUsers((com.strongsalt.strongdoc.sdk.proto.Account.ListUsersReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ListUsersResp>) responseObserver);
          break;
        case METHODID_REMOVE_USER:
          serviceImpl.removeUser((com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveUserResp>) responseObserver);
          break;
        case METHODID_PREPARE_PROMOTE_USER:
          serviceImpl.preparePromoteUser((com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PreparePromoteUserResp>) responseObserver);
          break;
        case METHODID_PROMOTE_USER:
          serviceImpl.promoteUser((com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PromoteUserResp>) responseObserver);
          break;
        case METHODID_DEMOTE_USER:
          serviceImpl.demoteUser((com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.DemoteUserResp>) responseObserver);
          break;
        case METHODID_LIST_DOCUMENTS:
          serviceImpl.listDocuments((com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocumentsResp>) responseObserver);
          break;
        case METHODID_REMOVE_DOCUMENT:
          serviceImpl.removeDocument((com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.RemoveDocumentResp>) responseObserver);
          break;
        case METHODID_E2EEPREPARE_DOWNLOAD_DOCUMENT:
          serviceImpl.e2EEPrepareDownloadDocument((com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEPrepareDownloadDocResp>) responseObserver);
          break;
        case METHODID_E2EEDOWNLOAD_DOCUMENT_STREAM:
          serviceImpl.e2EEDownloadDocumentStream((com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEDownloadDocStreamResp>) responseObserver);
          break;
        case METHODID_UPLOAD_DOCUMENT:
          serviceImpl.uploadDocument((com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD_DOCUMENT_STREAM:
          serviceImpl.downloadDocumentStream((com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocStreamResp>) responseObserver);
          break;
        case METHODID_DOWNLOAD_DOCUMENT:
          serviceImpl.downloadDocument((com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.DownloadDocResp>) responseObserver);
          break;
        case METHODID_ENCRYPT_DOCUMENT:
          serviceImpl.encryptDocument((com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocResp>) responseObserver);
          break;
        case METHODID_DECRYPT_DOCUMENT:
          serviceImpl.decryptDocument((com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocResp>) responseObserver);
          break;
        case METHODID_PREPARE_SHARE_DOCUMENT:
          serviceImpl.prepareShareDocument((com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.PrepareShareDocumentResp>) responseObserver);
          break;
        case METHODID_SHARE_DOCUMENT:
          serviceImpl.shareDocument((com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ShareDocumentResp>) responseObserver);
          break;
        case METHODID_UNSHARE_DOCUMENT:
          serviceImpl.unshareDocument((com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UnshareDocumentResp>) responseObserver);
          break;
        case METHODID_LIST_DOC_ACTION_HISTORY:
          serviceImpl.listDocActionHistory((com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.ListDocActionHistoryResp>) responseObserver);
          break;
        case METHODID_LOGIN:
          serviceImpl.login((com.strongsalt.strongdoc.sdk.proto.Account.LoginReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LoginResp>) responseObserver);
          break;
        case METHODID_PREPARE_LOGIN:
          serviceImpl.prepareLogin((com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareLoginResp>) responseObserver);
          break;
        case METHODID_PREPARE_AUTH:
          serviceImpl.prepareAuth((com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.PrepareAuthResp>) responseObserver);
          break;
        case METHODID_SRP_INIT:
          serviceImpl.srpInit((com.strongsalt.strongdoc.sdk.proto.Account.SrpInitReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpInitResp>) responseObserver);
          break;
        case METHODID_SRP_PROOF:
          serviceImpl.srpProof((com.strongsalt.strongdoc.sdk.proto.Account.SrpProofReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SrpProofResp>) responseObserver);
          break;
        case METHODID_LOGOUT:
          serviceImpl.logout((com.strongsalt.strongdoc.sdk.proto.Account.LogoutReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.LogoutResp>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((com.strongsalt.strongdoc.sdk.proto.Search.SearchReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Search.SearchResp>) responseObserver);
          break;
        case METHODID_ADD_SHARABLE_ORG:
          serviceImpl.addSharableOrg((com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.AddSharableOrgResp>) responseObserver);
          break;
        case METHODID_REMOVE_SHARABLE_ORG:
          serviceImpl.removeSharableOrg((com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.RemoveSharableOrgResp>) responseObserver);
          break;
        case METHODID_SET_MULTI_LEVEL_SHARING:
          serviceImpl.setMultiLevelSharing((com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetMultiLevelSharingResp>) responseObserver);
          break;
        case METHODID_SET_ACCOUNT_INFO:
          serviceImpl.setAccountInfo((com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetAccountInfoResp>) responseObserver);
          break;
        case METHODID_GET_BILLING_DETAILS:
          serviceImpl.getBillingDetails((com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingDetailsResp>) responseObserver);
          break;
        case METHODID_GET_BILLING_FREQUENCY_LIST:
          serviceImpl.getBillingFrequencyList((com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetBillingFrequencyListResp>) responseObserver);
          break;
        case METHODID_SET_NEXT_BILLING_FREQUENCY:
          serviceImpl.setNextBillingFrequency((com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetNextBillingFrequencyResp>) responseObserver);
          break;
        case METHODID_GET_LARGE_TRAFFIC:
          serviceImpl.getLargeTraffic((com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.GetLargeTrafficResp>) responseObserver);
          break;
        case METHODID_GET_ACCOUNT_INFO:
          serviceImpl.getAccountInfo((com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetAccountInfoResp>) responseObserver);
          break;
        case METHODID_GET_USER_INFO:
          serviceImpl.getUserInfo((com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.GetUserInfoResp>) responseObserver);
          break;
        case METHODID_CHANGE_USER_PASSWORD:
          serviceImpl.changeUserPassword((com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.ChangeUserPasswordResp>) responseObserver);
          break;
        case METHODID_SET_USER_INFO:
          serviceImpl.setUserInfo((com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Account.SetUserInfoResp>) responseObserver);
          break;
        case METHODID_LIST_CREDIT_CARDS:
          serviceImpl.listCreditCards((com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListCreditCardsResp>) responseObserver);
          break;
        case METHODID_ADD_PAYMENT_METHOD:
          serviceImpl.addPaymentMethod((com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.AddPaymentMethodResp>) responseObserver);
          break;
        case METHODID_SET_DEFAULT_PAYMENT_METHOD:
          serviceImpl.setDefaultPaymentMethod((com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.SetDefaultPaymentMethodResp>) responseObserver);
          break;
        case METHODID_REMOVE_PAYMENT_METHOD:
          serviceImpl.removePaymentMethod((com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.RemovePaymentMethodResp>) responseObserver);
          break;
        case METHODID_LIST_PAYMENTS:
          serviceImpl.listPayments((com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsReq) request,
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Billing.ListPaymentsResp>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_DOCUMENT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.uploadDocumentStream(
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.UploadDocStreamResp>) responseObserver);
        case METHODID_E2EEUPLOAD_DOCUMENT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.e2EEUploadDocumentStream(
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.Documents.E2EEUploadDocStreamResp>) responseObserver);
        case METHODID_ENCRYPT_DOCUMENT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.encryptDocumentStream(
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.EncryptDocStreamResp>) responseObserver);
        case METHODID_DECRYPT_DOCUMENT_STREAM:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.decryptDocumentStream(
              (io.grpc.stub.StreamObserver<com.strongsalt.strongdoc.sdk.proto.DocumentsNoStore.DecryptDocStreamResp>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class StrongDocServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    StrongDocServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.strongsalt.strongdoc.sdk.proto.StrongDocProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("StrongDocService");
    }
  }

  private static final class StrongDocServiceFileDescriptorSupplier
      extends StrongDocServiceBaseDescriptorSupplier {
    StrongDocServiceFileDescriptorSupplier() {}
  }

  private static final class StrongDocServiceMethodDescriptorSupplier
      extends StrongDocServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    StrongDocServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (StrongDocServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new StrongDocServiceFileDescriptorSupplier())
              .addMethod(getRegisterOrganizationMethod())
              .addMethod(getReactivateOrganizationMethod())
              .addMethod(getRemoveOrganizationMethod())
              .addMethod(getInviteUserMethod())
              .addMethod(getListInvitationsMethod())
              .addMethod(getRevokeInvitationMethod())
              .addMethod(getRegisterUserMethod())
              .addMethod(getGetUserPrivateKeysMethod())
              .addMethod(getSetUserKdfMetadataMethod())
              .addMethod(getSetUserAuthMetadataMethod())
              .addMethod(getListUsersMethod())
              .addMethod(getRemoveUserMethod())
              .addMethod(getPreparePromoteUserMethod())
              .addMethod(getPromoteUserMethod())
              .addMethod(getDemoteUserMethod())
              .addMethod(getListDocumentsMethod())
              .addMethod(getRemoveDocumentMethod())
              .addMethod(getUploadDocumentStreamMethod())
              .addMethod(getE2EEUploadDocumentStreamMethod())
              .addMethod(getE2EEPrepareDownloadDocumentMethod())
              .addMethod(getE2EEDownloadDocumentStreamMethod())
              .addMethod(getUploadDocumentMethod())
              .addMethod(getDownloadDocumentStreamMethod())
              .addMethod(getDownloadDocumentMethod())
              .addMethod(getEncryptDocumentStreamMethod())
              .addMethod(getEncryptDocumentMethod())
              .addMethod(getDecryptDocumentStreamMethod())
              .addMethod(getDecryptDocumentMethod())
              .addMethod(getPrepareShareDocumentMethod())
              .addMethod(getShareDocumentMethod())
              .addMethod(getUnshareDocumentMethod())
              .addMethod(getListDocActionHistoryMethod())
              .addMethod(getLoginMethod())
              .addMethod(getPrepareLoginMethod())
              .addMethod(getPrepareAuthMethod())
              .addMethod(getSrpInitMethod())
              .addMethod(getSrpProofMethod())
              .addMethod(getLogoutMethod())
              .addMethod(getSearchMethod())
              .addMethod(getAddSharableOrgMethod())
              .addMethod(getRemoveSharableOrgMethod())
              .addMethod(getSetMultiLevelSharingMethod())
              .addMethod(getSetAccountInfoMethod())
              .addMethod(getGetBillingDetailsMethod())
              .addMethod(getGetBillingFrequencyListMethod())
              .addMethod(getSetNextBillingFrequencyMethod())
              .addMethod(getGetLargeTrafficMethod())
              .addMethod(getGetAccountInfoMethod())
              .addMethod(getGetUserInfoMethod())
              .addMethod(getChangeUserPasswordMethod())
              .addMethod(getSetUserInfoMethod())
              .addMethod(getListCreditCardsMethod())
              .addMethod(getAddPaymentMethodMethod())
              .addMethod(getSetDefaultPaymentMethodMethod())
              .addMethod(getRemovePaymentMethodMethod())
              .addMethod(getListPaymentsMethod())
              .build();
        }
      }
    }
    return result;
  }
}
