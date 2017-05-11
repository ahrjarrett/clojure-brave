(map (fn [name] (str "Hi, " name))
  ["Curt Cobain" "Tobias Funke"])

(def my-special-multiplier (fn [n] (* n 3)))
(my-special-multiplier 8) ; 24

;; Shorthand
;; Where #() signifies an anon fn, and % is the param
(#(* % 3) 8) ; 24

;; Fn at top, rewritten:
(map #("Hi, " %)
  ["Curt Cobain" "Tobias Funke"])

;; NOTES:
;; Kinda weird not declaring the params in a vector,
;; instead jumping straight to using them in the return statement

;; Anon fns with multiple parameters
;; % is equivalent to %1, counts up from there (%2, %3)
(#(str %1 " and " %2) "beefy blocks" "whiskey lemonade")

;; Anon fns with rest paramenters
;; (Using the built-in identity function below)
(#(identity %&) "Babycakes" "Steve" "Frank") ; ("Babycakes" "Steve" "Frank")
(#(clojure.string/join %&) "Bisu" "Savior" "Boxer") ;; => "BisuSaviorBoxer"


;; Functions that return functions:
(defn inc-maker
  [inc-by]
  #(+ % inc-by))
(def inc3
  (inc-maker 3))
(inc3 7)
