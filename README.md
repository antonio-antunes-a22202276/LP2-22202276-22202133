![](diagrama.png?raw=true "Diagrama UML")

- URL Video: https://youtu.be/ofDxJqYTn8c

# Deisi-Jungle

---
### [PT] - Introdução

Certo dia, numa savana muito (muito) longe daqui, os animais decidiram descobrir quem seria
o melhor atleta entre eles. Para o fazer, definiram um conjunto de provas desportivas que iriam
disputar entre si - quem vencesse mais provas seria considerado o Rei da Selva. Rapidamente
a notícia chegou ao Tarzan, que não quis deixar de participar nas provas, convencido de que
facilmente as venceria.

A primeira prova a disputar é a prova de atletismo. Os animais vão-se reunir numa pista e
competir para determinar qual o melhor atleta.

> Trabalho desenvolvido no âmbito da disciplina de Linguagens de Progamação II

---
### [PT] - Customização do Tabuleiro

Com a inspiração de animais numa selva, foi decidido colocar fundos com esse tema, de forma a tornar mais "realista".

![](board3.png?raw=true "Board Customisation")

---
### [PT] - Escolha de Modelação de Classes

Como representado em UML e no código, nós decidimos utilizar várias das componentes aprendidas em programação orientada a objetos. Isto inclui nomeadamente a utilização de herança, classes abstratas e polimorfismo.
[AUTHORS.txt](AUTHORS.txt)
Como classe principal temos o GameManager, aquele que será o centro de todo o jogo e onde temos as principais funções para que possa ser criado o tabuleiro e efetuado as respetivas movimentações dos jogadores. Como auxiliar, o GameManager terá associado uma classe Player onde guardamos todas as informações de um jogador, como o seu id, nome, espécie e alguns outros dados. Cada jogador terá associado uma espécie em que dessa classe, temos outras duas para o seu tipo e energia. Considerámos importante o tipo de espécie ser uma classe abstrata e a utilização de herança para os diferentes tipos uma vez que nem todos têm as mesmas informações/opções.

Por outro lado, o GameManager tem também uma classe Food que contém a informação respetiva dos alimentos. Dessa mesma classe, considerámos importante utilizar herança, uma vez que cada alimento acaba por ter as suas diferenças, mas igualmente dados similares, por exemplo, todos os alimentos tem um id e um nome. Facilita também a adição de novos tipos de alimentos.


---
### [EN] - Introduction

One day, in a savannah very (very) far from here, the animals decided to find out who would be
the best athlete amongst them. To do so, they defined a set of sporting events that they would
compete against each other - whoever won the most events would be considered the King of the Jungle.
The news quickly reached Tarzan, who did not want to stop participating in the competitions, convinced that
he would easily win.

The first competition is the athletics race. The animals will gather on a track and
compete to determine which is the best athlete.

> Work developed in the scope of the subject Progamming Languages II

---
### [EN] - Board Customisation

With the inspiration from animals in the jungle, it was decided to put background with that theme, in a way to turn more "realistic".

![](board3.png?raw=true "Board Customisation")

---
### [EN] - Choice of Class Modelling

As represented in UML and in the code, we have decided to use several of the components learned in object-oriented programming. This includes namely the use of inheritance, abstract classes and polymorphism.

As the main class we have the GameManager, the one that will be the centre of the whole game and where we have the main functions so that the board can be created and the players can move around. As an auxiliary, the GameManager will have a Player class associated where we store all the player's information, such as id, name, species and some other data. Each player will have a species associated to that class, and from that class, we have two others for their type and energy. We considered important the species type being an abstract class and the use of inheritance for the different types since not all of them have the same information/options.

On the other hand, the GameManager also has a Food class which contains the respective information of the food. From this same class, we considered it important to use inheritance, since each food ends up having their differences, but also similar data, for example, all foods have an id and a name. It also makes it easier to add new types of food.