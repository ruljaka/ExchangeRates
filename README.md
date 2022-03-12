<h1 align="center">ExchangeRates</h1>
<p align="center">Приложение для получения курсов и конвертации валют, загружаемых с API ЦБ.</p>

## О проекте
![product-screenshot](https://user-images.githubusercontent.com/60775844/158011401-d19e67c8-8879-4515-b534-b90f04ca4f75.jpg)
![product-screenshot](https://user-images.githubusercontent.com/60775844/158011402-63cba751-bf0e-487b-a2a1-6a4945a42939.jpg)

Приложение состоит из одной активити и двух фрагментов: списка с курсом валют и конвертера. Каждые 15 минут происходит автоматическое обновление данных ЦБ 
в формате json (https://www.cbr-xml-daily.ru/daily_json.js), есть возиожность ручного обновления кнопкой resresh.

Для архитектуры приложения выбрана MVVM,как наиболее удобная и понятная, и компоненты Jetpack библиотеки: ViewModel и Livedata.
Для загрузки и обработки данных была использована библиотека Retrofit с собственным Gson конвертером. Это удобный инструмент для создания и обработки REST запросов.
Для асинхронной обработки запросов использовалась библиетка Kotlin Coroutines. По сравнению с RxJava нужно писать меньше кода, а сам код более читабельный и последовательный.
Для сохранения данных в локальной базе использована библиотека Room. Она создает API-интерфейс поверх встроенного SQLite API и упрощает работу с ним.
Для внедрения зависиостей использован Dagger Hilt. Он более удобен для небольших проектов и хорошо интегрируется с остальными продуктами в JetPack.
Для периодического обновления данных используется WorkManager. Эта библиотека лишена множества ограничений нативыных решений Android и предоставялет удобное API.
Библиотка ViewBindingPropertyDelegate упрощает работу с View Binding и уменьшает колличество кода.

## Стек
* [Jetpack libraries](https://developer.android.com/jetpack)
* [Retrofit](https://square.github.io/retrofit/)
* [Room](https://developer.android.com/jetpack/androidx/releases/room)
* [WorkManager](https://developer.android.com/jetpack/androidx/releases/work)
* [Courotines](https://developer.android.com/kotlin/coroutines?gclid=EAIaIQobChMIqZC4jo-i8gIVsAZ7Ch1rOASzEAAYASAAEgKAwvD_BwE&gclsrc=aw.ds)
* [Dagger Hilt](https://dagger.dev/hilt)
* [ViewBindingPropertyDelegate](https://github.com/androidbroadcast/ViewBindingPropertyDelegate)
