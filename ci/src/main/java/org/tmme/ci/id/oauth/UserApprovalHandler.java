package org.tmme.ci.id.oauth;

import java.util.Collection;
import java.util.HashSet;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.AuthorizationRequest;
import org.springframework.security.oauth2.provider.approval.TokenServicesUserApprovalHandler;

public class UserApprovalHandler extends TokenServicesUserApprovalHandler {

	private Collection<String> autoApproveClients = new HashSet<String>();

	private boolean useTokenServices = true;

	public void setUseTokenServices(final boolean useTokenServices) {
		this.useTokenServices = useTokenServices;
	}

	public void setAutoApproveClients(
			final Collection<String> autoApproveClients) {
		this.autoApproveClients = autoApproveClients;
	}

	@Override
	public AuthorizationRequest updateBeforeApproval(
			final AuthorizationRequest authorizationRequest,
			final Authentication userAuthentication) {
		return super.updateBeforeApproval(authorizationRequest,
				userAuthentication);
	}

	@Override
	public boolean isApproved(final AuthorizationRequest authorizationRequest,
			final Authentication userAuthentication) {

		if (useTokenServices
				&& super.isApproved(authorizationRequest, userAuthentication)) {
			return true;
		}

		if (!userAuthentication.isAuthenticated()) {
			return false;
		}

		final String flag = authorizationRequest.getApprovalParameters().get(
				AuthorizationRequest.USER_OAUTH_APPROVAL);
		final boolean approved = flag != null
				&& flag.toLowerCase().equals("true");

		return approved
				|| authorizationRequest.getResponseTypes().contains("token")
				&& autoApproveClients.contains(authorizationRequest
						.getClientId());

	}

}