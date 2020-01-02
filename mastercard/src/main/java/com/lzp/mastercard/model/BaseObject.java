/*
 * Copyright 2015 MasterCard International.
 *
 * Redistribution and use in source and binary forms, with or without modification, are
 * permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list of
 * conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * Neither the name of the MasterCard International Incorporated nor the names of its
 * contributors may be used to endorse or promote products derived from this software
 * without specific prior written permission.
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT
 * SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT,
 * INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS;
 * OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER
 * IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING
 * IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 *
 */

package com.lzp.mastercard.model;


import com.lzp.mastercard.ApiController;
import com.lzp.mastercard.exception.ApiException;
import com.lzp.mastercard.security.Authentication;

import java.util.Map;

/**
 *
 */
public abstract class BaseObject extends RequestMap {

    protected static ApiController apiController = new ApiController();

    protected abstract OperationConfig getOperationConfig(String operationUUID);

    protected abstract OperationMetadata getOperationMetadata();

    protected static BaseObject executeOperation(final Authentication authentication, String operationUUID, final BaseObject value)
            throws ApiException {

        return execute(authentication, operationUUID, value);
    }


    /**
     * Create an instance of Base Object for Response
     *
     * @param bo
     * @return
     */
    private static BaseObject createResponseBaseObject(final BaseObject bo) {
        return new BaseObject() {
            @Override
            protected OperationConfig getOperationConfig(String uuid) throws IllegalArgumentException {
                return bo.getOperationConfig(uuid);
            }

            @Override
            protected OperationMetadata getOperationMetadata() throws IllegalArgumentException {
                return bo.getOperationMetadata();
            }
        };
    }


    private static BaseObject execute(Authentication authentication, String operationUUID, BaseObject requestObject)
            throws ApiException {

        Map<? extends String, ? extends Object> response = apiController.execute(authentication, requestObject.getOperationConfig(operationUUID), requestObject.getOperationMetadata(), requestObject);

        BaseObject responseObject = createResponseBaseObject(requestObject);

        // Response can be null (204)
        if (response != null) {
            responseObject.putAll(response);
        }

        return responseObject;
    }


}
