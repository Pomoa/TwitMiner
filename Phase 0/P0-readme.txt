WriterCSV :
Tout d'abord, on cr�e le fichier - on le d�truit s'il existe d�j� et on le recr�e puis on cr�e le flux d'�criture correspondant au fichier .csv
En suite, on parcourt la liste de tweets et on les inscrit, mot par mot, dans le fichier, en inscrivant avant le message la date du tweet et l'utilisateur qui l'a post�.
On s�pare les mots, la date et l'utilisateur par des ';' et on les entoure par des guillemets.
En suite, on s'assure de fermer correctement le flux.

exec :
On cr�e ce dont on a besoin pour r�cup�rer les tweets et on initialise les variables le cas �ch�ant.
Puis on commence � r�cup�rer les tweets. 
Ne pouvant r�cup�rer que 100 tweets, on fait l'op�ration de r�cup�ration 100 fois (afin d'en avoir 10000), en changeant le MaxId de la requ�te pour l'Id du dernier tweet r�cup�r� +1
Afin de se rep�rer dans la longue ex�cution de la r�cup�ration, on affiche � chaque tour de boucle le pourcentage de progression.

A la fin de l'ex�cution, on affiche le nombre de tweets r�cup�r�s, ainsi que le temps d'ex�cution (en ns).