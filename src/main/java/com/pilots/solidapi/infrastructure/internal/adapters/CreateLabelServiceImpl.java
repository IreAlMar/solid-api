package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.label.CreateLabelService;
import com.pilots.solidapi.domain.Label;
import com.pilots.solidapi.infrastructure.internal.data.LabelRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class CreateLabelServiceImpl implements CreateLabelService {

    private final LabelRepository labelRepository;
    private static final Logger log = LoggerFactory.getLogger(SaveItemServiceImpl.class);

    public CreateLabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public boolean createLabel(Label label) {
        if (isValidLabel(label)) {
            labelRepository.save(label);
            labelRepository.findByName(label.getName()).forEach(i->{
                log.info(i.toString());
            });
            return true;
        }
        return false;
    }

    private boolean isValidLabel(Label label) {
        return (label.getName() != "") && (label.getPrice() > 0) && (label.getItemDescription() != "");
    }
}
