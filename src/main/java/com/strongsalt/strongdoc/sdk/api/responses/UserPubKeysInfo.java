package com.strongsalt.strongdoc.sdk.api.responses;

import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.strongdoc.sdk.proto.Encryption;
import com.strongsalt.strongdoc.sdk.utils.StrongDocUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserPubKeysInfo {
    private KeyInfo toUserKey;
    private List<KeyInfo> toOrgKeys;

    UserPubKeysInfo(KeyInfo toUserKey, List<KeyInfo> toOrgKeys){
        this.toUserKey = toUserKey;
        this.toOrgKeys = toOrgKeys;
    }

    public static UserPubKeysInfo deserializeUserPubKeys(Encryption.UserPubKeys keys) throws StrongSaltKeyException {
        Encryption.Key userKey = keys.getUserPubKey();
        byte[] serialKey = StrongDocUtils.decodeWithBase64(userKey.getKey()); //todo check
        System.out.println("serial key"+ Arrays.toString(serialKey));
        StrongSaltKey deserializedKey = StrongSaltKey.Deserialize(serialKey);
        KeyInfo toUserKey = new KeyInfo(deserializedKey, userKey.getKeyID(), userKey.getOwnerID());
        List<KeyInfo> toOrgKeys = new ArrayList<>();
        for(int i = 0; i < keys.getOrgPubKeysCount(); i++){
            Encryption.Key pubKey = keys.getOrgPubKeys(i);
            byte[] serial = StrongDocUtils.decodeWithBase64(pubKey.getKey()); //todo check
            StrongSaltKey key = StrongSaltKey.Deserialize(serial);
            KeyInfo orgKey = new KeyInfo(key, pubKey.getKeyID(), pubKey.getOwnerID());
            toOrgKeys.add(orgKey);
        }
        return new UserPubKeysInfo(toUserKey, toOrgKeys);
    }

    public List<KeyInfo> getToOrgKeys(){ return toOrgKeys; }

    public KeyInfo getToUserKey(){ return toUserKey; }

}
