# API Spring Boot – Gestion des Thèmes et Leçons

## Description

Cette API Spring Boot permet de gérer des **thèmes pédagogiques** et leurs **leçons associées**.  
Elle expose des endpoints REST pour :

- Créer des thèmes
- Ajouter des leçons à un thème
- Récupérer la liste des leçons avec pagination

L’API utilise une architecture **Controller / Service / DTO**.

---

## Base URL

```
/api
```

---

## Endpoints

### 📚 Leçons

#### Récupérer toutes les leçons (avec pagination)

```
GET /api/lessons/
```

**Description :**  
Retourne une liste paginée de leçons avec les informations de leur thème.

**Paramètres optionnels :**
- `page` : numéro de page
- `size` : nombre d’éléments par page
- `sort` : critère de tri

**Réponse :**
- `200 OK`
- `Page<LessonDto>`

---

### 🗂️ Thèmes

#### Créer un thème

```
POST /api/themes
```

**Description :**  
Crée un nouveau thème avec un nom unique.

**Body (JSON) :**
```json
{
  "name": "Nom du thème"
}
```

**Réponse :**
- `201 Created`
- `ThemeDto`

---

#### Ajouter une leçon à un thème

```
POST /api/themes/{id}/lessons
```

**Description :**  
Ajoute une leçon à un thème existant.

**Paramètres :**
- `id` : identifiant du thème

**Body (JSON) :**
```json
{
  "title": "Titre de la leçon",
  "content": "Contenu de la leçon"
}
```

**Réponse :**
- `200 OK`

---

## Technologies utilisées

- Java springboot
- API REST
- DTO Pattern

---


## Contributeurs
- Enzo Volpato
- Quentin Marty
- Romain Devulder