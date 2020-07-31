# CoinCoins_Insolites_Web_API_Java


## Choix de la technologie et de l'approche

Le projet a été fait en JAVA pour coller principalement à ce qui a été fait en cours. Au final on a switch pour le faire en C# avec un ORM car je maîtrise beaucoup plus le c# et ensuite parce que avec un ORM beaucoup de problèmes de sécurité seront déjà catché.
Au final le projet JAVA a été abandonné pour des problèmes de compétence. On n'arrivait pas à faire de l'asynchrone, ni a sécurisé correctement la web api. Je vous propose donc d'aller regarder [la même web api mais en c# et en mieux](https://github.com/Kosolax/CoinCoinsInsolitesWebAPI)

## Fonctionnalité de la web api

* Une architecture
* Validation d'objets (pour éviter de rentrer de la mauvaise donnée en base de données)
* Seed (comme faker mais on le fait nous-mêmes dans l'api)
* CRUD disponible pour d'autres clients sur les objets User et Place (On n'avait pas forcément besoin de tout le CRUD pour l'exercice mais tant qu'à faire j'ai tout fait)
* Test unitaire sur toutes les méthodes exposées aux clients


## Architecture du projet

* Business Object : Objet métier. Par exemple en base de données un user ne possède pas de liste de photo. Pourtant dans l'application c'est le cas. Donc l'objet User métier doit posséder une liste de photo.

* Business : Logique qui permet de faire les opérations sur les objets métier. Par exemple quand on met à jour un user on doit mettre à jour ses photos au passage. Pourtant c'est une autre table dans la base de données mais d'un point de vue métier c'est ce qui est attendu. Le business sert donc à relier les objets entre eux pour une utilisation plus intuitive de l'api. 

* Entities : Objet qui se map directement sur les entités de la base de données.

* DataAccess : Logique qui permet de faire les opérations sql sur la base de donnée (elle utilise donc les entités)

* IBusiness : Pour les injections de dépendance. Elles doivent implémenter IDisposable pour fermer les ressources quand c'est possible.

* IDataAccess : Pour les injections de dépendance

* WebApi : Ici il y a les controllers et les fichiers de config. C# met à disposition IConfiguration qui permet de lire dans les fichiers de configuration. J'ai donc mis les clés de cryptage dedans. Les controllers permettent d'exposer les fonctions disponibles aux autres clients.

* WebApi.Route : Une bibliothèque de constant pour définir les routes des controllers
