package com.pilots.solidapi.infrastructure.internal.adapters;

import com.pilots.solidapi.application.label.GetItemLabelService;
import com.pilots.solidapi.domain.Label;
import com.pilots.solidapi.infrastructure.internal.data.LabelRepository;
import org.springframework.stereotype.Service;

@Service
public class GetItemLabelServiceImpl implements GetItemLabelService {
    private final LabelRepository labelRepository;

    public GetItemLabelServiceImpl(LabelRepository labelRepository) {
        this.labelRepository = labelRepository;
    }

    @Override
    public Label getItemLabel(long id) {
        return labelRepository.findById(id);
    }

    @Override
    public Label getItemLabel(String itemName) {
        return labelRepository.findByName(itemName).get(0);
    }
}
