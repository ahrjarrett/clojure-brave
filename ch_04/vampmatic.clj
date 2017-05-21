

(def vampire-database
  {0 {:makes-blood-puns? false :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true :has-pulse? true :name "Donald Duck"}})

(defn vampire-related-details [ssn]
  (Thread/sleep 100)
  (get vampire-database ssn))

(defn vampire? [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))

(defn identify-vampire [ssns]
  (first (filter vampire?
                 (map vampire-related-details ssns))))


(time (vampire-related-details 0))
;; For some reason CIDER isn't printing how long the operation takes?

(time (def mapped-details (map vampire-related-details (range 0 1000000))))

;; Careful, this buffer takes ~30 seconds to run
;; b/c Clojure chunks lazy seqs
(:name (identify-vampire (range 0 1000000))) ;; => "Damon Salvatore"


;; Incidential Stuff:
(take 3 (repeatedly (fn []
                      str "Hello World")))

(defn even-n
  ([] (even-n 0))
  ([n] (cons n (lazy-seq (even-n (+ n 2))))))

;;(take 10 even-n) ;; not working, or compiler being slow?

(into #{} (map identity {:name "Brad Neeley"}))

(defn my-conj
  [target & additions]
  (into target additions))

(my-conj [] "ice-cream?" "I Hate Ice Cream")

(max 1 2 3)
;; This won't work:
;; (map [1 2 3])

;; Apply to the rescue:
(apply max [1 2 3]) ;; => 3

(defn my-into [target additions]
  (apply conj target additions))

(my-into [] [1 2 3])


;; Demonstration of PARTIAL:
(def add-missing-elements
  (partial conj ["Steve" "Frank" "Babycakes" "Zoomacrum" "Velasquez" "Monster Mash"]))

(add-missing-elements "Neely")

(defn my-partial
  [partial-fn & args]
  (fn [& more-args]
    (apply partial-fn (into args more-args))))

(def add100 (my-partial + 20))

(add100 13) ;; => 33



;; Now we're going to COMPLEMENT the vampire predicate:
(def not-vampire? (complement vampire?))
;;(defn identify-humans [ssns]
;;  (filter #(not (vampire?) %))
;;  (map vampire-related-details ssns))

(defn identify-humans
  [ssns]
  (filter not-vampire?
          (map vampire-related-details ssns)))
(identify-humans 0)
;;(identify-vampire 0)

(defn my-complement
  [fun]
  (fn [& args]
    (not (apply fun args))))
(def my-pos? (complement neg?))

(my-pos? 1)

