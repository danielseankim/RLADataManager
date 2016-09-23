package com.vmware.q3team7.util;

import com.vmware.q3team7.exceptions.RLADataFormatException;
import com.vmware.q3team7.models.RLAData;

/**
 * @author kdaniel
 *
 */
public class RLAValidator {
    public static void simpleDataValidation (RLAData data) {
        // basic data validation
        if (data.getId() == null || data.getId().isEmpty()) {
            throw new RLADataFormatException("The request to create a new data is missing the data id.");
        }
        if (data.getName() == null || data.getName().isEmpty()) {
            throw new RLADataFormatException("The request to create a new data is missing the data name.");
        }
        if (data.getTag() == null || data.getTag().isEmpty()) {
            throw new RLADataFormatException("The request to create a new data is missing the data tag.");
        }
        if (data.getVersion() == null || data.getVersion().isEmpty()) {
            throw new RLADataFormatException("The request to create a new data is missing the data version.");
        }
        if (data.getContent() != null && !data.getContent().isEmpty()) {
            //TODO validate the data json
        }
    }
}
