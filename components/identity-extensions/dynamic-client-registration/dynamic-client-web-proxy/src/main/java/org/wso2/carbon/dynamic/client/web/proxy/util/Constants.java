/*
 * Copyright (c) 2015, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * you may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.wso2.carbon.dynamic.client.web.proxy.util;

/**
 * Created by harshan on 12/10/15.
 */
public class Constants {

    public static final class ContentTypes {
        private ContentTypes() {
            throw new AssertionError();
        }

        public static final String CONTENT_TYPE_ANY = "*/*";
        public static final String CONTENT_TYPE_XML = "application/xml";
        public static final String CONTENT_TYPE_APPLICATION_JSON = "application/json";
    }

    public static final class CharSets {
        private CharSets() {
            throw new AssertionError();
        }

        public static final String CHARSET_UTF8 = "UTF8";
    }

    public static class ConfigurationProperties {
        private ConfigurationProperties() {
            throw new AssertionError();
        }

        public static final String AUTHENTICATOR_NAME = "OAuthAuthenticator";
        public static final String AUTHENTICATOR_CONFIG_IS_REMOTE = "isRemote";
        public static final String AUTHENTICATOR_CONFIG_HOST_URL = "hostURL";
    }

    public static class RemoteServiceProperties {
        private RemoteServiceProperties() {
            throw new AssertionError();
        }

        public static final String DYNAMIC_CLIENT_SERVICE_ENDPOINT = "/dynamic-client-web/register";
        public static final String DYNAMIC_CLIENT_SERVICE_PROTOCOL = "https";
    }
}
