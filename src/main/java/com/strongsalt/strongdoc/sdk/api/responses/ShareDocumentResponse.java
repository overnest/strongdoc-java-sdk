package com.strongsalt.strongdoc.sdk.api.responses;

import java.util.HashSet;
import java.util.Set;

public class ShareDocumentResponse {

    boolean isAccessible;
    boolean isSharable;
    Set<String> sharedReceivers;
    Set<String> alreadyAccessibleReceivers;
    Set<String> unsharableReceivers;

    public ShareDocumentResponse(boolean isAccessible, boolean isSharable) {
        this.isAccessible = isAccessible;
        this.isSharable = isSharable;
        sharedReceivers = new HashSet<>();
        alreadyAccessibleReceivers = new HashSet<>();
        unsharableReceivers = new HashSet<>();
    }

    public void addSharedReceivers(String receiver) {
        sharedReceivers.add(receiver);
    }

    public void addAlreadyAccessibleReceivers(String receiver) {
        alreadyAccessibleReceivers.add(receiver);
    }

    public void addUnsharableReceiver(String receiver) {
        unsharableReceivers.add(receiver);
    }

    public Set<String> getSharedReceivers() {
        return sharedReceivers;
    }

    public Set<String> getUnsharedReceivers() {
        return unsharableReceivers;
    }

    public Set<String> getAlreadyAccessibleReceivers() {
        return alreadyAccessibleReceivers;
    }

}
