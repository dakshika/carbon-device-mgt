/*
 *   Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *   WSO2 Inc. licenses this file to you under the Apache License,
 *   Version 2.0 (the "License"); you may not use this file except
 *   in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing,
 *   software distributed under the License is distributed on an
 *   "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 *   KIND, either express or implied.  See the License for the
 *   specific language governing permissions and limitations
 *   under the License.
 *
 */
package org.wso2.carbon.apimgt.webapp.publisher.internal;

import org.wso2.carbon.apimgt.keymgt.service.APIKeyMgtSubscriberService;
import org.wso2.carbon.apimgt.webapp.publisher.APIPublisherService;
import org.wso2.carbon.utils.ConfigurationContextService;

public class APIPublisherDataHolder {

    private APIPublisherService apiPublisherService;
    private ConfigurationContextService configurationContextService;
    private APIKeyMgtSubscriberService apiKeyMgtSubscriberService;

    private static APIPublisherDataHolder thisInstance = new APIPublisherDataHolder();

    private APIPublisherDataHolder() {
    }

    public static APIPublisherDataHolder getInstance() {
        return thisInstance;
    }

    public APIPublisherService getApiPublisherService() {
        if (apiPublisherService == null) {
            throw new IllegalStateException("APIPublisher service is not initialized properly");
        }
        return apiPublisherService;
    }

    public void setApiPublisherService(APIPublisherService apiPublisherService) {
        this.apiPublisherService = apiPublisherService;
    }

    public void setConfigurationContextService(ConfigurationContextService configurationContextService) {
        this.configurationContextService = configurationContextService;
    }

    public ConfigurationContextService getConfigurationContextService() {
        if (configurationContextService == null) {
            throw new IllegalStateException("ConfigurationContext service is not initialized properly");
        }
        return configurationContextService;
    }

    public void setApiKeyMgtSubscriberService(APIKeyMgtSubscriberService apiKeyMgtSubscriberService) {
        this.apiKeyMgtSubscriberService = apiKeyMgtSubscriberService;
    }

}
