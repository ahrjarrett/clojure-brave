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

