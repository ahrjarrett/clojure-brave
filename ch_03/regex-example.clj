(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

(matching-part {:name "left-eye" :size 1})
; (matching-part {:name "head" :size 1})
