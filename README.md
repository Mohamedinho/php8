# Web Service PHP 8 - Gestion des Étudiants 🎓

Ce projet est une application Android moderne développée avec **Jetpack Compose** qui communique avec un **Web Service PHP 8** et une base de données **MySQL**. Elle permet de gérer une liste d'étudiants via des opérations CRUD (Create, Read, Update, Delete).

---

## 📸 Galerie du Projet

### 1. Interface Android
| Formulaire d'Ajout | Liste des Étudiants |
| :---: | :---: |
| <img width="358" height="795" alt="10" src="https://github.com/user-attachments/assets/c56b448c-e28d-40ed-bc4c-5d584ac51d47" />|  <img width="364" height="797" alt="3" src="https://github.com/user-attachments/assets/884271c7-3712-4598-8bfe-e5d818bc4bef" />|

| Modification d'un Étudiant | Confirmation de Suppression |
| :---: | :---: |
|  <img width="373" height="788" alt="6" src="https://github.com/user-attachments/assets/0b1a0067-0e73-4501-aad5-0a406a4ca7dc" />| <img width="380" height="804" alt="7" src="https://github.com/user-attachments/assets/6366b8a3-8046-4448-a1b8-be3109dd893e" />|

### 2. Backend & Base de Données
| phpMyAdmin (Table etudiant) | Réponse JSON (Web Service) |
| :---: | :---: |
| <img width="1174" height="528" alt="5" src="https://github.com/user-attachments/assets/60497429-1253-4414-983d-667079824684" />| <img width="1332" height="167" alt="2" src="https://github.com/user-attachments/assets/1bac8a49-ee10-46b7-a88b-2ad6367a185f" />|

---

## 🛠️ Technologies Utilisées

### Backend (Serveur)
- **PHP 8.2** : Langage serveur pour le Web Service.
- **MySQL / PDO** : Gestion de la base de données `school1`.
- **JSON** : Format d'échange de données entre Android et le serveur.

### Frontend (Mobile)
- **Kotlin** : Langage de programmation moderne pour Android.
- **Jetpack Compose** : Framework UI déclaratif.
- **Volley** : Bibliothèque pour la gestion des requêtes HTTP (POST/GET).
- **Gson** : Sérialisation et désérialisation des données JSON.

---

## 🚀 Configuration et Installation

### 1. Préparation de la Base de Données (XAMPP)
- Créer une base de données nommée `school1`.
- Importer le schéma suivant :
   ```sql
   CREATE TABLE etudiant (
       id INT(11) NOT NULL AUTO_INCREMENT,
       nom VARCHAR(50) NOT NULL,
       prenom VARCHAR(50) NOT NULL,
       ville VARCHAR(50) NOT NULL,
       sexe VARCHAR(10) NOT NULL,
       PRIMARY KEY (id)
   ) ENGINE=InnoDB;
   ```
### 2.format JSON Postman:

<img width="1169" height="1007" alt="11" src="https://github.com/user-attachments/assets/2e5ce4e4-ef4f-42fd-b350-28018c40c796" />


### 3. Installation du Web Service
- Copier le dossier `projet` dans `C:\xampp\htdocs\`.
- Structure attendue :
    - `projet/ws/` : Scripts de l'API (create, load, delete, update).
    - `projet/connexion/` : Classe de connexion PDO.
    - `projet/service/` : Logique métier (EtudiantService).
    - `projet/classes/` : Modèle de données (Etudiant).

### 4. Configuration Android
- L'URL de base est configurée sur `http://10.0.2.2/projet/ws/` pour l'émulateur.
- Le fichier `network_security_config.xml` est configuré pour autoriser le trafic HTTP en local.

---

## 📂 Structure du Code Android
- **`beans/Etudiant.kt`** : Modèle de données côté mobile.
- **`network/ApiConfig.kt`** : Centralisation des URLs de l'API.
- **`network/VolleySingleton.kt`** : Gestionnaire de file d'attente pour Volley.
- **`MainActivity.kt`** : Point d'entrée avec Navigation Bar.
- **`AddEtudiantScreen.kt`** : Interface d'ajout avec validation.
- **`ListEtudiantScreen.kt`** : Liste interactive avec Dialogs de gestion.

---

## 👨‍💻 Auteur
Développé par **Mohamed DOUASSI** dans le cadre de l'apprentissage des Web Services avec Android et PHP 8.
