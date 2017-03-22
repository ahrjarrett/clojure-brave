(defn inc-maker
  [inc-by]
  #(+ % inc-by))

(defn inc3 (inc-maker 3))

(inc3 7) ; => 10
