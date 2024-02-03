package com.Flock.domain.Member.DTO;

import com.Flock.domain.Member.Entity.Enum.Gender;
import com.nimbusds.jose.shaded.gson.JsonElement;
import com.nimbusds.jose.shaded.gson.JsonObject;
import com.nimbusds.jose.shaded.gson.JsonParser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Slf4j
public class KakaoProfile {

    private Integer id;
    private LocalDateTime connectedAt;
    private String email;
    private String nickname;
    private Integer age_range;
    private Gender gender;
    private Boolean isMember=false;



    public KakaoProfile(String jsonResponseBody){
        JsonParser parser = new JsonParser();
        JsonElement element = parser.parse(jsonResponseBody);

        log.info("형태 : " + element.toString());

        this.id = element.getAsJsonObject().get("id").getAsInt();

        String connected_at = element.getAsJsonObject().get("connected_at").getAsString();
        connected_at = connected_at.substring(0, connected_at.length() - 1);
        LocalDateTime connectDateTime = LocalDateTime.parse(connected_at, DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss"));
        this.connectedAt = connectDateTime;

        JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
        this.nickname = properties.getAsJsonObject().get("nickname").getAsString();

        JsonObject kakaoAccount = element.getAsJsonObject().get("kakao_account").getAsJsonObject();
        this.email = kakaoAccount.getAsJsonObject().get("email").getAsString();
        String strRange = kakaoAccount.getAsJsonObject().get("age_range").getAsString();

        this.age_range = Integer.valueOf(strRange.split("~")[0]);
        this.gender = Gender.fromString(kakaoAccount.getAsJsonObject().get("gender").getAsString());

    }
}
