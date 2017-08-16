%pacman_initial_position(-XPosition, -YPosition).
pacman_initial_position(3,3).

%ghost_initial_position(-XPosition, -YPosition).
ghost_initial_position(15,5).

%next_position(-XPosition, -YPosition, +NewXPosition, +NewYPosition).
next_position(XPosition, YPosition, NewXPosition, YPosition):- NewXPosition is XPosition+1.

%pacman_lives(-Lives).
pacman_lives(3).

%ghost_lives(-Lives).
ghost_lives(1).

%move is responsible for characters' movement; if character is moving himself into the
%street, he can make the move, otherwhise he cannot.
%	Input parameters:
% 	-character's current X position;
% 	-character's current Y position;
% 	-direction of the movement (up, down, left, right).
% Output parameters:
% 	-character's X position after the movement (if the character is able to move himself);
% 	-character's Y position after the movement (if the character is able to move himself).
%move(+XCurrentPosition, +YCurrentPosition, +Direction, -NewXPosition, -NewYPosition).
move(X,Y,D,X1,Y1):-  D==left, X1 is X-1, Y1 is Y, street(X1,Y1),!.
move(X,Y,D,X1,Y1):-  D==right, X1 is X+1, Y1 is Y, street(X1,Y1),!.
move(X,Y,D,X1,Y1):-  D==up, X1 is X, Y1 is Y+1, street(X1,Y1),!.
move(X,Y,D,X1,Y1):-  D==down, X1 is X, Y1 is Y-1, street(X1,Y1).

%eat_object allows pacman to eat objects.
%	Input parameters:
% 	-pacman with this structure: pacman(X,Y,Lives,Score);
% 	-list of objects that can be eaten.
% Output parameters:
% 	-the new pacman's score resulting after summing eatable object value to pacman's score;
% 	-list containing objects that have not been eaten yet.
%eat_object(+pacman(X,Y,_,Score), +EatableObjectList, -NewScore, -ListOfRemainingEatableObject).
eat_object(pacman(_,_,_,S),[],S,[],"").
eat_object(pacman(PX,PY,_,S),[eatable_object(PX,PY,V,N)|T],NS,T,N):- NS is S+V,!.
eat_object(pacman(PX,PY,_,S),[H|T],NS,[H|L1],N):- eat_object(pacman(PX,PY,_,S),T,NS,L1,N).

%pacman_victory checks if pacman has won.
% Input parameters:
% 	-pacman with this structur: pacman(X,Y,Lives,Score);
% 	-list containing object that have not been eaten yet.
%pacman_victory(+pacman(_,_,Lives,_), +ListOfRemainingEatableObject).
pacman_victory(pacman(_,_,L,_),[]):- L>=1.

%ghosts_victory checks if pacman has dead, then ghosts won.
% Input parameters:
% 	-pacman with this structur: pacman(X,Y,Lives,Score).
%ghosts_victory(+pacman(_,_,Lives,_)).
ghosts_victory(pacman(_,_,0,_)).

%ghost_defeat checks if one or more ghosts have been eaten from pacman.
%Input parameters:
% 	-pacman with this structure: pacman(X,Y,Lives,Score);
% 	-a ghosts' list (each ghost has the following structure: ghost(X, Y, Score, Color));
% 	-number of ghosts already eaten.
% Output parameters:
% 	-the new pacman score thus, current score plus value of ghost eaten;
% 	-a list containg colors associated to each ghost that pacman ate.
%ghost_defeat(+pacman(X,Y,_,Score), +GhostList, +NumberOfGhostEaten, -NewPacmanScore, -ListOfEatenGhostsColor).
ghost_defeat(pacman(_, _, _, PS),[],N,PS,[]).
ghost_defeat(pacman(PX,PY,_,PS),[ghost(PX,PY,_,C)| T],N,NPS,[C|L1]):- ghost_defeat(pacman(PX, PY, _,PS),T,N+1,NPS1,L1), NPS is NPS1+(200*(N+1)), !.
ghost_defeat(pacman(PX,PY,_,PS),[_|T],N,NPS,L1):- ghost_defeat(pacman(PX,PY,_,PS),T,N,NPS,L1), !.

%eat_pacman checks if pacman has been eaten by ghost because they are in the same position
% Input parameters:
% 	-pacman with this structure: pacman(X,Y,Lives,Score);
% 	-list of ghosts (each ghost has the following structure: ghost(X, Y, Score, Color));
% Output parameters:
% 	-number of pacman lives left
% 	-the new ghost score thus, current score plus value of pacman eaten
% 	-the color of ghost that ate pacman
%eat_pacman(+pacman(X,Y,Lives,_), +GhostList, -NewPacmanLives, -NewGhostScore, -GhostName).
eat_pacman(pacman(_,_,NL,_),[],NL,0,"").
eat_pacman(pacman(PX,PY,NL,_),[ghost(PX,PY,GS,GN)|T],NL1,NGS,GN):- NL1 is NL-1, NGS is GS+500, !.
eat_pacman(pacman(PX,PY,NL,_),[_|T],NL1,NGS,GN):- eat_pacman(pacman(PX,PY,NL,_),T,NL1,NGS,GN).
