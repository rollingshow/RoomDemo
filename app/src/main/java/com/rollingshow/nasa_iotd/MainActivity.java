package com.rollingshow.nasa_iotd;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.rollingshow.nasa_iotd.databases.PicturesDB;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

public class MainActivity extends AppCompatActivity {
    // Инициализируем переменные
    PicturesDB DB;
    ImageAdapter adapter;
    ListView ListView;

    // Обозначаем ссылку для получения и ключ
    final String API_URL = "https://api.nasa.gov/";
    final String key = "txm14nwE4luPveZLAFg4431ETODRtVVlNGeN5IeP";
    static final String page = "/planetary/apod";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Находим ListView и загружаем старую/создаём новую базу данных
        ListView = findViewById(R.id.imagesListView);
        DB = PicturesDB.create(this);

        // Запускаем обновление, на случай если есть новые картинки дня
        updatePicture();
        Log.d("mytag", "update...");

        // Переходим к отображению
        displayPictures(null);
    }

    // Вывод всех результатов
    public void displayPictures(@SuppressWarnings("unused") View v) {
        adapter = new ImageAdapter(getApplicationContext(), DB.products().selectAll());
        ListView.setAdapter(adapter);
    }

    // Получение новой картинки дня
    public void updatePicture() {
        // создаём экземпляр службы для обращения к API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL) // адрес API сервера
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // создаём обработчик, определённый интерфейсом PixabayAPI выше
        NasaAPI api = retrofit.create(NasaAPI.class);

        // указываем, какую функцию API будем использовать
        Call<Hit> call = api.update(key);  // создали запрос

        // Обработка ответа
        Callback<Hit> callback = new Callback<Hit>() {
            // При успехе
            @Override
            public void onResponse(@NonNull Call<Hit> call, retrofit2.Response<Hit> response) {
                // Получили ответ в виде объекта
                Hit r = response.body();
                if (r != null) {
                    // Отобразили ответ
                    displayResults(r);
                    Log.d("mytag_response", r.toString());
                } else {
                    Toast toast = Toast.makeText(getApplicationContext(), "No images found or connection problems", Toast.LENGTH_LONG);
                    try {
                        if (response.errorBody() != null) {
                            Log.d("mytag_error", response.errorBody().string());
                            Log.d("mytag_error", String.valueOf(response.raw().request().url()));
                        }

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    toast.show();
                }


            }

            // При ошибке загрузки
            @Override
            public void onFailure(@NonNull Call<Hit> call, @NonNull Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "Connection error!", Toast.LENGTH_LONG);
                toast.show();

                Log.e("mytag_error", t.getLocalizedMessage(), t);
            }
        }; // обработка ответа
        call.enqueue(callback); // ставим запрос в очередь
    }

    // Вывод ответа
    public void displayResults(Hit hit) {
        // вызывается, когда появятся результаты поиска
        Picture p_tst = new Picture(
                hit.date,
                hit.hdurl,
                hit.explanation,
                hit.title
        );
        DB.products().insertNew(p_tst);

        displayPictures(null);
    }

    // Интерфейс для API
    interface NasaAPI {
        @GET(page)
        Call<Hit> update(@Query("api_key") String key);
        // Тип ответа, действие, содержание запроса
    }
}
