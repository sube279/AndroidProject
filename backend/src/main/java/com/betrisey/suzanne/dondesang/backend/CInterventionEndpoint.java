package com.betrisey.suzanne.dondesang.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import java.util.logging.Logger;

import javax.inject.Named;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "cInterventionApi",
        version = "v1",
        resource = "cIntervention",
        namespace = @ApiNamespace(
                ownerDomain = "backend.dondesang.suzanne.betrisey.com",
                ownerName = "backend.dondesang.suzanne.betrisey.com",
                packagePath = ""
        )
)
public class CInterventionEndpoint {

    private static final Logger logger = Logger.getLogger(CInterventionEndpoint.class.getName());

    /**
     * This method gets the <code>CIntervention</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>CIntervention</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getCIntervention")
    public CIntervention getCIntervention(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getCIntervention method");
        return null;
    }

    /**
     * This inserts a new <code>CIntervention</code> object.
     *
     * @param cIntervention The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertCIntervention")
    public CIntervention insertCIntervention(CIntervention cIntervention) {
        // TODO: Implement this function
        logger.info("Calling insertCIntervention method");
        return cIntervention;
    }
}