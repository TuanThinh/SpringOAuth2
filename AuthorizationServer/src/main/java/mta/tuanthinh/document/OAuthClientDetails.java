package mta.tuanthinh.document;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.ClientDetails;

@Document(collection = "oauth_client_details")
public class OAuthClientDetails implements ClientDetails{
	private static final long serialVersionUID = 1L;

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

	@Override
	public String getClientId() {
		return id;
	}

	@Override
	public Set<String> getResourceIds() {
		return resources != null ? new HashSet<>(Arrays.asList(resources.split(","))) : null;
	}

	@Override
	public boolean isSecretRequired() {
		return true;
	}

	@Override
	public String getClientSecret() {
		return clientSecret;
	}

	@Override
	public boolean isScoped() {
		return false;
	}

	@Override
	public Set<String> getScope() {
		if(scopes != null) {
			Set<String> setScopes = new HashSet<>();
			scopes.forEach(scope -> {
				setScopes.add(scope.getName());
			});
			return setScopes;
		}
		return null;
	}

	@Override
	public Set<String> getAuthorizedGrantTypes() {
		return grantTypes != null ? new HashSet<>(Arrays.asList(grantTypes.split(","))) : null;
	}

	@Override
	public Set<String> getRegisteredRedirectUri() {
		return redirectUris != null ? new HashSet<>(Arrays.asList(redirectUris.split(","))) : null;
	}

	@Override
	public Collection<GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public Integer getAccessTokenValiditySeconds() {
		return accessTokenValidity;
	}

	@Override
	public Integer getRefreshTokenValiditySeconds() {
		return refreshTokenValidity;
	}

	@Override
	public boolean isAutoApprove(String scope) {
		if(id.equals("5e4c0153d066b209d86f50b5")) {
			return true;
		}
		return false;
	}

	@Override
	public Map<String, Object> getAdditionalInformation() {
		return null;
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

	public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
		return clientName;
	}

	public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public void setGrantTypes(String grantTypes) {
        this.grantTypes = grantTypes;
    }

    public void setScopes(List<Scope> scopes) {
        this.scopes = scopes;
    }

    public void setResources(String resources) {
        this.resources = resources;
    }

    public void setRedirectUris(String redirectUris) {
        this.redirectUris = redirectUris;
    }

    public void setAccessTokenValidity(Integer accessTokenValidity) {
        this.accessTokenValidity = accessTokenValidity;
    }

    public void setRefreshTokenValidity(Integer refreshTokenValidity) {
        this.refreshTokenValidity = refreshTokenValidity;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

}
