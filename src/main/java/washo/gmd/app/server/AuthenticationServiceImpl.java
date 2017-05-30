package washo.gmd.app.server;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.ObjectMapper;
import washo.gmd.app.client.model.User;
import washo.gmd.app.client.services.AuthenticationService;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import com.github.scribejava.core.oauth.OAuth20Service;


public class AuthenticationServiceImpl extends RemoteServiceServlet implements AuthenticationService {

    private static final String NETWORK_NAME = "Facebook";
    private static final String PROTECTED_RESOURCE_URL = "https://graph.facebook.com/v2.8/me";
    private OAuth20Service service;
    private ObjectMapper objectMapper;

    @Override
    public String authenticate(String apiKey, String secretKey) {
        // Replace these with your client id and secret
        final String clientId = "514471105343945";
        final String clientSecret = "aebef0dc3d975de866babae075d42a8e";
        final String secretState = "secret" + new Random().nextInt(999_999);
        service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .state(secretState)
                .callback("http://localhost:8888/#dashboard")
                .build(FacebookApi.instance());

        // Obtain the Authorization URL
        final String authorizationUrl = service.getAuthorizationUrl();
        return authorizationUrl;
    }

    @Override
    public User getRequest(String code) {
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = service.getAccessToken(code);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        Response response = null;
        try {
            response = service.execute(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(response.getCode());
        try {
            System.out.println(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            return getUser(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected User getUser(Response response) throws IOException {
        objectMapper = new ObjectMapper();

        String responseBody = response.getBody();
        JsonNode jsonNode = objectMapper.readTree(responseBody);
        JsonNode idNode = jsonNode.get("id");

        User user = new User();
        user.setId(jsonNode.get("id").asText());
        user.setName(jsonNode.get("name").asText());
        return user;
    }

    protected void test() {
        /*// Replace these with your client id and secret
        final String clientId = "514471105343945";
        final String clientSecret = "aebef0dc3d975de866babae075d42a8e";
        final String secretState = "secret" + new Random().nextInt(999_999);
        final OAuth20Service service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(clientSecret)
                .state(secretState)
                .callback("http://localhost:8888/#home")
                .build(FacebookApi.instance());

        final Scanner in = new Scanner(System.in, "UTF-8");

        System.out.println("=== " + NETWORK_NAME + "'s OAuth Workflow ===");
        System.out.println();

        // Obtain the Authorization URL
        System.out.println("Fetching the Authorization URL...");
        final String authorizationUrl = service.getAuthorizationUrl();
        System.out.println("Got the Authorization URL!");
        System.out.println("Now go and authorize ScribeJava here:");
        System.out.println(authorizationUrl);
        System.out.println("And paste the authorization code here");
        System.out.print(">>");
        final String code = in.nextLine();
        System.out.println();

        System.out.println("And paste the state from server here. We have set 'secretState'='" + secretState + "'.");
        System.out.print(">>");
        final String value = in.nextLine();
        if (secretState.equals(value)) {
            System.out.println("State value does match!");
        } else {
            System.out.println("Ooops, state value does not match!");
            System.out.println("Expected = " + secretState);
            System.out.println("Got      = " + value);
            System.out.println();
        }

        // Trade the Request Token and Verfier for the Access Token
        System.out.println("Trading the Request Token for an Access Token...");
        OAuth2AccessToken accessToken = null;
        try {
            accessToken = service.getAccessToken(code);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("Got the Access Token!");
        System.out.println("(if your curious it looks like this: " + accessToken
                + ", 'rawResponse'='" + accessToken.getRawResponse() + "')");
        System.out.println();

        // Now let's go and ask for a protected resource!
        System.out.println("Now we're going to access a protected resource...");
        final OAuthRequest request = new OAuthRequest(Verb.GET, PROTECTED_RESOURCE_URL);
        service.signRequest(accessToken, request);
        Response response = null;
        try {
            response = service.execute(request);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Got it! Lets see what we found...");
        System.out.println();
        System.out.println(response.getCode());
        try {
            System.out.println(response.getBody());
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
        System.out.println("Thats it man! Go and build something awesome with ScribeJava! :)");*/
    }
}
