; max
(max 0 1 2) ; => 2

; apply
(apply max [0 1 2])

; conj, written in terms of into
(defn my-conj
  [target & additions]
  (into target additions))
(my-conj [0] 1 2 3) ; => [0 1 2 3]

; into, written in terms of conj and apply
(defn my-into
  [target additions]
  (apply conj target additions))

; partial, takes the fn and the args you want to partially apply
; notice we’re not using `defn` to bind
(def add10 (partial + 10))
(add10 3) ; => 13
(def add-missing-elements
  (partial conj ["oxygen" "cadmium" "boron" "magnesium"]))

; partial, written in terms of into using apply (love it!)
(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn (into args more-args))))

; a dumb logger built with partial
(defn my-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))
(def warn (partial my-logger :warn))
(def emergency (partial my-logger :emergency))

; complement, written in terms of filter and not
(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))
