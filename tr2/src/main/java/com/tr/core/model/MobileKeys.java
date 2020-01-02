package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 17:40
 */
public class MobileKeys {
    public static final String SERIALIZED_NAME_TRANSPORT_KEY = "transportKey";
    @SerializedName(SERIALIZED_NAME_TRANSPORT_KEY)
    private String transportKey;

    public static final String SERIALIZED_NAME_MAC_KEY = "macKey";
    @SerializedName(SERIALIZED_NAME_MAC_KEY)
    private String macKey;

    public static final String SERIALIZED_NAME_DATA_ENCRYPTION_KEY = "dataEncryptionKey";
    @SerializedName(SERIALIZED_NAME_DATA_ENCRYPTION_KEY)
    private String dataEncryptionKey;

    public MobileKeys transportKey(String transportKey) {
        this.transportKey = transportKey;
        return this;
    }

    /**
     * The Mobile Transport Key used to provide confidentiality of data at the
     * transport level between the Mobile Payment App and MDES. This is a
     * 128-bit AES key (M_KEY_CONF as described in Mastercard Cloud-Based
     * Payments – Issuer Cryptographic Algorithms), and is encrypted by the
     * randomly-generated key (RGK) provided by the Mobile Payment App
     * using ECB mode with no padding.
     *
     * @return transportKey
     */
    @ApiModelProperty(required = true, example = "4E4F54205245414C2044415441202D20746869732077696C6C206265207468",
            value = "The Mobile Transport Key used to provide confidentiality of data at the transport level between the Mobile Payment App and MDES. This is a 128-bit AES key (M_KEY_CONF as described in Mastercard Cloud-Based Payments – Issuer Cryptographic Algorithms), and is encrypted by the randomly-generated key (RGK) provided by the Mobile Payment App using ECB mode with no padding. ")
    public String getTransportKey() {
        return transportKey;
    }

    public void setTransportKey(String transportKey) {
        this.transportKey = transportKey;
    }

    public MobileKeys macKey(String macKey) {
        this.macKey = macKey;
        return this;
    }

    /**
     * The Mobile MAC Key used to provide integrity of data at the transport
     * level between the Mobile Payment App and MDES. This is a 128-bit AES
     * key (M_KEY_MAC as described in Mastercard Cloud-Based Payments –
     * Issuer Cryptographic Algorithms), and is encrypted by the randomly-
     * generated key (RGK) provided by the Mobile Payment App using ECB
     * mode with no padding.
     *
     * @return macKey
     */
    @ApiModelProperty(required = true, example = "4E4F54205245414C2044415441202D20746869732077696C6C206265207468", value = "This is a 128-bit AES\n" +
            " key (M_KEY_MAC as described in Mastercard Cloud-Based Payments –\n" +
            " Issuer Cryptographic Algorithms), and is encrypted by the randomly-\n" +
            " generated key (RGK) provided by the Mobile Payment App using ECB\n" +
            " mode with no padding.")
    public String getMacKey() {
        return macKey;
    }

    public void setMacKey(String macKey) {
        this.macKey = macKey;
    }

    public MobileKeys dataEncryptionKey(String dataEncryptionKey) {
        this.dataEncryptionKey = dataEncryptionKey;
        return this;
    }

    /**
     * The Mobile Data Encryption Key used to encrypt any sensitive data at
     * the data field level between the Mobile Payment App and MDES. This is
     * a 128-bit AES key and is encrypted by the randomly-generated key
     * (RGK) provided by the Mobile Payment App using ECB mode with no
     * padding.
     *
     * @return dataEncryptionKey
     */
    @ApiModelProperty(required = true, example = "4E4F54205245414C2044415441202D20746869732077696C6C2062652074686",
            value = "The Mobile Data Encryption Key used to encrypt any sensitive data at the data field level between the Mobile Payment App and MDES. .. ")
    public String getDataEncryptionKey() {
        return dataEncryptionKey;
    }

    public void setDataEncryptionKey(String dataEncryptionKey) {
        this.dataEncryptionKey = dataEncryptionKey;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        MobileKeys that = (MobileKeys) o;
        return Objects.equals(transportKey, that.transportKey) &&
                Objects.equals(macKey, that.macKey) &&
                Objects.equals(dataEncryptionKey, that.dataEncryptionKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transportKey, macKey, dataEncryptionKey);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class TokenizeResponseSchema {");
        sb.append("    transportKey: ").append(toIndentedString(transportKey)).append("");
        sb.append("    macKey: ").append(toIndentedString(macKey)).append("");
        sb.append("    dataEncryptionKey: ").append(toIndentedString(dataEncryptionKey)).append("");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("", "");
    }
}
