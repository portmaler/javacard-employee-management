# Java Card Employee Management Project

This repository contains a Java Card applet and a JavaFX client application for communication with the applet. The applet is designed to perform simple counter operations, and the client allows you to interact with the applet using APDU commands.

## Table of Contents

- [Java Card Applet](#java-card-applet)
- [JavaFX Client Application](#javafx-client-application)
- [Getting Started](#getting-started)
- [Usage](#usage)
- [Contributing](#contributing)
- [License](#license)

## Java Card Applet

The Java Card applet (`compteur` package) is a simple applet that implements basic counter operations:

- Increment the counter
- Decrement the counter
- Get the current counter value
- Initialize the counter to a specific value

The applet is designed to be loaded onto a Java Card platform and can be interacted with using APDU commands.

## JavaFX Client Application

The JavaFX client application (`compteurclient` package) provides a graphical user interface (GUI) for interacting with the Java Card applet. It allows you to send APDU commands to the applet to perform counter operations and displays the results.

## Getting Started

To get started with this project, you'll need the following:

- A Java Card simulator or physical card to install the applet
- Java Development Kit (JDK) installed on your development machine
- JavaFX SDK (if not included in your JDK distribution)

Clone this repository to your local machine:

```shell
git clone https://github.com/your-username/your-repo.git
