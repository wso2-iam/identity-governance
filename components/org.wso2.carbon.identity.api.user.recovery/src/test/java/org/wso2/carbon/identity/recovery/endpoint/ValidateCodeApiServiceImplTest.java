/*
 *
 *  Copyright (c) 2017, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package org.wso2.carbon.identity.recovery.endpoint;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.testng.IObjectFactory;
import org.testng.annotations.ObjectFactory;
import org.testng.annotations.Test;
import org.wso2.carbon.identity.recovery.endpoint.Utils.RecoveryUtil;
import org.wso2.carbon.identity.recovery.endpoint.dto.CodeValidationRequestDTO;
import org.wso2.carbon.identity.recovery.endpoint.dto.PropertyDTO;
import org.wso2.carbon.identity.recovery.endpoint.impl.ValidateCodeApiServiceImpl;
import org.wso2.carbon.identity.recovery.password.NotificationPasswordRecoveryManager;

import java.util.ArrayList;
import java.util.List;

import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;
import static org.testng.Assert.*;

/**
 * This class covers unit tests for ValidateCodeApiServiceImpl.java
 */
@PrepareForTest({RecoveryUtil.class})
public class ValidateCodeApiServiceImplTest {

    @Mock
    NotificationPasswordRecoveryManager notificationPasswordRecoveryManager;

    @InjectMocks
    ValidateCodeApiServiceImpl validateCodeApiService;

    @Test
    public void testValidateCodePost() throws Exception {
        mockStatic(RecoveryUtil.class);
        when(RecoveryUtil.getNotificationBasedPwdRecoveryManager()).thenReturn(notificationPasswordRecoveryManager);
        assertEquals(validateCodeApiService.validateCodePost(buildCodeValidationRequestDTO()).getStatus(), 202);
    }

    private CodeValidationRequestDTO buildCodeValidationRequestDTO() {

        CodeValidationRequestDTO codeValidationRequestDTO = new CodeValidationRequestDTO();
        codeValidationRequestDTO.setCode("DummyCode");
        codeValidationRequestDTO.setStep("DummyStep");
        codeValidationRequestDTO.setProperties(buildPropertyListDTO());
        return codeValidationRequestDTO;
    }

    private List<PropertyDTO> buildPropertyListDTO() {

        PropertyDTO propertyDTO = new PropertyDTO();
        propertyDTO.setKey("DummyPropertyKey");
        propertyDTO.setValue("Dummy property value");
        List<PropertyDTO> propertyDTOList = new ArrayList<>();
        propertyDTOList.add(propertyDTO);
        return propertyDTOList;
    }

    @ObjectFactory
    public IObjectFactory getObjectFactory() {

        return new org.powermock.modules.testng.PowerMockObjectFactory();
    }
}