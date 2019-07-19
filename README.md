# iWantMovie

Why use Shared Preference?

- This app uses Shared Preference because it is more suitable for saving small value and any primitive data like string, booleans, floats, ints and longs.

Targeted function of Shared Preference is the will save the last screen that the user was on. When the app restores, it should present the user with that screen.

Why use MVP?

- MVP makes views independent from our data source. We divide the application into at least three different layers, which lets us test them independently. With MVP we take most of the logic out from the activities.
- With MVP, application is easily extensible and maintainable.
- View and Model arenít tightly  coupled for clear separation of concerns.
