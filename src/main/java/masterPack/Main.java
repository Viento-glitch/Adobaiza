package masterPack;
/*
Реализовать МИКРОСЕРВИСНУЮ структуру при необходимости расширить серверное приложение

    Программа для мониторинга цен в игре Stalcraft

    0.0 Освоить взаимодействие ключ к API
    0.1 Опробовать запросы к API

    Парсить наименования и id  предметов с гитхаба сталкрафта.




    1.Создать клиент которым попытаться оформить некоторые тестовые запросы к API сталкрафта.
    2.Создать Базу данных на которую будет складываться минимальная цена предметов.
    3.Создать серверную часть отвечающую за запросы и составление базы данных цены.





*/

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;


public class Main {

    final static String url = "https://exbo.net/oauth/token";
    final static String clientId = "308";
    final static String clientSecret = "Ne0VjVTkxaRsLcllsEwkqrTNZWoecGrLzDQAVZe0";
    final static String grantType = "client_credentials";
    final static String scope = "";

    OkHttpClient client = new OkHttpClient();
    Gson gson = new Gson();

    public TokenResponse postRequest(String url, String clientId, String clientSecret, String grantType, String scope) throws IOException {

        RequestBody formBody = new FormBody.Builder()
                .add("client_id", clientId)
                .add("client_secret", clientSecret)
                .add("grant_type", grantType)
                .add("scope", scope)
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            assert response.body() != null;
            String jsonResponse = response.body().string();
//            System.out.println(jsonResponse);

            return gson.fromJson(jsonResponse, TokenResponse.class);
        }
    }

    public static void main(String[] args) throws IOException {
        Main example = new Main();
        TokenResponse tokenResponse = example.postRequest(url, clientId, clientSecret, grantType, scope);
        /*todo я реализовал парс токена доступа, далее здесь потребуется используя токен
        * todo запросить историю товара по item id который мы должны получить с гитхаба сталкрафта, название мы получаем из этого же файла
        * todo
        */
    




    }
}