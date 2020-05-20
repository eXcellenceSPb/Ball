Написать программу, которая представляет собой множество агентов взаимодействующих по заданным правилам.
Все агенты должны отображаться в виде графических объектов, на некотором поле. Взаимодействие агентов начинается после нажатия кнопки «старт» и прекращается после нажатия кнопки «стоп». Каждый агент работает в отдельном потоке. Агенты не должны выходит за пределы поля. Если агент достигает границы поля, то он должен отскочить от нее. Агенты питаются случайно разбросанным по полю кормом, который появляется в разных местах через некоторое время. Для перемещения по полю каждый агент тратит энергию, которую получил из корма, т.е. энергия агента уменьшается при перемещении и он должен пополнять ее. Если уровень энергии агента станет меньше некоторого заранее определенного уровня, то он умрет и должен быть удален с поля. Побеждает агент, имеющий наибольшую энергию.
Результаты конечной энергии, группу и координаты каждого агента на момент окончания игры поместить в таблицу БД (СУБД выбрать на свое усмотрение). 
Для настройки уровня минимальной энергии для жизни агента, параметров подключения к БД и т.д. использовать отдельный файл конфигурации.
#           Структура проекта:
	Balls.pkg
	•	Agents.pkg
	•	Enemy.java
	•	Bonus.java

	Game.pkg
	•	GameBack.java
	•	GamePanel.java
	•	Listeners.java
	•	Menu.java
	•	GameStart.java
	•	Config.ini
#    Объекты игры:
	•	Enemy – класс агента игры
	•	Bonus – класс бонуса игры
#    Обслуживающие классы:
	•	GamePanel – отрисовка меню, объектов и их взаимодействие
	•	Listeners – слушатель действий мышки и клавиш
#  	Внешний интерфейс:
	•	Menu - настройки отображения кнопок меню и их действий
	•	GameBack – настройки заднего фона игры
# 	Файл конфигурации:
	•	config.ini – файл содержащий информацию о настройках игры
Изменив атрибуты в файле config.ini мы можем задать подключение к БД и время жизни Агентов
