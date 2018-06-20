# surfing kata

The goal of this kata is to practice TDD.

Alex likes to go surfing, fortunately for him he lives close to the beach in Waikiki, Hawaii. On days when he leaves the office early
he checks the weather conditions on the internet and decides whether to go out for a surf or not. His manager has noticed he keeps
getting distracted from constantly checking the weather - and has told him off for it. He'd like to automate this process to save him
time...and his job!

1. Alex has found a mobile app with an open API `WeatherApp` that provides the current `WeatherConditions` for a given `cityId`. Waikiki's
`cityId` is 55. He also knows the mobile app that pops up notifications on his phone is the `NotifierApp`.

 But he's no good at coding and he'd like
 you to write a `SurfingApp` for him that'll pop up a simple notification on his phone saying `You should go surfing today` when the weather
 conditions in Waikiki are ideal.

 Alex considers it's good enough to go surfing if it's at least 21C and the wind's speed is at least 12m/s or if the temperature is at least 25C and the wind's speed is
at least 9m/s.

2. If the weather is really nice Alex is keen on working harder in order to finish the day's work early. For him to notice he needs step up his game
if he wants to enjoy a great day of surfing, he'd like a pop up reading `Go surfing ASAP!` with a vibration on his phone.

 A temperature higher than 25C and wind speeds higher than 12m/s is all it takes for Alex to get a surfing rush.

3. The app you've written for Alex works very well, so well it pops up a new notification every few seconds! He'd like the `SurfingApp`
to run continuously in the background but only notify him every hour (if the weather conditions are ideal). Use the `ClockApp` for this.

4. Sometimes Alex has to travel for work, but he wouldn't want that to stop him from going surfing. He'd actually like the
`SurfingApp` to consider cities near by his current location where the weather conditions are good and also have a beach. Given there could
be multiple cities where to surf, change the notification messages to contain the city names at the end, e.g. `You should go surfing today to Waikiki` and `Go surfing ASAP to Waikiki!`.
The `GPSApp` can give you near by cities - neat!