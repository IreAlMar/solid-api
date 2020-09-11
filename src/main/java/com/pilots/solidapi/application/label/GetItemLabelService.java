package com.pilots.solidapi.application.label;

import com.pilots.solidapi.domain.Label;

public interface GetItemLabelService {
    Label getItemLabel(long id);

    Label getItemLabel(String itemName);
}
