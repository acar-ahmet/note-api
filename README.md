# Note API

Bu proje, notları yönetmek için geliştirilmiş bir REST API'dir. Spring Boot kullanılarak geliştirilmiştir.

## 🚀 Teknolojiler

- **Java 17**
- **Spring Boot 3.x**
- **Spring Data JPA**
- **H2 Database**
- **Lombok**
- **Spring Validation**
- **Spring Web**
- **Spring Test**
- **Mockito**

## 📋 Özellikler

- Not oluşturma
- Not güncelleme
- Not silme
- ID'ye göre not getirme
- Tarihe göre notları listeleme
- Tüm notları listeleme
- Validasyon kontrolleri
- Hata yönetimi
- Unit testler

## 🛠️ Kurulum

1. Projeyi klonlayın:
```bash
git clone https://github.com/kullaniciadi/note-api.git
```

2. Proje dizinine gidin:
```bash
cd note-api
```

3. Uygulamayı başlatın:
```bash
./mvnw spring-boot:run
```

## 📝 API Endpoints

### Not Oluşturma
```
POST /notes
Content-Type: application/json

{
    "title": "Toplantı Notları",
    "date": "2024-03-25",
    "description": "Haftalık ekip toplantısı notları"
}
```

### Not Getirme
```
GET /notes/{id}
```

### Tarihe Göre Notları Getirme
```
GET /notes/date/{date}
```

### Tüm Notları Getirme
```
GET /notes
```

### Not Güncelleme
```
PUT /notes/{id}
Content-Type: application/json

{
    "title": "Güncellenmiş Not",
    "date": "2024-03-25",
    "description": "Güncellenmiş açıklama"
}
```

### Not Silme
```
DELETE /notes/{id}
```

## 🧪 Testler

Testleri çalıştırmak için:
```bash
./mvnw test
```

## 📊 Veritabanı

- H2 in-memory veritabanı kullanılmaktadır
- Uygulama başlatıldığında otomatik olarak oluşturulur
- H2 Console: http://localhost:8080/h2-console
  - JDBC URL: jdbc:h2:mem:testdb
  - Username: sa
  - Password: password

## 🛡️ Validasyon Kuralları

- Başlık boş olamaz
- Tarih boş olamaz
- Tarih formatı: YYYY-MM-DD

## 🤝 Katkıda Bulunma

1. Fork'layın
2. Feature branch oluşturun 
3. Değişikliklerinizi commit edin 
4. Branch'inizi push edin
5. Pull Request oluşturun

## 📄 Lisans

Bu proje MIT lisansı altında lisanslanmıştır. Detaylar için [LICENSE](LICENSE) dosyasına bakın. 