package com.tr.core.model;

import com.google.gson.annotations.SerializedName;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

/**
 * @author liuzhipeng
 * @description
 * @create 2019-10-21 17:04
 */
public class RnsInfo {
    public static final String SERIALIZED_NAME_GCM_REGISTRATION_ID = "gcmRegistrationId";
    @SerializedName(SERIALIZED_NAME_GCM_REGISTRATION_ID)
    private String gcmRegistrationId;

    public RnsInfo gcmRegistrationId(String gcmRegistrationId) {
        this.gcmRegistrationId = gcmRegistrationId;
        return this;
    }

    /**
     * The Google Cloud Messaging Registration ID.
     * <p>
     * Conditional – required if the RNS used is Google Cloud Messaging.
     *
     * @return gcmRegistrationId
     */
    @javax.annotation.Nullable
    @ApiModelProperty(example = "APA91bHPRgkF3JUikC4ENAHEeMrd41Zxv3hVZjC9KtT8OvPVGJ-" +
            "hQMRKRrZuJAEcl7B338qju59zJMjw2DELjzEvxwYv7hH5Ynpc1ODQ0aT4U4OFEeco8ohsN5PjL1iC2dNtk2BA" +
            "okeMCg2ZXKqpc8FXKmhX94kIxQ", value = "The Google Cloud Messaging Registration ID.        __Max Length:2000__ ")
    public String getGcmRegistrationId() {
        return gcmRegistrationId;
    }

    public void setGcmRegistrationId(String gcmRegistrationId) {
        this.gcmRegistrationId = gcmRegistrationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RnsInfo rnsInfo = (RnsInfo) o;
        return Objects.equals(gcmRegistrationId, rnsInfo.gcmRegistrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(gcmRegistrationId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class RnsInfo {");
        sb.append("    gcmRegistrationId: ").append(toIndentedString(gcmRegistrationId)).append("");
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
