(seq '(1 2 3)) ; => (1 2 3)
(seq #{1 2 3}) ; => (1 2 3)
(seq [1 2 3]) ; => (1 2 3)

(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])
