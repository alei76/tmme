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
import org.tmme.ci.model.Purchase;
import org.tmme.ci.model.Review;
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

	@Override
	public void review(final String userId, final String typeName,
			final String itemId, final Review review) {
		new ActionPerformable(itemId, typeName) {
			@Override
			void action() {
				review.setItemId(itemId);
				review.setUserId(userId);
				review.setCreatedAt(new Date());
				reviewRepository.save(review);
			}
		}.execute();
	}

	@Override
	public void purchase(final String userId, final String typeName,
			final String itemId) {
		new ActionPerformable(itemId, typeName) {
			@Override
			void action() {
				purchaseRepository.save(new Purchase(userId, itemId));
			}
		}.execute();
	}

	@Override
	public void visit(final String userId, final String typeName,
			final String itemId) {
		new ActionPerformable(itemId, typeName) {
			@Override
			void action() {
				visitRepository.save(new Visit(userId, itemId));
			}
		}.execute();
	}

	private abstract class ActionPerformable {

		private final String itemId;
		private final String collectionName;

		ActionPerformable(final String itemId, final String collectionName) {
			this.itemId = itemId;
			this.collectionName = collectionName;
		}

		abstract void action();

		void execute() {
			if (!catalogRepository.itemExists(itemId, collectionName)) {
				LOG.error("Item {} does not exist in collection {}", itemId,
						collectionName);
				throw new IllegalArgumentException("Invalid itemId");
			}
			action();
		}
	}

}
