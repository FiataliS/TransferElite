Программа на данный момент в разработке. Работает подключение к удаленному серверу и загрузка файла в обе стороны.
Это полностью консольное клиент/серверное приложение!

Для сбора проекта требуется jdk 11.

Клонируйте репозиторий на компьтер сомандой git clone

Компилируем mvn compile

Собираем mvn package

Или можете скачать уже готовый TransferElite-1.0.jar

Запускаем java -jar название программы.

На серверной части требуется запустить сервер командой start

Не забываем при этом задать пользователя и пороль для подключения командой set user
Сервер готов к работе.

Если не хотим чтобы настройки потерились после следующего запуска. Требуется их сохранить командой save opt

На клиенской части настраиваем сервер и порт для подключения командой set connect

Не забываем сохранить настройки save opt.

Теперь можно подключаться командой connect
Потребуется ввести логин и пороль сервера. 

Будет уведомление об успешном подключении.

командой LS можено получить список файлов на сервере в директории ("TransferDirectory") которая будет создана в папке команды. (Внимание есть bag, папка не должна быть пустой, исправлю как вернусь из отпуска.)

get название файла  скачает файл клиенту

put загрузит файл на сервер.

Спасибо за прочтение!
Сам md доработаю как вернусь из отпуска.