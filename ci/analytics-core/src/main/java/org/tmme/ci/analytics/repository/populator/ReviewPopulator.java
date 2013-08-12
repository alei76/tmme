package org.tmme.ci.analytics.repository.populator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.tmme.ci.analytics.models.Review;
import org.tmme.ci.analytics.repository.ReviewRepository;

public class ReviewPopulator {

	private final ReviewRepository reviewRepository;

	private static final String SEPARATOR = ",";

	private final boolean enabled;
	private final String filename;

	public ReviewPopulator(final ReviewRepository reviewRepository,
			final boolean enabled, final String filename) {
		Validate.notNull(reviewRepository);
		Validate.notBlank(filename);
		this.reviewRepository = reviewRepository;
		this.enabled = enabled;
		this.filename = filename;
	}

	public void populate() throws NumberFormatException, IOException {
		if (enabled) {
			final List<Review> reviews = new ArrayList<Review>(100);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new InputStreamReader(getClass()
						.getResourceAsStream(filename)));
				String line;
				while ((line = br.readLine()) != null) {
					final String[] tokens = line.split(SEPARATOR);
					final Review review = new Review();
					review.setUserId(tokens[0]);
					review.setItemId(tokens[1]);
					review.setRate(Integer.valueOf(tokens[2]));
					reviews.add(review);
				}
			} finally {
				if (br != null) {
					br.close();
				}
			}
			reviewRepository.save(reviews);
		}
	}
}
