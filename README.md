# JSP_Servlet by Kadar Issam
Fonctionnalité fonctionnel : 
- Creation d'un Utilisateur
- Creation d'un Evenement 
- Voir les details d'un Evenement
- Liste de tout les Evenement
- Gestion des Sessions (Login)

Fonctionnalité non aboutit:
- ManyToMany entre utilisateur et evenement afin de lister les poarticipants d'un evenement ou les evenement auquels une personne participe
- Filtrer une liste d'évenement 
- Creation d'évenements seulement par un administrateur
- exportation des statistique de reservation

La principale difficulé que j'ai rencontrer est demarage du projet où j'ai perdu énormement de temps avec Eclipse_link. Je suis donc passé à Hibernate.
Ensuite, une autre difficulté que j'ai rencontré est la mise en place du ManyToMany entre les Evenement et les Utilisateurs.

Les Routes disponibles sont:
http://localhost:8080/EnterpriseApplication2-war/eventServlet => list event
http://localhost:8080/EnterpriseApplication2-war/eventServlet?id=1 => description evenement
http://localhost:8080/EnterpriseApplication2-war/user/login
http://localhost:8080/EnterpriseApplication2-war/user/sign
