package com.betrisey.suzanne.dondesang.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p/>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "cDonneurApi",
        version = "v1",
        resource = "cDonneur",
        namespace = @ApiNamespace(
                ownerDomain = "backend.dondesang.suzanne.betrisey.com",
                ownerName = "backend.dondesang.suzanne.betrisey.com",
                packagePath = ""
        )
)
public class CDonneurEndpoint {

    private static final Logger logger = Logger.getLogger(CDonneurEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(CDonneur.class);
    }

    /**
     * Returns the {@link CDonneur} with the corresponding ID.
     *
     * @param Id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code CDonneur} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "cDonneur/{Id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CDonneur get(@Named("Id") Long Id) throws NotFoundException {
        logger.info("Getting CDonneur with ID: " + Id);
        CDonneur cDonneur = ofy().load().type(CDonneur.class).id(Id).now();
        if (cDonneur == null) {
            throw new NotFoundException("Could not find CDonneur with ID: " + Id);
        }
        return cDonneur;
    }

    /**
     * Inserts a new {@code CDonneur}.
     */
    @ApiMethod(
            name = "insert",
            path = "cDonneur",
            httpMethod = ApiMethod.HttpMethod.POST)
    public CDonneur insert(CDonneur cDonneur) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that cDonneur.Id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(cDonneur).now();
        logger.info("Created CDonneur with ID: " + cDonneur.getId());

        return ofy().load().entity(cDonneur).now();
    }

    /**
     * Updates an existing {@code CDonneur}.
     *
     * @param Id       the ID of the entity to be updated
     * @param cDonneur the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code CDonneur}
     */
    @ApiMethod(
            name = "update",
            path = "cDonneur/{Id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public CDonneur update(@Named("Id") Long Id, CDonneur cDonneur) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(Id);
        ofy().save().entity(cDonneur).now();
        logger.info("Updated CDonneur: " + cDonneur);
        return ofy().load().entity(cDonneur).now();
    }

    /**
     * Deletes the specified {@code CDonneur}.
     *
     * @param Id the ID of the entity to delete
     * @throws NotFoundException if the {@code Id} does not correspond to an existing
     *                           {@code CDonneur}
     */
    @ApiMethod(
            name = "remove",
            path = "cDonneur/{Id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("Id") Long Id) throws NotFoundException {
        checkExists(Id);
        ofy().delete().type(CDonneur.class).id(Id).now();
        logger.info("Deleted CDonneur with ID: " + Id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "cDonneur",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<CDonneur> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<CDonneur> query = ofy().load().type(CDonneur.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<CDonneur> queryIterator = query.iterator();
        List<CDonneur> cDonneurList = new ArrayList<CDonneur>(limit);
        while (queryIterator.hasNext()) {
            cDonneurList.add(queryIterator.next());
        }
        return CollectionResponse.<CDonneur>builder().setItems(cDonneurList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(Long Id) throws NotFoundException {
        try {
            ofy().load().type(CDonneur.class).id(Id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find CDonneur with ID: " + Id);
        }
    }
}