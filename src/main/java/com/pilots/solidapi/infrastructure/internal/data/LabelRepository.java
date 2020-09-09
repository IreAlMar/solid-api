package com.pilots.solidapi.infrastructure.internal.data;

import com.pilots.solidapi.domain.Label;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface LabelRepository extends CrudRepository<Label, Long> {

    List<Label> findByName(String name);

    Label findById(long id);
}
