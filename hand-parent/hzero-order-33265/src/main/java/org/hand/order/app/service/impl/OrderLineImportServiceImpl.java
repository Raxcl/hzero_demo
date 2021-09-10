package org.hand.order.app.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.hand.order.domain.entity.SoLine;
import org.hand.order.domain.repository.SoLineRepository;
import org.hzero.boot.imported.app.service.IDoImportService;
import org.hzero.boot.imported.infra.validator.annotation.ImportService;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @author 33265
 */
@ImportService(templateCode = "ORDER_IMPORT_013")
public class OrderLineImportServiceImpl implements IDoImportService {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private SoLineRepository soLineRepository;

    @Override
    public Boolean doImport(String data) {
        SoLine soLine;
        try {
            soLine = objectMapper.readValue(data, SoLine.class);
        } catch (IOException e) {
            return false;
        }
        soLineRepository.insertSelective(soLine);
        return true;
    }
}
