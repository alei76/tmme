package org.tmme.ci.analytics.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.Closure;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.Validate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmme.ci.analytics.models.AcceptRecommendation;
import org.tmme.ci.analytics.models.Purchase;
import org.tmme.ci.analytics.models.RejectRecommendation;
import org.tmme.ci.analytics.models.Review;
import org.tmme.ci.analytics.models.Visit;
import org.tmme.ci.analytics.repository.AcceptRecommendationRepository;
import org.tmme.ci.analytics.repository.PurchaseRepository;
import org.tmme.ci.analytics.repository.RejectRecommendationRepository;
import org.tmme.ci.analytics.repository.ReviewRepository;
import org.tmme.ci.analytics.repository.VisitRepository;
import org.tmme.ci.analytics.service.AnalyticsService;
import org.tmme.ci.clients.CatalogClient;
import org.tmme.ci.clients.UserClient;
import org.tmme.ci.models.Item;
import org.tmme.ci.models.User;

public class AnalyticsServiceImpl implements AnalyticsService {

	private static final Logger LOG = LoggerFactory
			.getLogger(AnalyticsServiceImpl.class);

	private final ReviewRepository reviewRepository;
	private final VisitRepository visitRepository;
	private final PurchaseRepository purchaseRepository;
	private final AcceptRecommendationRepository acceptRecommendationRepository;
	private final RejectRecommendationRepository rejectRecommendationRepository;

	private final CatalogClient catalogClient;
	private final UserClient userClient;

	public AnalyticsServiceImpl(
			final ReviewRepository reviewRepository,
			final VisitRepository visitRepository,
			final PurchaseRepository purchaseRepository,
			final AcceptRecommendationRepository acceptRecommendationRepository,
			final RejectRecommendationRepository rejectRecommendationRepository,
			final CatalogClient catalogClient, final UserClient userClient) {
		Validate.notNull(reviewRepository);
		Validate.notNull(visitRepository);
		Validate.notNull(purchaseRepository);
		Validate.notNull(acceptRecommendationRepository);
		Validate.notNull(rejectRecommendationRepository);
		Validate.notNull(catalogClient);
		Validate.notNull(userClient);
		this.reviewRepository = reviewRepository;
		this.visitRepository = visitRepository;
		this.purchaseRepository = purchaseRepository;
		this.acceptRecommendationRepository = acceptRecommendationRepository;
		this.rejectRecommendationRepository = rejectRecommendationRepository;
		this.catalogClient = catalogClient;
		this.userClient = userClient;
	}

	@Override
	public void review(final String userId, final String typeName,
			final String itemId, final Review review) {
		new ActionPerformable(itemId, typeName, userId) {
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
		new ActionPerformable(itemId, typeName, userId) {
			@Override
			void action() {
				purchaseRepository.save(new Purchase(userId, itemId));
			}
		}.execute();
	}

	@Override
	public void visit(final String userId, final String typeName,
			final String itemId) {
		new ActionPerformable(itemId, typeName, userId) {
			@Override
			void action() {
				visitRepository.save(new Visit(userId, itemId));
			}
		}.execute();
	}

	@Override
	public void acceptRecommendation(final String userId,
			final String typeName, final String itemId) {
		new ActionPerformable(itemId, typeName, userId) {
			@Override
			void action() {
				acceptRecommendationRepository.save(new AcceptRecommendation(
						userId, itemId));
			}
		}.execute();
	}

	@Override
	public void rejectRecommendation(final String userId,
			final String typeName, final String itemId) {
		new ActionPerformable(itemId, typeName, userId) {
			@Override
			void action() {
				rejectRecommendationRepository.save(new RejectRecommendation(
						userId, itemId));
			}
		}.execute();

	}

	private abstract class ActionPerformable {

		private final String itemId;
		private final String email;
		private final String collectionName;

		ActionPerformable(final String itemId, final String collectionName,
				final String email) {
			this.itemId = itemId;
			this.collectionName = collectionName;
			this.email = email;
		}

		abstract void action();

		void execute() {
			final Item item = catalogClient.getItemById(itemId, collectionName);
			if (item == null) {
				LOG.error("Item {} does not exist in collection {}", itemId,
						collectionName);
				throw new IllegalArgumentException("Item does not exist");
			}
			final User user = userClient.findByEmail(email);
			if (user == null) {
				LOG.error("User {} does not exist", email);
				throw new IllegalArgumentException("User does not exist");
			}
			action();
		}
	}

	@Override
	public List<String> getRejects(final String userId) {
		final List<RejectRecommendation> rejections = rejectRecommendationRepository
				.findByUserId(userId);
		final List<String> itemIds = new ArrayList<String>();
		if (CollectionUtils.isNotEmpty(rejections)) {
			CollectionUtils.forAllDo(rejections, new Closure() {
				@Override
				public void execute(final Object rejectRecommendation) {
					itemIds.add(((RejectRecommendation) rejectRecommendation)
							.getItemId());
				}
			});
		}
		return itemIds;
	}

}
