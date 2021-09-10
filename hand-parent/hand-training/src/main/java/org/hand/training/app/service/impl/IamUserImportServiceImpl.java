package org.hand.training.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hand.training.domain.entity.IamUser;
import org.hand.training.domain.repository.IamUserRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@ImportService(templateCode = "HAND_DEMO_012")
public class IamUserImportServiceImpl implements IDoImportService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private IamUserRepository iamUserRepository;

    @Override
    public Boolean doImport(String data) {
        IamUser iamUser;
        try {
            iamUser = objectMapper.readValue(data, IamUser.class);
        } catch (IOException e) {
            return false;
        }
        iamUserRepository.insertSelective(iamUser);
        return true;
    }
}
