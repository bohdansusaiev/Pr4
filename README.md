Приклад асинхронної обробки

Ця практична демонструє асинхронну обробку файлів та даних за допомогою API CompletableFuture в Java. 

Вона складається з двох основних класів: AsyncFileProcessing та AsyncDoubleProcessing, де обидва класи показують, як виконувати асинхронні завдання, такі як читання файлів, обробка даних, обчислення сум.

AsyncFileProcessing
Демонструє асинхронне читання текстових файлів та їх обробку:

Спочатку файли зчитуються асинхронно.
Потім текст обробляється (всі латинські літери видаляються).
Результат виводиться в консоль разом із часом виконання.

AsyncDoubleProcessing
Демонструє генерацію випадкових чисел та обчислення:

Генеруються 20 випадкових чисел від 1 до 100.
Обчислюється сума чисел та сума їхніх квадратів.
Результати виводяться в консоль, разом із часом виконання.

Як працює?
Для асинхронного виконання використовуються CompletableFuture для обробки потоків.
Використовуються стандартні Java бібліотеки для роботи з файлами (Files.readString).
Визначені методи для паралельного обчислення результатів, таких як сума та сума квадратів.
