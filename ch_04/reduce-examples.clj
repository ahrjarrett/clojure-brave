(reduce (fn [new-map [key val]]
         (if (> val 4)
           (assoc new-map key val)
           new-map))
        {}
        {:human 4.1
         :critter 3.9})
; => {:human 4.1}

(reduce (fn [make-new-map [key val]]
          (assoc make-new-map key (inc val)))
          {}
          {:max 30 :min 10})
; => {:max 31, :min 11}
