package org.hand.order.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hand.order.domain.entity.SoHeader;
import org.hand.order.domain.repository.SoHeaderRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author 33265
 */
@ImportService(templateCode = "ORDER_IMPORT_012")
public class OrderImportServiceImpl implements IDoImportService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SoHeaderRepository soHeaderRepository;

    @Override
    public Boolean doImport(String data) {
        SoHeader soHeader;
        try {
            soHeader = objectMapper.readValue(data, SoHeader.class);
        } catch (IOException e) {
            return false;
        }
        soHeaderRepository.insertSelective(soHeader);
        return true;
    }
}
