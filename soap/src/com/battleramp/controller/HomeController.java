package com.battleramp.controller;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.battleramp.handler.HomeCallbackURLHandler;
import com.battleramp.redirection.HomeRedirection;

@Controller
@RequestMapping("*")
public class HomeController {

	@Autowired
	private HomeRedirection redirectURL;

	@Autowired
	private HomeCallbackURLHandler callbackHandler;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String testMethod() {
		return "home";
	}

	@RequestMapping(value = "/facebook", method = RequestMethod.GET)
	public ModelAndView facebookPage() throws IOException, URISyntaxException {
		return redirectURL.facebookRedirectionURL();
	}

	@RequestMapping(value = "/google", method = RequestMethod.GET)
	public ModelAndView googlePage() throws IOException, URISyntaxException {
		return redirectURL.googleRedirectionURL();
	}

	@RequestMapping(value = "/github", method = RequestMethod.GET)
	public ModelAndView githubPage() throws IOException, URISyntaxException {
		return redirectURL.githubRedirectionURL();
	}

	@RequestMapping(value = "/linkedin", method = RequestMethod.GET)
	public ModelAndView linkedinPage() throws IOException, URISyntaxException {
		return redirectURL.linkedinRedirectionURL();
	}

	@RequestMapping(value = "/callbackURL", method = RequestMethod.GET, params = { "code" })
	public String github(Model model, HttpServletRequest request) {
		callbackHandler.githubURLHandler(request, model);
		return "callBack";
	}

	@RequestMapping(value = "/linkedinCallbackURL", method = RequestMethod.GET, params = { "code" })
	public String linkedin(Model model, HttpServletRequest request) {
		callbackHandler.linkedinURLHandler(request, model);
		return "callBack";
	}

	@RequestMapping(value = "/facebookCallbackURL", method = RequestMethod.GET, params = { "code" })
	public String facebook(Model model, HttpServletRequest request) {
		callbackHandler.facebookURLHandler(request, model);
		return "callBack";
	}

	@RequestMapping(value = "/googleCallbackURL", method = RequestMethod.GET, params = { "code" })
	public String google(Model model, HttpServletRequest request) {
		callbackHandler.googleURLHandler(request, model);
		return "callBack";
	}

}