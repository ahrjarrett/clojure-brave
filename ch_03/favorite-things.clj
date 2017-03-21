;; rest parameters example
(defn favorite-things
  [name & things]
  (str "Hi " name ", these are my favorite things: "
      (clojure.string/join ", " things)))
