package org.api.controller.authentication.oauth;

import java.net.InetAddress;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.apache.http.HttpStatus;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.api.dao.UserDAO;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@Getter
public class OauthAuthenticationCallbackURLHandler {

	@Autowired
	private UserDAO userDAO;

	// For Dynamic Behaviour Like Dynamic Server Address,Port,Context we can use
	// this method in place of hard code
	OauthAuthenticationCallbackURLHandler(Environment env, ServletContext ctx) {
		UriComponents uriComponents = UriComponentsBuilder.newInstance().scheme("http")
				.host(InetAddress.getLoopbackAddress().getHostAddress()).port(env.getProperty("server.port"))
				.path(ctx.getContextPath()).build();
		this.callBackURL = uriComponents.toUriString();
	}

	private String callBackURL;

	public void facebookURLHandler(HttpServletRequest request, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside facebookURLHandler() **** ");
		}
		try {
			String accessToken = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();

			URIBuilder builder2 = null;
			try {
				builder2 = new URIBuilder("https://graph.facebook.com/v3.2/oauth/access_token");
				builder2.setParameter("redirect_uri", "http://localhost:8080//facebookCallbackURL");
				builder2.setParameter("client_secret", "27fdab0230a0c534379e88b164007c11");
				builder2.setParameter("client_id", "254725772086596");
				builder2.setParameter("code", request.getParameter("code"));
			} catch (URISyntaxException e1) {
				log.error("Error inside facebookURLHandler() :", e1);
			}

			HttpGet httpPost = new HttpGet(builder2.build());
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			if (response2 != null && HttpStatus.SC_OK == response2.getStatusLine().getStatusCode()) {

				JSONObject responseJSON = new JSONObject(EntityUtils.toString(response2.getEntity()));

				if (responseJSON != null && responseJSON.get("access_token") != null) {
					accessToken = responseJSON.get("access_token").toString();
					response2.close();

					URIBuilder builder = null;
					try {
						builder = new URIBuilder("https://graph.facebook.com/me");
						builder.setParameter("fields", "name,email,picture,short_name,name_format");
						builder.setParameter("access_token", accessToken);

					} catch (URISyntaxException e1) {
						log.error("Error inside facebookURLHandler() :", e1);
					}

					HttpGet httpGet = new HttpGet(builder.build());
					CloseableHttpResponse response1 = httpclient.execute(httpGet);

					if (response1 != null && HttpStatus.SC_OK == response1.getStatusLine().getStatusCode()) {
						JSONObject result = new JSONObject(EntityUtils.toString(response1.getEntity()));

						if (result != null && result.get("name") != null) {
							String userName = result.getString("name").toString();
							model.addAttribute("name", userName);
							model.addAttribute("json", result.toString());

							String email = "";
							if (result.getString("email") != null) {
								email = result.getString("email");
							}
							userDAO.setUserData(userName, email, "FaceBook Login", result);
						}
						response1.close();

					}

				}

			}

		} catch (Exception e) {
			log.error("Error inside facebookURLHandler() :", e);
		}

	}

	public void githubURLHandler(HttpServletRequest request, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside githubURLHandler() **** ");
		}
		try {
			String accessToken = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost("https://github.com/login/oauth/access_token");
			List<BasicNameValuePair> postParameters = new ArrayList<>();
			postParameters.add(new BasicNameValuePair("client_id", "01b25ccb4a21e290157b"));
			postParameters.add(new BasicNameValuePair("client_secret", "051daf43ef188afbd98ffcde424d55f1cfaf0a35"));
			postParameters.add(new BasicNameValuePair("state", request.getParameter("state")));
			postParameters.add(new BasicNameValuePair("redirect_uri", "http://127.0.0.1:8080/soap/callbackURL"));
			postParameters.add(new BasicNameValuePair("code", request.getParameter("code")));

			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			httpPost.setHeader("Access-Control-Allow-Origin", "*");
			httpPost.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
			httpPost.setHeader("Access-Control-Max-Age", "151200");
			httpPost.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, authToken");
			CloseableHttpResponse response2 = httpclient.execute(httpPost);

			if (response2 != null && HttpStatus.SC_OK == response2.getStatusLine().getStatusCode()) {

				String responseJSON = EntityUtils.toString(response2.getEntity());

				if (responseJSON != null) {
					List<org.apache.http.NameValuePair> params = URLEncodedUtils.parse(responseJSON,
							Charset.forName("UTF-8"));
					for (org.apache.http.NameValuePair param : params) {
						if (param.getName().equalsIgnoreCase("access_token"))
							accessToken = param.getValue();
					}
					response2.close();
				}

				if (accessToken != null) {
					URIBuilder builder = null;
					try {
						builder = new URIBuilder("https://api.github.com/user?access_token");
						builder.setParameter("access_token", accessToken);

					} catch (URISyntaxException e1) {
						log.error("Error inside githubURLHandler() :", e1);
					}

					HttpGet httpGet = new HttpGet(builder.build());
					CloseableHttpResponse response1 = httpclient.execute(httpGet);

					if (response1 != null && HttpStatus.SC_OK == response1.getStatusLine().getStatusCode()) {
						JSONObject result = new JSONObject(EntityUtils.toString(response1.getEntity()));

						if (result != null && result.get("bio") != null) {
							String userName = result.getString("bio").toString();
							model.addAttribute("name", userName);
							model.addAttribute("json", result.toString());
							String email = "";
							if (result.getString("email") != null) {
								email = result.getString("email");
							}
							userDAO.setUserData(userName, email, "Github Login", result);
						}
						response1.close();

					}

				}

			}

		} catch (Exception e) {
			log.error("Error inside githubURLHandler() :", e);
		}

	}

	public void googleURLHandler(HttpServletRequest request, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside googleURLHandler() **** ");
		}

		try {
			String accessToken = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost("https://www.googleapis.com/oauth2/v4/token");
			List<BasicNameValuePair> postParameters = new ArrayList<>();
			postParameters.add(new BasicNameValuePair("client_id",
					"996191516731-si2nt7ko6v2s4ok8o2krpqec79c904kq.apps.googleusercontent.com"));
			postParameters.add(new BasicNameValuePair("client_secret", "aJPWFCcLICJ8uoJz0k_5Actn"));
			postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
			postParameters.add(new BasicNameValuePair("access_type", "offline"));
			postParameters.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/soap/googleCallbackURL"));
			postParameters.add(new BasicNameValuePair("code", request.getParameter("code")));

			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			httpPost.setHeader("Access-Control-Allow-Origin", "*");
			httpPost.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
			httpPost.setHeader("Access-Control-Max-Age", "151200");
			httpPost.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, authToken");
			httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
			httpPost.setHeader("Host", "www.googleapis.com");

			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			if (response2 != null && HttpStatus.SC_OK == response2.getStatusLine().getStatusCode()) {

				JSONObject responseJSON = new JSONObject(EntityUtils.toString(response2.getEntity()));

				if (responseJSON != null && responseJSON.get("id_token") != null) {
					accessToken = responseJSON.get("id_token").toString();
					response2.close();

					URIBuilder builder = null;
					try {
						builder = new URIBuilder("https://oauth2.googleapis.com/tokeninfo");
						builder.setParameter("id_token", accessToken);

					} catch (URISyntaxException e1) {
						log.error("Error inside googleURLHandler() :", e1);
					}

					HttpGet httpGet = new HttpGet(builder.build());
					CloseableHttpResponse response1 = httpclient.execute(httpGet);
					if (response1 != null && HttpStatus.SC_OK == response1.getStatusLine().getStatusCode()) {

						JSONObject result = new JSONObject(EntityUtils.toString(response1.getEntity()));

						if (result != null && result.get("name") != null) {
							String userName = result.getString("name").toString();
							model.addAttribute("name", userName);
							model.addAttribute("json", result.toString());

							String email = "";
							if (result.getString("email") != null) {
								email = result.getString("email");
							}
							userDAO.setUserData(userName, email, "Google Login", result);
						}
						response1.close();
					}

				}

			}

		} catch (Exception e) {
			log.error("Error inside googleURLHandler() :", e);
		}

	}

	public void linkedinURLHandler(HttpServletRequest request, Model model) {
		if (log.isDebugEnabled()) {
			log.debug("**** Inside linkedinURLHandler() **** ");
		}

		try {
			String accessToken = null;
			CloseableHttpClient httpclient = HttpClients.createDefault();

			HttpPost httpPost = new HttpPost("https://www.linkedin.com/oauth/v2/accessToken");
			List<BasicNameValuePair> postParameters = new ArrayList<>();
			postParameters.add(new BasicNameValuePair("client_id", "81moxezknrvqpq"));
			postParameters.add(new BasicNameValuePair("client_secret", "tvUaJf1MwcnTeHUM"));
			postParameters.add(new BasicNameValuePair("grant_type", "authorization_code"));
			postParameters
					.add(new BasicNameValuePair("redirect_uri", "http://localhost:8080/soap/linkedinCallbackURL"));
			postParameters.add(new BasicNameValuePair("code", request.getParameter("code")));

			httpPost.setEntity(new UrlEncodedFormEntity(postParameters));
			httpPost.setHeader("Access-Control-Allow-Origin", "*");
			httpPost.setHeader("Access-Control-Allow-Methods", "POST, GET, PUT, PATCH, OPTIONS, DELETE");
			httpPost.setHeader("Access-Control-Max-Age", "151200");
			httpPost.setHeader("Access-Control-Allow-Headers", "x-requested-with, Content-Type, authToken");

			CloseableHttpResponse response2 = httpclient.execute(httpPost);
			if (response2 != null && HttpStatus.SC_OK == response2.getStatusLine().getStatusCode()) {

				JSONObject responseJSON = new JSONObject(EntityUtils.toString(response2.getEntity()));

				if (responseJSON != null && responseJSON.get("access_token") != null) {
					accessToken = responseJSON.get("access_token").toString();
					response2.close();

					URIBuilder builder = null;
					try {
						builder = new URIBuilder(
								"https://api.linkedin.com/v1/people/~:(id,first-name,last-name,headline,picture-url,industry,summary,specialties,positions:(id,title,summary,start-date,end-date,is-current,company:(id,name,type,size,industry,ticker)),educations:(id,school-name,field-of-study,start-date,end-date,degree,activities,notes),associations,interests,num-recommenders,date-of-birth,publications:(id,title,publisher:(name),authors:(id,name),date,url,summary),patents:(id,title,summary,number,status:(id,name),office:(name),inventors:(id,name),date,url),languages:(id,language:(name),proficiency:(level,name)),skills:(id,skill:(name)),certifications:(id,name,authority:(name),number,start-date,end-date),courses:(id,name,number),recommendations-received:(id,recommendation-type,recommendation-text,recommender),honors-awards,three-current-positions,three-past-positions,volunteer)");
						builder.setParameter("oauth2_access_token", accessToken);
						builder.setParameter("format", "json");

					} catch (URISyntaxException e1) {
						log.error("Error inside linkedinURLHandler() :", e1);
					}

					HttpGet httpGet = new HttpGet(builder.build());
					CloseableHttpResponse response1 = httpclient.execute(httpGet);
					if (response1 != null && HttpStatus.SC_OK == response1.getStatusLine().getStatusCode()) {

						JSONObject result = new JSONObject(EntityUtils.toString(response1.getEntity()));

						if (result != null && result.get("firstName") != null) {
							String userName = result.getString("firstName").toString() + " "
									+ result.getString("lastName").toString();
							String email = "";
							model.addAttribute("name", userName);
							model.addAttribute("json", result.toString());

							if (result.has("email") && result.getString("email") != null) {
								email = result.getString("email");
							}
							userDAO.setUserData(userName, email, "Linkedin Login", result);
						}
						response1.close();
					}

				}

			}

		} catch (Exception e) {
			log.error("Error inside linkedinURLHandler() :", e);
		}

	}

}
