(def vampire-database
  {0 {:makes-blood-puns? false :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true :has-pulse? true :name "Donald Duck"}})

(defn vampire-related-details [ssn]
  (Thread/sleep 1000)
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
(identify-vampire (range 0 1000000))








