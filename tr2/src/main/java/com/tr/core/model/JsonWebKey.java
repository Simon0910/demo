package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;
import java.util.Objects;

/**
 * The JsonWebKey object is a JSON Web Key (JWK) data structure as specified in RFC 7517.
 * The following is a subset of JWK parameters as supported by MDES:
 *
 * @author liuzhipeng
 * @description
 * @create 2019-10-22 11:15
 */
public class JsonWebKey {
    public static final String SERIALIZED_NAME_KTY = "kty";
    @SerializedName(SERIALIZED_NAME_KTY)
    private String kty;

    public static final String SERIALIZED_NAME_X5C = "x5c";
    @SerializedName(SERIALIZED_NAME_X5C)
    private List<String> x5c;

    public JsonWebKey kty(String kty) {
        this.kty = kty;
        return this;
    }

    /**
     * The "kty" (key type) parameter identifies the cryptographic algorithm
     * family used with the key, such as "RSA" or "EC".
     * Must be one of the following supported key types:
     * {@link com.tr.core.enums.KtyEnum}
     * <p>
     * Data Type:  String
     * Max Length:  3
     * Required:  Yes
     *
     * @return kty
     */
    @ApiModelProperty(required = true, value = "The \"kty\" (key type) parameter identifies the cryptographic algorithm family used with the key, such as \"RSA\" or \"EC\". Max Length:  3")
    public String getKty() {
        return kty;
    }

    public void setKty(String kty) {
        this.kty = kty;
    }

    public JsonWebKey x5c(List<String> x5c) {
        this.x5c = x5c;
        return this;
    }

    /**
     * The "x5c" (X.509 certificate chain) parameter contains a chain of one or
     * more PKIX certificates. The certificate chain is represented as a JSON
     * array of certificate value strings. Each string in the array is a base64-
     * encoded DER PKIX certificate value. The PKIX certificate containing the
     * key value MUST be the first certificate. This MAY be followed by
     * additional certificates, with each subsequent certificate being the one
     * used to certify the previous one.
     * <p>
     * Data Type:  Array[Base64-encoded String]
     * Max Length:  N/A
     * Required:  Yes
     *
     * @return x5c
     */
    @ApiModelProperty(required = true, value = "The \"x5c\" (X.509 certificate chain) parameter contains a chain of one or\n" +
            "more PKIX certificates. The certificate chain is represented as a JSON\n" +
            "array of certificate value strings. Each string in the array is a base64-\n" +
            "encoded DER PKIX certificate value. The PKIX certificate containing the\n" +
            "key value MUST be the first certificate. This MAY be followed by\n" +
            "additional certificates, with each subsequent certificate being the one\n" +
            "used to certify the previous one.")
    public List<String> getX5c() {
        return x5c;
    }

    public void setX5c(List<String> x5c) {
        this.x5c = x5c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonWebKey that = (JsonWebKey) o;
        return Objects.equals(kty, that.kty) &&
                Objects.equals(x5c, that.x5c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(kty, x5c);
    }

    @Override
    public String
    toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class JsonWebKey {");
        sb.append("    kty: ").append(toIndentedString(kty)).append("");
        sb.append("    x5c: ").append(toIndentedString(x5c)).append("");
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
