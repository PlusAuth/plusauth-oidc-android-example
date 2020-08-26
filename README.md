# PlusAuth Android Example

This is a very simple Android project demonstrating basic authentication flows such as register, login and logout. To keep things simple we used [PlusAuth OpenId Connect Library For Android](https://github.com/PlusAuth/plusauth-oidc-android) for authentication.

## Table of Contents

- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [License](#license)

## Prerequisites

Before running the project, you must first follow these steps:

1. Create a Plusauth account and a tenant at [PlusAuth Dashboard](https://dashboard.plusauth.com)
2. Navigate to `Clients` tab and create a client of type `Native Application`.
3. Go to details page of the client that you've just created and set the following fields as:

- Redirect Uris:  com.plusauth.androidexample:/callback
- Post Logout Redirect Uris:  com.plusauth.androidexample:/callback

Finally note your Client Id and domain(e.g. test.plusauth.com) for app configuration on the next step.

## Getting Started

Edit the `PlusAuthInstance` class using your Client Id and PlusAuth host.

That's all! Hit the 'Run' button in Android Studio to start the app. 

## License

This project is licensed under the MIT license. See the [LICENSE](LICENSE) file for more info.
