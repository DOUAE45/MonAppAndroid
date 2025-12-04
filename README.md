# M-Health Patients

Application Android (Java) offrant un portail m-Health complet, pensé pour une navigation fluide et un rendu visuel harmonieux. L’interface exploite Material Design (dialogues, cartes, listes dynamiques) afin d’assurer un parcours utilisateur cohérent sur mobile.

## Vue d’ensemble fonctionnelle

| Espace | Expérience proposée |
| --- | --- |
| **Patient** | Consultation du dossier, planification de rendez-vous, renouvellements d’ordonnance, messagerie sécurisée et notifications temps réel. |
| **Médecin** | Suivi des patients, lecture des résultats médicaux, commentaires contextuels, réponses rapides aux messages. |
| **Administrateur** | Gestion des comptes, ajout d’alertes système, journalisation d’audits via boîtes de dialogue dédiées. |
| **Secrétaire** | Organisation des rendez-vous, synchronisation des dossiers administratifs, escalade des demandes urgentes. |

Chaque vue est alimentée par un couple `Fragment` + `ViewModel` dialoguant avec des dépôts en mémoire (`AuthRepository`, `CareRepository`) afin de simuler les échanges m-Health.

## Architecture

```
app/
  src/main/java/com/mhealth/app/
    data/model/         # Entités métiers (patients, rendez-vous, messages…)
    data/local/         # Room (DAO, entités, seed) pour la persistance
    data/repository/    # Auth & dépôt clinique offline-first
    ui/auth/            # Connexion sécurisée avec rôle choisi
    ui/dashboard/       # Fragments & ViewModels par rôle (patient, médecin, admin, secrétaire)
```

### Données & persistance
- Base locale Room (`MHealthDatabase`) alimentée au premier lancement avec les données de démonstration (patients, rendez-vous, messages, alertes…).
- `CareRepository` s’appuie désormais sur des `LiveData` venant de Room : toute action (prise de RDV, message, création d’utilisateur) persiste et survit aux relances de l’app.
- Couches prêtes pour brancher un backend REST (Retrofit + OkHttp déjà intégrés).

### UX & UI
- Thème Material Components personnalisable (`Theme.MHealth`).
- Dialogues contextuels (prise de rendez-vous, alertes admin, messages médecin) pour capter les entrées utilisateur.
- Mise à jour instantanée des cartes (Recycler-like) après chaque action pour garder une cohérence visuelle.

## Mise en route
1. Ouvrir le dossier dans Android Studio (Giraffe ou +).
2. Laisser Gradle télécharger les dépendances (AGP 8.4, JDK 17).
3. Sélectionner un appareil/emulateur API 24+.
4. `Run > Run 'app'` pour lancer la build et l’installation (Room se peuple automatiquement si la base est vide).

### Comptes de démonstration

| Rôle | Email | Mot de passe |
| --- | --- | --- |
| Patient | `patient@mhealth.com` | `patient123` |
| Médecin | `doctor@mhealth.com` | `doctor123` |
| Admin | `admin@mhealth.com` | `admin123` |
| Secrétaire | `secretary@mhealth.com` | `secretary123` |

## Pistes d’évolution
- Connexion à un backend FHIR/REST réel (Retrofit/OkHttp).
- Persistance Room + WorkManager pour la synchronisation hors ligne.
- Notifications push (Firebase Cloud Messaging).
- Authentification OIDC/SSO avec MFA et politiques de sécurité renforcées.

Ce socle UI/UX offre une base solide pour continuer vers une solution m-Health de production tout en garantissant une présentation raffinée et des interactions fluides.

