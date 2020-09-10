package com.strongsalt.strongdoc.sdk.api.responses;

import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.strongdoc.sdk.proto.Encryption;
import com.strongsalt.strongdoc.sdk.utils.StrongDocUtils;

public class KeyInfo {
    private StrongSaltKey key;
    private String keyID;
    private String ownerID;

    KeyInfo(StrongSaltKey key, String keyID, String ownerID){
        this.key = key;
        this.keyID = keyID;
        this.ownerID = ownerID;
    }
    public StrongSaltKey getKey(){ return key; }

    public String getKeyID(){ return keyID; }

    public String getOwnerID(){ return ownerID; }

    public Encryption.EncryptedKey buildEncryptedKey(byte[] toBeEncrypted) throws StrongSaltKeyException {
        byte[] encryptedKey = getKey().encrypt(toBeEncrypted);
        Encryption.EncryptedKey res = Encryption.EncryptedKey.newBuilder()
                .setEncKey(StrongDocUtils.encodeWithBase64(encryptedKey))
                .setEncryptorID(getKeyID())
                .setOwnerID(getOwnerID()).build();
        return res;
    }
}
