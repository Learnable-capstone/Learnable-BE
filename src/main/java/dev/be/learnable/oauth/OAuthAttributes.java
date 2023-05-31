package dev.be.learnable.oauth;

import dev.be.learnable.core.domain.Member;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;

public enum OAuthAttributes {


    GOOGLE("google", (attributes) -> {
        return Member.of(
                (String) attributes.get("name"),
                (String) attributes.get("email"),
                "ROLE_USER",
                "google",
                "google_"+ attributes.get("sub"),
            1L
        );
    }),
    /*
        카카오 추가
     */
    KAKAO("kakao", (attributes) -> {
       return Member.of(
           (String) attributes.get("nickname"),
           (String) attributes.get("account_email"),
           "ROLE_USER",
           "kakao",
           "kakao_"+ attributes.get("id").toString(),
           1L
       );
    });

    private final String registrationId;
    private final Function<Map<String, Object>, Member> of;

    OAuthAttributes(String registrationId, Function<Map<String, Object>, Member> of) {
        this.registrationId = registrationId;
        this.of = of;
    }

    public static Member extract(String registrationId, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> registrationId.equals(provider.registrationId))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of.apply(attributes);
    }
}
