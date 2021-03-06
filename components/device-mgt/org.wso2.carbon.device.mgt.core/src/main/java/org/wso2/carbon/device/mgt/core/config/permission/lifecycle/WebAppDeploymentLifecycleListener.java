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

package org.wso2.carbon.device.mgt.core.config.permission.lifecycle;

import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleEvent;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.wso2.carbon.device.mgt.common.permission.mgt.Permission;
import org.wso2.carbon.device.mgt.common.permission.mgt.PermissionManagementException;
import org.wso2.carbon.device.mgt.core.config.permission.PermissionConfiguration;
import org.wso2.carbon.device.mgt.core.permission.mgt.PermissionManagerServiceImpl;
import org.wso2.carbon.device.mgt.core.permission.mgt.PermissionUtils;

import javax.servlet.ServletContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * This listener class will initiate the permission addition of permissions defined in
 * permission.xml of any web-app.
 */
@SuppressWarnings("unused")
public class WebAppDeploymentLifecycleListener implements LifecycleListener {

	private static final String PERMISSION_CONFIG_PATH = "META-INF" + File.separator + "permissions.xml";
	private static final Log log = LogFactory.getLog(WebAppDeploymentLifecycleListener.class);

	@Override
	public void lifecycleEvent(LifecycleEvent lifecycleEvent) {
		if (Lifecycle.AFTER_START_EVENT.equals(lifecycleEvent.getType())) {
			StandardContext context = (StandardContext) lifecycleEvent.getLifecycle();
			ServletContext servletContext = context.getServletContext();
			String contextPath = context.getServletContext().getContextPath();
			try {
				InputStream permissionStream = servletContext.getResourceAsStream(PERMISSION_CONFIG_PATH);
				if (permissionStream != null) {
                /* Un-marshaling Device Management configuration */
					JAXBContext cdmContext = JAXBContext.newInstance(PermissionConfiguration.class);
					Unmarshaller unmarshaller = cdmContext.createUnmarshaller();
					PermissionConfiguration permissionConfiguration = (PermissionConfiguration)
							unmarshaller.unmarshal(permissionStream);
                    List<Permission> permissions = permissionConfiguration.getPermissions();
                    String apiVersion = permissionConfiguration.getApiVersion();
                    if (permissionConfiguration != null && permissions != null) {
                        for (Permission permission : permissions) {
                            // update the permission path to absolute permission path
                            permission.setPath(PermissionUtils.getAbsolutePermissionPath(permission.getPath()));
                            permission.setUrl(PermissionUtils.getAbsoluteContextPathOfAPI(contextPath, apiVersion,
                                                                                           permission.getUrl()).toLowerCase());
                            permission.setMethod(permission.getMethod().toUpperCase());
                            PermissionManagerServiceImpl.getInstance().addPermission(permission);
                        }
					}
				}
			} catch (JAXBException e) {
                log.error(
                        "Exception occurred while parsing the permission configuration of webapp : "
                        + context.getServletContext().getContextPath(), e);
            } catch (PermissionManagementException e) {
                log.error("Exception occurred while adding the permissions from webapp : "
                          + servletContext.getContextPath(), e);
            }

        }
	}

}
