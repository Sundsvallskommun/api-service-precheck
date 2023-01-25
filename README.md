# PreCheck

## Leverantör

Sundsvalls kommun

## Beskrivning
PreCheck är en tjänst som kontrollerar möjligheten att leverera olika tjänster för en specifik adress.

## Tekniska detaljer

### Integrationer
Tjänsten integrerar mot:

* Lantmäteriverkets API för belägenhetsadress
* GIS

### Obligatorisk konfiguration för att starta tjänsten

|Konfigurationsnyckel|Beskrivning|
|---|---|
|**Lantmäteriverket**||
|integration.lmv.url|URL för endpoint till Lantmäteriverkets belägenhetsadress-API|
|`spring.security.oauth2.client.registration.lmv.client-id`|Klient-ID som ska användas vid anrop mot Lantmäteriverket|
|`spring.security.oauth2.client.registration.lmv.client-secret`|Klienthemlighet som ska användas vid anrop mot Lantmäteriverket|
|`spring.security.oauth2.client.provider.lmv.token-uri`|URI till endpoint för att förnya token för anrop mot Lantmäteriverket|
|**GIS**||
|integration.gis.url|URL för endpoint till GIS|
|`spring.security.oauth2.client.registration.gis.client-id`|Klient-ID som ska användas vid anrop mot GIS|
|`spring.security.oauth2.client.registration.gis.client-secret`|Klienthemlighet som ska användas vid anrop mot GIS|
|`spring.security.oauth2.client.provider.gis.token-uri`|URI till endpoint för att förnya token för anrop mot GIS|


## Paketering

### Paketera och starta tjänsten
Applikationen kan paketeras genom:

```
./mvnw package
```
Kommandot skapar filen `api-service-precheck-<version>.jar` i katalogen `target`. Tjänsten kan nu köras genom kommandot `java -jar target/api-service-precheck-<version>.jar`.

### Bygga och starta med Docker
Exekvera följande kommando för att bygga en Docker-image:

```
docker build -f src/main/docker/Dockerfile -t api.sundsvall.se/ms-precheck:latest .
```

Exekvera följande kommando för att starta samma Docker-image i en container:

```
docker run -i --rm -p8080:8080 api.sundsvall.se/ms-precheck

```

#### Kör applikationen lokalt

Exekvera följande kommando för att bygga och starta en container i sandbox mode:  

```
docker-compose -f src/main/docker/docker-compose-sandbox.yaml build && docker-compose -f src/main/docker/docker-compose-sandbox.yaml up
```


## 
Copyright (c) 2022 Sundsvalls kommun
