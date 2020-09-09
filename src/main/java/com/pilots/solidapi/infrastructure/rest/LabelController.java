package com.pilots.solidapi.infrastructure.rest;


import com.pilots.solidapi.application.CreateLabelService;
import com.pilots.solidapi.application.GetItemLabelService;
import com.pilots.solidapi.domain.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class LabelController {

    @Autowired
    private GetItemLabelService getItemLabelService;

    @Autowired
    private CreateLabelService createLabelService;

    @GetMapping(value = "/getItemLabel", params = "itemName")
    public Label getLabelByItemName(@RequestParam(value = "itemName", defaultValue = "empty") String itemName) {
        return getItemLabelService.getItemLabel(itemName);
    }

    @GetMapping(value = "/getItemLabel", params = "id")
    public Label getLabelByLabelId(@RequestParam(value = "id", defaultValue = "empty") long id) {
        return getItemLabelService.getItemLabel(id);
    }

    @RequestMapping(value = "/createLabel", method = RequestMethod.POST)
    public ResponseEntity<String> createLabel(@RequestBody Label label) {
        if (createLabelService.createLabel(label)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }
        return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
    }

}
