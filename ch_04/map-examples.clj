(map inc [1 2 3]) ; => (2 3 4)

(map str ["a" "b" "c"] ["A" "B" "C"]) ; => ("aA" "bB" "cC")
; Which is basically the same as doing:
(list (str "a" "A") (str "b" "B") (str "c" "C"))

; This is a program for vampires on a diet
; I really love how easy it is to structure/
; destructure data!
(def human-consumption   [8.1 7.4 6.9 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
(map unify-diet-data human-consumption critter-consumption)
; => ({:human 8.1, :critter 0.0}
;     {:human 7.4, :critter 0.2}
;     {:human 6.9, :critter 0.3}
;     {:human 5.0, :critter 1.1})
; Are the commas ^ necessary?
