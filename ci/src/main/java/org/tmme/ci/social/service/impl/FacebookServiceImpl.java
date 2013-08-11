package org.tmme.ci.social.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.LikeOperations;
import org.springframework.social.facebook.api.Page;
import org.tmme.ci.social.service.CheckinService;
import org.tmme.ci.social.service.LikeService;

public class FacebookServiceImpl implements LikeService, CheckinService {

	@Override
	public List<String> getCheckins(final String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getLikes(final String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	private List<String> getLikes(final Connection<Facebook> connection) {
		if (connection == null) {
			return Collections.<String> emptyList();
		}
		final Facebook facebook = connection.getApi();
		final LikeOperations likeOperations = facebook.likeOperations();
		final List<String> likes = new ArrayList<String>();
		final List<Page> pages = likeOperations.getPagesLiked();
		for (final Page page : pages) {
			likes.add(page.getName());
		}
		return likes;
	}

	private List<String> getCheckins(final Connection<Facebook> connection) {
		if (connection == null) {
			return Collections.<String> emptyList();
		}
		final Facebook facebook = connection.getApi();
		return null;
	}

}
