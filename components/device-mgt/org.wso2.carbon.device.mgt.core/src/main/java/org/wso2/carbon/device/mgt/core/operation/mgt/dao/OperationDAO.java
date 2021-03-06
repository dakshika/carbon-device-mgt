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
package org.wso2.carbon.device.mgt.core.operation.mgt.dao;

import org.wso2.carbon.device.mgt.core.dto.operation.mgt.Operation;

import java.util.List;

public interface OperationDAO {

    int addOperation(Operation operation) throws OperationManagementDAOException;

    void updateOperation(Operation operation) throws OperationManagementDAOException;

    void deleteOperation(int operationId) throws OperationManagementDAOException;

    Operation getOperation(int operationId) throws OperationManagementDAOException;

    Operation getOperationByDeviceAndId(int enrolmentId, int operationId) throws OperationManagementDAOException;

    List<? extends Operation> getOperationsByDeviceAndStatus(int enrolmentId, Operation.Status status)
            throws OperationManagementDAOException;

    List<? extends Operation> getOperationsForDevice(int enrolmentId) throws OperationManagementDAOException;

    Operation getNextOperation(int enrolmentId) throws OperationManagementDAOException;

    void updateOperationStatus(int enrolmentId, int operationId,Operation.Status status)
            throws OperationManagementDAOException;

    void addOperationResponse(int enrolmentId, int operationId, Object operationResponse)
            throws OperationManagementDAOException;

}
