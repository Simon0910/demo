//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//
package com.lzp.mastercard.security.fle;

import com.lzp.mastercard.exception.SdkException;
import com.lzp.mastercard.security.util.DataEncoding;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class Config {
    public List<String> triggeringEndPath = null;
    public List<String> fieldsToEncrypt = null;
    public List<String> fieldsToDecrypt = null;
    public String symmetricAlgorithm = null;
    public String symmetricCipher = null;
    public int symmetricKeysize = -1;
    public String asymmetricCipher = null;
    public String oaepHashingAlgorithm = null;
    public String publicKeyFingerprintHashing = null;
    public String ivFieldName = null;
    public String oaepHashingAlgorithmFieldName = null;
    public String encryptedKeyFiledName = null;
    public String encryptedDataFieldName = null;
    public String publicKeyFingerprintFiledName = null;
    public DataEncoding dataEncoding;

    public Config() {
    }

    protected void validate() {
        if (this.triggeringEndPath == null) {
            throw new SdkException("Config: triggetingEndPath is null");
        } else if (this.fieldsToEncrypt == null) {
            throw new SdkException("Config: fieldsToEncrypt is null");
        } else if (this.fieldsToDecrypt == null) {
            throw new SdkException("Config: fieldsToDecrypt is null");
        } else if (this.symmetricAlgorithm == null) {
            throw new SdkException("Config: symmetricAlgorithm is null");
        } else if (this.symmetricCipher == null) {
            throw new SdkException("Config: symmetricCipher is null");
        } else if (this.symmetricKeysize == -1) {
            throw new SdkException("Config: symmetricKeysize is not set");
        } else if (this.asymmetricCipher == null) {
            throw new SdkException("Config: asymmetricCipher is null");
        } else if (this.oaepHashingAlgorithm == null) {
            throw new SdkException("Config: oaepHashingAlgorithm is null");
        } else if (this.publicKeyFingerprintHashing == null) {
            throw new SdkException("Config: publicKeyFingerprintHashing is null");
        } else if (this.ivFieldName == null) {
            throw new SdkException("Config: ivFieldName is null");
        } else if (this.oaepHashingAlgorithmFieldName == null) {
            throw new SdkException("Config: oaepHashingAlgorithmFieldName is null");
        } else if (this.encryptedKeyFiledName == null) {
            throw new SdkException("Config: encryptedKeyFiledName is null");
        } else if (this.encryptedDataFieldName == null) {
            throw new SdkException("Config: encryptedDataFieldName is null");
        } else if (this.publicKeyFingerprintFiledName == null) {
            throw new SdkException("Config: publicKeyFingerprintFiledName is null");
        }
    }

    public static final Config parseFromJson(String json) {
        JSONObject object = (JSONObject) JSONValue.parse(json);
        Config tmpConfig = new Config();
        ArrayList tmp;
        ListIterator iterator;
        if (object.containsKey("triggeringEndPath") && object.get("triggeringEndPath") instanceof JSONArray) {
            tmp = new ArrayList();
            iterator = ((JSONArray) object.get("triggeringEndPath")).listIterator();

            while (iterator.hasNext()) {
                tmp.add(iterator.next());
            }

            tmpConfig.triggeringEndPath = tmp;
        }

        if (object.containsKey("fieldsToEncrypt") && object.get("fieldsToEncrypt") instanceof JSONArray) {
            tmp = new ArrayList();
            iterator = ((JSONArray) object.get("fieldsToEncrypt")).listIterator();

            while (iterator.hasNext()) {
                tmp.add(iterator.next());
            }

            tmpConfig.fieldsToEncrypt = tmp;
        }

        if (object.containsKey("fieldsToDecrypt") && object.get("fieldsToDecrypt") instanceof JSONArray) {
            tmp = new ArrayList();
            iterator = ((JSONArray) object.get("fieldsToDecrypt")).listIterator();

            while (iterator.hasNext()) {
                tmp.add(iterator.next());
            }

            tmpConfig.fieldsToDecrypt = tmp;
        }

        tmpConfig.symmetricAlgorithm = (String) object.get("symmetricAlgorithm");
        tmpConfig.symmetricCipher = (String) object.get("symmetricCipher");
        if (object.containsKey("symmetricKeysize")) {
            try {
                tmpConfig.symmetricKeysize = Integer.parseInt(object.get("symmetricKeysize").toString());
            } catch (NumberFormatException var5) {
            }
        }

        tmpConfig.asymmetricCipher = (String) object.get("asymmetricCipher");
        tmpConfig.oaepHashingAlgorithm = (String) object.get("oaepHashingAlgorithm");
        tmpConfig.publicKeyFingerprintHashing = (String) object.get("publicKeyFingerprintHashing");
        tmpConfig.ivFieldName = (String) object.get("ivFieldName");
        tmpConfig.oaepHashingAlgorithmFieldName = (String) object.get("oaepHashingAlgorithmFieldName");
        tmpConfig.encryptedKeyFiledName = (String) object.get("encryptedKeyFiledName");
        tmpConfig.encryptedDataFieldName = (String) object.get("encryptedDataFieldName");
        tmpConfig.publicKeyFingerprintFiledName = (String) object.get("publicKeyFingerprintFiledName");
        String dataEncoding = (String) object.get("dataEncoding");
        if (dataEncoding.toLowerCase().compareTo("hex") == 0) {
            tmpConfig.dataEncoding = DataEncoding.HEX;
        } else {
            tmpConfig.dataEncoding = DataEncoding.BASE64;
        }

        tmpConfig.validate();
        return tmpConfig;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o != null && this.getClass() == o.getClass()) {
            Config config = (Config) o;
            if (this.symmetricKeysize != config.symmetricKeysize) {
                return false;
            } else {
                if (this.triggeringEndPath != null) {
                    if (!this.triggeringEndPath.equals(config.triggeringEndPath)) {
                        return false;
                    }
                } else if (config.triggeringEndPath != null) {
                    return false;
                }

                if (this.fieldsToEncrypt != null) {
                    if (!this.fieldsToEncrypt.equals(config.fieldsToEncrypt)) {
                        return false;
                    }
                } else if (config.fieldsToEncrypt != null) {
                    return false;
                }

                label150:
                {
                    if (this.fieldsToDecrypt != null) {
                        if (this.fieldsToDecrypt.equals(config.fieldsToDecrypt)) {
                            break label150;
                        }
                    } else if (config.fieldsToDecrypt == null) {
                        break label150;
                    }

                    return false;
                }

                label143:
                {
                    if (this.symmetricAlgorithm != null) {
                        if (this.symmetricAlgorithm.equals(config.symmetricAlgorithm)) {
                            break label143;
                        }
                    } else if (config.symmetricAlgorithm == null) {
                        break label143;
                    }

                    return false;
                }

                if (this.symmetricCipher != null) {
                    if (!this.symmetricCipher.equals(config.symmetricCipher)) {
                        return false;
                    }
                } else if (config.symmetricCipher != null) {
                    return false;
                }

                if (this.asymmetricCipher != null) {
                    if (!this.asymmetricCipher.equals(config.asymmetricCipher)) {
                        return false;
                    }
                } else if (config.asymmetricCipher != null) {
                    return false;
                }

                label122:
                {
                    if (this.oaepHashingAlgorithm != null) {
                        if (this.oaepHashingAlgorithm.equals(config.oaepHashingAlgorithm)) {
                            break label122;
                        }
                    } else if (config.oaepHashingAlgorithm == null) {
                        break label122;
                    }

                    return false;
                }

                label115:
                {
                    if (this.publicKeyFingerprintHashing != null) {
                        if (this.publicKeyFingerprintHashing.equals(config.publicKeyFingerprintHashing)) {
                            break label115;
                        }
                    } else if (config.publicKeyFingerprintHashing == null) {
                        break label115;
                    }

                    return false;
                }

                if (this.ivFieldName != null) {
                    if (!this.ivFieldName.equals(config.ivFieldName)) {
                        return false;
                    }
                } else if (config.ivFieldName != null) {
                    return false;
                }

                if (this.oaepHashingAlgorithmFieldName != null) {
                    if (!this.oaepHashingAlgorithmFieldName.equals(config.oaepHashingAlgorithmFieldName)) {
                        return false;
                    }
                } else if (config.oaepHashingAlgorithmFieldName != null) {
                    return false;
                }

                label94:
                {
                    if (this.encryptedKeyFiledName != null) {
                        if (this.encryptedKeyFiledName.equals(config.encryptedKeyFiledName)) {
                            break label94;
                        }
                    } else if (config.encryptedKeyFiledName == null) {
                        break label94;
                    }

                    return false;
                }

                if (this.encryptedDataFieldName != null) {
                    if (!this.encryptedDataFieldName.equals(config.encryptedDataFieldName)) {
                        return false;
                    }
                } else if (config.encryptedDataFieldName != null) {
                    return false;
                }

                return this.publicKeyFingerprintFiledName != null ? this.publicKeyFingerprintFiledName.equals(config.publicKeyFingerprintFiledName) : config.publicKeyFingerprintFiledName == null;
            }
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        int result = this.triggeringEndPath != null ? this.triggeringEndPath.hashCode() : 0;
        result = 31 * result + (this.fieldsToEncrypt != null ? this.fieldsToEncrypt.hashCode() : 0);
        result = 31 * result + (this.fieldsToDecrypt != null ? this.fieldsToDecrypt.hashCode() : 0);
        result = 31 * result + (this.symmetricAlgorithm != null ? this.symmetricAlgorithm.hashCode() : 0);
        result = 31 * result + (this.symmetricCipher != null ? this.symmetricCipher.hashCode() : 0);
        result = 31 * result + this.symmetricKeysize;
        result = 31 * result + (this.asymmetricCipher != null ? this.asymmetricCipher.hashCode() : 0);
        result = 31 * result + (this.oaepHashingAlgorithm != null ? this.oaepHashingAlgorithm.hashCode() : 0);
        result = 31 * result + (this.publicKeyFingerprintHashing != null ? this.publicKeyFingerprintHashing.hashCode() : 0);
        result = 31 * result + (this.ivFieldName != null ? this.ivFieldName.hashCode() : 0);
        result = 31 * result + (this.oaepHashingAlgorithmFieldName != null ? this.oaepHashingAlgorithmFieldName.hashCode() : 0);
        result = 31 * result + (this.encryptedKeyFiledName != null ? this.encryptedKeyFiledName.hashCode() : 0);
        result = 31 * result + (this.encryptedDataFieldName != null ? this.encryptedDataFieldName.hashCode() : 0);
        result = 31 * result + (this.publicKeyFingerprintFiledName != null ? this.publicKeyFingerprintFiledName.hashCode() : 0);
        return result;
    }
}
