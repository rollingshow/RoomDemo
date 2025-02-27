package com.rollingshow.nasa_iotd;

// Класс для описания результатов поиска
public class Hit {
//    int id;
    String date;
    String explanation;
    String hdurl;
    String media_type;
    String service_version;
    String title;
    String url;
}

/*
пример данных ответа API Nasa POD

{
    "date": "2021-06-02",
    "explanation": "What's going on near the center of our galaxy? To help find out, a newly detailed panorama has been composed that explores regions just above and below the galactic plane in radio and X-ray light.  X-ray light taken by the orbiting Chandra Observatory is shown in orange (hot), green (hotter), and purple (hottest) and superposed with a highly detailed image in radio waves, shown in gray, acquired by the MeerKAT array.  Interactions are numerous and complex. Galactic beasts such as expanding supernova remnants, hot winds from newly formed stars, unusually strong and colliding magnetic fields, and a central supermassive black hole are all battling in a space only 1000 light years across.  Thin bright stripes appear to result from twisting and newly connecting magnetic fields in colliding regions, creating an energetic type of inner galactic space weather with similarities to that created by our Sun.  Continued observations and study hold promise to not only shed more light on the history and evolution of our own galaxy -- but all galaxies.",
    "hdurl": "https://apod.nasa.gov/apod/image/2106/gcenter_ChandraMeerKAT_960.jpg",
    "media_type": "image",
    "service_version": "v1",
    "title": "The Galactic Center in Stars, Gas, and Magnetism",
    "url": "https://apod.nasa.gov/apod/image/2106/gcenter_ChandraMeerKAT_960.jpg"
}


 */
