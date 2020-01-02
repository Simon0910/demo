package com.example.mvc.model;

import com.example.mvc.annotation.SecretBody;
import lombok.Data;

import java.io.Serializable;

@Data
@SecretBody
public class TokenNotifyReq implements Serializable {
    private static final long serialVersionUID = 5404392623091196572L;

    private String responseHost;

    private String requestId;

    private EncryptedPayload encryptedPayload;
}