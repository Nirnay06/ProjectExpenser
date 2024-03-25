package com.expenser.security;

public class CustomOAuth2UserServicedep {
//
//    @Override
//    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
//        OAuth2User oAuth2User = new DefaultOAuth2UserService().loadUser(userRequest);
//
//        // Extract user details
//        Map<String, Object> attributes = oAuth2User.getAttributes();
//        String userId = null; // User ID
//        String email = null; // User email
//        String name = null; // User name
//		if (userRequest != null && userRequest.getClientRegistration() != null
//				&& "GitHub".equals(userRequest.getClientRegistration().getClientName())) {
//			userId = attributes.get("login")!=null ? attributes.get("login").toString() : "";;
//			email = attributes.get("email")!=null ? attributes.get("email").toString() : "";
//			name =attributes.get("name")!=null ? attributes.get("name").toString() : "";;
//;		}
//        // Extract desired user details (customize based on provider)
//       
//        // ... add more attributes as needed
//
//        // Create authorities/roles if necessary
//        Set<GrantedAuthority> authorities = new HashSet<>();
//        authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
//
//        // Create UserDetails object
//        UserDetails userDetails = new User(userId, "", authorities);
//        System.out.println(userId + email +  name );
//        // You can return a custom object if needed
//        return new DefaultOAuth2User(authorities, attributes, "name");
//    }
}
