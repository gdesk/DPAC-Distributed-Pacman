%pacman(Xposition, Yposition, lives, score)
pacman_initial_position(30,30).
pacman_lives(3).

%passate a prolog da java/scala le liste di:
%-eatableObject(Xposition, YPosition, V). (es. pill, cherry,...)
%-ghost(Xposition, Yposition, score, color)
%-pacman(X, Y, L, S)
%-street(X,Y)
%character(Xposition, Yposition, score). (?)											%utile per l'inserimento futuro di altri personaggi

%move(+Xactual, +Yactual, +direction, -newX, -newY)
move(X, Y, D, X1, Y1):-  D == left, X1 is X-1, Y1 is Y, street(X1,Y1),!.
move(X, Y, D, X1, Y1):-  D == right, X1 is X+1, Y1 is Y, street(X1,Y1),!.
move(X, Y, D, X1, Y1):-  D == up, X1 is X, Y1 is Y+1, street(X1,Y1),!.
move(X, Y, D, X1, Y1):-  D == down, X1 is X, Y1 is Y-1, street(X1,Y1).

%eat_object(+pacman(X,Y,_,score), +eatableObjectList, -newScore, -listOfRemainingEatableObject)
%eat_object(pacman(PX,PY,_,S),EOL, NS):- member(eatable_object(PX,PY,V), EOL), NS is S+V,!.
eat_object(pacman(_,_,_,S),[], S,[], "").
eat_object(pacman(PX,PY,_,S),[eatable_object(PX,PY,V,N)|T], NS,T,N):- NS is S+V,!.
eat_object(pacman(PX,PY,_,S),[H|T], NS, [H|L1], N):- eat_object(pacman(PX,PY,_,S),T, NS, L1,N).

%pacman_victory(+pacman(_,_,lives,_),+listOfRemainingEatableObject)
pacman_victory(pacman(_,_,L,_),[]):- L >=1.

%pacman_death(+pacman(_,_,lives,_)).
pacman_death(pacman(_,_,0,_)).

%metodo da richiamare in scala solo quando i fantasmi sono blu
%tenere il conto dei 10 secondi dopo che pacman ha mangiato la power pill
%tenere memorizzato il numero di fantasmi mangiati (value+200)
%ghost_defeat(+pacman(X,Y,_,Score), +[ghosts_list], +startValueToAdd, -newPacmanScore, -ListOfEatenGhostsColor).
ghost_defeat(pacman(_, _, _, PS), [], V, PS, []).
ghost_defeat(pacman(PX, PY, _, PS), [ghost(PX, PY,_,C) | T], V, NPS, [C|L1]):- ghost_defeat(pacman(PX, PY, _, PS), T, V+200, NPS1, L1),  NPS is NPS1+V, !.
ghost_defeat(pacman(PX, PY, _, PS), [_| T], V, NPS, L1):- ghost_defeat(pacman(PX, PY, _, PS), T, V, NPS, L1), !.

%eat_pacman(+pacman(X,Y,lives,_), +[ghost_list], -new_pacman_lives, -new_ghost_score, -ghost_color).
eat_pacman(pacman(_,_,NL,_),[], NL, 0, "").
eat_pacman(pacman(PX,PY,NL,_), [ghost(PX,PY,GS,C) | T], NL1, NGS, C):- NL1 is NL-1, NGS is GS+500, !.
eat_pacman(pacman(PX,PY,NL,_), [_|T], NL1, NGS, C):- eat_pacman(pacman(PX,PY,NL,_), T, NL1, NGS, C).

