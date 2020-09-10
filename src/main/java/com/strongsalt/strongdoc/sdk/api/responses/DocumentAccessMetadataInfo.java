package com.strongsalt.strongdoc.sdk.api.responses;

import com.strongsalt.crypto.exception.StrongSaltKeyException;
import com.strongsalt.crypto.key.StrongSaltKey;
import com.strongsalt.strongdoc.sdk.client.StrongDocManager;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocAccountException;
import com.strongsalt.strongdoc.sdk.exceptions.StrongDocDocumentException;
import com.strongsalt.strongdoc.sdk.proto.Documents;
import com.strongsalt.strongdoc.sdk.utils.StrongDocUtils;

public class DocumentAccessMetadataInfo {
    private StrongSaltKey docKey;
    private StrongSaltKey macKey;
    private byte[] serialDocKey;
    private byte[] serialMacKey;

    DocumentAccessMetadataInfo(StrongSaltKey docKey, StrongSaltKey macKey, byte[] serialDocKey, byte[] serialMacKey){
        this.docKey = docKey;
        this.macKey = macKey;
        this.serialDocKey = serialDocKey;
        this.serialMacKey = serialMacKey;
    }

    public StrongSaltKey getDocKey(){
        return docKey;
    }

    public StrongSaltKey getMACKey(){
        return macKey;
    }

    public byte[] getSerializedMACKey(){return serialMacKey; }

    public byte[] getSerializedDocKey(){return serialDocKey; }

    public static DocumentAccessMetadataInfo deserialize(Documents.DocumentAccessMetadata documentAccessMetadata) throws StrongDocDocumentException {
        String encryptorID = documentAccessMetadata.getUserAsymKeyEncryptorId();
        String password = StrongDocManager.getInstance().getPasswordKeyID();
        if(!encryptorID.equals(password)){
            throw new StrongDocDocumentException("user must login again");
        }
        try{
            StrongSaltKey docKeyEncryptor;
            if (documentAccessMetadata.getIsKeyOrgs()){
                byte[] encryptedSerialAsymKeyBytes = StrongDocUtils.decodeWithBase64(documentAccessMetadata.getEncUserAsymKey());
                byte[] serialAsymKeyBytes = StrongDocManager.getInstance().getPasswordKey().decrypt(encryptedSerialAsymKeyBytes);
                docKeyEncryptor = StrongSaltKey.Deserialize(serialAsymKeyBytes);

            }else{
                docKeyEncryptor = StrongDocManager.getInstance().getPasswordKey();
            }

            byte[] encryptedSerialAsymKey = StrongDocUtils.decodeWithBase64(documentAccessMetadata.getDocKeyEncryptor());
            byte[] serialAsymKey = docKeyEncryptor.decrypt(encryptedSerialAsymKey);
            StrongSaltKey asymKey = StrongSaltKey.Deserialize(serialAsymKey);

            byte[] encryptedSerialDocKey = StrongDocUtils.decodeWithBase64(documentAccessMetadata.getEncDocKey());
            byte[] serialDocKey = asymKey.decrypt(encryptedSerialDocKey);

            byte[] encryptedSerialMACKey = StrongDocUtils.decodeWithBase64(documentAccessMetadata.getEncMACKey());
            byte[] serialMACKey = asymKey.decrypt(encryptedSerialMACKey);
            return new DocumentAccessMetadataInfo(StrongSaltKey.Deserialize(serialDocKey), StrongSaltKey.Deserialize(serialMACKey), serialDocKey, serialMACKey);
        }catch(StrongSaltKeyException e){
            throw new StrongDocDocumentException("cannot deserialize DocumentAccessMetadataInfo");
        }
    }
}
