package org.tmme.catalog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.catalog.model.Item;
import org.tmme.catalog.model.ItemType;
import org.tmme.catalog.service.CatalogService;

@Controller
public class CatalogController {

    private static final Logger LOG = LoggerFactory.getLogger(CatalogController.class);

    @Autowired
    private CatalogService catalogService;

    @RequestMapping(value = "/{itemtype}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    void createItemType(@PathVariable(value = "itemtype") final String typeName) {
        catalogService.createItemType(typeName);
    }

    @RequestMapping(value = "/{itemtype}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    void createItem(@PathVariable(value = "itemtype") final String typeName, final HttpServletRequest request)
            throws IOException {
        final String body = IOUtils.toString(request.getInputStream());
        catalogService.createItem(typeName, body);
    }

    @RequestMapping(value = "/bulk/{itemtype}", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public @ResponseBody
    void createItems(@PathVariable(value = "itemtype") final String typeName, final HttpServletRequest request)
            throws IOException {
        final String body = IOUtils.toString(request.getInputStream());
        catalogService.createItems(typeName, body);
    }

    @RequestMapping(value = "/itemtypes", method = RequestMethod.GET)
    public @ResponseBody
    List<ItemType> getItemTypes() {
        return catalogService.getItemTypes();
    }

    @RequestMapping(value = "/{itemtype}", method = RequestMethod.GET)
    public @ResponseBody
    List<Item> getItems(@PathVariable(value = "itemtype") final String typeName) {
        return catalogService.getItems(typeName);
    }

    @ExceptionHandler({ IllegalArgumentException.class, IOException.class, NullPointerException.class })
    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
    public void handleException(final Exception ex, final HttpServletResponse response) {
        LOG.error("Handling exception {}", ex.getMessage());
    }

}
