package com.SaloonProj.saloonapointment.ServiceImpl;

import com.SaloonProj.saloonapointment.Model.User;
import com.SaloonProj.saloonapointment.OtherDto.*;
import com.SaloonProj.saloonapointment.Response.TokenResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class KeyCloakService {
    private static final String KEYCLOAK_BASE_URL = "http://keycloak:8080";
    private static final String KEYCLOAK_ADMIN_API = KEYCLOAK_BASE_URL + "/admin/realms/Saloon-Booking-Project/users";
    private static final String TOKEN_URL_API = KEYCLOAK_BASE_URL + "/realms/Saloon-Booking-Project/protocol/openid-connect/token";
    private static final String ADMIN_TOKEN_URL_API = KEYCLOAK_BASE_URL + "/realms/master/protocol/openid-connect/token";

    private static final String CLIENT_ID = "saloon_booking_project";
    private static final String ADMIN_CLIENT_ID = "admin-cli";
    private static final String CLIENT_SECRET = "DisWxEFR6NuoFFaOUnsQr2eCgYLj3Cva";

    private static final String GRANT_TYPE = "password";

    private static final String scope = "openid profile email";

    private static final String username = "admin";

    private static final String password = "admin";

    private static final String clientid = "7041cabd-ea2d-4d97-b120-9cd83479f301";


    private final RestTemplate restTemplate;
    private final User user;

    public void createuser(SignupDto signupDto) throws Exception {
        // accessing the token

        String ACCESS_TOKEN = getAdminAccesstoken(username, password, GRANT_TYPE, null).getAccessToken();

        Credentials credentials = new Credentials();
        credentials.setTemporary(false);
        credentials.setType("password");
        credentials.setValue(signupDto.getPassword());

        UserRequest userRequest = new UserRequest();

        userRequest.setUsername(signupDto.getUsername());

        userRequest.setEmail(signupDto.getEmail());

        userRequest.setLastName(signupDto.getLastName());

        userRequest.setFirstName(signupDto.getFirstName());

        userRequest.setEnabled(true);
        userRequest.getCredentials().add(credentials);

        //Create an empty set of HTTP headers
        HttpHeaders headers = new HttpHeaders();

        //Tell the server the request body is JSON
        headers.setContentType(MediaType.APPLICATION_JSON);

        //	Attach a Bearer token for authentication
        headers.setBearerAuth(ACCESS_TOKEN);



        /*
        This line creates a complete HTTP request object by combining:

        The request body (userRequest)

        The request headers (headers)
         */
        //A wrapper for the HTTP request containing body and headers
        HttpEntity<UserRequest> requestEntity = new HttpEntity<>(userRequest, headers);


        ResponseEntity<String> response = restTemplate.exchange(
                KEYCLOAK_ADMIN_API,
                HttpMethod.POST,
                requestEntity,
                String.class
        );

        if (response.getStatusCode() == HttpStatus.CREATED) {
            System.out.println("user created succesfully");
            KeycloakUserDto user = fetchFirstUserByUsername(signupDto.getUsername(), ACCESS_TOKEN);
            List<KeycloakRole> roles = new ArrayList<>();
            KeycloakRole role = getRoleByName(clientid, ACCESS_TOKEN, signupDto.getRole().toString());

            roles.add(role);

            assignRoleToUser(
                    user.getId(),
                    clientid,
                    roles,
                    ACCESS_TOKEN
            );
        } else {
            System.out.println("user creation failed");
            throw new Exception(response.getBody());
        }


    }


    public TokenResponse getAdminAccesstoken(String username, String password, String grantType,
                                             String refreshToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        // ✅ Fix: set correct content type
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("username", username);
        requestBody.add("password", password);
        requestBody.add("client_id", ADMIN_CLIENT_ID);

        // Only add refresh_token if not null
        if (refreshToken != null) {
            requestBody.add("refresh_token", refreshToken);
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                ADMIN_TOKEN_URL_API,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        }
        throw new Exception("failed to obtain access token");
    }


    public TokenResponse getLoginToken(String username, String password, String grantType,
                                       String refreshToken) throws Exception {

        HttpHeaders headers = new HttpHeaders();

        // ✅ Fix: set correct content type
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("grant_type", grantType);
        requestBody.add("client_id", CLIENT_ID);
        requestBody.add("client_secret", CLIENT_SECRET);

        if (grantType.equals("password")) {
            requestBody.add("username", username);
            requestBody.add("password", password);
            requestBody.add("scope", scope);
        }

        // Only add refresh_token if not null
        if (refreshToken != null) {
            requestBody.add("refresh_token", refreshToken);
        }

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(requestBody, headers);

        ResponseEntity<TokenResponse> response = restTemplate.exchange(
                TOKEN_URL_API,
                HttpMethod.POST,
                requestEntity,
                TokenResponse.class
        );

        if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
            return response.getBody();
        }
        throw new Exception("failed to obtain access token");
    }

    public KeycloakRole getRoleByName(String clientId, String token, String role) throws Exception {

        String Url = KEYCLOAK_BASE_URL + "/admin/realms/Saloon-Booking-Project/clients/" + clientId + "/roles/" + role;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakRole> response = restTemplate.exchange(
                Url,
                HttpMethod.GET,
                requestEntity,
                KeycloakRole.class
        );

        if (response.getBody() != null) {
            return response.getBody();
        }
        throw new Exception("failed to get Role");

    }

    public KeycloakUserDto fetchFirstUserByUsername(String username, String token) throws Exception {

        String Url = KEYCLOAK_BASE_URL + "/admin/realms/Saloon-Booking-Project/users?username=" + username;

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<KeycloakUserDto[]> response = restTemplate.exchange(
                Url,
                HttpMethod.GET,
                requestEntity,
                KeycloakUserDto[].class
        );
        KeycloakUserDto[] users = response.getBody();
        System.out.println(Arrays.toString(users));
        if (users != null && users.length > 0) {
            return users[0];
        }
        throw new Exception("user not found with" + username);


    }


    public void assignRoleToUser(
            String userId,
            String clientId,
            List<KeycloakRole> roles,
            String token
    ) throws Exception {


        String Url = KEYCLOAK_BASE_URL + "/admin/realms/Saloon-Booking-Project/users/{userId}/role-mappings/clients/{clientId}";

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<List<KeycloakRole>> requestEntity = new HttpEntity<>(roles, headers);


        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    Url,
                    HttpMethod.POST,
                    requestEntity,
                    String.class,
                    userId, clientId
            );
        } catch (Exception e) {
            throw new Exception("failed to assign new role" + e.getMessage());
        }

    }


    public KeycloakUserDto fetchUserProfileFromJwt(String token) throws Exception {
        String url = KEYCLOAK_BASE_URL + "/realms/Saloon-Booking-Project/protocol/openid-connect/userinfo";

        // Remove "Bearer " if it's present
        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);  // now it's only the raw JWT
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        try {
            ResponseEntity<KeycloakUserDto> response = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    requestEntity,
                    KeycloakUserDto.class
            );
            return response.getBody();
        } catch (Exception e) {
            throw new Exception("failed to get userInfo " + e.getMessage());
        }
    }


}
