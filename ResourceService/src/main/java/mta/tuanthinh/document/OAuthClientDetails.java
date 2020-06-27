package mta.tuanthinh.document;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "oauth_client_details")
public class OAuthClientDetails {
	
	@Id
    private String id;
	
	@Field(name = "username")
	private String username;

	@Field(name = "client_name")
    private String clientName;

	@Field(name = "client_secret")
    private String clientSecret;

	@Field(name = "authorized_grant_types")
    private String grantTypes;

	@Field(name = "scopes")
    private List<Scope> scopes;

	@Field(name = "resource_ids")
    private String resources;

	@Field(name = "web_server_redirect_uri")
    private String redirectUris;

	@Field(name = "access_token_validity")
    private Integer accessTokenValidity;

	@Field(name = "refresh_token_validity")
    private Integer refreshTokenValidity;

	@Field(name = "additional_information")
    private String additionalInformation;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public String getGrantTypes() {
		return grantTypes;
	}

	public void setGrantTypes(String grantTypes) {
		this.grantTypes = grantTypes;
	}

	public List<Scope> getScopes() {
		return scopes;
	}

	public void setScopes(List<Scope> scopes) {
		this.scopes = scopes;
	}

	public String getResources() {
		return resources;
	}

	public void setResources(String resources) {
		this.resources = resources;
	}

	public String getRedirectUris() {
		return redirectUris;
	}

	public void setRedirectUris(String redirectUris) {
		this.redirectUris = redirectUris;
	}

	public Integer getAccessTokenValidity() {
		return accessTokenValidity;
	}

	public void setAccessTokenValidity(Integer accessTokenValidity) {
		this.accessTokenValidity = accessTokenValidity;
	}

	public Integer getRefreshTokenValidity() {
		return refreshTokenValidity;
	}

	public void setRefreshTokenValidity(Integer refreshTokenValidity) {
		this.refreshTokenValidity = refreshTokenValidity;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	

}
