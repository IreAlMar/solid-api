package com.pilots.solidapi.application;

import com.pilots.solidapi.domain.Label;

public interface GetItemLabelService {
    Label getItemLabel(long id);

    Label getItemLabel(String itemName);
}
