# EShop

# Navigation List
- [Module 1](#module-1)


## Module 1
### Reflection 1.1
- Pada modul 1 ini, saya menyadari pentingnya tingkat keterbacaan dan interpretabilitas dalam hal penulisan kode karena kebanyakan program yang kita tulis perlu mampu dimengerti oleh orang lain, lebih tepatnya rekan kerja. 
- Oleh karena itu, sesuai pembelajaran di pekan ini serta catatan modul yang ada, saya menerapkan prinsip "clean code". Prinsip ini (jika diimplementasikan) memastikan kode yang kita tulis betul secara penamaan (naming convention), method yang masing-masing memiliki tugasnya ketika dieksekusi, import library seperlunya, comment yang memberikan penjelasan secukupnya, penanganan error yang terjadi, dan lain-lain.
- Hal yang saya pelajari dari kode saya pada module ini (selain melakukan debug berkepanjangan hanya karena versi junit yang sepertinya outdated) adalah perihal refactor file dan package yang ada dan perihal testing yang dilakukan. Saya baru mengetahui bahwa best practice untuk mengimplementasikan berbagai hal yang ada, misal pada kasus ini, controller serta service yang dipisah-pisah. Walaupun pada saat PBP semester lalu, sudah cukup sering dilakukan, tetapi saya baru benar-benar menyadari kegunaannya. Kemudian, terdapat testing yang cukup ketat, yang mana cukup saya sepelekan di semester-semester lalu (biasanya hanya dengan melihat log secara manual).
- Masih banyak hal yang kurang dari progress program saya sekarang, misalnya error yang terjadi (yang sekarang masih ditampilkan ke user dalam bentuk whitelabel page) dapat disajikan dengan lebih baik dengan membuat suatu page khusus yang lebih ramah dilihat. Selain itu, penambahan fitur register dan login juga dapat dilakukan karena kemungkinan besar, pada kasus nyata, tiap user memiliki daftar product-nya masing-masing (ini bisa diwujudkan dengan autentikasi). Terakhir, list product ini dapat hilang ketika di-stop-and-rerun, integrasi dengan database mungkin akan menjadi fitur yang cocok untuk membuat web tersebut lebih "nyata".
### Reflection 1.2
- Setelah menulis unit test, saya lebih merasa tenang mengenai kelanjutan potensi pengembangan kode saya karena telah terdapat beberapa kasus yang seakan telah ditangani oleh unit test. 
- Menurut saya, banyaknya unit test tidak terbatas. Hal ini juga dipengaruhi oleh seberapa kompleks program yang dibuat. Namun, banyaknya unit test tidak menjamin program bisa bebas dari error.
- Sebenarnya tidak ada cara mutlak untuk mengetahui apakah kita sudah cover seluruh kemungkinan kasus yang akan terjadi bermodalkan unit test. Namun, kita dapat mengoptimalkan unit test tersebut agar dapat sebisa mungkin meng-cover seluruh kasus, misal dengan menguji happy path dan edge cases (positive, negative, boundary cases), AAA pattern (Arrange, Act, Assert), penamaan metode unit test yang deskriptif, idempotensi, penggunaan test coverage tools, dan lain sebagainya.
- Walaupun code coverage mencapai 100%, tidak ada jaminan bahwa program kita bebas dari bug atau error. Terdapat beberapa cara untuk meraih 100% code coverage yang tidak benar, salah satunya test yang memanggil method test, tetapi tidak melakukan assertion terhadap hasil pemanggilan metode tersebut. Sumber: https://medium.com/code4it/why-reaching-100-code-coverage-must-not-be-your-testing-goal-with-examples-in-c-85739f69cc86, https://matanbobi.dev/posts/why-i-dont-like-code-coverage
- Menambahkan class Java baru yang mirip dengan functional test yang sudah ada dengan prosedur setup dan instance variables yang sama adalah bentuk redundancy. Segala bentuk redundancy dapat mengurangi tingkat code cleanliness dan akibatnya, memperkeruh kualitas kode yang telah ada. 
- Jauh lebih baik jika method functional test yang menguji banyak item di product list digabung dalam file functional test yang sudah ada sebelumnya. Hal ini juga dapat meningkatkan tingkat cleanliness of the code.

## Module 2
Aplikasi saya di-deploy pada tautan berikut: https://yeasty-alina-dardrich-451d7711.koyeb.app/
### Reflection 2.1
- Code quality issues yang saya hadapi pada pengerjaan modul kali ini bervariasi, yakni:
1. Banyaknya lines of code yang saya comment, tetapi tak kunjung dihapus
2. Melakukan return redirection ke file dalam resources yang penamaannya kurang tepat sehingga rentan mengakibatkan error atau bug
3. Method setUp dalam unit-test yang tidak berisikan apapun (useless method)
- Saya menyelesaikan permasalahan code quality issues tersebut dengan:
1. Memastikan lines of code yang saya comment tidak memiliki fungsi apapun dalam versi push terbaru, lalu menghapusnya dari program
2. Contoh kasusnya adalah controller editProductPage me-return "editProduct", padahal file html yang seharusnya ditujukan adalah "EditProduct" sehingga hal ini mengakibatkan inkonsistensi. Cara menyelesaikannya sangat sederhana, yakni cukup menjadikan semua return sejenis sesuai kaidah Pascal Case. Hal ini selain menimbulkan inkonsistensi, juga dapat menyebabkan error atau bug jika dicoba untuk mengintegrasikan program dengan sistem Linux (karena Windows ternyata cukup "toleran" ketimbang Linux sehingga jika terdapat sedikit saja tidak match, Linux akan mengembalikan pesan error)
3. Sebenarnya terdapat beberapa cara, tetapi yang salah pilih adalah menghilangkan method setUp-nya karena saya menginisialisasi Product langsung pada tiap method test-nya
- Ya, menurut saya, implementasi workflow CI/CD saya sudah sesuai dengan definisi Continuous Integration dan Continuous Deployment. Dengan GitHub workflows (melalui GitHub Actions), program saya dapat secara otomatis melalui terlebih dahulu testing (dilakukan pengecekan menggunakan ci dan analisis kebenaran menggunakan SonarQube) dan di-deploy setiap kali melakukan push ke repository GitHub (dengan scorecard). Kemudian, dari sisi CD, saya telah menggunakan PaaS berupa Koyeb. Untaian task inilah yang membentuk workflow dengan CI/CD.
- Bonus (Code Coverage 100%):
![codcov.png](codcov.png)

## Module 3
### Reflection 3.1
- Dari lima unsur prinsip S.O.L.I.D, saya telah menerapkan empat prinsip, di antaranya:
1. Single Responsibility Principle (SRP)
Prinsip ini berbicara tentang satu class sebaiknya hanya bertugas dalam melakukan satu aspek fungsionalitas program saja. Hal ini saya lakukan dengan memisahkan class Controller based on tugasnya, seperti HomePageController bertugas untuk menangani Home Page, ProductController menangani Product, dan CarController menangani Car.
2. Liskov Substitution Principle (LSP)
Pada program pra-penerapan prinsip SOLID (dalam branch before-solid), CarController merupakan subclass dari ProductController. Padahal, keduanya memiliki fungsi yang berlainan (CarController tidak bisa menggantikan tugas ProductController), contohnya editProductPost yang method-nya adalah PUT, sementara editCarPost ber-method POST. Oleh karena itu, saya pisah keduanya.
3. Interface Segregation Principle (ISP)
Prinsip ini intinya berbicara bahwa sebuah interface sebaiknya dibagi-bagi menjadi interface yang lebih kecil sesuai fungsinya masing-masing. Implementasi dalam program saya berupa pemisahan interface service berupa CarService dan ProductService karena dengan menerapkan hal ini, setiap interface memiliki segmentasi tugas yang jelas.
4. Dependency Inversion Principle (DIP)
Prinsip ini berbicara tentang semua kode harus terikat pada abstraksi (bukan implementasi). Hal ini saya terapkan lewat penggunaan CarService carService (bergantung pada interface) instead of CarServiceImpl carService (bergantung pada implementation) pada CarController.
- Menerapkan prinsip S.O.L.I.D memberikan banyak keuntungan dalam pengembangan perangkat lunak. Pertama, kode menjadi lebih mudah dipelihara dan diuji karena setiap kelas memiliki tanggung jawab spesifik (SRP), serta berkurangnya ketergantungan antar kelas berkat Dependency Inversion (DIP). Ini mempermudah unit testing tanpa perlu mempertimbangkan implementasi lain yang kompleks. Kedua, keterbacaan kode meningkat karena metode-metode yang panjang dan kompleks dipecah menjadi bagian yang lebih kecil sesuai tanggung jawabnya, sehingga lebih mudah dipahami oleh pengembang lain maupun diri sendiri di masa depan. Terakhir, penerapan Dependency Inversion dengan interface membantu mengurangi dampak perubahan kode, memastikan bahwa perubahan pada satu bagian tidak merusak bagian lain, serta memungkinkan penambahan fitur baru dengan lebih aman dan fleksibel.
- Tidak menerapkan prinsip S.O.L.I.D dapat menyebabkan berbagai masalah dalam pengembangan perangkat lunak. Pertama, kode menjadi sulit dipahami dan dikelola karena kompleksitas yang tinggi, terutama jika satu kelas atau metode menangani terlalu banyak tugas tanpa pemisahan yang jelas (SRP). Kedua, pengujian menjadi lebih sulit karena banyaknya ketergantungan antar bagian kode, sehingga proses debugging lebih rumit dan memerlukan usaha ekstra untuk menemukan sumber bug. Ketiga, menambahkan fitur baru menjadi lebih berisiko karena perubahan di satu bagian kode dapat merusak bagian lain, mempersulit pemeliharaan, dan memerlukan refactor besar. Akibatnya, kolaborasi dalam tim juga menjadi lebih sulit karena kode yang tidak terstruktur sulit untuk direview dan dipahami oleh pengembang lain.