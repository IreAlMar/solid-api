package com.pilots.solidapi.infrastructure.external.rick.adapter;

import com.pilots.solidapi.application.item.NameRequestService;
import com.pilots.solidapi.infrastructure.external.rick.model.RickMortyCharacter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class RickNameRequestServiceImpl implements NameRequestService {

    RestTemplate restTemplate = new RestTemplate();

    public String getName() {
        Random random = new Random();
        int x = random.nextInt(591);
        String API_URL = "https://rickandmortyapi.com/api/character";
        final String url = API_URL + "/" + x ;

        ResponseEntity<RickMortyCharacter> characterResponseEntity = restTemplate.getForEntity(url, RickMortyCharacter.class);
        if (characterResponseEntity.getStatusCode() != HttpStatus.OK) {
            return null;
        }
        else {
            return characterResponseEntity.getBody().getName();
        }

    }

}
