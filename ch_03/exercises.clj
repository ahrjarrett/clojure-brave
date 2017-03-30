; EXERCISE 1: Use the str, vector, list, hash-map & hash-set fns
;    NOTES: Notice that a set’s order does not matter,
;    just that the elements be unique.

(def cool-str
  (str "No " "sleep " "till " "Brooklyn!"))
(println cool-str)
; => No sleep till Brooklyn!

(def cool-set
  (set
    ["no" "sleep" "sleep" "til" "Brooklyn"]))
(println cool-set)
; => #{"Brooklyn" "sleep" "til" "no"}
(println (set? cool-set)) ; => true
(println (list? cool-set)) ; => false

(def cool-vector
  (vector 2 4 6 6 7 7 8))
(println cool-vector)
; => [[1 4 6 6 7 7 8]]
;    NOTES: Why doesn't vector take an array like the set above?
(println (vector? cool-vector)) ; => true
(println (list? cool-vector)) ; => false

(println (list (/ 1 2) "elbow"))
; => (1/2 "elbow")

(println (hash-map :key1 1 :key1 2))
; => {:key1 2}
;    NOTES: notice if you create a hash-map with the function,
;    duplicate keys will remap to the last value. However with
;    a hash-map “literal” the compiler will throw an Illegal Arg
;    Exception : Duplicate Key

(println (hash-set "shfourteen-teen" "doo" "doo-and-a-have"))
; => #{doo-and-a-have shfourteen-teen doo}



; EXERCISE 2: Write a function that takes a number and adds 100 to it
(defn add100
  [number]
  (+ number 100))
(println (add100 10))



; EXERCISE 3: Write a function, dec-maker, that works exactly like
;    the function inc-maker except with subtraction, like so:
;    (def dec9 (dec-maker 9))
;    (dec9 10)) ; => 1
;(defn dec-maker





; EXERCISE 4: Write a function, mapset, that works like map,
;    except the return value is a set



; 5: Create a function that’s similar to symmetrize-body-parts
;    except that it has to work with weird space aliens with
;    radial symmetry. Instead of two eyes, arms, legs, and so on,
;    they have five



; 6: Create a function that generalizes symmetrize-body-parts
;    and the func- tion you created in Exercise 5. The new function
;    should take a col- lection of body parts and the number of
;    matching body parts to add.






