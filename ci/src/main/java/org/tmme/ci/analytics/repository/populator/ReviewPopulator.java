package org.tmme.ci.analytics.repository.populator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.apache.hadoop.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.tmme.ci.analytics.repository.ReviewRepository;
import org.tmme.ci.model.Review;

public class ReviewPopulator {

	@Autowired
	private ReviewRepository reviewRepository;

	private final boolean enabled;
	private final String filename;

	public ReviewPopulator(final boolean enabled, final String filename) {
		Validate.notBlank(filename);
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
					final String[] tokens = line.split(StringUtils.COMMA_STR);
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
