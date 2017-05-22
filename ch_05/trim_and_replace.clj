(require '[clojure.string :as s])

;; This is a basic example of function composition:
(defn clean
  [text]
  (s/replace (s/trim text) #"dog" "DOGE"))

(clean "Grizzly Bear Jones Morrison is such a good but nosy dog.")
;; => "Grizzly Bear Jones Morrison is such a good but nosy DOGE."

;; COMP, or compose, works just like you'd expect: functions are
;; applied right-to-left:
((comp inc /) 3 4) ;; => 7/4


;; From page 116, here's another way to write CLEAN using reduce
;; to compose functions together (cool!)
(defn string-clean
  [text]
  (reduce (fn [string string-fn] (string-fn string))
          text
          [s/trim #(s/replace % #"lollercopter" "LOLLERCOPTER")]))
