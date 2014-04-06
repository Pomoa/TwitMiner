WriterCSV :
Tout d'abord, on crée le fichier - on le détruit s'il existe déjà et on le recrée puis on crée le flux d'écriture correspondant au fichier .csv
En suite, on parcourt la liste de tweets et on les inscrit, mot par mot, dans le fichier, en inscrivant avant le message la date du tweet et l'utilisateur qui l'a posté.
On sépare les mots, la date et l'utilisateur par des ';' et on les entoure par des guillemets.
En suite, on s'assure de fermer correctement le flux.

exec :
On crée ce dont on a besoin pour récupérer les tweets et on initialise les variables le cas échéant.
Puis on commence à récupérer les tweets. 
Ne pouvant récupérer que 100 tweets, on fait l'opération de récupération 100 fois (afin d'en avoir 10000), en changeant le MaxId de la requête pour l'Id du dernier tweet récupéré +1
Afin de se repérer dans la longue exécution de la récupération, on affiche à chaque tour de boucle le pourcentage de progression.

A la fin de l'exécution, on affiche le nombre de tweets récupérés, ainsi que le temps d'exécution (en ns).