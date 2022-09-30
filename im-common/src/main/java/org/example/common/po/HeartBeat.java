// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: HeartbeatMessage.proto

package org.example.common.po;

public final class HeartBeat {
  private HeartBeat() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface HeartbeatMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:org.example.common.po.HeartbeatMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    String getId();
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    com.google.protobuf.ByteString
        getIdBytes();

    /**
     * <code>int64 userId = 2;</code>
     * @return The userId.
     */
    long getUserId();

    /**
     * <code>int32 userPlatformId = 3;</code>
     * @return The userPlatformId.
     */
    int getUserPlatformId();

    /**
     * <code>string userMacId = 4;</code>
     * @return The userMacId.
     */
    String getUserMacId();
    /**
     * <code>string userMacId = 4;</code>
     * @return The bytes for userMacId.
     */
    com.google.protobuf.ByteString
        getUserMacIdBytes();

    /**
     * <code>int64 receiveId = 5;</code>
     * @return The receiveId.
     */
    long getReceiveId();

    /**
     * <code>int64 timeStamp = 6;</code>
     * @return The timeStamp.
     */
    long getTimeStamp();
  }
  /**
   * Protobuf type {@code org.example.common.po.HeartbeatMessage}
   */
  public static final class HeartbeatMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:org.example.common.po.HeartbeatMessage)
      HeartbeatMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use HeartbeatMessage.newBuilder() to construct.
    private HeartbeatMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private HeartbeatMessage() {
      id_ = "";
      userMacId_ = "";
    }

    @Override
    @SuppressWarnings({"unused"})
    protected Object newInstance(
        UnusedPrivateParameter unused) {
      return new HeartbeatMessage();
    }

    @Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private HeartbeatMessage(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new NullPointerException();
      }
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10: {
              String s = input.readStringRequireUtf8();

              id_ = s;
              break;
            }
            case 16: {

              userId_ = input.readInt64();
              break;
            }
            case 24: {

              userPlatformId_ = input.readInt32();
              break;
            }
            case 34: {
              String s = input.readStringRequireUtf8();

              userMacId_ = s;
              break;
            }
            case 40: {

              receiveId_ = input.readInt64();
              break;
            }
            case 48: {

              timeStamp_ = input.readInt64();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (com.google.protobuf.UninitializedMessageException e) {
        throw e.asInvalidProtocolBufferException().setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return HeartBeat.internal_static_org_example_common_po_HeartbeatMessage_descriptor;
    }

    @Override
    protected FieldAccessorTable
        internalGetFieldAccessorTable() {
      return HeartBeat.internal_static_org_example_common_po_HeartbeatMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              HeartbeatMessage.class, Builder.class);
    }

    public static final int ID_FIELD_NUMBER = 1;
    private volatile Object id_;
    /**
     * <code>string id = 1;</code>
     * @return The id.
     */
    @Override
    public String getId() {
      Object ref = id_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        id_ = s;
        return s;
      }
    }
    /**
     * <code>string id = 1;</code>
     * @return The bytes for id.
     */
    @Override
    public com.google.protobuf.ByteString
        getIdBytes() {
      Object ref = id_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        id_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int USERID_FIELD_NUMBER = 2;
    private long userId_;
    /**
     * <code>int64 userId = 2;</code>
     * @return The userId.
     */
    @Override
    public long getUserId() {
      return userId_;
    }

    public static final int USERPLATFORMID_FIELD_NUMBER = 3;
    private int userPlatformId_;
    /**
     * <code>int32 userPlatformId = 3;</code>
     * @return The userPlatformId.
     */
    @Override
    public int getUserPlatformId() {
      return userPlatformId_;
    }

    public static final int USERMACID_FIELD_NUMBER = 4;
    private volatile Object userMacId_;
    /**
     * <code>string userMacId = 4;</code>
     * @return The userMacId.
     */
    @Override
    public String getUserMacId() {
      Object ref = userMacId_;
      if (ref instanceof String) {
        return (String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        String s = bs.toStringUtf8();
        userMacId_ = s;
        return s;
      }
    }
    /**
     * <code>string userMacId = 4;</code>
     * @return The bytes for userMacId.
     */
    @Override
    public com.google.protobuf.ByteString
        getUserMacIdBytes() {
      Object ref = userMacId_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (String) ref);
        userMacId_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int RECEIVEID_FIELD_NUMBER = 5;
    private long receiveId_;
    /**
     * <code>int64 receiveId = 5;</code>
     * @return The receiveId.
     */
    @Override
    public long getReceiveId() {
      return receiveId_;
    }

    public static final int TIMESTAMP_FIELD_NUMBER = 6;
    private long timeStamp_;
    /**
     * <code>int64 timeStamp = 6;</code>
     * @return The timeStamp.
     */
    @Override
    public long getTimeStamp() {
      return timeStamp_;
    }

    private byte memoizedIsInitialized = -1;
    @Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, id_);
      }
      if (userId_ != 0L) {
        output.writeInt64(2, userId_);
      }
      if (userPlatformId_ != 0) {
        output.writeInt32(3, userPlatformId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userMacId_)) {
        com.google.protobuf.GeneratedMessageV3.writeString(output, 4, userMacId_);
      }
      if (receiveId_ != 0L) {
        output.writeInt64(5, receiveId_);
      }
      if (timeStamp_ != 0L) {
        output.writeInt64(6, timeStamp_);
      }
      unknownFields.writeTo(output);
    }

    @Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(id_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, id_);
      }
      if (userId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, userId_);
      }
      if (userPlatformId_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(3, userPlatformId_);
      }
      if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(userMacId_)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(4, userMacId_);
      }
      if (receiveId_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(5, receiveId_);
      }
      if (timeStamp_ != 0L) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(6, timeStamp_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @Override
    public boolean equals(final Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof HeartbeatMessage)) {
        return super.equals(obj);
      }
      HeartbeatMessage other = (HeartbeatMessage) obj;

      if (!getId()
          .equals(other.getId())) return false;
      if (getUserId()
          != other.getUserId()) return false;
      if (getUserPlatformId()
          != other.getUserPlatformId()) return false;
      if (!getUserMacId()
          .equals(other.getUserMacId())) return false;
      if (getReceiveId()
          != other.getReceiveId()) return false;
      if (getTimeStamp()
          != other.getTimeStamp()) return false;
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + ID_FIELD_NUMBER;
      hash = (53 * hash) + getId().hashCode();
      hash = (37 * hash) + USERID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getUserId());
      hash = (37 * hash) + USERPLATFORMID_FIELD_NUMBER;
      hash = (53 * hash) + getUserPlatformId();
      hash = (37 * hash) + USERMACID_FIELD_NUMBER;
      hash = (53 * hash) + getUserMacId().hashCode();
      hash = (37 * hash) + RECEIVEID_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getReceiveId());
      hash = (37 * hash) + TIMESTAMP_FIELD_NUMBER;
      hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
          getTimeStamp());
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static HeartbeatMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static HeartbeatMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static HeartbeatMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static HeartbeatMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static HeartbeatMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static HeartbeatMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static HeartbeatMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static HeartbeatMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static HeartbeatMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static HeartbeatMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static HeartbeatMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static HeartbeatMessage parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(HeartbeatMessage prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @Override
    protected Builder newBuilderForType(
        BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code org.example.common.po.HeartbeatMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:org.example.common.po.HeartbeatMessage)
        HeartbeatMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return HeartBeat.internal_static_org_example_common_po_HeartbeatMessage_descriptor;
      }

      @Override
      protected FieldAccessorTable
          internalGetFieldAccessorTable() {
        return HeartBeat.internal_static_org_example_common_po_HeartbeatMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                HeartbeatMessage.class, Builder.class);
      }

      // Construct using org.example.common.po.HeartBeat.HeartbeatMessage.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @Override
      public Builder clear() {
        super.clear();
        id_ = "";

        userId_ = 0L;

        userPlatformId_ = 0;

        userMacId_ = "";

        receiveId_ = 0L;

        timeStamp_ = 0L;

        return this;
      }

      @Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return HeartBeat.internal_static_org_example_common_po_HeartbeatMessage_descriptor;
      }

      @Override
      public HeartbeatMessage getDefaultInstanceForType() {
        return HeartbeatMessage.getDefaultInstance();
      }

      @Override
      public HeartbeatMessage build() {
        HeartbeatMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @Override
      public HeartbeatMessage buildPartial() {
        HeartbeatMessage result = new HeartbeatMessage(this);
        result.id_ = id_;
        result.userId_ = userId_;
        result.userPlatformId_ = userPlatformId_;
        result.userMacId_ = userMacId_;
        result.receiveId_ = receiveId_;
        result.timeStamp_ = timeStamp_;
        onBuilt();
        return result;
      }

      @Override
      public Builder clone() {
        return super.clone();
      }
      @Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.setField(field, value);
      }
      @Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          Object value) {
        return super.addRepeatedField(field, value);
      }
      @Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof HeartbeatMessage) {
          return mergeFrom((HeartbeatMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(HeartbeatMessage other) {
        if (other == HeartbeatMessage.getDefaultInstance()) return this;
        if (!other.getId().isEmpty()) {
          id_ = other.id_;
          onChanged();
        }
        if (other.getUserId() != 0L) {
          setUserId(other.getUserId());
        }
        if (other.getUserPlatformId() != 0) {
          setUserPlatformId(other.getUserPlatformId());
        }
        if (!other.getUserMacId().isEmpty()) {
          userMacId_ = other.userMacId_;
          onChanged();
        }
        if (other.getReceiveId() != 0L) {
          setReceiveId(other.getReceiveId());
        }
        if (other.getTimeStamp() != 0L) {
          setTimeStamp(other.getTimeStamp());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @Override
      public final boolean isInitialized() {
        return true;
      }

      @Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        HeartbeatMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (HeartbeatMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }

      private Object id_ = "";
      /**
       * <code>string id = 1;</code>
       * @return The id.
       */
      public String getId() {
        Object ref = id_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          id_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string id = 1;</code>
       * @return The bytes for id.
       */
      public com.google.protobuf.ByteString
          getIdBytes() {
        Object ref = id_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          id_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string id = 1;</code>
       * @param value The id to set.
       * @return This builder for chaining.
       */
      public Builder setId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        id_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string id = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearId() {
        
        id_ = getDefaultInstance().getId();
        onChanged();
        return this;
      }
      /**
       * <code>string id = 1;</code>
       * @param value The bytes for id to set.
       * @return This builder for chaining.
       */
      public Builder setIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        id_ = value;
        onChanged();
        return this;
      }

      private long userId_ ;
      /**
       * <code>int64 userId = 2;</code>
       * @return The userId.
       */
      @Override
      public long getUserId() {
        return userId_;
      }
      /**
       * <code>int64 userId = 2;</code>
       * @param value The userId to set.
       * @return This builder for chaining.
       */
      public Builder setUserId(long value) {
        
        userId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 userId = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearUserId() {
        
        userId_ = 0L;
        onChanged();
        return this;
      }

      private int userPlatformId_ ;
      /**
       * <code>int32 userPlatformId = 3;</code>
       * @return The userPlatformId.
       */
      @Override
      public int getUserPlatformId() {
        return userPlatformId_;
      }
      /**
       * <code>int32 userPlatformId = 3;</code>
       * @param value The userPlatformId to set.
       * @return This builder for chaining.
       */
      public Builder setUserPlatformId(int value) {
        
        userPlatformId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int32 userPlatformId = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearUserPlatformId() {
        
        userPlatformId_ = 0;
        onChanged();
        return this;
      }

      private Object userMacId_ = "";
      /**
       * <code>string userMacId = 4;</code>
       * @return The userMacId.
       */
      public String getUserMacId() {
        Object ref = userMacId_;
        if (!(ref instanceof String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          String s = bs.toStringUtf8();
          userMacId_ = s;
          return s;
        } else {
          return (String) ref;
        }
      }
      /**
       * <code>string userMacId = 4;</code>
       * @return The bytes for userMacId.
       */
      public com.google.protobuf.ByteString
          getUserMacIdBytes() {
        Object ref = userMacId_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (String) ref);
          userMacId_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <code>string userMacId = 4;</code>
       * @param value The userMacId to set.
       * @return This builder for chaining.
       */
      public Builder setUserMacId(
          String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  
        userMacId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>string userMacId = 4;</code>
       * @return This builder for chaining.
       */
      public Builder clearUserMacId() {
        
        userMacId_ = getDefaultInstance().getUserMacId();
        onChanged();
        return this;
      }
      /**
       * <code>string userMacId = 4;</code>
       * @param value The bytes for userMacId to set.
       * @return This builder for chaining.
       */
      public Builder setUserMacIdBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  checkByteStringIsUtf8(value);
        
        userMacId_ = value;
        onChanged();
        return this;
      }

      private long receiveId_ ;
      /**
       * <code>int64 receiveId = 5;</code>
       * @return The receiveId.
       */
      @Override
      public long getReceiveId() {
        return receiveId_;
      }
      /**
       * <code>int64 receiveId = 5;</code>
       * @param value The receiveId to set.
       * @return This builder for chaining.
       */
      public Builder setReceiveId(long value) {
        
        receiveId_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 receiveId = 5;</code>
       * @return This builder for chaining.
       */
      public Builder clearReceiveId() {
        
        receiveId_ = 0L;
        onChanged();
        return this;
      }

      private long timeStamp_ ;
      /**
       * <code>int64 timeStamp = 6;</code>
       * @return The timeStamp.
       */
      @Override
      public long getTimeStamp() {
        return timeStamp_;
      }
      /**
       * <code>int64 timeStamp = 6;</code>
       * @param value The timeStamp to set.
       * @return This builder for chaining.
       */
      public Builder setTimeStamp(long value) {
        
        timeStamp_ = value;
        onChanged();
        return this;
      }
      /**
       * <code>int64 timeStamp = 6;</code>
       * @return This builder for chaining.
       */
      public Builder clearTimeStamp() {
        
        timeStamp_ = 0L;
        onChanged();
        return this;
      }
      @Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:org.example.common.po.HeartbeatMessage)
    }

    // @@protoc_insertion_point(class_scope:org.example.common.po.HeartbeatMessage)
    private static final HeartbeatMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new HeartbeatMessage();
    }

    public static HeartbeatMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<HeartbeatMessage>
        PARSER = new com.google.protobuf.AbstractParser<HeartbeatMessage>() {
      @Override
      public HeartbeatMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new HeartbeatMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<HeartbeatMessage> parser() {
      return PARSER;
    }

    @Override
    public com.google.protobuf.Parser<HeartbeatMessage> getParserForType() {
      return PARSER;
    }

    @Override
    public HeartbeatMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_org_example_common_po_HeartbeatMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_org_example_common_po_HeartbeatMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\026HeartbeatMessage.proto\022\025org.example.co" +
      "mmon.po\"\177\n\020HeartbeatMessage\022\n\n\002id\030\001 \001(\t\022" +
      "\016\n\006userId\030\002 \001(\003\022\026\n\016userPlatformId\030\003 \001(\005\022" +
      "\021\n\tuserMacId\030\004 \001(\t\022\021\n\treceiveId\030\005 \001(\003\022\021\n" +
      "\ttimeStamp\030\006 \001(\003B$\n\025org.example.common.p" +
      "oB\tHeartBeatH\001b\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_org_example_common_po_HeartbeatMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_org_example_common_po_HeartbeatMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_org_example_common_po_HeartbeatMessage_descriptor,
        new String[] { "Id", "UserId", "UserPlatformId", "UserMacId", "ReceiveId", "TimeStamp", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}
