package org.tmme.ci.analytics.service.impl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmme.ci.analytics.repository.PurchaseRepository;
import org.tmme.ci.analytics.repository.ReviewRepository;
import org.tmme.ci.analytics.repository.VisitRepository;
import org.tmme.ci.analytics.service.AnalyticsService;
import org.tmme.ci.catalog.repository.CatalogRepository;
import org.tmme.ci.id.repository.UserRepository;
import org.tmme.ci.model.Item;
import org.tmme.ci.model.Purchase;
import org.tmme.ci.model.Review;
import org.tmme.ci.model.User;
import org.tmme.ci.model.Visit;

@Service
public class AnalyticsServiceImpl implements AnalyticsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(AnalyticsServiceImpl.class);

	@Autowired
	private ReviewRepository reviewRepository;
	@Autowired
	private VisitRepository visitRepository;
	@Autowired
	private PurchaseRepository purchaseRepository;
	@Autowired
	private CatalogRepository catalogRepository;
	@Autowired
	private UserRepository userRepository;

	@Override
	public void review(final String username, final String typeName,
			final String itemId, final Review review) {
		new ActionPerformable(itemId, typeName, username) {
			@Override
			void action() {
				review.setItemId(itemId);
				review.setUserId(username);
				review.setCreatedAt(new Date());
				reviewRepository.save(review);
			}
		}.execute();
	}

	@Override
	public void purchase(final String username, final String typeName,
			final String itemId) {
		new ActionPerformable(itemId, typeName, username) {
			@Override
			void action() {
				purchaseRepository.save(new Purchase(username, itemId));
			}
		}.execute();
	}

	@Override
	public void visit(final String username, final String typeName,
			final String itemId) {
		new ActionPerformable(itemId, typeName, username) {
			@Override
			void action() {
				visitRepository.save(new Visit(username, itemId));
			}
		}.execute();
	}

	private abstract class ActionPerformable {

		private final String itemId;
		private final String username;
		private final String collectionName;

		ActionPerformable(final String itemId, final String collectionName,
				final String username) {
			this.itemId = itemId;
			this.collectionName = collectionName;
			this.username = username;
		}

		abstract void action();

		void execute() {
			final Item item = catalogRepository
					.findById(itemId, collectionName);
			if (item == null) {
				LOG.error("Item {} does not exist in collection {}", itemId,
						collectionName);
				throw new IllegalArgumentException("Item does not exist");
			}
			final User user = userRepository.findByUsername(username);
			if (user == null) {
				LOG.error("User {} does not exist", username);
				throw new IllegalArgumentException("User does not exist");
			}
			action();
		}
	}

}
