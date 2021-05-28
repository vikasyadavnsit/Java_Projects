package org.api.controller.authentication.oauth;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class OauthAuthenticationRedirectionUtil {

	OauthAuthenticationRedirectionUtil(Environment env, ServletContext ctx) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http")
				.host(InetAddress.getLoopbackAddress().getHostAddress()).port(env.getProperty("server.port"))
				.path(ctx.getContextPath()).build();
	}

	public ModelAndView linkedinRedirectionURL() throws ClientProtocolException, IOException, URISyntaxException {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside linkedinRedirectionURL() **** ");
		}

		URIBuilder builder = null;
		try {
			builder = new URIBuilder("https://www.linkedin.com/oauth/v2/authorization");
			builder.setParameter("client_id", "81moxezknrvqpq");
			builder.setParameter("scope", "r_basicprofile");
			builder.setParameter("redirect_uri", "http://localhost:8080/soap/linkedinCallbackURL");
			builder.setParameter("state", "test123");
			builder.setParameter("response_type", "code");
		} catch (URISyntaxException e1) {
			log.error("Error inside linkedinRedirectionURL() :", e1);
		}
		return new ModelAndView("redirect:" + builder.build());
	}

	public ModelAndView facebookRedirectionURL() throws ClientProtocolException, IOException, URISyntaxException {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside facebookRedirectionURL() **** ");
		}

		URIBuilder builder = null;
		try {
			builder = new URIBuilder("https://www.facebook.com/v3.2/dialog/oauth");
			builder.setParameter("client_id", "254725772086596");
			builder.setParameter("scope", "email");
			builder.setParameter("redirect_uri", "http://localhost:8080/soap/facebookCallbackURL");
			builder.setParameter("state", "test123");
		} catch (URISyntaxException e1) {
			log.error("Error inside facebookRedirectionURL() :", e1);
		}
		return new ModelAndView("redirect:" + builder.build());
	}

	public ModelAndView googleRedirectionURL() throws ClientProtocolException, IOException, URISyntaxException {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside googleRedirectionURL() **** ");
		}

		URIBuilder builder = null;
		try {
			builder = new URIBuilder("https://accounts.google.com/o/oauth2/v2/auth");
			builder.setParameter("client_id",
					"996191516731-si2nt7ko6v2s4ok8o2krpqec79c904kq.apps.googleusercontent.com");
			builder.setParameter("scope", "profile email");
			builder.setParameter("access_type", "offline");
			builder.setParameter("include_granted_scopes", "true");
			builder.setParameter("redirect_uri", "http://localhost:8080/soap/googleCallbackURL");
			builder.setParameter("state", "state_parameter_passthrough_value");
			builder.setParameter("response_type", "code");
		} catch (URISyntaxException e1) {
			log.error("Error inside googleRedirectionURL() :", e1);
		}
		return new ModelAndView("redirect:" + builder.build());
	}

	public ModelAndView githubRedirectionURL() throws ClientProtocolException, IOException, URISyntaxException {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside githubRedirectionURL() **** ");
		}

		URIBuilder builder = null;
		try {
			builder = new URIBuilder("https://github.com/login/oauth/authorize");
			builder.setParameter("client_id", "01b25ccb4a21e290157b");
			builder.setParameter("allow_signup", "false");
			builder.setParameter("state", "gf2KwWtfGs");
			builder.setParameter("redirect_uri", "http://127.0.0.1:8080/soap/callbackURL");
			builder.setParameter("scope", "user public_repo");
			builder.setParameter("login", "");
		} catch (URISyntaxException e1) {
			log.error("Error inside githubRedirectionURL() :", e1);
		}
		return new ModelAndView("redirect:" + builder.build());
	}

}
