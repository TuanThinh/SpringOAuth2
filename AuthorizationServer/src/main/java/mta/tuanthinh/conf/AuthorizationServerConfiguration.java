package mta.tuanthinh.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;

import mta.tuanthinh.lib.MongoTokenStore;
import mta.tuanthinh.service.OAuthClientDetailsService;

@Configuration
public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer{

	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private OAuthClientDetailsService oauthClientDetailsService;
	
	
//	@Bean
//    TokenStore jdbcTokenStore() {
//        return new JdbcTokenStore(dataSource);
//    }
//	
	@Bean
    public TokenStore tokenStore() {
        return new MongoTokenStore();
    }

	@Override
	public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
		security
			.checkTokenAccess("isAuthenticated()")
//			.tokenKeyAccess("hasAuthority('ROLE_ADMIN')")
			.tokenKeyAccess("permitAll()")
			.passwordEncoder(passwordEncoder)
			.allowFormAuthenticationForClients();
	}

	@Override
	public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
		clients.withClientDetails(oauthClientDetailsService);
	}

	@Override
	public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
		endpoints
			.tokenStore(tokenStore())
			.authenticationManager(authenticationManager)
			.userDetailsService(userDetailsService);
	}

}
