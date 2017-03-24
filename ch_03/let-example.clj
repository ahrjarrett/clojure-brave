; let allows you to bind names to values
; within a particular scope
(let [x 3]
  x) ; => 3

(def dalmation-list
  ["Pongo" "Perdita" "Puppy 1" "Puppy 2"])

; this syntax threw me off at first, until
; I realized that we’re returning the value
; at the end (right?)
(let [dalmations (take 2 dalmation-list)]
  dalmations) ; => ("Pongo" "Perdita")

; here’s an example illustrating how to
; use ‘let’ to create a new scope
(def x 0)
(let [x 1] x) ; => 1

; Or, referencing existing bindings:
(let [x (inc x)] x) ; => 1

; COOL: ‘let’ works with rest params, too...
(let [[pongo & dalmations] dalmatian-list]
  [pongo dalmatians])
; => ["Pongo" ("Perdita" "Puppy 1" "Puppy 2")]

; INTO fn
; example on pg. 62, adds the elements of a set into
; a vector--syntax.
; in this example, (set [:a :a]) returns
; #{:a} because a set contains only unique
; values. :a is lifted from the set and dropped into
; the vector, which is initialized as []:
(into [] (set [:a :a])) ; => [:a]

