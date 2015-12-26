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
        name = "cSangApi",
        version = "v1",
        resource = "cSang",
        namespace = @ApiNamespace(
                ownerDomain = "backend.dondesang.suzanne.betrisey.com",
                ownerName = "backend.dondesang.suzanne.betrisey.com",
                packagePath = ""
        )
)
public class CSangEndpoint {

    private static final Logger logger = Logger.getLogger(CSangEndpoint.class.getName());

    /**
     * This method gets the <code>CSang</code> object associated with the specified <code>id</code>.
     *
     * @param id The id of the object to be returned.
     * @return The <code>CSang</code> associated with <code>id</code>.
     */
    @ApiMethod(name = "getCSang")
    public CSang getCSang(@Named("id") Long id) {
        // TODO: Implement this function
        logger.info("Calling getCSang method");
        return null;
    }

    /**
     * This inserts a new <code>CSang</code> object.
     *
     * @param cSang The object to be added.
     * @return The object to be added.
     */
    @ApiMethod(name = "insertCSang")
    public CSang insertCSang(CSang cSang) {
        // TODO: Implement this function
        logger.info("Calling insertCSang method");
        return cSang;
    }
}