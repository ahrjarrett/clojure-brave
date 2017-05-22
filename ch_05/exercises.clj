;; 1.
;; You used (comp :intelligence :attributes) to create a function
;; that returns a character’s intelligence. Create a new function,
;; attr, that you can call like (attr :intelligence) and that does
;; the same thing.

(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defn attr
  [attribute char]
  (let [attributes (get char :attributes)]
    (attribute attributes)))

(def c-int (partial attr :intelligence))

(c-int character) ;; => 10


;; 2. Implement the comp function.

(defn my-comp
  []
  )







