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

;; From Clojure docs on COMP:

;; Takes a set of functions and returns a fn that is the composition
;; of those fns. The returned fn takes a variable number of args,
;; applies the rightmost of fns to the args, the next
;; fn (right-to-left) to the result, etc.

;;(defn my-comp
;;  [& fs]
;;  (fn [& args]
;;    (reduce (fn [result-thus-far next-fn] (next-fn result-thus-far))
;;            (apply (last fs) args)
;;            (rest (reverse fns)))))

(defn my-comp
  [& fns]
  (reduce (fn [f g]
            #(f (apply g %&))) fns))

(defn answer-2 [n]
  ((my-comp inc inc) n))

(answer-2 12) ;; 14

;; 3. Implement the assoc-in function.
;;(defn my-assoc-in
;;  [m [k & ks] v]
;;
;;  )


