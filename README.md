# README - Projet eCommerce

Ce projet est une application eCommerce développée avec **Spring Boot** pour le backend et **React** pour le frontend.

## Prérequis

Avant de commencer, assurez-vous d avoir installé :
- [Java 17+](https://adoptopenjdk.net/)
- [Maven](https://maven.apache.org/)
- [Node.js & npm](https://nodejs.org/)

## Lancement du Backend

1. Ouvrez un terminal et accédez au dossier `ecommerce-backend` :
   ```sh
   cd ecommerce-backend
   ```
2. Lancez l application Spring Boot avec Maven :
   ```sh
   mvn spring-boot:run
   ```
   Par défaut, le serveur démarre sur **http://localhost:8080**.

## Lancement du Frontend

1. Ouvrez un terminal et accédez au dossier `ecommerce-frontend` :
   ```sh
   cd ecommerce-frontend
   ```
2. Installez les dépendances si ce n est pas encore fait :
   ```sh
   npm install
   ```
3. Lancez l application React :
   ```sh
   npm start
   ```
   L application frontend sera accessible par défaut sur **http://localhost:3000**.


## Documentation API 

Nous utilisons Springdoc OpenAPI pour générer la documentation de l API.

Une fois le backend démarré, vous pouvez accéder à la documentation Swagger UI ici :

JSON brut : http://localhost:5454/v3/api-docs

Interface Swagger UI : http://localhost:5454/swagger-ui/index.html

## Remarque
- Assurez-vous que le backend est bien démarré avant de lancer le frontend.
- Vérifiez que les ports ne sont pas déjà utilisés par d autres applications.

Bonne utilisation ! 🚀

