; Examples of using map with lambda functions
; Q: Why don’t we define these anonymous fns
; with `defn`?
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

(stats [3 4 10]) ; => (17 3 17/3)

(stats [80 1 44 13 6]) ; => (144 5 144/5)

