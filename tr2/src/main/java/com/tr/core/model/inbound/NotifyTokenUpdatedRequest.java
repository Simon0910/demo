package com.tr.core.model.inbound;

import com.tr.core.model.Token;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * A.26 P241
 * Created by daniel on 2019/7/22 10:16.
 */
@Data
public class NotifyTokenUpdatedRequest implements Serializable {
    private static final long serialVersionUID = -5955026400094813266L;
    /**
     * Contains the Tokens which were updated
     */
    private List<Token> tokens;
    /**
     * Identifier for the specific Mobile Payment App instance, unique across a given Wallet Identifier.
     */
    private String paymentAppInstanceId;
    /**
     * The date/time after which this encrypted payload object is considered invalid.
     */
    private String dataValidUntilTimestamp;

    /**
     * 文档未找到
     * 根据反参定义
     */
    private Boolean redigitizationRequired;

    public NotifyTokenUpdatedRequest tokens(List<Token> tokens) {
        this.tokens = tokens;
        return this;
    }
}
