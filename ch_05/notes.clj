; example of using recursion to add the values of a vector
(defn sum
  ([vals] (sum vals 0))
  ([vals accumulating-total]
    (if (empty? vals)
      accumulating-total
      (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1]) ; => 45

; for larger amounts of data, explicitly use RECUR instead
; of calling the fn name explicitly, for performance reasons
; (Clojure doesn’t support tail call optimizations like Scheme does)

; to use RECUR, write the function exactly as above, substituting the
; second SUM call with RECUR (but why not the first one? oh, b/c a call
; to that branch will only run once before triggering the second branch

; here’s a simple example of functional composition in Clojure,
; presented as an alternative to mutative methods like in Ruby
(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))
(clean "My boa constrictor is so sassy lol!   ")
; => "My boa constrictor is so sassy LOL!"

; COMP
; compose 2 or more functions
; formula -> f1, f2, ... fn creates a new function g such that:
;            g(x1, x2, ... xn) equals f1(f2(fn(x1, x2, ... xn)))

; cool example of using compose to drill down into maps:
(def character
  {:name "Babycakes"
   :attributes {:intelligence 2
                :strength 10
                :dexterity 3}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))
(c-int character)
; => 2

; these functions could also be written like this, but it’s less elegant:
(fn [c] (:strength (:attributes c)))

; what do you do if one of the functions you want to compose needs to take more than one argument?
; answer: you wrap it in an anonymous function

; more imperative solution:
(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))
(spell-slots character) ; => 2

; using function composition and a lambda fn:
(def spell-slots-comp (comp int inc #(/ % 2) c-int))
     ; why is this def, and not defn??

; COMP takes any number of fns as args;
; here is a nieve version that composes 2 functions:
(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args)))) ; makes sense

; MEMOIZE
; b/c fns are referentially transparent, we can store
; the results of fn calls so they are returned immediately later

; this function takes a while to run:
(defn sleepy-identity
  "returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "The Professor Brothers")
; => "The Professor Brothers" after 1 sec

; here’s how you create a memoized version of sleepy-identity:
(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "The Professor Brothers")
; => "The Professor Brothers" after 1 sec
(memo-sleepy-identity "The Professor Brothers")
; => "The Professor Brothers" immediately
