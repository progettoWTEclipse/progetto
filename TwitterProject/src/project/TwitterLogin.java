package project;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.RequestToken;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;
/**
 * Servlet implementation class TwitterLogin
 */
@WebServlet("/TwitterLogin")
public class TwitterLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ConfigurationBuilder cb = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwitterLogin() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config){
    	
    	ServletContext context = config.getServletContext();

		cb = new ConfigurationBuilder();
		cb.setDebugEnabled(true);
		cb.setOAuthConsumerKey(context.getInitParameter("consumerKey"));
		cb.setOAuthConsumerSecret(context.getInitParameter("consumerSecret"));
		cb.setOAuthRequestTokenURL("https://api.twitter.com/oauth/request_token");
		cb.setOAuthAuthorizationURL("https://api.twitter.com/oauth/authorize");
		cb.setOAuthAccessTokenURL("https://api.twitter.com/oauth/access_token");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	     

        TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		request.getSession().setAttribute("twitter", twitter);

		try {
			StringBuffer callbackURL = request.getRequestURL();
			int index = callbackURL.lastIndexOf("/");
			callbackURL.replace(index, callbackURL.length(), "").append("/TwitterCallback");

			RequestToken rtk = twitter.getOAuthRequestToken(callbackURL.toString());
			request.getSession().setAttribute("requestToken", rtk);
			response.sendRedirect(rtk.getAuthenticationURL());
		}

		catch (TwitterException e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}
	

}
