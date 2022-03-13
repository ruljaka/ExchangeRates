<h1 align="center">ExchangeRates</h1>
<p align="center">Приложение для получения курсов и конвертации валют, загружаемых с API ЦБ.</p>

## О проекте
![product-screenshot](https://user-images.githubusercontent.com/60775844/158014876-932f0391-8467-467e-9937-189ae1df63ab.jpg)
![product-screenshot](https://user-images.githubusercontent.com/60775844/158014752-579cf2ec-2d22-4508-84b9-f9aef6610987.jpg)

Приложение состоит из одной активити и двух фрагментов: списка с курсом валют и конвертера. Каждые 15 минут происходит автоматическое обновление [данных ЦБ 
в формате json](https://www.cbr-xml-daily.ru/daily_json.js), есть возиожность ручного обновления кнопкой resresh.

Для архитектуры приложения выбрана MVVM,как наиболее удобная и понятная, и компоненты Jetpack библиотеки: ViewModel и LiveData.
Для загрузки и обработки данных была использована библиотека Retrofit с собственным Gson конвертером. Это удобный инструмент для создания и обработки REST запросов.
Для асинхронной обработки запросов использовалась библиотека Kotlin Coroutines. По сравнению с RxJava нужно писать меньше кода, а сам код более читабельный и последовательный.
Для сохранения данных в локальной базе использована библиотека Room. Она создает API-интерфейс поверх встроенного SQLite API и упрощает работу с ним.
Для внедрения зависимостей использован Dagger Hilt. Он более удобен для небольших проектов и хорошо интегрируется с остальными продуктами в JetPack.
Для периодического обновления данных используется WorkManager. Эта библиотека лишена множества ограничений нативных решений Android и предоставляет удобное API.
Библиотека ViewBindingPropertyDelegate упрощает работу с View Binding и уменьшает количество кода.

## Стек
* [Jetpack libraries](https://developer.android.com/jetpack)
* [Retrofit](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [WorkManager](https://developer.android.com/jetpack/androidx/releases/work)
* [Coroutines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [Dagger Hilt](https://dagger.dev/hilt)
* [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)
