package org.tmme.ci.catalog.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.tmme.ci.catalog.service.CatalogService;
import org.tmme.ci.models.Item;

@Controller
public class CatalogController {

	private static final Logger LOG = LoggerFactory
			.getLogger(CatalogController.class);

	@Autowired
	private CatalogService catalogService;

	@RequestMapping(value = "/itemtypes/{type}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void createItemType(@PathVariable(value = "type") final String typeName) {
		catalogService.createItemType(typeName);
	}

	@RequestMapping(value = "/item/{type}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void createItem(@PathVariable(value = "type") final String typeName,
			final HttpServletRequest request) throws IOException {
		final String body = IOUtils.toString(request.getInputStream());
		catalogService.createItem(typeName, body);
	}

	@RequestMapping(value = "/items/{type}", method = RequestMethod.POST)
	@ResponseStatus(value = HttpStatus.CREATED)
	public @ResponseBody
	void createItems(@PathVariable(value = "type") final String typeName,
			final HttpServletRequest request) throws IOException {
		final String body = IOUtils.toString(request.getInputStream());
		catalogService.createItems(typeName, body);
	}

	@RequestMapping(value = "/itemtypes", method = RequestMethod.GET)
	public @ResponseBody
	Set<String> getItemTypes() {
		return catalogService.getItemTypes();
	}

	@RequestMapping(value = "/item/{type}.{itemid}", method = RequestMethod.GET)
	public @ResponseBody
	Item getItem(@PathVariable(value = "type") final String typeName,
			@PathVariable(value = "itemid") final String itemId) {
		return catalogService.getItem(typeName, itemId);
	}

	@RequestMapping(value = "/items/{type}", method = RequestMethod.GET)
	public @ResponseBody
	List<Item> getItems(@PathVariable(value = "type") final String typeName) {
		return catalogService.getItems(typeName);
	}

	@RequestMapping(value = "/items/{type}", method = RequestMethod.GET, params = "itemids")
	public @ResponseBody
	List<Item> getItem(
			@PathVariable(value = "type") final String typeName,
			@RequestParam(value = "itemids", required = true) final List<String> itemIds) {
		return catalogService.getItems(typeName, itemIds);
	}

	@RequestMapping(value = "/items", method = RequestMethod.GET)
	public @ResponseBody
	Map<String, List<Item>> getAllItems() {
		return catalogService.getItems();
	}

	@ExceptionHandler({ IllegalArgumentException.class, IOException.class,
			NullPointerException.class })
	@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Bad Request")
	public void handleException(final Exception ex,
			final HttpServletResponse response) {
		LOG.error("Handling exception {}", ex.getMessage());
	}

}
