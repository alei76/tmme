package org.tmme.ci.analytics.repository.populator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.analytics.models.Review;
import org.tmme.ci.analytics.repository.ReviewRepository;

public class ReviewPopulator {

	private static final Logger LOG = LoggerFactory
			.getLogger(ReviewPopulator.class);

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
				br = new BufferedReader(new FileReader(new File(filename)));
				String line;
				while ((line = br.readLine()) != null) {
					final String[] tokens = line.split(SEPARATOR);
					final Review review = new Review();
					review.setUserId(tokens[0]);
					review.setItemId(tokens[1]);
					review.setRate(Double.valueOf(tokens[2]));
					reviews.add(review);
				}
				reviewRepository.save(reviews);
			} catch (final Exception exc) {
				LOG.error("Could not import reviews {}", exc.getMessage());
			} finally {
				if (br != null) {
					br.close();
				}
			}
		}
	}
}
