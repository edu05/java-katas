# simulate the service provided by Amazon

The goal is to get more exposure to Unit Testing tools in Java with the Mockito library.

Setting: Your company wants to write a copy of Amazon so it can eventually overtake it. Your company has identified the minimum
components needed for a basic Amazon service are:

- a `Warehouse` to dispatch `Item`s to `Customer`s
- a `Wallet` for billing money to the `Customer`s
- a `Notifier` to confirm `Customer`s of their purchases for good `Customer` experience

Each of those components will be written by different teams. These teams will develop these components in parallel to reduce the
release date of your own Amazon. You'll be the main developer in charge of writing the code that glues up everything - following
up to date TDD practices. The fact that the code for those components hasn't been written yet won't be a problem thanks to
Java's Mockito library and its facilities for faking the behaviour of dependencies.

1. When a `Customer` buys an `Item` it gets dispatched by the `Warehouse`
2. When a `Customer` buys an `Item` they have to pay for it with the `Wallet`
3. When a `Customer` buys an `Item` they get notified about their purchase with the `Notifier`