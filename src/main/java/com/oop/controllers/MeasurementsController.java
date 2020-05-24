package com.oop.controllers;

import com.oop.dao.ISensorLocationDao;
import com.oop.entities.CoMeasurement;
import com.oop.entities.PmMeasurement;
import com.oop.exceptions.SensorLocationIdDoesNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.oop.dao.ICoMeasurementDao;
import com.oop.dao.IPmMeasurementDao;

@RestController
public class MeasurementsController {

    @Autowired
    IPmMeasurementDao pmService;

    @Autowired
    ISensorLocationDao slService;

    @RequestMapping(value = "/pm", method = RequestMethod.POST, produces = "application/json")
    public String pmMeasurement(@RequestBody PmMeasurement pm) throws Exception, SensorLocationIdDoesNotExistException {
        long id = pm.getSensorsLocationsId();
        if (slService.findById(id) == null) {
            throw new SensorLocationIdDoesNotExistException(id);
        } else {
            pmService.saveAndFlush(pm);
            return pm + " measurement saved!";
        }
    }

    @Autowired
    ICoMeasurementDao coService;

    @RequestMapping(value = "/co", method = RequestMethod.POST, produces = "application/json")
    public String coMeasurement(@RequestBody CoMeasurement co) {
        long id = co.getSensorsLocationsId();
        if (slService.findById(id) == null) {
            throw new SensorLocationIdDoesNotExistException(id);
        } else {
            coService.saveAndFlush(co);
            return co + " measurement saved!";
        }
    }

}
